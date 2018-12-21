package com.john.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import com.john.websocket.ThromboseWebSocketHandler;

@Configuration
@EnableWebSocket
@ComponentScan("com.john.websocket")
public class WebSocketConfig implements WebSocketConfigurer {

   @Autowired
   private ThromboseWebSocketHandler myWebSocketHandler;

   @Override
   public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
      registry.addHandler(myWebSocketHandler, "/socketHandler");
   }

}