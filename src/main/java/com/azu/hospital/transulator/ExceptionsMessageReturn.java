package com.azu.hospital.transulator;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;
import java.util.Locale;


@Component
public class ExceptionsMessageReturn {

    private final HttpServletRequest servletRequest;

    @Autowired
    public ExceptionsMessageReturn(HttpServletRequest servletRequest) {
        this.servletRequest = servletRequest;
    }


    public Locale getMessageLocally(){
        String acceptLanguage = servletRequest.getHeader("Accept-Language");

        if (acceptLanguage != null && !acceptLanguage.trim().isEmpty()) {
            return Locale.forLanguageTag(acceptLanguage);
        }
        return Locale.getDefault();
    }
}
