package com.swapiffy.swapiffybe.websocket;

import com.swapiffy.swapiffybe.dto.ChatMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class ChatController {

    private final SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/chat")
    public void processMessage(@Payload ChatMessage chatMessage) {
        System.out.println(chatMessage);
        String userDestination = "/user/2"  + "/queue/private";
        messagingTemplate.convertAndSend(userDestination,chatMessage);
    }
    @MessageMapping("/app/receiveMessage")
    public void receiveMessage(@Payload ChatMessage chatMessage) {
        String senderUserDestination = "/user/" + "2" + "/queue/private";
        messagingTemplate.convertAndSend(senderUserDestination, chatMessage);
    }

}