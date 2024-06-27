package com.example.SpringBootCRUD.repository;

import com.example.SpringBootCRUD.Entity.AuthenticationToken;
import com.example.SpringBootCRUD.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends JpaRepository<AuthenticationToken, Integer> {
    AuthenticationToken findByUser(UserEntity user);
}
