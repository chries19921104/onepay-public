package org.example.agent.utils;

import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 深藏buled蓝先生
 */

// Bean拷贝封装工具类
public class BeanCopyUtils {
    private BeanCopyUtils() {

    }

    public static <V> V copyBean(Object source, Class<V> clazz) {

        V result = null;

        // 创建目标对象
        try {
            result = clazz.newInstance();
            // 实现属性copy
            BeanUtils.copyProperties(source, result);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 返回结果
        return result;
    }

    public static <O,V> List<V> copyBeanList(List<O> list, Class<V> clazz) {

        return list.stream()
                .map(o -> copyBean(o, clazz))
                .collect(Collectors.toList());


    }



}