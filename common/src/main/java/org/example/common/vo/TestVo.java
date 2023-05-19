package org.example.common.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;


@Data
public class TestVo {
    @JsonProperty("active_merchant")
    private int activeMerchant;

    @JsonProperty("all_numbe")
    private int allNumbe;

    @JsonProperty("all_ops_loss")
    private int allOpsLoss;

    @JsonProperty("all_pending_settlement")
    private double allPendingSettlement;

    @JsonProperty("all_qr_deposit_loss")
    private int allQrDepositLoss;

    @JsonProperty("all_qr_deposit_loss_request")
    private int allQrDepositLossRequest;

    @JsonProperty("all_true_deposit_loss")
    private int allTrueDepositLoss;

    @JsonProperty("all_true_deposit_loss_request")
    private int allTrueDepositLossRequest;

    @JsonProperty("current_month_8pay_payout_revenue")
    private int currentMonth8PayPayoutRevenue;

    @JsonProperty("current_month_bank_deposit_revenue")
    private int currentMonthBankDepositRevenue;

    @JsonProperty("current_month_dapay_BANK2BANK_deposit_revenue")
    private int currentMonthDapayBank2BankDepositRevenue;

    @JsonProperty("current_month_dapay_NETGATE_deposit_revenue")
    private int currentMonthDapayNetgateDepositRevenue;

    @JsonProperty("current_month_dapay_ZFBYHK_deposit_revenue")
    private int currentMonthDapayZfbyhkDepositRevenue;

    @JsonProperty("current_month_dapay_payout_revenue")
    private int currentMonthDapayPayoutRevenue;

    @JsonProperty("current_month_dapay_settlement_revenue")
    private int currentMonthDapaySettlementRevenue;

    @JsonProperty("current_month_h1pay_UNION_PAY_deposit_revenue")
    private int currentMonthH1payUnionPayDepositRevenue;

    @JsonProperty("current_month_h1pay_payout_revenue")
    private int currentMonthH1payPayoutRevenue;

    @JsonProperty("current_month_h1pay_settlement_revenue")
    private int currentMonthH1paySettlementRevenue;

    @JsonProperty("current_month_mnfi_revenue")
    private int currentMonthMnfiRevenue;

    @JsonProperty("current_month_mnfo_revenue")
    private int currentMonthMnfoRevenue;

    @JsonProperty("current_month_payout_revenue")
    private int currentMonthPayoutRevenue;

    @JsonProperty("current_month_qijipay_payout_revenue")
    private int currentMonthQijipayPayoutRevenue;

    @JsonProperty("current_month_qr_deposit_loss")
    private int currentMonthQrDepositLoss;

    @JsonProperty("current_month_qr_deposit_loss_request")
    private int currentMonthQrDepositLossRequest;

    @JsonProperty("current_month_qrpay_deposit_revenue")
    private int currentMonthQrpayDepositRevenue;

    @JsonProperty("current_month_settlement_revenue")
    private int currentMonthSettlementRevenue;

    @JsonProperty("current_month_true_deposit_loss")
    private int currentMonthTrueDepositLoss;

    @JsonProperty("current_month_true_deposit_loss_request")
    private int currentMonthTrueDepositLossRequest;

    @JsonProperty("current_month_true_deposit_revenue")
    private int currentMonthTrueDepositRevenue;

    @JsonProperty("current_month_vnpay_bank_transfer_deposit_revenue")
    private int currentMonthVnpayBankTransferDepositRevenue;

    @JsonProperty("current_month_vnpay_online_banking_deposit_revenue")
    private int currentMonthVnpayOnlineBankingDepositRevenue;

    @JsonProperty("current_month_vnpay_prepaid_deposit_revenue")
    private int currentMonthVnpayPrepaidDepositRevenue;

    @JsonProperty("current_month_vnpay_qrpay_deposit_revenue")
    private int currentMonthVnpayQrpayDepositRevenue;

    @JsonProperty("current_month_youktpay_BANK2BANK_deposit_revenue")
    private int currentMonthYouktpayBank2BankDepositRevenue;

    @JsonProperty("current_month_youktpay_payout_revenue")
    private int currentMonthYouktpayPayoutRevenue;

    @JsonProperty("last_month_8pay_payout_revenue")
    private int lastMonth8PayPayoutRevenue;

    @JsonProperty("last_month_bank_deposit_revenue")
    private int lastMonthBankDepositRevenue;

    @JsonProperty("last_month_dapay_BANK2BANK_deposit_revenue")
    private int lastMonthDaPayBank2BankDepositRevenue;

    @JsonProperty("last_month_dapay_NETGATE_deposit_revenue")
    private int lastMonthDaPayNetgateDepositRevenue;

    @JsonProperty("last_month_dapay_ZFBYHK_deposit_revenue")
    private int lastMonthDaPayZFBYHKDepositRevenue;

    @JsonProperty("last_month_dapay_payout_revenue")
    private int lastMonthDaPayPayoutRevenue;

