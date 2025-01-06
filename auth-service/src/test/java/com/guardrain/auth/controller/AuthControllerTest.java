package com.guardrain.auth.controller;

import com.guardrain.auth.domain.User;
import com.guardrain.auth.dto.request.SignUpRequest;
import com.guardrain.auth.service.AuthService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class AuthControllerTest {

    @Mock
    private AuthService userService;

    @InjectMocks
    private AuthController authController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(authController)
                .build();
    }

    @Test
    @DisplayName("회원가입 API 테스트")
    void signUp() throws Exception {
        SignUpRequest request = new SignUpRequest("testuser", "password123!", "test@example.com", "Test User");

        User mockUser = new User();
        when(userService.signUp(any(SignUpRequest.class))).thenReturn(mockUser);

        mockMvc.perform(post("/api/auth/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\":\"testuser\",\"password\":\"password123!\",\"email\":\"test@example.com\",\"name\":\"Test User\"}"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("중복된 이메일로 회원가입 실패")
    void signUpFailWithDuplicateEmail() throws Exception {
        SignUpRequest request = new SignUpRequest("testuser", "password123!", "test@example.com", "Test User");

        when(userService.signUp(any(SignUpRequest.class)))
                .thenThrow(new DuplicateEmailException("Email already exists"));

        mockMvc.perform(post("/api/auth/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isConflict());
    }

    @Test
    @DisplayName("유효하지 않은 비밀번호로 회원가입 실패")
    void signUpFailWithInvalidPassword() throws Exception {
        SignUpRequest request = new SignUpRequest("testuser", "123", "test@example.com", "Test User");

        mockMvc.perform(post("/api/auth/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    /*
    @Test
    @DisplayName("회원가입 성공시 JWT 토큰 반환")
    void signUpWithToken() throws Exception {
        SignUpRequest request = new SignUpRequest("testuser", "password123!", "test@example.com", "Test User");
        SignUpResponse response = new SignUpResponse("access.token.here", "refresh.token.here");

        when(userService.signUp(any(SignUpRequest.class))).thenReturn(response);

        mockMvc.perform(post("/api/auth/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.accessToken").exists())
                .andExpect(jsonPath("$.refreshToken").exists());
    }

    @Test
    @DisplayName("로그인 성공시 JWT 토큰 반환")
    void loginSuccess() throws Exception {
        // given
        LoginRequest request = new LoginRequest("test@email.com", "password123!");
        LoginResponse response = new LoginResponse("access.token.here", "refresh.token.here");

        when(authService.login(any(LoginRequest.class))).thenReturn(response);

        // when & then
        mockMvc.perform(post("/api/v1/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.accessToken").exists())
                .andExpect(jsonPath("$.refreshToken").exists());
    }

    @Test
    @DisplayName("잘못된 로그인 정보로 로그인 실패")
    void loginFail() throws Exception {
        // given
        LoginRequest request = new LoginRequest("wrong@email.com", "wrongpass");
        when(authService.login(any(LoginRequest.class)))
                .thenThrow(new AuthenticationException("Invalid credentials"));

        // when & then
        mockMvc.perform(post("/api/v1/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isUnauthorized());
    }*/
}