package com.rubinho.fourth_lab.controller;

import com.rubinho.fourth_lab.model.User;
import com.rubinho.fourth_lab.repository.UserRepository;
import com.rubinho.fourth_lab.services.CheckAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/")
public class UserController {
    private final UserRepository userRepository;
    private final CheckAuthService authService;

    @Autowired
    public UserController(UserRepository userRepository, CheckAuthService authService) {
        this.userRepository = userRepository;
        this.authService = authService;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(value = "/authorize")
    public ResponseEntity<?> authorizeUser(@RequestParam Map<String, String> body) {

        User user = new User();
        user.setUsername(body.get("username"));
        user.setPassword(body.get("hashPassword"));


        authService.checkUserAndPassword(body.get("username"), user.getPassword());


        return ResponseEntity.ok(user);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(value = "/register")
    public ResponseEntity<?> registerUser(@RequestParam Map<String, String> body) {
        User user = new User();
        String username = body.get("username");

        user.setUsername(username);
        user.setPassword(body.get("hashPassword"));

        authService.checkReg(username);

        User savedUser = userRepository.save(user);
        return ResponseEntity.ok(savedUser);

    }
}
