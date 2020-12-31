package org.dubbo.websocket.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @author _lizy
 * @version 1.0
 * @description 配置websocket并开启
 * @date 2020/11/26 20:20
 */
@Configuration
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    @Bean
    public ServerEndpointExporter serverEndpointExporter(){
        return new ServerEndpointExporter();
    }

//    @Override
//    public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {
//        webSocketHandlerRegistry
//                .addHandler(new MyWebSocketHandler(), "/websocket/{ID}")
//                .setAllowedOrigins("*")
//                .addInterceptors(new WebSocketInterceptor());
//    }


    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/websocket/{vmcno}").setAllowedOrigins("*");
    }


}
