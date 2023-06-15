package com.z7.collabowriteapi.realTime;

import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {


    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/document/edit/id").withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/topic", "/documentId");
        registry.setApplicationDestinationPrefixes("/app");
    }
}
