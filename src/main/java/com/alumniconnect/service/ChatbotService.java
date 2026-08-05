package com.alumniconnect.service;

import com.alumniconnect.dto.ChatRequest;
import com.alumniconnect.dto.ChatResponse;

public interface ChatbotService {

    ChatResponse chat(ChatRequest request);

}