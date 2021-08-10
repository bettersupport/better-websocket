package io.github.bettersupport.websocket.demo;

import io.github.bettersupport.websocket.core.annotation.WebSocketMapScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author wang.wencheng
 * date 2021-8-3
 * describe
 */
@SpringBootApplication
@WebSocketMapScan({"io.github.bettersupport.websocket.demo.endpoint"})
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}
