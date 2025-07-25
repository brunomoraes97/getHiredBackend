package dev.gethired.backend.dtos;

import lombok.Data;

@Data
public class UserDTO {
    // Identifier
    private Long id;

    // Basic data
    private String firstName;
    private String middleName;
    private String lastName;
    private String emailAddress;
    private String phoneNumber;
    private String website;
    private String githubUsername;
    private String linkedinUsername;

    // Credentials data
    private String username;
    private String password;

}
;