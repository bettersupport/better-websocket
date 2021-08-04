package cn.better.websocket.demo;

import cn.better.websocket.core.annotation.WebSocketMapScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author wang.wencheng
 * @date 2021-8-3
 * @remark
 */
@SpringBootApplication
@WebSocketMapScan({"cn.better.websocket.demo.endpoint"})
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}
