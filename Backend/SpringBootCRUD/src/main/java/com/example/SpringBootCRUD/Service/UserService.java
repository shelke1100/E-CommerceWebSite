
package com.example.SpringBootCRUD.Service;

//package com.example.SpringBootCRUD.Service;

import com.example.SpringBootCRUD.Entity.AuthenticationToken;
import com.example.SpringBootCRUD.Entity.UserEntity;
import com.example.SpringBootCRUD.dto.ResponseDto;
import com.example.SpringBootCRUD.dto.user.SigninResponseDto;
import com.example.SpringBootCRUD.dto.user.SignupDto;
import com.example.SpringBootCRUD.exceptions.AuthenticationFailException;
import com.example.SpringBootCRUD.exceptions.CustomException;
import com.example.SpringBootCRUD.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.DatatypeConverter;
import jakarta.transaction.Transactional;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthenticatonService authenticationService;

    @Transactional
    public ResponseDto signUp(SignupDto signupDto) {
        if (Objects.nonNull(userRepository.findByEmail(signupDto.getEmail()))) {
            throw new CustomException("User already present");
        }
        String encryptedPassword = signupDto.getPassword();
        try {
            encryptedPassword = hashPassword(signupDto.getPassword());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        UserEntity user = new UserEntity(signupDto.getFirstname(), signupDto.getLastname(), signupDto.getEmail(), encryptedPassword);
        userRepository.save(user);

        final AuthenticationToken authenticationToken = new AuthenticationToken(user);
        authenticationService.saveConfirmationToken(authenticationToken);

        ResponseDto responseDto = new ResponseDto("success", "User created successfully");
        return responseDto;
    }

    private String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256"); // or use "MD5"
        md.update(password.getBytes());
        byte[] digest = md.digest();
        return DatatypeConverter.printHexBinary(digest).toUpperCase();
    }

    public SigninResponseDto signIn(SignupDto signupDto) {
        UserEntity user = userRepository.findByEmail(signupDto.getEmail());
        if (Objects.isNull(user)) {
            throw new AuthenticationFailException("User is not valid");
        }
        try {
            if (!user.getPassword().equals(hashPassword(signupDto.getPassword()))) {
                throw new AuthenticationFailException("Wrong password");
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        AuthenticationToken token = authenticationService.getToken(user);
        if (Objects.isNull(token)) {
            throw new CustomException("Token is not present");
        }
        return new SigninResponseDto("success", token.getToken());
    }
}

//import com.example.SpringBootCRUD.Entity.AuthenticationToken;
//import com.example.SpringBootCRUD.Entity.UserEntity;
//import com.example.SpringBootCRUD.dto.ResponseDto;
//import com.example.SpringBootCRUD.dto.user.SigninResponseDto;
//import com.example.SpringBootCRUD.dto.user.SignupDto;
//import com.example.SpringBootCRUD.exceptions.AuthenticationFailException;
//import com.example.SpringBootCRUD.exceptions.CustomException;
//import com.example.SpringBootCRUD.repository.UserRepository;
//import jakarta.transaction.Transactional;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.jackson.JacksonProperties;
//import org.springframework.boot.autoconfigure.security.SecurityProperties;
//import org.springframework.stereotype.Service;
//
//import javax.xml.bind.DatatypeConverter;
//import java.security.MessageDigest;
//import java.security.NoSuchAlgorithmException;
//import java.util.Locale;
//import java.util.Objects;
//
//@Service
//public class UserService
//{
//    @Autowired
//    UserRepository userRepository;
//
//    @Autowired
//    AuthenticatonService authenticatonService;
//    @Transactional
//    public ResponseDto signUp(SignupDto signupDto)
//    {
//        if (Objects.nonNull(userRepository.findByEmail(signupDto.getEmail())))
//        {
//            throw new CustomException("User already present");
//        }
//        String encryptedpassword = signupDto.getPassword();
//        try
//        {
//            encryptedpassword=hashPassword(signupDto.getPassword());
//
//        }
//        catch(NoSuchAlgorithmException e)
//        {
//            e.printStackTrace();
//        }
//        UserEntity users=new UserEntity(signupDto.getFirstname(),signupDto.getLastname(), signupDto.getEmail(),signupDto.getPassword());
//        userRepository.save(users);
//
//
//         final AuthenticationToken authenticationToken = new AuthenticationToken(users);
//         authenticatonService.saveConfirmationToken(authenticationToken);
//
//
////        UserEntity users=new UserEntity(signupDto.getFirstname(), signupDto.getLastname(), signupDto.getEmail(),signupDto.getPassword());
////        userRepository.save(users);
//
//        ResponseDto responseDto = new ResponseDto("success","User created successfully");
//        return responseDto;
//
//    }
//
//    private String hashPassword(String password) throws NoSuchAlgorithmException {
//
//        MessageDigest md=MessageDigest.getInstance("MSD");
//        md.update(password.getBytes());
//        byte[] digest=md.digest();
//        String hash= DatatypeConverter.printHexBinary(digest).toUpperCase();
//        return hash;
//
//
//    }
//
//    public SigninResponseDto signIn(SignupDto signupDto) {
//        UserEntity user = userRepository.findByEmail(signupDto.getEmail());
//        if (Objects.isNull(user)) {
//
//            throw new AuthenticationFailException("user is not valid");
//        }
//        try {
//            if (!user.getPassword().equals(hashPassword(signupDto.getPassword()))) {
//                throw new AuthenticationFailException("wrong Password");
//
//            }
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        }
//
//        AuthenticationToken token= authenticatonService.getToken(user);
//        if (Objects.isNull(token))
//        {
//            throw new CustomException("token is not present");
//
//        }
//        return  new SigninResponseDto("success",token.getToken());
//
//
//
//    }
//}