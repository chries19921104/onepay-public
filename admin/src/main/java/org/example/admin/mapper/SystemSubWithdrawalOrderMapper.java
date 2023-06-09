package org.example.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.example.admin.vo.SubWithdrawalOrderVo;
import org.example.common.entity.SystemSubWithdrawalOrder;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * <p>
 * Withdrawal_子代付订单 Mapper 接口
 * </p>
 *
 * @author rj
 * @since 2023-06-06
 */
@Repository
public interface SystemSubWithdrawalOrderMapper extends BaseMapper<SystemSubWithdrawalOrder> {

    List<SubWithdrawalOrderVo> selectSubWithdrawalOrderVo(@Param("foId") Long foId);

}
