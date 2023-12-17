package com.rubinho.fourth_lab.services;

import com.rubinho.fourth_lab.exceptions.TokenUnauthorizedException;
import com.rubinho.fourth_lab.model.User;
import com.rubinho.fourth_lab.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CheckAuthService {
    private final UserRepository userRepository;

    @Autowired
    public CheckAuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void checkUser(String username){
        User existingUser = userRepository.findByUsername(username);
        if (existingUser == null) {
            throw new TokenUnauthorizedException("No access");
        }
    }

    public void checkReg(String username){
        User existingUser = userRepository.findByUsername(username);
        if (existingUser != null) {
            throw new TokenUnauthorizedException("No access");
        }
    }

    public void checkUserAndPassword(String username, String password){
        User existingUser = userRepository.findByUsername(username);
        if (existingUser == null) {
            throw new TokenUnauthorizedException("No access");
        }

        if (!existingUser.getPassword().equals(password)) {
            throw new TokenUnauthorizedException("Invalid password failed");
        }
    }
}
