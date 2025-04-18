package com.example.UserService.controllers;

import com.example.UserService.domain.dto.JwtAuthResponse;
import com.example.UserService.domain.dto.SignInRequest;
import com.example.UserService.domain.dto.SignUpRequest;
import com.example.UserService.services.auth.AuthService;
import com.example.UserService.services.auth.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authenticationService;

    @PostMapping("/signup")
    public ResponseEntity<Long> signUp(@RequestBody SignUpRequest request) {
        long response = authenticationService.SignUp(request);
//        ResponseCookie cookie = ResponseCookie.from("theme", response) // Используем переданное значение
//                .httpOnly(true)
//                .secure(true)
//                .path("/")
//                .maxAge(3600)
//                .sameSite("Strict")
//                .build();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/signin")
    public ResponseEntity<JwtAuthResponse> signIn(@RequestBody SignInRequest request) {
        JwtAuthResponse response = authenticationService.signIn(request);
        return ResponseEntity.ok(response);
    }
}
