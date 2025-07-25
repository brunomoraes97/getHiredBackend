package dev.gethired.backend.controllers;

import dev.gethired.backend.services.TexCodeService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

import dev.gethired.backend.dtos.ResumeAndJobDescriptionDTO;

@RestController
public class GenerateTexCodeController {
    
    private final TexCodeService texCodeService;

    @Autowired
    public GenerateTexCodeController(TexCodeService texCodeService) {
        this.texCodeService = texCodeService;
    }

    @PostMapping("/texCode")
    public String getTexCode(@RequestBody ResumeAndJobDescriptionDTO requestBody) {
        String resume = requestBody.getResume();
        String jobDescription = requestBody.getJob_description();
        String language = requestBody.getLanguage();

        return texCodeService.generateTexCode(resume, jobDescription, language);
    }
}