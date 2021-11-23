package com.irving.starter.config;

import org.hibernate.validator.HibernateValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import javax.validation.Validator;

/**
 * @author zdq
 * @version 1.0
 * @date 2021/11/18 16:25
 * @describe  属性只要第一个不符合validation，则立马返回异常
 */
public class ValidConfiguration {

    @Bean
    public Validator validator(){
        ValidatorFactory aTrue = Validation.byProvider(HibernateValidator.class)
                .configure()
                //开启快速返回模式
                .addProperty("hibernate.validator.fail_fast", "true")
                .buildValidatorFactory();
        return aTrue.getValidator();
    }

    @Bean
    public MethodValidationPostProcessor methodValidationPostProcessor(){
        MethodValidationPostProcessor methodValidationPostProcessor = new MethodValidationPostProcessor();
        Validator validator = validator();
        methodValidationPostProcessor.setValidator(validator);
        return methodValidationPostProcessor;
    }


}