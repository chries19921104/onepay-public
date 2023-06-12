package org.example.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.example.admin.vo.SubWithdrawalOrderVo;
import org.example.common.entity.SystemSubWithdrawalOrder;
import org.mapstruct.Mapper;

import java.util.List;


/**
 * <p>
 * Withdrawal_子代付订单 ExternalStatementMapper 接口
 * </p>
 *
 * @author rj
 * @since 2023-06-06
 */
@Mapper
public interface SystemSubWithdrawalOrderMapper extends BaseMapper<SystemSubWithdrawalOrder> {

    List<SubWithdrawalOrderVo> selectSubWithdrawalOrderVo(@Param("foId") Long foId);
}
