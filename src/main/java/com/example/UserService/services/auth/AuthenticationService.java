package com.example.UserService.services.auth;

import com.example.UserService.domain.dto.JwtAuthResponse;
import com.example.UserService.domain.dto.SignInRequest;
import com.example.UserService.domain.dto.SignUpRequest;
import com.example.UserService.domain.model.User;
import com.example.UserService.services.Jwt.JwtService;
import com.example.UserService.services.Jwt.JwtServiceImpl;
import com.example.UserService.services.password.PasswordGenerator;
import com.example.UserService.services.user.UserSerivce;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService implements AuthService {
    private final UserSerivce userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public long SignUp(SignUpRequest request) {

        User user = User.builder()
                .email(request.getEmail())
                .password(request.getPassword())
                .build();

        return userService.Create(user).getId();
    }

    public JwtAuthResponse signIn(SignInRequest request) {
        System.out.println(request.getPassword());
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getEmail(),
                request.getPassword()
        ));
        var user = userService.GetByEmail(request.getEmail());
        var jwt = jwtService.generateToken(user);
        return new JwtAuthResponse(jwt);
    }


}
