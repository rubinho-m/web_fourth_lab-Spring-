package com.rubinho.fourth_lab.controller;

import com.rubinho.fourth_lab.Checker;
import com.rubinho.fourth_lab.exceptions.TokenUnauthorizedException;
import com.rubinho.fourth_lab.model.Point;
import com.rubinho.fourth_lab.model.User;
import com.rubinho.fourth_lab.repository.PointRepository;
import com.rubinho.fourth_lab.repository.UserRepository;
import com.rubinho.fourth_lab.services.CheckAuthService;
import com.rubinho.fourth_lab.services.SetupUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/")
public class PointController {
    private final PointRepository pointRepository;

    private final CheckAuthService authService;

    private final SetupUserService setupService;

    @Autowired
    public PointController(PointRepository pointRepository, CheckAuthService authService, SetupUserService setupService) {
        this.pointRepository = pointRepository;
        this.authService = authService;
        this.setupService = setupService;
    }

    private final Checker checker = new Checker();

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/points")
    public List<Point> getAllPoints(@RequestHeader("username") String username) {
        return pointRepository.findByUsername(username);
    }
//    @RequestParam Map<String, String> body
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(value = "/points")
    public ResponseEntity<?> createPoint(Point point, @RequestHeader("username") String username) {
        authService.checkUser(username);

//        setupService.checkValid(Float.parseFloat(body.get("x")), Float.parseFloat(body.get("y")), Float.parseFloat(body.get("R")));
//
        setupService.setUpPoint(point, username);


        pointRepository.save(point);

        return new ResponseEntity<>("added successfully", HttpStatus.CREATED);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("/points")
    public ResponseEntity<?> clearAllShots(@RequestHeader("username") String username) {
        authService.checkUser(username);

        List<Point> points = pointRepository.findByUsername(username);
        for (Point point : points) {
            pointRepository.deleteById(point.getId());
        }

        return new ResponseEntity<>("deleted successfully", HttpStatus.NO_CONTENT);
    }
}