    @JsonProperty("last_month_dapay_settlement_revenue")
    private int lastMonthDaPaySettlementRevenue;

    @JsonProperty("last_month_h1pay_UNION_PAY_deposit_revenue")
    private int lastMonthH1PayUnionPayDepositRevenue;

    @JsonProperty("last_month_h1pay_payout_revenue")
    private int lastMonthH1PayPayoutRevenue;

    @JsonProperty("last_month_h1pay_settlement_revenue")
    private int lastMonthH1PaySettlementRevenue;

    @JsonProperty("last_month_mnfi_revenue")
    private int lastMonthMnfiRevenue;

    @JsonProperty("last_month_mnfo_revenue")
    private int lastMonthMnfoRevenue;

    @JsonProperty("last_month_payout_revenue")
    private int lastMonthPayoutRevenue;

    @JsonProperty("last_month_qijipay_payout_revenue")
    private int lastMonthQijipayPayoutRevenue;

    @JsonProperty("last_month_qr_deposit_loss")
    private int lastMonthQrDepositLoss;

    @JsonProperty("last_month_qr_deposit_loss_request")
    private int lastMonthQrDepositLossRequest;

    @JsonProperty("last_month_qrpay_deposit_revenue")
    private int lastMonthQrpayDepositRevenue;

    @JsonProperty("last_month_settlement_revenue")
    private int lastMonthSettlementRevenue;

    @JsonProperty("last_month_true_deposit_loss")
    private int lastMonthTrueDepositLoss;

    @JsonProperty("last_month_true_deposit_loss_request")
    private int lastMonthTrueDepositLossRequest;

    @JsonProperty("last_month_true_deposit_revenue")
    private int lastMonthTrueDepositRevenue;

    @JsonProperty("last_month_vnpay_bank_transfer_deposit_revenue")
    private int lastMonthVnpayBankTransferDepositRevenue;

    @JsonProperty("last_month_vnpay_online_banking_deposit_revenue")
    private int lastMonthVnpayOnlineBankingDepositRevenue;

    @JsonProperty("last_month_vnpay_prepaid_deposit_revenue")
    private int lastMonthVnpayPrepaidDepositRevenue;

    @JsonProperty("last_month_vnpay_qrpay_deposit_revenue")
    private int lastMonthVnpayQrpayDepositRevenue;

    @JsonProperty("last_month_youktpay_BANK2BANK_deposit_revenue")
    private int lastMonthYouktpayBank2BankDepositRevenue;

    @JsonProperty("last_month_youktpay_payout_revenue")
    private int lastMonthYouktpayPayoutRevenue;

    @JsonProperty("pending_merchant")
    private int pendingMerchant;

    @JsonProperty("show_bank_fee_8pay")
    private boolean showBankFee8pay;

    @JsonProperty("show_bank_fee_Dapay")
    private boolean showBankFeeDapay;

    @JsonProperty("show_bank_fee_QijiPay")
    private boolean showBankFeeQijiPay;

    @JsonProperty("today_8pay_FO_revenue")
    private int today8payFoRevenue;

    @JsonProperty("today_MNFO_bank_fee")
    private int todayMNFOBankFee;

    @JsonProperty("today_bank_deposit_revenue")
    private int todayBankDepositRevenue;

    @JsonProperty("today_dapay_BANK2BANK_deposit_revenue")
    private int todayDapayBank2BankDepositRevenue;

    @JsonProperty("today_dapay_FO_revenue")
    private int todayDapayFoRevenue;

    @JsonProperty("today_dapay_FX_revenue")
    private int todayDapayFxRevenue;

    @JsonProperty("today_dapay_NETGATE_deposit_revenue")
    private int todayDapayNetgateDepositRevenue;

    @JsonProperty("today_dapay_ZFBYHK_deposit_revenue")
    private int todayDapayZfbyhkDepositRevenue;

    @JsonProperty("today_external_transfer_fee")
    private int todayExternalTransferFee;

    @JsonProperty("today_h1pay_FO_revenue")
    private int todayH1payFoRevenue;

    @JsonProperty("today_h1pay_FX_revenue")
    private int todayH1payFxRevenue;

    @JsonProperty("today_h1pay_UNION_PAY_deposit_revenue")
    private int todayH1payUnionPayDepositRevenue;

    @JsonProperty("today_internal_transfer_fee")
    private int todayInternalTransferFee;

    @JsonProperty("today_mnfi_revenue")
    private int todayMnfiRevenue;

    @JsonProperty("today_mnfo_revenue")
    private int todayMnfoRevenue;

    @JsonProperty("today_n_fi_bank_amount")
    private int todayFiBankAmount;

    @JsonProperty("today_n_fi_bank_number")
    private int todayFiBankNumber;

    @JsonProperty("today_n_fi_dapay_BANK2BANK_amount")
    private int todayFiDapayBank2BankAmount;

    @JsonProperty("today_n_fi_dapay_BANK2BANK_number")
    private int todayFiDapayBank2BankNumber;

    @JsonProperty("today_n_fi_dapay_NETGATE_amount")
    private int todayFiDapayNetgateAmount;

