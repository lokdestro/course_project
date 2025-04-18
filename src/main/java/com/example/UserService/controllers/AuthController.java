package com.example.UserService.controllers;

import com.example.UserService.domain.dto.JwtAuthResponse;
import com.example.UserService.domain.dto.SignInRequest;
import com.example.UserService.domain.dto.SignUpRequest;
import com.example.UserService.services.auth.AuthService;
import com.example.UserService.services.auth.AuthenticationService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authenticationService;

    @PostMapping("/signup")
    public ResponseEntity<Long> signUp(@RequestBody SignUpRequest request, HttpServletResponse response) {
        System.out.println("SIGN UP");
        var userId = authenticationService.SignUp(request);

        ResponseCookie cookie = ResponseCookie.from("user_id", userId)
                .httpOnly(true)    // Защита от XSS
                .secure(true)      // Только для HTTPS
                .path("/")         // Доступно для всех путей
                .maxAge(7 * 24 * 60 * 60)  // Время жизни (7 дней)
                .sameSite("Strict") // Защита от CSRF
                .build();

        // Добавляем cookie в заголовки ответа
        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());

        return ResponseEntity.ok(1L);
    }

    @PostMapping("/signin")
    public ResponseEntity<JwtAuthResponse> signIn(@RequestBody SignInRequest request, HttpServletResponse response) {
        System.out.println("SIGN IN");
        JwtAuthResponse resp = authenticationService.signIn(request);
        ResponseCookie cookie = ResponseCookie.from("user_id", resp.getToken())
                .httpOnly(true)    // Защита от XSS
                .secure(true)      // Только для HTTPS
                .path("/")         // Доступно для всех путей
                .maxAge(7 * 24 * 60 * 60)  // Время жизни (7 дней)
                .sameSite("Strict") // Защита от CSRF
                .build();

        // Добавляем cookie в заголовки ответа
        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());
        return ResponseEntity.ok(resp);
    }
}
