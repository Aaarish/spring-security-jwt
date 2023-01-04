package com.example.jwtdatabase.auth;

public interface AuthService {
    AuthResponse registerUser(RegisterRequest registerRequest);
    AuthResponse authenticateUser(AuthRequest authRequest);
}
