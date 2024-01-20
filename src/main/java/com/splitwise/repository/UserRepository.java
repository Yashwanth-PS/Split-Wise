package com.splitwise.repository;

import com.splitwise.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository  extends JpaRepository<User, Long> {
    @Override
    Optional<User> save(User user);

    @Override
    Optional<User> findById(Long userId);
}