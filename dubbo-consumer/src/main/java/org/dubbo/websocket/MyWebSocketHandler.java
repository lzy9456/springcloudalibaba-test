//package org.dubbo.websocket;
//
//import com.alibaba.fastjson.JSONObject;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Service;
//import org.springframework.web.socket.*;
//
//import java.io.IOException;
//import java.util.Map;
//import java.util.Set;
//import java.util.concurrent.ConcurrentHashMap;
//
///**
// * @author _lizy
// * @version 1.0
// * @description my WebSocketHandler
// * @date 2020/11/26 20:38
// */
//@Slf4j
//@Service
//public class MyWebSocketHandler implements WebSocketHandler {
//
//    private static final Map<String, WebSocketSession> users = new ConcurrentHashMap<>(); //在线用户列表
//
//
//    @Override
//    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
//        log.info("connect websocket successful!");
//        String ID = session.getUri().toString().split("ID=")[1];
//        log.info(ID);
//        if (ID != null) {
//            users.put(ID, session);
//            session.sendMessage(new TextMessage("{\"message\":\"socket successful connection!\"}"));
//            log.info("id:" + ID + ",session:" + session + "");
//        }
//        log.info("current user number is:"+users.size());
//    }
//
//    @Override
//    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
//        try{
//            JSONObject jsonobject = JSONObject.parseObject((String)message.getPayload());
//            log.info("receive message:" + jsonobject);
////			log.info(jsonobject.get("message")+":来自"+(String)session.getAttributes().get("WEBSOCKET_USERID")+"的消息");
//            //jsonobject.get("id")
//            sendMessageToUser(2+"", new TextMessage("{\"message\":\"server received message,hello!\"}"));
//        }catch (Exception e) {
//            log.error("e",e);
//        }
//    }
//
//    @Override
//    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
//        if (session.isOpen()) {
//            session.close();
//        }
//        log.error("connect error", exception);
//        users.remove(getClientId(session));
//    }
//
//    @Override
//    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
//        log.error("connection closed: " + closeStatus);
//        users.remove(getClientId(session));
//    }
//
//    @Override
//    public boolean supportsPartialMessages() {
//        return false;
//    }
//
//    /**
//     * 发送信息给指定用户
//     * @param clientId
//     * @param message
//     * @return
//     */
//    public boolean sendMessageToUser(String clientId, TextMessage message) {
//        if (users.get(clientId) == null) { return false; }
//
//        WebSocketSession session = users.get(clientId);
//        if (!session.isOpen()) { return false; }
//
//        log.info("sendMessage:" + message);
//        try {
//            session.sendMessage(message);
//        } catch (IOException e) {
//            log.error("error", e);
//            return false;
//        }
//        return true;
//    }
//
//    /**
//     * 广播信息
//     * @param message
//     * @return
//     */
//    public boolean sendMessageToAllUsers(TextMessage message) {
//        boolean allSendSuccess = true;
//        Set<String> clientIds = users.keySet();
//        WebSocketSession session = null;
//        for (String clientId : clientIds) {
//            try {
//                session = users.get(clientId);
//                if (session.isOpen()) {
//                    session.sendMessage(message);
//                }
//            } catch (IOException e) {
//                log.error("e", e);
//                allSendSuccess = false;
//            }
//        }
//        return allSendSuccess;
//    }
//
//    /**
//     * 获取用户标识
//     * @param session
//     * @return
//     */
//    private String getClientId(WebSocketSession session) {
//        try {
//            String clientId = (String) session.getAttributes().get("WEBSOCKET_USERID");
//            return clientId;
//        } catch (Exception e) {
//            log.error("e", e);
//            return null;
//        }
//    }
//    /**
//     * 获取在线人数
//     * @return
//     */
//    public synchronized int getOnlineNum(){
//        return users.size();
//    }
//}
