package com.brightcomplete.boot.starter.job.annotation;

import com.brightcomplete.boot.starter.job.config.XxlJobConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author lmy
 */
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import({ XxlJobConfiguration.class })
public @interface EnableXxlJob {

}
