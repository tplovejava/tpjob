package com.tp.soft;

import com.tp.soft.config.RabbitmqServerConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @Author: taop
 * @Date: 2019/6/9 下午12:08
 * @Version 1.0
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({RabbitmqServerConfiguration.class})
public @interface EnableRabbitmqServer {
}
