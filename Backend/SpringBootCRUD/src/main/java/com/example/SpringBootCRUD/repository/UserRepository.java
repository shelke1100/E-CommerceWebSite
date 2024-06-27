package com.example.SpringBootCRUD.repository;

import com.example.SpringBootCRUD.Entity.UserEntity;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer > {
    UserEntity findByEmail(String email);
}
