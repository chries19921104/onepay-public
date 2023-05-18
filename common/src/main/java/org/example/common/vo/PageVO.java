package org.example.common.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 分页返回
 * @author Administrator
 */
@Getter
@Setter
public class PageVO {
    private Long total;

    private List rows;
}