    @JsonProperty("today_n_fi_dapay_NETGATE_number")
    private int todayFiDapayNetgateNumber;

    @JsonProperty("today_n_fi_dapay_ZFBYHK_amount")
    private int todayFiDapayZfbyhkAmount;

    @JsonProperty("today_n_fi_dapay_ZFBYHK_number")
    private int todayFiDapayZfbyhkNumber;

    @JsonProperty("today_n_fi_h1pay_UNION_PAY_amount")
    private int todayFiH1payUnionPayAmount;

    @JsonProperty("today_n_fi_h1pay_UNION_PAY_number")
    private int todayFiH1payUnionPayNumber;

    @JsonProperty("today_n_fi_qrpay_amount")
    private int todayFiQrPayAmount;

    @JsonProperty("today_n_fi_qrpay_number")
    private int todayNFiQrPayNumber;

    @JsonProperty("today_n_fi_true_amount")
    private int todayNFiTrueAmount;

    @JsonProperty("today_n_fi_true_number")
    private int todayNFiTrueNumber;

    @JsonProperty("today_n_fi_vnpay_bank_transfer_amount")
    private int todayNFiVnpayBankTransferAmount;

    @JsonProperty("today_n_fi_vnpay_bank_transfer_number")
    private int todayNFiVnpayBankTransferNumber;

    @JsonProperty("today_n_fi_vnpay_online_banking_amount")
    private int todayNFiVnpayOnlineBankingAmount;

    @JsonProperty("today_n_fi_vnpay_online_banking_number")
    private int todayNFiVnpayOnlineBankingNumber;

    @JsonProperty("today_n_fi_vnpay_prepaid_amount")
    private int todayNFiVnpayPrepaidAmount;

    @JsonProperty("today_n_fi_vnpay_prepaid_number")
    private int todayNFiVnpayPrepaidNumber;

    @JsonProperty("today_n_fi_vnpay_qrpay_amount")
    private int todayNFiVnpayQrPayAmount;

    @JsonProperty("today_n_fi_vnpay_qrpay_number")
    private int todayNFiVnpayQrPayNumber;

    @JsonProperty("today_n_fi_youktpay_BANK2BANK_amount")
    private int todayNFiYouktpayBank2BankAmount;

    @JsonProperty("today_n_fi_youktpay_BANK2BANK_number")
    private int todayNFiYouktpayBank2BankNumber;

    @JsonProperty("today_n_fo_8pay_amount")
    private int todayNFo8PayAmount;

    @JsonProperty("today_n_fo_8pay_number")
    private int todayNFo8PayNumber;

    @JsonProperty("today_n_fo_amount")
    private int todayNFoAmount;

    @JsonProperty("today_n_fo_dapay_amount")
    private int todayNFoDapayAmount;

    @JsonProperty("today_n_fo_dapay_number")
    private int todayNFoDapayNumber;

    @JsonProperty("today_n_fo_h1pay_amount")
    private int todayNFoH1PayAmount;

    @JsonProperty("today_n_fo_h1pay_number")
    private int todayNFoH1PayNumber;

    @JsonProperty("today_n_fo_number")
    private int todayNFoNumber;

    @JsonProperty("today_n_fo_qijipay_amount")
    private int todayNFoQijipayAmount;

    @JsonProperty("today_n_fo_qijipay_number")
    private int todayNFoQijipayNumber;

    @JsonProperty("today_n_fo_youktpay_amount")
    private int todayNFoYouktpayAmount;

    @JsonProperty("today_n_fo_youktpay_number")
    private int todayNFoYouktpayNumber;

    @JsonProperty("today_n_fx_amount")
    private int todayNFxAmount;

    @JsonProperty("today_n_fx_dapay_amount")
    private int todayNFxDapayAmount;

    @JsonProperty("today_n_fx_dapay_number")
    private int todayNFxDapayNumber;

    @JsonProperty("today_n_fx_h1pay_amount")
    private int todayNFxH1payAmount;

    @JsonProperty("today_n_fx_h1pay_number")
    private int todayNFxH1payNumber;

    @JsonProperty("today_n_fx_number")
    private int todayNFxNumber;

    @JsonProperty("today_n_mnfi_amount")
    private int todayNMnfiAmount;

    @JsonProperty("today_n_mnfi_number")
    private int todayNMnfiNumber;

    @JsonProperty("today_n_mnfo_amount")
    private int todayNMnfoAmount;

    @JsonProperty("today_n_mnfo_number")
    private int todayNMnfoNumber;

    @JsonProperty("today_number")
    private int todayNumber;

    @JsonProperty("today_ops_loss")
    private int todayOpsLoss;

    @JsonProperty("today_payout_bank_fee_Merchant")
    private int todayPayoutBankFeeMerchant;

    @JsonProperty("today_payout_bank_fee_Merchant_8pay")
    private int todayPayoutBankFeeMerchant8pay;

    @JsonProperty("today_payout_bank_fee_Merchant_Dapay")
    private int todayPayoutBankFeeMerchantDapay;

