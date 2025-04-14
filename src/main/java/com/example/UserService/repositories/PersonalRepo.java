package com.example.UserService.repositories;

import com.example.UserService.domain.model.Personal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonalRepo extends JpaRepository<Personal, Long> {
}
