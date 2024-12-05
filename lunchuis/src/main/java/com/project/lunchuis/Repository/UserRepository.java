package com.project.lunchuis.Repository;

import java.util.Optional;

import com.project.lunchuis.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByCodeAndPassword(String code, String password);
}
