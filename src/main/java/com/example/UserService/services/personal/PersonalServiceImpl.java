package com.example.UserService.services.personal;

import com.example.UserService.repositories.PersonalRepo;
import org.springframework.stereotype.Service;

@Service
public class PersonalServiceImpl implements PersonalService {
    private PersonalRepo personalRepo;

    public PersonalServiceImpl(PersonalRepo personalRepo) {
        this.personalRepo = personalRepo;
    }


}
