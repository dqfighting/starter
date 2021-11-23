package com.irving.starter.exception;

import cn.hutool.http.HttpStatus;
import com.irving.starter.response.R;
import lombok.Getter;

/**
 * @author zdq
 * @version 1.0
 * @date 2021/11/23 14:00
 * @describe
 */
@Getter
public class IMException extends RuntimeException{

    private final int code;
    private final String message;


    public IMException(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public static <T> IMException build(R<T> r){
        return build(r.getCode(),r.getMsg());
    }

    public static IMException build(String message){
        return build(HttpStatus.HTTP_INTERNAL_ERROR,message);
    }

    public static IMException build(int code, String message){
        return new IMException(code,message);
    }
}
