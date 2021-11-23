package com.irving.starter.exception;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.http.HttpStatus;
import com.irving.starter.response.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.ServletException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zdq
 * @version 1.0
 * @date 2021/11/22 13:35
 * @describe  统一异常处理
 */
@RestControllerAdvice
@Slf4j
public class IRestControllerException {

    @ExceptionHandler(value = IMException.class)
    public R<Void> hander(IMException imException){
        log.info(imException.getMessage());
        return R.fail(HttpStatus.HTTP_INTERNAL_ERROR,imException.getMessage());
    }

    @ExceptionHandler(value = Exception.class)
    public R<Void> hander(Exception exception){
        log.info(exception.getMessage());
        return R.fail(HttpStatus.HTTP_INTERNAL_ERROR,"服务器发生未知异常");
    }

    /**
     * 不符合请求规范的请求-----------------------------------------------------------
     */
    @ExceptionHandler(ServletException.class)
    public R<Void> handlerBadRequestException(Exception exception){
        log.info(exception.getMessage());
        return R.fail(HttpStatus.HTTP_BAD_REQUEST,"不规范的请求");
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public R<Void> handlerBadRequestException2(Exception exception){
        return handlerBadRequestException(exception);
    }

    @ExceptionHandler(BindException.class)
    public R<List<String>> handlerBindException(BindException ex){
        return getBindErrors(ex.getBindingResult());
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public R<List<String>> handlerMethodArgumentNotValidException(MethodArgumentNotValidException ex){
        return getBindErrors(ex.getBindingResult());
    }

    private R<List<String>> getBindErrors(BindingResult bindingResult) {
        List<FieldError> errors = bindingResult.getFieldErrors();
        return R.fail(
                HttpStatus.HTTP_BAD_REQUEST,
                CollUtil.isEmpty(errors)?
                        null:
                        errors.stream().map(
                               item->item.getField()+":"+item.getRejectedValue()+":"+item.getDefaultMessage()
                        ).collect(Collectors.toList()),
                "参数错误"

        );
    }

}
