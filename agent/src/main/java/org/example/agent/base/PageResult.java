package org.example.agent.base;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 通用分页数据格式
 */
@Data
@NoArgsConstructor
public class PageResult<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 当前页数
     */
    private long pageNo;

    /**
     * 每页记录数
     */
    private long pageSize;

    /**
     * 总页数
     */
    private long totalPage;

    /**
     * 总记录数
     */
    private long totalCount;

    /**
     * 列表数据
     */
    private List<T> records;

    /**
     * 分页结果
     *
     * @param page 分页结果
     */
    public PageResult(IPage<T> page) {
        this.records = page.getRecords();
        this.totalCount = page.getTotal();
        this.pageSize =  page.getSize();
        this.pageNo =  page.getCurrent();
        this.totalPage =  page.getPages();
    }
}