    @JsonProperty("today_payout_bank_fee_Merchant_H1Pay")
    private int todayPayoutBankFeeMerchantH1Pay;

    @JsonProperty("today_payout_bank_fee_Merchant_QijiPay")
    private int todayPayoutBankFeeMerchantQijiPay;

    @JsonProperty("today_payout_bank_fee_Merchant_YouktPay")
    private int todayPayoutBankFeeMerchantYouktPay;

    @JsonProperty("today_payout_bank_fee_OneWallet_8pay")
    private int todayPayoutBankFeeOneWallet8pay;

    @JsonProperty("today_payout_bank_fee_OneWallet_Dapay")
    private int todayPayoutBankFeeOneWalletDapay;

    @JsonProperty("today_payout_bank_fee_OneWallet_H1Pay")
    private int todayPayoutBankFeeOneWalletH1Pay;

    @JsonProperty("today_payout_bank_fee_OneWallet_QijiPay")
    private int todayPayoutBankFeeOneWalletQijiPay;

    @JsonProperty("today_payout_bank_fee_OneWallet_YouktPay")
    private int todayPayoutBankFeeOneWalletYouktPay;

    @JsonProperty("today_payout_revenue")
    private int todayPayoutRevenue;

    @JsonProperty("today_pending_settlement")
    private int todayPendingSettlement;

    @JsonProperty("today_qijipay_FO_revenue")
    private int todayQijipayFORevenue;

    @JsonProperty("today_qr_deposit_loss")
    private int todayQRDepositLoss;

    @JsonProperty("today_qr_deposit_loss_request")
    private int todayQRDepositLossRequest;

    @JsonProperty("today_qrpay_deposit_revenue")
    private int todayQRPayDepositRevenue;

    @JsonProperty("today_registered_farmer")
    private int todayRegisteredFarmer;

    @JsonProperty("today_registered_merchant")
    private int todayRegisteredMerchant;

    @JsonProperty("today_settlement_bank_fee")
    private int todaySettlementBankFee;

    @JsonProperty("today_settlement_revenue")
    private int todaySettlementRevenue;

    @JsonProperty("today_success_FO_bank_fee_OneWallet")
    private int todaySuccessFOBankFeeOneWallet;

    @JsonProperty("today_successful_settlement")
    private int todaySuccessfulSettlement;

    @JsonProperty("today_true_deposit_loss")
    private int todayTrueDepositLoss;

    @JsonProperty("today_true_deposit_loss_request")
    private int todayTrueDepositLossRequest;

    @JsonProperty("today_true_deposit_revenue")
    private int todayTrueDepositRevenue;

    @JsonProperty("today_vnpay_bank_transfer_deposit_revenue")
    private int todayVnpayBankTransferDepositRevenue;

    @JsonProperty("today_vnpay_online_banking_deposit_revenue")
    private int todayVnpayOnlineBankingDepositRevenue;

    @JsonProperty("today_vnpay_prepaid_deposit_revenue")
    private int todayVnpayPrepaidDepositRevenue;

    @JsonProperty("today_vnpay_qrpay_deposit_revenue")
    private int todayVnpayQrpayDepositRevenue;

    @JsonProperty("today_y_fi_bank_amount")
    private int todayYFiBankAmount;

    @JsonProperty("today_y_fi_bank_number")
    private int todayYFiBankNumber;

    @JsonProperty("today_y_fi_dapay_BANK2BANK_amount")
    private int todayYFiDapayBank2BankAmount;

    @JsonProperty("today_y_fi_dapay_BANK2BANK_number")
    private int todayYFiDapayBank2BankNumber;

    @JsonProperty("today_y_fi_dapay_NETGATE_amount")
    private int todayYFiDapayNetgateAmount;

    @JsonProperty("today_y_fi_dapay_NETGATE_number")
    private int todayYFiDapayNetgateNumber;

    @JsonProperty("today_y_fi_dapay_ZFBYHK_amount")
    private int todayYFiDapayZFBYHKAmount;

    @JsonProperty("today_y_fi_dapay_ZFBYHK_number")
    private int todayYFiDapayZFBYHKNumber;

    @JsonProperty("today_y_fi_h1pay_UNION_PAY_amount")
    private int todayYFiH1payUNIONPAYAmount;

    @JsonProperty("today_y_fi_h1pay_UNION_PAY_number")
    private int todayYFiH1payUNIONPAYNumber;

    @JsonProperty("today_y_fi_qrpay_amount")
    private int todayYFiQrpayAmount;

    @JsonProperty("today_y_fi_qrpay_number")
    private int todayYFiQrpayNumber;

    @JsonProperty("today_y_fi_true_amount")
    private int todayYFiTrueAmount;

    @JsonProperty("today_y_fi_true_number")
    private int todayYFiTrueNumber;

    @JsonProperty("today_y_fi_vnpay_bank_transfer_amount")
    private int todayYFiVnpayBankTransferAmount;

