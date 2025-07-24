package dev.gethired.backend.services;

import org.springframework.stereotype.Service;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.FileCopyUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.genai.Client;
import com.google.genai.types.GenerateContentResponse;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Service
public class AI {
    private final Client client;
    private static ObjectMapper objectMapper = new ObjectMapper();

    public AI() {
        this.client = new Client();
        objectMapper = new ObjectMapper();
    }

    private String removeLatexDelimiters(String text) {
        if (text == null || text.isEmpty()) {
            return text;
        }

        // Removes first ```latex
        text = text.replaceFirst("^```latex", "");
        // Removes last ```
        text = text.replaceFirst("```$", "");

        return text.trim();
    }


    public String generateTexCode(String resume, String jobDescription, String language) {
        try {
            // 1. Read system prompt from resources
            String systemPrompt = readSystemPrompt();

            // 2. Create a Map to store all parameters
            Map<String, String> promptData = new HashMap<>();
            promptData.put("system_prompt", systemPrompt);
            promptData.put("resume", resume);
            promptData.put("job_description", jobDescription);
            promptData.put("language", language);

            // 3. Convert to JSON string
            String jsonPrompt = objectMapper.writeValueAsString(promptData);

            // 4. Generate the response
            GenerateContentResponse response = this.client.models.generateContent(
                    "gemini-1.5-flash-8b",
                    jsonPrompt,
                    null
            );

            String responseText = response.text();

            return removeLatexDelimiters(responseText);

        } catch (IOException e) {
            throw new RuntimeException("Failed to process the request", e);
        }
    }

    private static String readSystemPrompt() throws IOException {
        ClassPathResource resource = new ClassPathResource("system_prompt.txt");
        byte[] bytes = FileCopyUtils.copyToByteArray(resource.getInputStream());
        return new String(bytes, StandardCharsets.UTF_8);
    }
}