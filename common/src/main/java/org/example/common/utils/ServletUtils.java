package org.example.common.utils;

import cn.hutool.core.exceptions.UtilException;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.NumberUtil;


import lombok.SneakyThrows;
import org.example.common.enums.AckCode;
import org.example.common.exception.MsgException;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

/**
 * 获取Servlet(Spring方式)
 * @author TAOTAO
 */
public class ServletUtils {

    public static HttpServletRequest getRequest(){

        ServletRequestAttributes servletRequestAttributes= (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        return request;
    }


    /**
     * 根据键获取请求中的值
     * @param key
     * @return
     */
    public static String getParams(String key){
        return getRequest().getParameter(key);
    }

    /**
     *  得到参数把变整数
     * @param key
     * @return
     */
    @SneakyThrows
    public static Integer getParamsToInt(String key) {
        String value=getParams(key);
        if(NumberUtil.isInteger(value)){
            return  Integer.parseInt(value);
        }
        else{
            throw new MsgException(AckCode.SYSTEM_PARAM_FAIL);
        }
    }

    /**
     * 得到参数把变整数
     * @param key
     * @param defaultValue  默认值
     * @return
     */
    public static Integer getParamsToInt(String key,int defaultValue){
        String value=getParams(key);
        if(NumberUtil.isInteger(value)){
            return  Integer.parseInt(value);
        }
        else{
            return defaultValue;
        }
    }

    /**
     * 向前端输出json数据
     * @param response
     * @param jsonValue
     */
    public static void renderString(HttpServletResponse response, String jsonValue){
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        Writer writer = null;
        try {
            writer = response.getWriter();
            writer.write(jsonValue);
            writer.flush();
        } catch (IOException e) {
            throw new UtilException(e);
        } finally {
            IoUtil.close(writer);
        }
    }

}
