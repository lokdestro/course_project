package com.example.UserService.services.personal;

import com.example.UserService.domain.dto.UpdateRequest;
import com.example.UserService.domain.model.Personal;
import com.example.UserService.repositories.PersonalRepo;
import com.example.UserService.services.user.UserSerivce;
import org.springframework.stereotype.Service;

@Service
public class PersonalServiceImpl implements PersonalService {
    private final PersonalRepo personalRepo;
    private final UserSerivce userSerivce;

    public PersonalServiceImpl(PersonalRepo personalRepo, UserSerivce userSerivce) {
        this.personalRepo = personalRepo;
        this.userSerivce = userSerivce;
    }

    public void Add(UpdateRequest request) {
        Personal personal = Personal.builder()
                .userId(userSerivce.GetCurrentUser().getId())
                .name(request.getName())
                .value(request.getValue())
                .build();

        personalRepo.save(personal);
    }


}
