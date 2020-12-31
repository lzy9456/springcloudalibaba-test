package org.dubbo.websocket.service;

/**
 * @author _lizy
 * @version 1.0
 * @description TODO
 * @date 2020/11/27 1:14
 */
public interface IWebSocketPushMsg {
    Boolean pushMsg(String msg);
    Boolean pushMsg(Long userId, String msg);
}
