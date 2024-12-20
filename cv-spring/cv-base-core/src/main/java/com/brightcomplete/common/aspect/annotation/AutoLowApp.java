package com.brightcomplete.common.aspect.annotation;

import java.lang.annotation.*;

import com.brightcomplete.common.constant.enums.LowAppAopEnum;

/**
 * 自动注入low_app_id
 * 
 * @Author: lmy
 * @Date 2022年01月05日
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AutoLowApp {

	/**
	 * 切面类型（add、delete、db_import等其他操作）
	 *
	 * @return
	 */
	LowAppAopEnum action();

	/**
	 * 业务类型（cgform等）
	 *
	 * @return
	 */
	String bizType();

}
