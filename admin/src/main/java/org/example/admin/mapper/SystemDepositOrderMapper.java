package org.example.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.example.admin.vo.DepositQRLossCommissionVo;
import org.example.admin.vo.DepositOrderCommissionVo;
import org.example.admin.vo.DepositOrderInfoVo;
import org.example.admin.vo.DepositOrderVo;
import org.example.common.entity.SystemDepositOrder;

import java.util.List;

/**
 * <p>
 * system_deposit_order Mapper 接口
 * </p>
 *
 * @author zhangmi
 * @since 2023-05-17 19:16:15
 */
@Mapper
public interface SystemDepositOrderMapper extends BaseMapper<SystemDepositOrder> {

    List<DepositOrderVo> selectMoneyAndCount(@Param("currency") String currency,
                                             @Param("beginTime") String beginTime,
                                             @Param("endTime") String endTime,
                                             @Param("status") String status,
                                             @Param("cardId") String cardId,
                                             @Param("merchantId") String merchantId
    );

    DepositOrderVo selectSettlement(@Param("currency") String currency,
                                    @Param("beginTime") String beginTime,
                                    @Param("endTime") String endTime,
                                    @Param("status") String status,
                                    @Param("cardId") String cardId

    );

    DepositOrderVo selectWithdrawal(@Param("currency") String currency,
                                    @Param("beginTime") String beginTime,
                                    @Param("endTime") String endTime,
                                    @Param("status") String status,
                                    @Param("cardId") String cardId

    );

    DepositOrderVo selectCrypto(@Param("currency") String currency,
                                @Param("beginTime") String beginTime,
                                @Param("endTime") String endTime,
                                @Param("status") String status,
                                @Param("cardId") String cardId
    );

    List<DepositOrderCommissionVo> selectWithdrawalrCommission(@Param("currency") String currency,
                                                               @Param("beginTime") String beginTime,
                                                               @Param("endTime") String endTime,
                                                               @Param("cardId") String cardId,
                                                               @Param("merchantId") String merchantId);

    DepositOrderCommissionVo selectSettlementCommission(@Param("currency") String currency,
                                                        @Param("beginTime") String beginTime,
                                                        @Param("endTime") String endTime,
                                                        @Param("cardId") String cardId,
                                                        @Param("merchantId") String merchantId);
    DepositOrderCommissionVo selectExternalTradeCommission(@Param("currency") String currency,
                                                           @Param("beginTime") String beginTime,
                                                           @Param("endTime") String endTime,
                                                           @Param("cardId") String cardId,
                                                           @Param("merchantId") String merchantId);
    DepositOrderCommissionVo selectIntroversionCommission(@Param("currency") String currency,
                                                          @Param("beginTime") String beginTime,
                                                          @Param("endTime") String endTime,
                                                          @Param("cardId") String cardId,
                                                          @Param("merchantId") String merchantId);
    DepositQRLossCommissionVo selectDepositQRLossCommission(@Param("currency") String currency,
                                                            @Param("beginTime") String beginTime,
                                                            @Param("endTime") String endTime,
                                                            @Param("cardId") String cardId,
                                                            @Param("merchantId") String merchantId);
    DepositQRLossCommissionVo selectDepositTrueLossCommission(@Param("currency") String currency,
                                    @Param("beginTime") String beginTime,
                                    @Param("endTime") String endTime,
                                    @Param("cardId") String cardId,
                                    @Param("merchantId") String merchantId);
    int selectMerchantRegister(@Param("currency") String currency,
                               @Param("beginTime") String beginTime,
                               @Param("endTime") String endTime,
                               @Param("cardGroupId") Integer cardGroupId);
    int selectMerchantExamine(@Param("currency") String currency
            ,@Param("status") String status
            ,@Param("cardGroupId") Integer cardGroupId);
    DepositOrderInfoVo selectBankCardFreeze(@Param("currency") String currency,
                                            @Param("beginTime") String beginTime,
                                            @Param("endTime") String endTime,
                                            @Param("cardGroupId") Integer cardGroupId);
    DepositQRLossCommissionVo processingFoAmount(@Param("currency") String currency,@Param("table") String table);
    DepositQRLossCommissionVo confirmableFoAmount(@Param("currency") String currency,

                                                  @Param("confirmAccName")String confirmAccName);

    DepositQRLossCommissionVo approvalEtAmount(@Param("currency") String currency);
}