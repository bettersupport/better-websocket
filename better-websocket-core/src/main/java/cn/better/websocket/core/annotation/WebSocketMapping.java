package cn.better.websocket.core.annotation;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * @author wang.wencheng
 * @date 2021-8-3
 * @remark
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface WebSocketMapping {

    @AliasFor("path")
    String value() default "";

    @AliasFor("value")
    String path() default "";

}
