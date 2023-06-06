package org.example.admin.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.common.enums.AckCode;

import java.io.Serializable;

/**
 * @title: 统一响应模型接口
 * @Author: wangxiaodong
 * @Date: 2020/11/10 13:03
 * @Version 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class R<T> implements Serializable {

    private static final long serialVersionUID = -3761029186203372163L;
    private final long timestamps = System.currentTimeMillis();
    private int code;
    private T data;
    private String msg;

    public synchronized static <T> R<T> build(AckCode statusEnum) {
        return build(statusEnum, null);
    }

    public synchronized static <T> R<T> build(AckCode ackCode, T data) {
        R<T> res = new R<>();
        res.setCode(ackCode.code);
        res.setMsg(ackCode.msg);
        res.setData(data);
        return res;
    }

    public synchronized static <T> R<T> build(AckCode ackCode, T data, String msg) {
        R<T> res = new R<>();
        res.setCode(ackCode.code);
        res.setMsg(msg);
        res.setData(data);
        return res;
    }

    public synchronized static <T> R<T> ok() {
        return build(AckCode.SUCCESS, null);
    }

    public synchronized static <T> R<T> okHasData(T data) {
        return build(AckCode.SUCCESS, data);
    }


}
