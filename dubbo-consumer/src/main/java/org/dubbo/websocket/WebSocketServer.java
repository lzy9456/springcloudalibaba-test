package org.dubbo.websocket;

import com.alibaba.fastjson.JSON;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.common.utils.StringUtils;
import com.example.utils.OptionalC;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author _lizy
 * @version 1.0
 * @description websocket ServerEndpoint
 * @date 2020/11/26 21:26
 */
@Slf4j
@Component
@ServerEndpoint("/websocket/{vmcno}") // 客户端URI访问的路径
public class WebSocketServer {

    /**
     * 本地缓存所有连接的userid，sessionid
     * 本地缓存所有连接的sessionid，session
     */
    private static final Map<String, Set<String>> USERID_SESSIONIDS_MAP = new ConcurrentHashMap<>(); //在线用户sessionId缓存(key,value) ==> (userid,sessionId set)
    private static final Map<String, Session> SESSIONID_SESSION_MAP = new ConcurrentHashMap<>(); //在线用户session缓存(key,value) ==>(sessionId，session)
    public static final String FAILED = "failed";
    public static final String OK = "ok";

    /**
     * 保存所有连接的webSocket实体
     */
    private static CopyOnWriteArrayList<WebSocketServer> sWebSocketServers = new CopyOnWriteArrayList<>();
    private Session mSession;   // 与客户端连接的会话，用于发送数据
    private String mVmcNo;      // 客户端的标识(优先传userid，然后机器编号)
    private AtomicInteger count = new AtomicInteger(); // TODO 测试使用，分布式使用第三方缓存统计

    @OnOpen
    public void onOpen(Session session, @PathParam("vmcno") String vmcno){
        if(StringUtils.isBlank(vmcno)){ return; }

        mSession = session;
        log.info("-->onOpen new connect vmcno is "+vmcno);
        mVmcNo = vmcno;
        count.incrementAndGet();

        cache(vmcno, session);
    }




    @OnClose
    public void onClose(){
        sWebSocketServers.remove(this);
        log.info("-->onClose a connect");
        count.decrementAndGet();
    }

    @OnMessage
    public void onMessage(String message, Session session){
        if(StringUtils.isBlank(message)){ return; }

        log.info("-->onMessage "+message);
        Msg msg = convert2Msg(message);
        if(msg ==null) { return; }

        // 这里选择的是让其他客户端都知道消息，类似于转发的聊天室，可根据使用场景使用
        for (WebSocketServer socketServer : sWebSocketServers){
            socketServer.sendMessage("i have rcv you message! "+message);
        }
    }



    @OnError
    public void onError(Session session, Throwable error) {
        log.error("onError {}", error);
        try {
            session.close();
        } catch (IOException e) {
            log.error("close session failed {}", e.getMessage());
        }
    }



    /**
     * 对外发送消息
     * @param message
     */
    public boolean sendMessage(String message){
        if(StringUtils.isBlank(message)){ return false; }

        try {
            mSession.getBasicRemote().sendText(message);
        } catch (IOException e) {
            log.info(e.toString());
            return false;
        }
        return true;
    }

    /**
     * 对某个机器发送消息
     * @param message
     * @param vmcno 机器编号
     * @return true,返回发送的消息,false，返回failed字符串
     */
    public String sendMessage(String vmcno, String message){
        if(StringUtils.isBlank(vmcno)){ return StringUtils.EMPTY_STRING; }

        boolean success = false;
        for (WebSocketServer server : sWebSocketServers){
            if (server.mVmcNo.equals(vmcno)){
                success = server.sendMessage(message);
                break;
            }
        }
        return success ? message : "failed";
    }

    /**
     * 发送消息
     * @param message
     * @param session
     * @return true,返回发送的消息,false，返回failed字符串
     */
    public String sendMessage(String message, Session session){
        if(StringUtils.isBlank(message) || session==null){ return StringUtils.EMPTY_STRING; }

        boolean success = false;
        try {
            Future<Void> sendFuture = session.getAsyncRemote().sendText(message);
            sendFuture.get();
            success = true;
        } catch (Exception e) {
            log.error("",e);
        }
        return success ? OK : FAILED;
    }

    /**
     * 对某用户发送消息
     * @param message
     * @param userId 机器编号
     * @return true,返回发送的消息,false，返回failed字符串
     */
    public String sendMessage2User(String userId, String message){
        if(StringUtils.isBlank(userId)){ return StringUtils.EMPTY_STRING; }

        boolean success = false;
//        for (WebSocketServer server : sWebSocketServers){
//            if (server.mVmcNo.equals(userId)){
//                success = server.sendMessage(message);
//                break;
//            }
//        }


        Set<String> sessionIds = USERID_SESSIONIDS_MAP.get(userId);
        sessionIds.forEach(sessionId -> {
            if(StringUtils.isBlank(sessionId)){ return; }

            Session session = SESSIONID_SESSION_MAP.get(sessionId);
            sendMessage(message, session);
        });


        return success ? message : FAILED;
    }





    private void cache(String vmcno, Session session) {
        sWebSocketServers.add(this); // 将回话保存
        String sessionId = session.getId();


        // OptionalC.ofNullable()设置包装对象，
        // orElse 如果ofNullable包装对象USERID_SESSIONIDS_MAP.get(vmcno)为空，设置新对象，
        // ifPresent()如果value存在，执行操作,
        // get()返回值
        USERID_SESSIONIDS_MAP.put(vmcno, OptionalC.ofNullable(USERID_SESSIONIDS_MAP.get(vmcno))
                                            .orElse(new HashSet<>())
                                            .ifPresent(s -> s.add(sessionId))
                                            .get() );

        SESSIONID_SESSION_MAP.put(sessionId, session);
    }
    private Msg convert2Msg(String msgJsonStr) {
        try {
            return JSON.parseObject(msgJsonStr, Msg.class);
        } catch (Exception e) {
            log.error("-->convert2Msg ",e);
        }
        return null;
    }
}

@Data
class Msg{
    private String id;
    private String msg;
}



