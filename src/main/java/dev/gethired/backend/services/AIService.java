package dev.gethired.backend.services;

import org.springframework.stereotype.Service;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.FileCopyUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.genai.Client;
import com.google.genai.types.GenerateContentResponse;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Service
public class AIService {

    private final Client client;

    public AIService() {
        this.client = new Client();
        ObjectMapper objectMapper = new ObjectMapper();
    }

    public GenerateContentResponse promptAI(String prompt) {
        return this.client.models.generateContent(
                "gemini-1.5-flash-8b",
                prompt,
                null
        );
    }

    public String readSystemPrompt(String filePath) throws IOException {
        ClassPathResource resource = new ClassPathResource(filePath);
        byte[] bytes = FileCopyUtils.copyToByteArray(resource.getInputStream());
        return new String(bytes, StandardCharsets.UTF_8);
    }
}