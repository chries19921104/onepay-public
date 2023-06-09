package org.example.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import org.apache.ibatis.annotations.Param;
import org.example.admin.dto.WithdrawalOrderDto;
import org.example.common.entity.SystemWithdrawalOrder;
import org.example.admin.vo.WithdrawalOrderVo;
import org.mapstruct.Mapper;

import java.math.BigDecimal;
import java.util.List;

@Mapper
public interface SystemWithdrawalOrderMapper extends BaseMapper<SystemWithdrawalOrder> {

    /**
     * 通过条件查询WithdrawalOrderVo的全部数据
     * @param dto 全部条件
     * @param foId 去掉的拼接主键id
     * @param subFoAltId 去掉的子代付拼接主键id
     * @param requestAmountMin 请求金额最小值
     * @param requestAmountMax 请求金额最大值
     * @param paidAmountMin 到账金额最小值
     * @param paidAmountMax 到账金额最大值
     * @return
     */
    List<WithdrawalOrderVo> selectWithdrawalOrderVo(@Param("dto") WithdrawalOrderDto dto,
                                                    @Param("foId") Long foId,
                                                    @Param("subFoAltId") Long subFoAltId,
                                                    @Param("requestAmountMin") BigDecimal requestAmountMin,
                                                    @Param("requestAmountMax") BigDecimal requestAmountMax,
                                                    @Param("paidAmountMin") BigDecimal paidAmountMin,
                                                    @Param("paidAmountMax") BigDecimal paidAmountMax);

    /**
     * 通过条件查询总数
     * @param dto 全部条件
     * @param foId 去掉的拼接主键id
     * @param subFoAltId 去掉的子代付拼接主键id
     * @param requestAmountMin 请求金额最小值
     * @param requestAmountMax 请求金额最大值
     * @param paidAmountMin 到账金额最小值
     * @param paidAmountMax 到账金额最大值
     * @return
     */
    Integer selectTotal(@Param("dto") WithdrawalOrderDto dto,
                                        @Param("foId") Long foId,
                                        @Param("subFoAltId") Long subFoAltId,
                                        @Param("requestAmountMin") BigDecimal requestAmountMin,
                                        @Param("requestAmountMax") BigDecimal requestAmountMax,
                                        @Param("paidAmountMin") BigDecimal paidAmountMin,
                                        @Param("paidAmountMax") BigDecimal paidAmountMax);

    /**
     * 通过 代付id查询WithdrawalOrderVo的数据
     * @param foId 代付id
     * @return
     */
    WithdrawalOrderVo selectWithdrawalOrderVoByFoId(@Param("foId") Long foId);
}
