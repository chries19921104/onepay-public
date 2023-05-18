package org.example.common.dto;

import lombok.Data;

@Data
public class BrankDto {
    private Integer BK100_ID;
    private String message;
    private Integer is_enabled;
    private Integer txn_type;
}
