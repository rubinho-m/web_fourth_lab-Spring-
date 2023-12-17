package com.rubinho.fourth_lab.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "points")
public class Point {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "x")
    private float x;


    @Column(name = "y")
    private float y;

    @Column(name = "r")
    private float R;

    @Column(name = "currenttime")
    private String currentTime;

    @Column(name = "executiontime")
    private String executionTime;

    @Column(name = "ishit")
    private boolean isHit;

    @Column(name = "username")
    private String username;
}
