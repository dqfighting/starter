package com.irving.starter.config;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(ValidConfiguration.class)

/**
 * @author zdq
 * @version 1.0
 * @date 2021/11/18 16:25
 * @describe  注解 ValidConfiguration
 */
public @interface EableFormValidator {

}
