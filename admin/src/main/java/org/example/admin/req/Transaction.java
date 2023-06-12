package org.example.admin.req;

/**
 * @author Txd
 * @since 2023-06-12 15:49:39
 */
public class Transaction {
    public final static int STATUS_PENDING = 1;
    public final static int STATUS_PROCESSING = 2;
    public final static int STATUS_COMPLETED = 3;
    public final static int STATUS_MANUAL_PROCESS = 4;
    public final static int STATUS_FAILED = 5;
    public final static int STATUS_RETRIED = 6;
    public final static int STATUS_UNMATCHED_ACC_NAME = 7;
    public final static int STATUS_ACC_NAME_CONFIRMED = 8;
    public final static int STATUS_AWAITING_APPROVAL = 9;

    public final static int IS_WEIRD_NORMAL = 0;  //非异常
    public final static int IS_WEIRD_ISSUE = 1;   //异常
    public final static int IS_WEIRD_SOLVED = 2;  //已解决

    public final static int IS_URGENT_NORMAL = 0;  //催单-无动作
    public final static int IS_URGENT_ISSUE = 1;   //催单-进行催单

    public final static int TXN_MODE_MN_BANK_TO_BANK   = 10;     //卡卡
    public final static int TXN_MODE_MN_ALI_TO_BANK    = 11;      //宝卡
    public final static int TXN_MODE_MN_WECHAT_TO_BANK = 12;   //微卡
    public final static int TXN_MODE_MN_UNION_TO_BANK  = 13;    //云卡
}
