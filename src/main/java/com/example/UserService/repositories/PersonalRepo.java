package com.example.UserService.repositories;

import com.example.UserService.domain.model.Personal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface PersonalRepo extends JpaRepository<Personal, Long> {

    @Query("SELECT p FROM Personal p " +
            "WHERE p.name = :name " +
            "AND p.userId = :id " +
            "AND p.createAt = (SELECT MAX(p2.createAt) FROM Personal p2 " +
            "WHERE p2.name = :name AND p2.id = :id AND p2.createAt < :targetDate)")
    Optional<Personal> findByNameIdAndMaxCreateAtBeforeDate(
            @Param("name") String name,
            @Param("id") Long id,
            @Param("targetDate") LocalDateTime targetDate);

    Optional<Personal> findFirstByUserIdAndNameOrderByCreateAtDesc(Long userId, String name);
}
