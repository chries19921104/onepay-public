package org.example.common.utils;

import java.util.List;

public class PageUtils {

    /**
     * 通过全部数据，获取当前页数据
     * @param page 当前页
     * @param pageSize 每页显示数量
     * @param list 总数据
     * @param <T> 列表数据类型
     * @return 当前页显示数据
     */
    public static <T> List<T> getPageRecords(int page, int pageSize, List<T> list) {
        if (list.size() <= pageSize){
            return list;
        }
        int startNum = (page - 1) * pageSize;
        int endNum = page * pageSize;
        List<T> records = list.subList(startNum, endNum);
        return records;
    }
}
