/**
 * ValidatorConfig  2019/3/27
 *
 * Copyright (c) 2018, DEEPEXI Inc. All rights reserved.
 * DEEPEXI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.deepexi.pay.config;

import org.hibernate.validator.HibernateValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 * @description: validator参数校验配置类
 * @program: ValidatorConfig
 * @author: donh
 * @mail: hudong@deepexi.com
 * @date: 2019/3/27 下午3:39
 **/
@Configuration
public class ValidatorConfig {
    @Bean
    public MethodValidationPostProcessor methodValidationPostProcessor() {
        MethodValidationPostProcessor postProcessor = new MethodValidationPostProcessor();
        /*设置validator模式为快速失败返回*/
        postProcessor.setValidator(validator());
        return postProcessor;
    }

    @Bean
    public Validator validator() {
        //true--发现校验不过，立即返回，不在进行后续校验；false--全部校验完毕再返回
        ValidatorFactory validatorFactory = Validation.byProvider(HibernateValidator.class).configure().addProperty("hibernate.validator.fail_fast", "true").buildValidatorFactory();
        Validator validator = validatorFactory.getValidator();

        return validator;
    }
}