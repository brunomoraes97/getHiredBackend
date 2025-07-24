package dev.gethired.backend.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GenerateCoverLetter {

    @PostMapping("/coverLetter")
    public String getCoverLetter() {
        return "Cover Letter";
    }
}
