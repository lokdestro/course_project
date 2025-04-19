package com.example.UserService.controllers;

import com.example.UserService.domain.dto.GetPersonalRequest;
import com.example.UserService.domain.dto.GetPersonalResponse;
import com.example.UserService.domain.dto.GetResponse;
import com.example.UserService.domain.dto.UpdateRequest;
import com.example.UserService.services.personal.PersonalService;
import com.example.UserService.services.user.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/personal")
public class PersonalController {

    private final PersonalService personalService;

    @Autowired
    public PersonalController(UserServiceImpl userService, PersonalService personalService) {
        this.personalService = personalService;
    }

    @GetMapping("/get")
    public ResponseEntity<GetPersonalResponse> get(@RequestBody GetPersonalRequest request) {
        try {
            var data = personalService.Get(request);
            return ResponseEntity.ok(data);
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/update")
    public ResponseEntity<String> update(@RequestBody UpdateRequest request){
        try {
            personalService.Add(request);
            return ResponseEntity.ok("Success");
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
//
//    @DeleteMapping("/delete")
//    public ResponseEntity<String> delete(@RequestBody DeleteRequest deleteUserProfileRequest) {
//        userService.DeleteCurrentUser();
//        return ResponseEntity.ok("Профиль успешно удален");
//    }
}
