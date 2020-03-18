package com.example.websocket2.Web;

import org.springframework.stereotype.Component;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;


@ServerEndpoint(value = "/websocket") //接受websocket请求路径
@Component //注册到spring容器中
public class MyWebSocket {

    //当前连接（每个websocket连入都会创建一个MyWebSocket实例
    private Session session;
    //处理连接建立
    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        ServerManager.add(this);
    }
    //接受消息
    @OnMessage
    public void onMessage(String message,Session session){
        System.out.println("收到客户端"+session.getId()+"消息："+message);
        try{
            this.sendMessage("收到消息："+message);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    //处理错误
    @OnError
    public void onError(Throwable error,Session session){
        System.out.println("发生错误"+session.getId()+error.getMessage());
    }
    //处理连接关闭
    @OnClose
    public void onClose(){
        ServerManager.remove(this);
        System.out.println("连接关闭:"+this.session.getId());
    }
    //群发消息
    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }


}

