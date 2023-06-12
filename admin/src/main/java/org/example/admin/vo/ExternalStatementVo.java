package org.example.admin.vo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ExternalStatementVo {

    private String statement;

    private String name;

    private String accountCode;

    private LocalDateTime transactionDateStartData;

    private LocalDateTime transactionDateEndData;

    private String description;

    private String note;

    private Long debit;

    private Long credit;

    private LocalDateTime createdAt;

    private String creator;

    private LocalDateTime updaterStartData;

    private LocalDateTime updaterEndData;

    private String updater;

    private Long bankFee;

    private Integer type;
}
