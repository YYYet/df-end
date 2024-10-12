package com.ruoyi.common.utils.cloud.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

/**
 * @describe 多语言消息工具
 * @author shenkai
 * @date 2022/8/9
 */
@Component
public class MessageUtil {

    private static ResourceBundleMessageSource messageSource;

    public static String get(String code, String... args) {
        Locale locale = LocaleContextHolder.getLocale();
        String message = messageSource.getMessage(code, args, locale);
        return message;
    }

    @Autowired
    public void setMessageSource(ResourceBundleMessageSource messageSource) {
        this.messageSource = messageSource;
    }

}
