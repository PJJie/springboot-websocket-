package com.example.websocket2.Controller;

import com.example.websocket2.Web.ServerManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class websocketController {
    @GetMapping("/broadcast")
    public void broadCast(){
        ServerManager.broadCast("服务器发出的一条测试广播！");
    }

}
