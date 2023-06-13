package org.example.agent.base;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 分页工具类
 */
@Setter
@Getter
@NoArgsConstructor
public class PageUtils<T> {

    public static final String PAGE_NO = "page";
    public static final String PAGE_SIZE = "rp";

    /**
     * 获取分页
     */
    public Page<T> getPage(Map<String, Object> param) {
        // 分页参数
        long pageNo = 1;
        long pageSize = 20;

        if (param.get(PAGE_NO) != null) {
            pageNo = Long.parseLong(param.get(PAGE_NO).toString());
        }
        if (param.get(PAGE_SIZE) != null) {
            pageSize = Long.parseLong(param.get(PAGE_SIZE).toString());
        }

        // 分页对象
        return new Page<>(pageNo, pageSize);
    }

    public Page<T> getPage() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // 分页参数
        long pageNo = 1;
        long pageSize = 20;

        if (request.getParameter(PAGE_NO) != null) {
            pageNo = Long.parseLong(request.getParameter(PAGE_NO));
        }
        if (request.getParameter(PAGE_SIZE) != null) {
            pageSize = Long.parseLong(request.getParameter(PAGE_SIZE));
        }

        // 分页对象
        return new Page<>(pageNo, pageSize);
    }

}