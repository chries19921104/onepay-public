package org.example.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.admin.vo.SubWithdrawalOrderVo;
import org.example.common.entity.SystemSubWithdrawalOrder;

import java.util.List;


/**
 * <p>
 * Withdrawal_子代付订单 服务类
 * </p>
 *
 * @author rj
 * @since 2023-06-06
 */
public interface SystemSubWithdrawalOrderService extends IService<SystemSubWithdrawalOrder> {
    List<SubWithdrawalOrderVo> getSubWithdrawalOrderVo(Long foId);
}
