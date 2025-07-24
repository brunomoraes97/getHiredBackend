package dev.gethired.backend.dtos;
import lombok.Data;

@Data
public class ResumeAndJobDescriptionDTO {
    private String resume;
    private String job_description;
    private String language;
}
