package com.example.SpringBootCRUD.Service;

import com.example.SpringBootCRUD.Entity.AuthenticationToken;
import com.example.SpringBootCRUD.Entity.UserEntity;
import com.example.SpringBootCRUD.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticatonService {

    @Autowired
     TokenRepository tokenRepository;

    public void saveConfirmationToken(AuthenticationToken authenticationToken) {
        tokenRepository.save(authenticationToken);

    }

    public AuthenticationToken getToken(UserEntity user) { // Corrected parameter type
        return tokenRepository.findByUser(user);
    }
}



//
//<?xml version="1.0" encoding="UTF-8"?>
//<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
//xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
//	<modelVersion>4.0.0</modelVersion>
//	<parent>
//		<groupId>org.springframework.boot</groupId>
//		<artifactId>spring-boot-starter-parent</artifactId>
//		<version>3.2.4</version>
//		<relativePath/> <!-- lookup parent from repository -->
//	</parent>
//	<groupId>com.example</groupId>
//	<artifactId>SpringBootCRUD</artifactId>
//	<version>0.0.1-SNAPSHOT</version>
//	<name>SpringBootCRUD</name>
//<description>Demo project for Spring Boot</description>
//	<properties>
//		<java.version>17</java.version>
//	</properties>
//	<dependencies>
//		<dependency>
//			<groupId>org.springframework.boot</groupId>
//			<artifactId>spring-boot-starter-data-jpa</artifactId>
//		</dependency>
//		<dependency>
//			<groupId>org.springframework.boot</groupId>
//			<artifactId>spring-boot-starter-web</artifactId>
//		</dependency>
//		<dependency>
//			<groupId>org.springframework.boot</groupId>
//			<artifactId>spring-boot-devtools</artifactId>
//			<scope>runtime</scope>
//			<optional>true</optional>
//		</dependency>
//		<dependency>
//			<groupId>com.mysql</groupId>
//			<artifactId>mysql-connector-j</artifactId>
//			<scope>runtime</scope>
//		</dependency>
//		<dependency>
//			<groupId>org.projectlombok</groupId>
//			<artifactId>lombok</artifactId>
//			<optional>true</optional>
//		</dependency>
//		<dependency>
//			<groupId>org.springframework.boot</groupId>
//			<artifactId>spring-boot-starter-test</artifactId>
//			<scope>test</scope>
//		</dependency>
//	</dependencies>
//
//	<build>
//		<plugins>
//			<plugin>
//				<groupId>org.springframework.boot</groupId>
//				<artifactId>spring-boot-maven-plugin</artifactId>
//				<configuration>
//					<excludes>
//						<exclude>
//							<groupId>org.projectlombok</groupId>
//							<artifactId>lombok</artifactId>
//						</exclude>
//					</excludes>
//				</configuration>
//			</plugin>
//		</plugins>
//	</build>
//</project>
