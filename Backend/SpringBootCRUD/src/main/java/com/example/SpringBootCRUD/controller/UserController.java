package com.example.SpringBootCRUD.controller;

import com.example.SpringBootCRUD.Service.UserService;
import com.example.SpringBootCRUD.dto.ResponseDto;
import com.example.SpringBootCRUD.dto.user.SigninResponseDto;
import com.example.SpringBootCRUD.dto.user.SignupDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseDto signUp(@RequestBody SignupDto signupDto) {
        return userService.signUp(signupDto);
    }

    @PostMapping("/signin")
    public SigninResponseDto signIn(@RequestBody SignupDto signupDto) {
        return userService.signIn(signupDto);
    }
}
