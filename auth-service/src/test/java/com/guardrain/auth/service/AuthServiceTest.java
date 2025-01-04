package com.guardrain.auth.service;

import com.guardrain.auth.domain.User;
import com.guardrain.auth.dto.request.SignUpRequest;
import com.guardrain.auth.exception.UserAlreadyExistsException;
import com.guardrain.auth.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

    @InjectMocks
    private AuthService authService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Test
    @DisplayName("회원가입 성공 테스트")
    void signUp_Success() {
        // Given
        SignUpRequest request = new SignUpRequest(
                "jsc",
                "password123!",
                "jsc@kr.com",
                "Test User"
        );

        given(userRepository.existsByUsername(request.getUsername())).willReturn(false);
        given(userRepository.existsByEmail(request.getEmail())).willReturn(false);
        given(passwordEncoder.encode(request.getPassword())).willReturn("encodedPassword");
        given(userRepository.save(any(User.class))).willAnswer(invocation -> invocation.getArgument(0));

        // When
        User savedUser = authService.signUp(request);  // 실제 서비스 메소드 호출

        // Then
        assertThat(savedUser).isNotNull();
        assertThat(savedUser.getUsername()).isEqualTo(request.getUsername());
        assertThat(savedUser.getEmail()).isEqualTo(request.getEmail());
    }

    @Test
    @DisplayName("중복된 사용자명으로 회원가입 시 실패")
    void signUp_DuplicateUsername_ThrowsException() {
        // Given
        SignUpRequest request = new SignUpRequest(
                "existinguser",
                "password123!",
                "new@example.com",
                "Test User"
        );

        given(userRepository.existsByUsername(request.getUsername())).willReturn(true);

        // When & Then
        assertThatThrownBy(() -> authService.signUp(request))
                .isInstanceOf(UserAlreadyExistsException.class)
                .hasMessageContaining("이미 존재하는 사용자명입니다");
    }

    @Test
    @DisplayName("중복된 이메일로 회원가입 시 실패")
    void signUp_DuplicateEmail_ThrowsException() {
        // Given
        SignUpRequest request = new SignUpRequest(
                "newuser",
                "password123!",
                "existing@example.com",
                "Test User"
        );

        given(userRepository.existsByUsername(request.getUsername())).willReturn(false);
        given(userRepository.existsByEmail(request.getEmail())).willReturn(true);

        // When & Then
        assertThatThrownBy(() -> authService.signUp(request))
                .isInstanceOf(UserAlreadyExistsException.class)
                .hasMessageContaining("이미 존재하는 이메일입니다");
    }
}