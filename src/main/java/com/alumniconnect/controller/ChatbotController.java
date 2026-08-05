package com.alumniconnect.controller;

import com.alumniconnect.dto.ChatRequest;
import com.alumniconnect.dto.ChatResponse;
import com.alumniconnect.service.ChatbotService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/chat")
@RequiredArgsConstructor
public class ChatbotController {

    private final ChatbotService chatbotService;

    @PostMapping
    public ChatResponse chat(@RequestBody ChatRequest request) {

        return chatbotService.chat(request);

    }

}