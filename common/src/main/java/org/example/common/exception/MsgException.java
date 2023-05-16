package org.example.common.exception;


import cn.hutool.json.JSONUtil;

public class MsgException extends Exception {

    public MsgException(String message) {
        super(message);
    }

    public MsgException(Object o) {
        super(JSONUtil.toJsonStr(o));
    }
}
