package com.rubinho.fourth_lab.services;

import com.rubinho.fourth_lab.Checker;
import com.rubinho.fourth_lab.exceptions.InvalidDataException;
import com.rubinho.fourth_lab.model.Point;
import com.rubinho.fourth_lab.repository.PointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class SetupUserService {
    private final PointRepository pointRepository;

    private final Checker checker = new Checker();

    @Autowired
    public SetupUserService(PointRepository pointRepository) {
        this.pointRepository = pointRepository;
    }


    public Point setUpPoint(Point point, String username) {
        long startTime = System.nanoTime();
        point.setUsername(username);

        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDateTime = currentDateTime.format(formatter);

        point.setHit(checker.isHit(point.getX(), point.getY(), point.getR()));

        point.setCurrentTime(formattedDateTime);

        String msTime = String.format("%.6f", ((System.nanoTime() - startTime) / 1_000_000.0)).replace(',', '.');
        point.setExecutionTime(msTime);

        return point;
    }

    public Point setUpPoint(String username, float x, float y, float R) {
        Point point = new Point();

        point.setUsername(username);
        point.setX(x);
        point.setY(y);
        point.setR(R);


        long startTime = System.nanoTime();


        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDateTime = currentDateTime.format(formatter);

        point.setHit(checker.isHit(point.getX(), point.getY(), point.getR()));

        point.setCurrentTime(formattedDateTime);

        String msTime = String.format("%.6f", ((System.nanoTime() - startTime) / 1_000_000.0)).replace(',', '.');
        point.setExecutionTime(msTime);

        return point;
    }

    public void checkValid(float x, float y, float R){
        if (!checker.isValid(x, y, R)) {
            throw new InvalidDataException("Invalid Data");
        }
    }
}
