package com.example.UserService.services.user;

import com.example.UserService.domain.dto.NewUserRequest;
import com.example.UserService.services.minio.MinioService;
import com.example.UserService.domain.model.User;
import com.example.UserService.repositories.UserRepo;
import com.example.UserService.services.password.PasswordGenerator;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserSerivce {

    private final UserRepo userRepo;

    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public User GetByNumber(String phoneNumber) {
        return userRepo.findByPhoneNumber(phoneNumber).
                orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Override
    public long NewUser(NewUserRequest request) {
        User user = User.builder()
                .email(request.getEmail())
                .password(PasswordGenerator.generatePasswordFromEmail(request.getEmail()))
                .build();

        return userRepo.save(user).getId();
    }

    public UserDetailsService userDetailsService() {
        return this::GetByNumber;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
