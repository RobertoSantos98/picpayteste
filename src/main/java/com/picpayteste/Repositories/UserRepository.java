package com.picpayteste.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.picpayteste.Domain.User.User;

public interface UserRepository extends JpaRepository<User, Long> {
    
    Optional<User> findUserByDocument(String document);

    Optional<User> findUserById(Long id);

}
