package com.guardrain.auth.controller;

import com.guardrain.auth.domain.User;
import com.guardrain.auth.dto.request.SignUpRequest;
import com.guardrain.auth.dto.response.UserResponse;
import com.guardrain.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<UserResponse> signUp(@RequestBody SignUpRequest request) {
        User user = authService.signUp(request);
        return ResponseEntity.ok(UserResponse.from(user));
    }

}