    @JsonProperty("today_y_fi_vnpay_bank_transfer_number")
    private int todayYFiVnpayBankTransferNumber;

    @JsonProperty("today_y_fi_vnpay_online_banking_amount")
    private int todayYFiVnpayOnlineBankingAmount;

    @JsonProperty("today_y_fi_vnpay_online_banking_number")
    private int todayYFiVnpayOnlineBankingNumber;

    @JsonProperty("today_y_fi_vnpay_prepaid_amount")
    private int todayYFiVnpayPrepaidAmount;

    @JsonProperty("today_y_fi_vnpay_prepaid_number")
    private int todayYFiVnpayPrepaidNumber;

    @JsonProperty("today_y_fi_vnpay_qrpay_amount")
    private int todayYFiVnpayQrpayAmount;

    @JsonProperty("today_y_fi_vnpay_qrpay_number")
    private int todayYFiVnpayQrpayNumber;

    @JsonProperty("today_y_fi_youktpay_BANK2BANK_amount")
    private int todayYFiYouktpayBank2BankAmount;

    @JsonProperty("today_y_fi_youktpay_BANK2BANK_number")
    private int todayYFiYouktpayBank2BankNumber;

    @JsonProperty("today_y_fo_8pay_amount")
    private int todayYFo8payAmount;

    @JsonProperty("today_y_fo_8pay_number")
    private int todayYFo8payNumber;

    @JsonProperty("today_y_fo_amount")
    private int todayYFoAmount;

    @JsonProperty("today_y_fo_dapay_amount")
    private int todayYFoDapayAmount;

    @JsonProperty("today_y_fo_dapay_number")
    private int todayYFoDapayNumber;

    @JsonProperty("today_y_fo_h1pay_amount")
    private int todayYFoH1payAmount;

    @JsonProperty("today_y_fo_h1pay_number")
    private int todayYFoH1payNumber;

    @JsonProperty("today_y_fo_number")
    private int todayYFoNumber;

    @JsonProperty("today_y_fo_qijipay_amount")
    private int todayYFoQijipayAmount;

    @JsonProperty("today_y_fo_qijipay_number")
    private int todayYFoQijipayNumber;

    @JsonProperty("today_y_fo_youktpay_amount")
    private int todayYFoYouktpayAmount;

    @JsonProperty("today_y_fo_youktpay_number")
    private int todayYFoYouktpayNumber;

    @JsonProperty("today_y_fx_amount")
    private int todayYFxAmt;

    @JsonProperty("today_y_fx_dapay_amount")
    private int todayYFxDapayAmount;

    @JsonProperty("today_y_fx_dapay_number")
    private int todayYFxDapayNumber;

    @JsonProperty("today_y_fx_h1pay_amount")
    private int todayYFxH1payAmount;

    @JsonProperty("today_y_fx_h1pay_number")
    private int todayYFxH1payNumber;

    @JsonProperty("today_y_fx_number")
    private int todayYFxNumber;

    @JsonProperty("today_y_mnfi_amount")
    private int todayYMnfiAmount;

    @JsonProperty("today_y_mnfi_number")
    private int todayYMnfiNumber;

    @JsonProperty("today_y_mnfo_amount")
    private int todayYMnfoAmount;

    @JsonProperty("today_y_mnfo_number")
    private int todayYMnfoNumber;

    @JsonProperty("today_youktpay_BANK2BANK_deposit_revenue")
    private int todayYouktpayBank2BankDepositRevenue;

    @JsonProperty("today_youktpay_FO_revenue")
    private int todayYouktpayFoRevenue;

    @JsonProperty("yesterday_8pay_payout_revenue")
    private int yesterday8PayPayoutRevenue;

    @JsonProperty("yesterday_MNFO_bank_fee")
    private int yesterdayMnfoBankFee;

    @JsonProperty("yesterday_bank_deposit_revenue")
    private int yesterdayBankDepositRevenue;

    @JsonProperty("yesterday_dapay_BANK2BANK_deposit_revenue")
    private int yesterdayDapayBank2BankDepositRevenue;

    @JsonProperty("yesterday_dapay_NETGATE_deposit_revenue")
    private int yesterdayDapayNetgateDepositRevenue;

    @JsonProperty("yesterday_dapay_ZFBYHK_deposit_revenue")
    private int yesterdayDapayZfbyhkDepositRevenue;

    @JsonProperty("yesterday_dapay_payout_revenue")
    private int yesterdayDapayPayoutRevenue;

    @JsonProperty("yesterday_dapay_settlement_revenue")
    private int yesterdayDapaySettlementRevenue;

    @JsonProperty("yesterday_external_transfer_fee")
    private int yesterdayExternalTransferFee;

    @JsonProperty("yesterday_h1pay_UNION_PAY_deposit_revenue")
    private int yesterdayH1PayUnionPayDepositRevenue;

    @JsonProperty("yesterday_h1pay_payout_revenue")
    private int yesterdayH1PayPayoutRevenue;

    @JsonProperty("yesterday_h1pay_settlement_revenue")
    private int yesterdayH1PaySettlementRevenue;

