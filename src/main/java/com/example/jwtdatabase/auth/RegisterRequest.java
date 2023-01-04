package com.example.jwtdatabase.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class RegisterRequest {
    private String firstname;
    private String lastname;
    private String email;
    private String password;
}
