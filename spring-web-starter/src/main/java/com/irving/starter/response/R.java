package com.irving.starter.response;

import cn.hutool.core.util.ReflectUtil;
import com.irving.starter.exception.IMException;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.Optional;

/**
 * @author zdq
 * @version 1.0
 * @date 2021/11/23 13:53
 * @describe
 */
@Slf4j
public class R<T> extends ApiResponse<T> implements Serializable {

    /**
     * 私有化构造方法
     */
    private R(int code, T data, String msg) {
        super(code, data, msg);
    }

    /**
     * 判断是否成功
     */
    public boolean isSuccess(){
        return Code.OK == this.getCode();
    }

    public static <T> R<T> success(){
        return success(null);
    }

    public static <T> R<T> success(T data){
        return success(data,"success");
    }

    public static <T> R<T> success(T data,String message){
        return new R<>(Code.OK,data,message);
    }

    public static <T> R<T> fail(String message){
        return fail(Code.HTTP_BAD_REQUEST,message);
    }

    public static <T> R<T> fail(int code,String message){
        return fail(code,null,message);
    }

    public static <T> R<T> fail(int code,T data,String message){
        return new R<>(code,data,message);
    }

    public static <T> R<T> fail(int code,T data){
        return fail(code,data,null);
    }

    /**
     * 反射强行修改值得结果
     * 尽量避免使用
     */
    public R<T> unsafeData(Object data){
        ReflectUtil.setFieldValue(this,"data",data);
        return this;
    }
    /**
     * 检查远程调用返回结果
     */
    public Optional<T> checkGet(){
        if (this.isSuccess()){
            return Optional.ofNullable(this.getData());
        }
        throw IMException.build(this.getCode(),this.getMsg());
    }

    @Override
    public String toString() {
        return "R{" +
                "data=" + data +
                '}';
    }
}
