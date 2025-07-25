package dev.gethired.backend.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class TexCodeService {

    private final AIService aiService;
    private final ObjectMapper objectMapper;

    @Autowired
    public TexCodeService(AIService aiService) {
        this.objectMapper = new ObjectMapper();
        this.aiService = aiService;
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
            String systemPrompt = aiService.readSystemPrompt("system_prompt_tex_code.txt");

            // 2. Create a Map to store all parameters
            Map<String, String> promptData = new HashMap<>();
            promptData.put("system_prompt", systemPrompt);
            promptData.put("resume", resume);
            promptData.put("job_description", jobDescription);
            promptData.put("language", language);

            // 3. Convert to JSON string
            String fullPrompt = objectMapper.writeValueAsString(promptData);

            // 4. Generate the response
            String texCode = aiService.promptAI(fullPrompt).text();

            return removeLatexDelimiters(texCode);

        } catch (IOException e) {
            throw new RuntimeException("Failed to process the request", e);
        }
    }


}
