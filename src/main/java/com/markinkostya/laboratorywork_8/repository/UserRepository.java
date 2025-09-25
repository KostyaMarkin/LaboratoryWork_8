package com.markinkostya.laboratorywork_8.repository;

import com.markinkostya.laboratorywork_8.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmail(String email);
}
