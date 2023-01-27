package com.example.jwtdatabase.auth;

import com.example.jwtdatabase.jwt.JwtService;
import com.example.jwtdatabase.user.User;
import com.example.jwtdatabase.user.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.example.jwtdatabase.user.Role.ROLE_USER;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepo userRepo;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    @Override
    public AuthResponse registerUser(RegisterRequest registerRequest) {
        User user = User.builder()
                .firstname(registerRequest.getFirstname())
                .lastname(registerRequest.getLastname())
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .role(ROLE_USER)
                .build();

        User registeredUser = userRepo.save(user);

        String jwt = jwtService.generateToken(registeredUser);

        return AuthResponse.builder()
                .jwt(jwt)
                .build();
    }

    @Override
    public AuthResponse authenticateUser(AuthRequest authRequest) {
        User user = userRepo.findByEmail(authRequest.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        Authentication authToken = new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword());
        Authentication authenticated = authenticationManager.authenticate(authToken);

        String jwt = null;

        if(authenticated != null){
            jwt = jwtService.generateToken(user);
        }

        return AuthResponse.builder()
                .jwt(jwt)
                .build();
    }
}
