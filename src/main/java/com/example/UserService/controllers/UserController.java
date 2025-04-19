package com.example.UserService.controllers;

import com.example.UserService.domain.dto.GetResponse;
import com.example.UserService.services.user.UserSerivce;
import com.example.UserService.services.user.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserSerivce userService;

    @Autowired
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping("/get")
    public ResponseEntity<GetResponse> get() {
        var user = userService.GetCurrentUser();
        var userResp = GetResponse.builder()
                .email(user.getEmail())
                .build();

        return ResponseEntity.ok(userResp);
    }

//    @PostMapping("/update")
//    public ResponseEntity<String> update(
//            @ModelAttribute UpdateRequest updateRequest,
//            @RequestParam("photo") MultipartFile photo) throws IOException {
//        updateRequest.setPhoto(photo);
//        userService.Update(updateRequest);
//        return ResponseEntity.ok("Профиль успешно обновлен");
//    }
//
//    @DeleteMapping("/delete")
//    public ResponseEntity<String> delete(@RequestBody DeleteRequest deleteUserProfileRequest) {
//        userService.DeleteCurrentUser();
//        return ResponseEntity.ok("Профиль успешно удален");
//    }
}
