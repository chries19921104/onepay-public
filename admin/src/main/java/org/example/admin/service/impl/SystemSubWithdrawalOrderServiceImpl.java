package org.example.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.admin.mapper.SystemSubWithdrawalOrderMapper;
import org.example.admin.service.SystemSubWithdrawalOrderService;
import org.example.admin.vo.SubWithdrawalOrderVo;
import org.example.common.entity.SystemSubWithdrawalOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public List<SubWithdrawalOrderVo> getSubWithdrawalOrderVo(Long foId) {
        return subWithdrawalOrderMapper.selectSubWithdrawalOrderVo(foId);
    }
}
