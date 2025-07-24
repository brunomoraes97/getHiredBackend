package dev.gethired.backend.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

import dev.gethired.backend.dtos.ResumeAndJobDescriptionDTO;
import dev.gethired.backend.services.AI;

@RestController
public class GenerateTexCode {
    
    private final AI aiService;

    @Autowired
    public GenerateTexCode(AI aiService) {
        this.aiService = aiService;
    }

    @PostMapping("/texCode")
    public String getTexCode(@RequestBody ResumeAndJobDescriptionDTO requestBody) {
        String resume = requestBody.getResume();
        String jobDescription = requestBody.getJob_description();
        String language = requestBody.getLanguage();

        return aiService.generateTexCode(resume, jobDescription, language);
    }
}