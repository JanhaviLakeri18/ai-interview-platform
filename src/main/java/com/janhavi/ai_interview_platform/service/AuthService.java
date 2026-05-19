package com.janhavi.ai_interview_platform.service;

import com.janhavi.ai_interview_platform.dto.LoginRequest;
import com.janhavi.ai_interview_platform.dto.RegisterRequest;
import com.janhavi.ai_interview_platform.entity.User;
import com.janhavi.ai_interview_platform.repository.UserRepository;
import com.janhavi.ai_interview_platform.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public String register(RegisterRequest request) {

        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(
                        passwordEncoder.encode(
                                request.getPassword()
                        )
                )
                .build();

        userRepository.save(user);

        return "User Registered Successfully";
    }

    public String login(LoginRequest request) {

        User user = userRepository.findByEmail(
                request.getEmail()
        ).orElseThrow();

        if (!passwordEncoder.matches(
                request.getPassword(),
                user.getPassword()
        )) {
            throw new RuntimeException("Invalid Password");
        }

        return jwtService.generateToken(user.getEmail());
    }
}