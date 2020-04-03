package com.pfa.microserviceusers.repository;


import com.pfa.microserviceusers.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsernameIgnoreCase(String username);
    User findByEmailIgnoreCase(String email);
    User findByUsernameOrEmailIgnoreCase(String username,String email);
    Optional<User> findById(Long id);
    @Query("select u.photo.encoded_string from User u where u.id = ?1")
    String getEncodStringOfImage(Long id);
}