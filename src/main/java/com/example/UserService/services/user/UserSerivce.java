package com.example.UserService.services.user;

import com.example.UserService.domain.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserSerivce extends UserDetailsService {
    User Create(User user);

    User GetByEmail(String phoneNumber);
    User GetCurrentUser();
//    User Create(User user);
//    void DeleteCurrentUser();
//    void Update(UpdateRequest updateRequest) throws IOException;
    UserDetailsService userDetailsService();
}
