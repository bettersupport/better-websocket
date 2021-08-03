package cn.better.websocket.core.annotation;

import cn.better.websocket.core.configuration.WebSocketMapRegister;
import org.springframework.context.annotation.Import;
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
@Import({WebSocketMapRegister.class})
public @interface WebSocketMapScan {

    @AliasFor("basePackages")
    String[] value() default {};

    @AliasFor("value")
    String[] basePackages() default {};

}
