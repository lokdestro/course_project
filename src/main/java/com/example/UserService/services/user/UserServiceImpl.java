package com.example.UserService.services.user;

import com.example.UserService.domain.dto.AddPhotoRequest;
import com.example.UserService.domain.model.Photo;
import com.example.UserService.domain.model.User;
import com.example.UserService.repositories.PhotoRepo;
import com.example.UserService.repositories.UserRepo;
import com.example.UserService.services.minio.MinioService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class UserServiceImpl implements UserSerivce {

    private final UserRepo userRepo;
    private final PhotoRepo photoRepo;
    private final MinioService minioService;

    public UserServiceImpl(UserRepo userRepo, PhotoRepo photoRepo, MinioService minioService) {
        this.userRepo = userRepo;
        this.photoRepo = photoRepo;
        this.minioService = minioService;
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

    private void AddPhoto (AddPhotoRequest request) throws IOException {
        User user = GetCurrentUser();

        String path =minioService.uploadFile(
                "photo",
                request.getPhoto().getOriginalFilename(),
                request.getPhoto().getInputStream(),
                request.getPhoto().getSize(),
                request.getPhoto().getContentType());

        Photo photo = Photo.builder()
                .userId(user.getId())
                .name(request.getPhoto().getName())
                .photo(path)
                .build();

        photoRepo.save(photo);
    }

    public UserDetailsService userDetailsService() {
        return this::GetByEmail;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
