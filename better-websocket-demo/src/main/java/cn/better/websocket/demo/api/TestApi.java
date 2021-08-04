package cn.better.websocket.demo.api;

import cn.better.websocket.core.configuration.WebSocketMapRegister;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wang.wencheng
 * @date 2021-8-4
 * @remark
 */
@RestController
public class TestApi {

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public Object test() {
        return WebSocketMapRegister.getWebSocketMap();
    }

}
