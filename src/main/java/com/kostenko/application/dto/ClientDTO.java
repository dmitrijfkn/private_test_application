package com.kostenko.application.dto;

import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Getter
@Setter
public class ClientDTO {
    @NotNull(message = "The name must not be null")
    @Size(min = 3, max = 64, message = "The name length must be between 3 and 64 characters")
    private String name;

    @NotNull(message = "The email must not be null")
    @Email(message = "This field should be an email")
    @Size(min = 3, max = 255, message = "The email length must be between 3 and 255 characters")
    private String email;

    @NotNull(message = "The password must not be null")
    @Size(min = 8, max = 255, message = "The password length must be between 8 and 255 characters")
    private String password;
}