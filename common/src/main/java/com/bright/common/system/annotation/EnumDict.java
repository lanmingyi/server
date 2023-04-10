package com.bright.common.system.annotation;

import java.lang.annotation.*;

/**
 * 将枚举类转化成字典数据
 **/
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EnumDict {

    /**
     * 作为字典数据的唯一编码
     */
    String value() default "";
}
