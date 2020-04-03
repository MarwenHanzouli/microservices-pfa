package com.pfa.microserviceusers.service;


import com.pfa.microserviceusers.models.*;
import com.pfa.microserviceusers.models.embedded.Photo;
import com.pfa.microserviceusers.models.enumuration.RoleName;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

public interface UserService {
    public User saveUser(User user);
    public User addPhotoToUser(User user, Photo photo);
    public User updateUser(User user);
    public String encodedStringOfImage(Long id);
    public User findByUsername(String username);
    public User findByEmail(String email);
    public User findByUsernameOrEmail(String username,String email);
    public void addRoleToUser(String username, RoleName role);
    Optional<User> findById(Long id);
    Page<User> findAll(Pageable pageable);
    void deleteUser(Long id);
}