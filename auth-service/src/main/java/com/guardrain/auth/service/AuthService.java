package com.guardrain.auth.service;

import com.guardrain.auth.domain.User;
import com.guardrain.auth.dto.request.LoginRequest;
import com.guardrain.auth.dto.request.SignUpRequest;
import com.guardrain.auth.exception.AuthException;
import com.guardrain.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public User signUp(SignUpRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new AuthException("이미 존재하는 사용자명입니다", HttpStatus.CONFLICT);
        }

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new AuthException("이미 존재하는 이메일입니다", HttpStatus.CONFLICT);
        }

        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .email(request.getEmail())
                .name(request.getName())
                .build();

        return userRepository.save(user);
    }

    public User login(LoginRequest request) {
        //TODO

        return null;
    }
}