    @JsonProperty("yesterday_internal_transfer_fee")
    private int yesterdayInternalTransferFee;

    @JsonProperty("yesterday_mnfi_revenue")
    private int yesterdayMnfiRevenue;

    @JsonProperty("yesterday_mnfo_revenue")
    private int yesterdayMnfoRevenue;

    @JsonProperty("yesterday_n_fi_bank_amount")
    private int yesterdayNFiBankAmount;

    @JsonProperty("yesterday_n_fi_bank_number")
    private int yesterdayNFiBankNumber;

    @JsonProperty("yesterday_n_fi_dapay_BANK2BANK_amount")
    private int yesterdayNFiDapayBANK2BANKAmount;

    @JsonProperty("yesterday_n_fi_dapay_BANK2BANK_number")
    private int yesterdayNFiDapayBANK2BANKNumber;

    @JsonProperty("yesterday_n_fi_dapay_NETGATE_amount")
    private int yesterdayNFiDapayNETGATEAmount;

    @JsonProperty("yesterday_n_fi_dapay_NETGATE_number")
    private int yesterdayNFiDapayNETGATENumber;

    @JsonProperty("yesterday_n_fi_dapay_ZFBYHK_amount")
    private int yesterdayNFiDapayZFBYHKAmount;

    @JsonProperty("yesterday_n_fi_dapay_ZFBYHK_number")
    private int yesterdayNFiDapayZFBYHKNumber;

    @JsonProperty("yesterday_n_fi_h1pay_UNION_PAY_amount")
    private int yesterdayNFiH1PayUNIONPAYAmount;

    @JsonProperty("yesterday_n_fi_h1pay_UNION_PAY_number")
    private int yesterdayNFiH1PayUNIONPAYNumber;

    @JsonProperty("yesterday_n_fi_qrpay_amount")
    private int yesterdayNFiQRPayAmount;

    @JsonProperty("yesterday_n_fi_qrpay_number")
    private int qrpayNumber;

    @JsonProperty("yesterday_n_fi_true_amount")
    private int trueAmount;

    @JsonProperty("yesterday_n_fi_true_number")
    private int trueNumber;

    @JsonProperty("yesterday_n_fi_vnpay_bank_transfer_amount")
    private int vnpayBankTransferAmount;

    @JsonProperty("yesterday_n_fi_vnpay_bank_transfer_number")
    private int vnpayBankTransferNumber;

    @JsonProperty("yesterday_n_fi_vnpay_online_banking_amount")
    private int vnpayOnlineBankingAmount;

    @JsonProperty("yesterday_n_fi_vnpay_online_banking_number")
    private int vnpayOnlineBankingNumber;

    @JsonProperty("yesterday_n_fi_vnpay_prepaid_amount")
    private int vnpayPrepaidAmount;

    @JsonProperty("yesterday_n_fi_vnpay_prepaid_number")
    private int vnpayPrepaidNumber;

    @JsonProperty("yesterday_n_fi_vnpay_qrpay_amount")
    private int vnpayQrpayAmount;

    @JsonProperty("yesterday_n_fi_vnpay_qrpay_number")
    private int vnpayQrpayNumber;

    @JsonProperty("yesterday_n_fi_youktpay_BANK2BANK_amount")
    private int youktpayBank2BankAmount;

    @JsonProperty("yesterday_n_fi_youktpay_BANK2BANK_number")
    private int youktpayBank2BankNumber;

    @JsonProperty("yesterday_n_fo_8pay_amount")
    private int fo8payAmount;

    @JsonProperty("yesterday_n_fo_8pay_number")
    private int yesterdayNFo8PayNumber;

    @JsonProperty("yesterday_n_fo_amount")
    private int yesterdayNFoAmount;

    @JsonProperty("yesterday_n_fo_dapay_amount")
    private int yesterdayNFoDapayAmount;

    @JsonProperty("yesterday_n_fo_dapay_number")
    private int yesterdayNFoDapayNumber;

    @JsonProperty("yesterday_n_fo_h1pay_amount")
    private int yesterdayNFoH1PayAmount;

    @JsonProperty("yesterday_n_fo_h1pay_number")
    private int yesterdayNFoH1PayNumber;

    @JsonProperty("yesterday_n_fo_number")
    private int yesterdayNFoNumber;

    @JsonProperty("yesterday_n_fo_qijipay_amount")
    private int yesterdayNFoQijipayAmount;

    @JsonProperty("yesterday_n_fo_qijipay_number")
    private int yesterdayNFoQijipayNumber;

    @JsonProperty("yesterday_n_fo_youktpay_amount")
    private int yesterdayNFoYouktpayAmount;

    @JsonProperty("yesterday_n_fo_youktpay_number")
    private int yesterdayNFoYouktpayNumber;

    @JsonProperty("yesterday_n_fx_amount")
    private int yesterdayNFxAmount;

    @JsonProperty("yesterday_n_fx_dapay_amount")
    private int yesterdayNFxDapayAmount;

