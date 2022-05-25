//package com.clink.blog.conf;
//
//import org.springframework.context.MessageSource;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
//
//@Configuration
//public class ValidationConfig {
//    private final MessageSource validationMessageSource = null;
//
//    @Bean
//    public LocalValidatorFactoryBean getValidator() {
//        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
//        bean.setValidationMessageSource(validationMessageSource);
//
//        return bean;
//    }
//}