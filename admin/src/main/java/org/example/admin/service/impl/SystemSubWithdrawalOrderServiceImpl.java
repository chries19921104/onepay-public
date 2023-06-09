package org.example.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.admin.mapper.SystemSubWithdrawalOrderMapper;
import org.example.admin.service.AdminsService;
import org.example.admin.service.SystemSubWithdrawalOrderService;
import org.example.admin.service.SystemWithdrawalOrderLogService;
import org.example.admin.service.SystemXyCodeService;
import org.example.admin.vo.SubWithdrawalOrderVo;
import org.example.common.entity.SystemSubWithdrawalOrder;
import org.example.common.entity.SystemWithdrawalOrderLog;
import org.example.common.entity.SystemXyCode;
import org.example.common.utils.BaseContext;
import org.example.common.utils.TransactionRecordUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static org.example.common.constant.WithdrawalOrderConstant.*;

/**
 * <p>
 * Withdrawal_子代付订单 服务实现类
 * </p>
 *
 * @author rj
 * @since 2023-06-06
 */
@Service
public class SystemSubWithdrawalOrderServiceImpl extends ServiceImpl<SystemSubWithdrawalOrderMapper, SystemSubWithdrawalOrder> implements SystemSubWithdrawalOrderService {

    @Autowired
    private SystemSubWithdrawalOrderMapper subWithdrawalOrderMapper;

    @Autowired
    private SystemWithdrawalOrderLogService withdrawalOrderLogService;

    @Autowired
    private AdminsService adminsService;

    @Autowired
    private SystemXyCodeService xyCodeService;

    @Override
    public List<SubWithdrawalOrderVo> getSubWithdrawalOrderVo(Long foId) {
        List<SubWithdrawalOrderVo> subOrderVoList = subWithdrawalOrderMapper.selectSubWithdrawalOrderVo(foId);
        subOrderVoList = subOrderVoList.stream().map(item -> {
            SubWithdrawalOrderVo vo = new SubWithdrawalOrderVo();
            // 拷贝
            BeanUtils.copyProperties(item, vo);
            // =====拼接主键=====
            vo.setAltId(TransactionRecordUtils.getAltId(item.getSubFoId(), "Ws-", item.getCreatedAt()));
            // =====获取checkBtn属性=====
            Integer status = item.getStatus();
            Boolean isPressButton = item.getIsPressButton();
            Integer retryTimes = item.getRetryTimes();
            String action = item.getAction();
            Integer foStatus = item.getFoStatus();
            boolean retryReady = (status == STATUS_MANUAL_PROCESS
                    && isPressButton == false
                    && retryTimes < 2
                    && action == null
                    && foStatus == STATUS_PROCESSING
                    || foStatus == STATUS_PENDING);
            Integer isCheckable = null;
            // 获取登录用户id
            Long userId = BaseContext.getCurrent();
            String username = adminsService.getById(userId).getUsername();
            if (retryReady ==  false){
                isCheckable = 2;
            }else {
                List<SystemWithdrawalOrderLog> list = withdrawalOrderLogService.list(new LambdaQueryWrapper<SystemWithdrawalOrderLog>()
                        .eq(SystemWithdrawalOrderLog::getWoId, item.getSubFoId()));
                String createdMan = null;
                // 获取type类型为0的第一个对象的created_man
                if (list != null && list.size() > 0){
                    createdMan = list.stream().map(withdrawalOrderLog -> {
                        int type = withdrawalOrderLog.getType();
                        if (type == TYPE_CHECK) {
                            return withdrawalOrderLog.getCreatedMan();
                        }
                        return null;
                    }).collect(Collectors.toList()).get(0);
                }
                if (createdMan == null){
                    isCheckable = 0;
                }else if (createdMan.equals(username)){
                    isCheckable = 1;
                }else {
                    isCheckable = 2;
                }
            }
            Long thirdId = item.getThirdId();
            vo.setCheckBtn(thirdId == null ? null : (thirdId > 0 ? 2 : isCheckable));
            // =====获取isErrurl属性=====
            String commandId = item.getCommandId();
            SystemXyCode xyCode = xyCodeService.getOne(new LambdaQueryWrapper<SystemXyCode>()
                    .eq(SystemXyCode::getCommandId, commandId));
            vo.setIsErrurl(xyCode == null ? false : true);
            // =====获取isRunMon属性=====
            Integer isEntry = item.getIsEntry();
            boolean isTpi = thirdId > 0 ? true : false;
            // 创建时间
            LocalDateTime createdAt = item.getCreatedAt();
            // 获取创建时间与当前时间的差
            long startSeconds = Duration.between(LocalDateTime.now(), createdAt).getSeconds();
            // 更新时间
            LocalDateTime updatedAt = item.getUpdatedAt();
            // 获取更新时间与当前时间的差
            long updateSeconds = Duration.between(LocalDateTime.now(), updatedAt).getSeconds();
            boolean isRunmon = isTpi ? (startSeconds >= 60 && status == STATUS_PENDING || status == STATUS_PROCESSING || status == STATUS_MANUAL_PROCESS) :
                                        (updateSeconds >= 900 && status == STATUS_PENDING || status == STATUS_PROCESSING);
            vo.setIsRunMon(isEntry == ENTRY_MANUAL ? false : isRunmon);
            vo.setPrevAltId(TransactionRecordUtils.getAltId(item.getFoPreId(), "Ws-", createdAt));
            // =====获取retryBtn字段=====
            // 类型不为0的 并且创建人还不能为用户自己的不能没有
            List<SystemWithdrawalOrderLog> list = withdrawalOrderLogService.list(new LambdaQueryWrapper<SystemWithdrawalOrderLog>()
                    .eq(SystemWithdrawalOrderLog::getWoId, item.getSubFoId())
                    .eq(SystemWithdrawalOrderLog::getType, TYPE_CHECK)
                    .ne(SystemWithdrawalOrderLog::getCreatedMan, username));
            // 类型为1的一个都不能有
            List<SystemWithdrawalOrderLog> listLog = withdrawalOrderLogService.list(new LambdaQueryWrapper<SystemWithdrawalOrderLog>()
                    .eq(SystemWithdrawalOrderLog::getWoId, item.getSubFoId())
                    .eq(SystemWithdrawalOrderLog::getType, TYPE_RETRY));
            boolean isRetryable = userId == null ? false : (retryReady && list != null && listLog == null);
            vo.setRetryBtn(thirdId > 0 ? false : isRetryable);

            return vo;
        }).collect(Collectors.toList());
        return subOrderVoList;
    }
}
