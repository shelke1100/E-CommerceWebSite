package com.example.SpringBootCRUD.Entity;

import jakarta.persistence.*;
import org.apache.catalina.User;
import org.springframework.boot.autoconfigure.security.SecurityProperties;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "tokens")
public class AuthenticationToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    private String token;

    @Column(name = "created_date")
    private Date createdDate;

    @OneToOne(targetEntity = UserEntity.class, fetch=FetchType.EAGER)
    @JoinColumn(nullable=false, name = "user_id")
    private UserEntity user;

    public AuthenticationToken() {
    }

    public AuthenticationToken(Integer id, String token, Date createdDate, UserEntity user) {
        this.id = id;
        this.token = token;
        this.createdDate = createdDate;
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public AuthenticationToken(UserEntity user)
    {
        this.user=user;
        this.createdDate=new Date();
        this.token= UUID.randomUUID().toString();
    }
}
