package com.irving.starter.response;

import lombok.Getter;

/**
 * @author zdq
 * @version 1.0
 * @date 2021/11/23 13:51
 * @describe
 */
@Getter
public class ApiResponse<T> {

    /**
     * 状态码
     */
    private final int code;

    /**
     * 数据
     */
    protected final T data;

    /**
     * 消息提示
     */
    private final String msg;

    public ApiResponse(int code, T data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
    }
}