    @JsonProperty("yesterday_n_fx_dapay_number")
    private int yesterdayNFxDapayNumber;

    @JsonProperty("yesterday_n_fx_h1pay_amount")
    private int yesterdayNFxH1PayAmount;

    @JsonProperty("yesterday_n_fx_h1pay_number")
    private int yesterdayNFxH1PayNumber;

    @JsonProperty("yesterday_n_fx_number")
    private int yesterdayNFxNumber;

    @JsonProperty("yesterday_n_mnfi_amount")
    private int yesterdayNMnfiAmount;

    @JsonProperty("yesterday_n_mnfi_number")
    private int yesterdayNMnfiNumber;

    @JsonProperty("yesterday_n_mnfo_amount")
    private int yesterdayNMnfoAmount;

    @JsonProperty("yesterday_n_mnfo_number")
    private int yesterdayNMnfoNumber;

    @JsonProperty("yesterday_payout_bank_fee_Merchant_8pay")
    private int yesterdayPayoutBankFeeMerchant8pay;

    @JsonProperty("yesterday_payout_bank_fee_Merchant_Dapay")
    private int yesterdayPayoutBankFeeMerchantDapay;

    @JsonProperty("yesterday_payout_bank_fee_Merchant_H1Pay")
    private int yesterdayPayoutBankFeeMerchantH1Pay;

    @JsonProperty("yesterday_payout_bank_fee_Merchant_QijiPay")
    private int yesterdayPayoutBankFeeMerchantQijiPay;

    @JsonProperty("yesterday_payout_bank_fee_Merchant_YouktPay")
    private int yesterdayPayoutBankFeeMerchantYouktPay;

    @JsonProperty("yesterday_payout_bank_fee_OneWallet")
    private int yesterdayPayoutBankFeeOneWallet;

    @JsonProperty("yesterday_payout_bank_fee_OneWallet_8pay")
    private int yesterdayPayoutBankFeeOneWallet8pay;

    @JsonProperty("yesterday_payout_bank_fee_OneWallet_Dapay")
    private int yesterdayPayoutBankFeeOneWalletDapay;

    @JsonProperty("yesterday_payout_bank_fee_OneWallet_H1Pay")
    private int yesterdayPayoutBankFeeOneWalletH1Pay;

    @JsonProperty("yesterday_payout_bank_fee_OneWallet_QijiPay")
    private int yesterdayPayoutBankFeeOneWalletQijiPay;

    @JsonProperty("yesterday_payout_bank_fee_OneWallet_YouktPay")
    private int yesterdayPayoutBankFeeOneWalletYouktPay;

    @JsonProperty("yesterday_payout_revenue")
    private int yesterdayPayoutRevenue;

    @JsonProperty("yesterday_qijipay_payout_revenue")
    private int yesterdayQijipayPayoutRevenue;

    @JsonProperty("yesterday_qrpay_deposit_revenue")
    private int yesterdayQrpayDepositRevenue;

    @JsonProperty("yesterday_registered_farmer")
    private int yesterdayRegisteredFarmer;

    @JsonProperty("yesterday_registered_merchant")
    private int yesterdayRegisteredMerchant;

    @JsonProperty("yesterday_settlement_bank_fee")
    private int yesterdaySettlementBankFee;

    @JsonProperty("yesterday_settlement_revenue")
    private int yesterdaySettlementRevenue;

    @JsonProperty("yesterday_success_FO_bank_fee_Merchant")
    private int yesterdaySuccessFOBankFeeMerchant;

    @JsonProperty("yesterday_true_deposit_revenue")
    private int yesterdayTrueDepositRevenue;

    @JsonProperty("yesterday_vnpay_bank_transfer_deposit_revenue")
    private int yesterdayVnpayBankTransferDepositRevenue;

    @JsonProperty("yesterday_vnpay_online_banking_deposit_revenue")
    private int yesterdayVnpayOnlineBankingDepositRevenue;

    @JsonProperty("yesterday_vnpay_prepaid_deposit_revenue")
    private int yesterdayVnpayPrepaidDepositRevenue;

    @JsonProperty("yesterday_vnpay_qrpay_deposit_revenue")
    private int yesterdayVnpayQrpayDepositRevenue;

    @JsonProperty("yesterday_y_fi_bank_amount")
    private int yesterdayYFiBankAmount;

    @JsonProperty("yesterday_y_fi_bank_number")
    private int yesterdayYFiBankNumber;

    @JsonProperty("yesterday_y_fi_dapay_BANK2BANK_amount")
    private int yesterdayYFiDapayBank2BankAmount;

    @JsonProperty("yesterday_y_fi_dapay_BANK2BANK_number")
    private int yesterdayYFiDapayBank2BankNumber;

    @JsonProperty("yesterday_y_fi_dapay_NETGATE_amount")
    private int yesterdayYFiDapayNetgateAmount;

    @JsonProperty("yesterday_y_fi_dapay_NETGATE_number")
    private int yesterdayYFiDapayNetgateNumber;

