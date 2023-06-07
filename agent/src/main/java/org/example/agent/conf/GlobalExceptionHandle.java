package org.example.agent.conf;

import cn.hutool.core.util.StrUtil;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.example.common.base.CommResp;
import org.example.common.exception.MsgException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandle {

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler({Exception.class})
    public CommResp handleException(Exception e) {
        e.printStackTrace();
        if (e instanceof MsgException) {
            return CommResp.FAIL(e.getMessage());
        }else if (e instanceof UnauthenticatedException) {
            return CommResp.FAIL("unauthenticated") ;
        }else if (e instanceof AuthorizationException) {
            return CommResp.FAIL("authorization") ;
        }else if (e instanceof BindException) {
            String s = StrUtil.subAfter(e.getMessage(), "default message", true);
            return CommResp.FAIL("param error > "+s);
        } else if (e instanceof Exception) {
            return CommResp.FAIL("system error > " + e.getLocalizedMessage());
        } else {
            return CommResp.FAIL("error error");
        }
    }
}
