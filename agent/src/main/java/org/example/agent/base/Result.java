package org.example.agent.base;

import cn.hutool.http.HttpStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 通用返回数据格式
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Result<T> {
    private Integer code;
    private String message;
    private T data;

    public static Result<Void> success() {
        return new Result<>(HttpStatus.HTTP_OK, "请求成功", null);
    }

    public static <T> Result<T> success(T data) {
        return new Result<>(HttpStatus.HTTP_OK, "请求成功", data);
    }

    public static <T> Result<T> success(String message, T data) {
        return new Result<>(HttpStatus.HTTP_OK, message, data);
    }

    public static Result<Void> failed() {
        return new Result<>(HttpStatus.HTTP_INTERNAL_ERROR, "请求失败", null);
    }

    public static Result<Void> failed(String message) {
        return new Result<>(HttpStatus.HTTP_INTERNAL_ERROR, message, null);
    }

    public static Result<Void> failed(Integer code, String message) {
        return new Result<>(code, message, null);
    }

    public static <T> Result<T> failed(Integer code, String message, T data) {
        return new Result<>(code, message, data);
    }

    public boolean isSuccess() {
        return HttpStatus.HTTP_INTERNAL_ERROR == this.code;
    }

}