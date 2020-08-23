package com.lagou.annotation;

import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Security {

    String[] value();

    // 备用，拦截器中通过header用户标识转换为AuthCode,用以表面是否有权限
    AuthCode code() default AuthCode.Allow;
}
