package com.example.UserService.services.user;

import com.example.UserService.domain.model.User;
import com.example.UserService.repositories.UserRepo;
import org.springframework.security.core.context.SecurityContextHolder;
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

    public User GetByEmail(String email) {
        return userRepo.findByEmail(email).
                orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Override
    public User Create(User user) {
        return userRepo.save(user);
    }

    public User GetCurrentUser() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof User) {
            String email = (String) ((User) authentication.getPrincipal()).getEmail();
            return GetByEmail(email);
        }
        throw new UsernameNotFoundException("Current user not found");
    }

    public UserDetailsService userDetailsService() {
        return this::GetByEmail;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
