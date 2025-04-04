package com.pechito.pechitobackend.repository;

import com.pechito.pechitobackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    public User findUserByEmail(String email);
    public Optional<User> findUserByUsername(String username);
}
