package org.example.admin.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ExternalStatementDto {

    private Long bankCardId;

    private String statement;

    private String  accountCode;

    private String commandId;

    private LocalDateTime transactionStartData;

    private LocalDateTime transactionEndData;

    private String currency;

    private Integer type;

    private Long debit;

    private Long credit;

    private String description;

    private LocalDateTime updatedStartAt;

    private LocalDateTime updatedEndAt;

    private Integer page;

    private Integer rq;
}
