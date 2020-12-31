package org.dubbo.websocket.service.impl;

import org.dubbo.websocket.WebSocketServer;
import org.dubbo.websocket.entity.WsUser;
import org.dubbo.websocket.service.IWebSocketPushMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author _lizy
 * @version 1.0
 * @description TODO
 * @date 2020/11/27 1:16
 */
@Service
public class WebSocketPushMsgImpl implements IWebSocketPushMsg {

    @Autowired
    private WebSocketServer webSocketServer;
//    @Autowired
//    private SimpMessagingTemplate simpMessagingTemplate;


    @Override
    public Boolean pushMsg(String msg) {
        return webSocketServer.sendMessage(msg);
    }

    @Override
    public Boolean pushMsg(Long userId, String msg) {
        if(userId==null) { return false; }

        return webSocketServer.sendMessage2User(String.valueOf(userId), msg).equals(WebSocketServer.FAILED) ? false : true;
    }


//    @Scheduled(fixedRate = 10000)
    public void sendTopicMessage() {
        System.out.println("后台广播推送！");
        WsUser user=new WsUser();
        user.setName("lizy");
//        this.simpMessagingTemplate.convertAndSend("/topic/getResponse",user);
    }

}