    @JsonProperty("yesterday_y_fi_dapay_ZFBYHK_amount")
    private int yesterdayYFiDapayZfbYhkAmount;

    @JsonProperty("yesterday_y_fi_dapay_ZFBYHK_number")
    private int yesterdayYFiDapayZfbYhkNumber;

    @JsonProperty("yesterday_y_fi_h1pay_UNION_PAY_amount")
    private int yesterdayYFiH1PayUnionPayAmount;

    @JsonProperty("yesterday_y_fi_h1pay_UNION_PAY_number")
    private int yesterdayYFiH1PayUnionPayNumber;

    @JsonProperty("yesterday_y_fi_qrpay_amount")
    private int yesterdayYFiQrPayAmount;

    @JsonProperty("yesterday_y_fi_qrpay_number")
    private int yesterdayYFiQrPayNumber;

    @JsonProperty("yesterday_y_fi_true_amount")
    private int yesterdayYFiTrueAmount;

    @JsonProperty("yesterday_y_fi_true_number")
    private int yesterdayYFiTrueNumber;

    @JsonProperty("yesterday_y_fi_vnpay_bank_transfer_amount")
    private int yesterdayYFiVnpayBankTransferAmount;

    @JsonProperty("yesterday_y_fi_vnpay_bank_transfer_number")
    private int yesterdayYFiVnpayBankTransferNumber;

    @JsonProperty("yesterday_y_fi_vnpay_online_banking_amount")
    private int yesterdayYFiVnpayOnlineBankingAmount;

    @JsonProperty("yesterday_y_fi_vnpay_online_banking_number")
    private int yesterdayYFiVnpayOnlineBankingNumber;

    @JsonProperty("yesterday_y_fi_vnpay_prepaid_amount")
    private int yesterdayYFiVnpayPrepaidAmount;

    @JsonProperty("yesterday_y_fi_vnpay_prepaid_number")
    private int yesterdayYFiVnpayPrepaidNumber;

    @JsonProperty("yesterday_y_fi_vnpay_qrpay_amount")
    private int yesterdayYFiVnpayQrpayAmount;

    @JsonProperty("yesterday_y_fi_vnpay_qrpay_number")
    private int yesterdayYFiVnpayQrpayNumber;

    @JsonProperty("yesterday_y_fi_youktpay_BANK2BANK_amount")
    private int yesterdayYFiYouktpayBank2BankAmount;

    @JsonProperty("yesterday_y_fi_youktpay_BANK2BANK_number")
    private int yesterdayYFiYouktpayBank2BankNumber;

    @JsonProperty("yesterday_y_fo_8pay_amount")
    private int yesterdayYFo8PayAmount;

    @JsonProperty("yesterday_y_fo_8pay_number")
    private int yesterdayYFo8PayNumber;

    @JsonProperty("yesterday_y_fo_amount")
    private int yesterdayYFoAmount;

    @JsonProperty("yesterday_y_fo_dapay_amount")
    private int yesterdayYFoDapayAmount;

    @JsonProperty("yesterday_y_fo_dapay_number")
    private int yesterdayYFoDapayNumber;

    @JsonProperty("yesterday_y_fo_h1pay_amount")
    private int yesterdayYFoH1payAmount;

    @JsonProperty("yesterday_y_fo_h1pay_number")
    private int yesterdayYFoH1payNumber;

    @JsonProperty("yesterday_y_fo_number")
    private int yesterdayYFoNumber;

    @JsonProperty("yesterday_y_fo_qijipay_amount")
    private int yesterdayYFoQijipayAmount;

    @JsonProperty("yesterday_y_fo_qijipay_number")
    private int yesterdayYFoQijipayNumber;

    @JsonProperty("yesterday_y_fo_youktpay_amount")
    private int yesterdayYFoYouktpayAmount;

    @JsonProperty("yesterday_y_fo_youktpay_number")
    private int yesterdayYFoYouktpayNumber;

    @JsonProperty("yesterday_y_fx_amount")
    private int yesterdayYFxAmt;

    @JsonProperty("yesterday_y_fx_dapay_amount")
    private int yesterdayYFxDapayAmount;

    @JsonProperty("yesterday_y_fx_h1pay_number")
    private int yesterdayYFxF1PayNumber;

    @JsonProperty("yesterday_y_fx_number")
    private int yesterdayYFxNumber;

    @JsonProperty("yesterday_y_mnfi_amount")
    private int yesterdayYMnfiAmount;

    @JsonProperty("yesterday_y_mnfi_number")
    private int yesterdayYMnfiNumber;

    @JsonProperty("yesterday_y_mnfo_amount")
    private int yesterdayYMnfoAmount;

    @JsonProperty("yesterday_y_mnfo_number")
    private int yesterdayYMnfoNumber;

    @JsonProperty("yesterday_youktpay_BANK2BANK_deposit_revenue")
    private int yesterdayYouktpayBank2BankDepositRevenue;

    @JsonProperty("yesterday_youktpay_payout_revenue")
    private int yesterdayYouktpayPayoutRevenue;


}
