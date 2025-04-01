package com.example.casino.controller;

import com.example.casino.model.ChatMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.messaging.handler.annotation.Payload;

@Controller
@Slf4j
public class PokerRoomController {

    @MessageMapping("/room/{roomId}")
    @SendTo("/room/{roomId}")
    public ChatMessage sendMessage(@Payload ChatMessage message) {
        System.out.println("Message: " + message.getContent());
        return message;

    }
}

