package com.rubinho.fourth_lab;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Checker {

    private List<Float> values = new ArrayList<>(Arrays.asList(
            -3.0f, -2.0f, -1.0f, 0.0f, 1.0f, 2.0f, 3.0f, 4.0f, 5.0f
    ));

    public boolean isValid(float x, float y, float R) {
        return (R > 0);
    }

    public boolean isHit(float x, float y, float R) {
        return isInCircle(x, y, R) || isInRect(x, y, R) || isInTriangle(x, y, R);
    }

    public boolean isInCircle(float x, float y, float R) {
        return x <= 0 && y >= 0 && x * x + y * y <= R * R;
    }

    public boolean isInRect(float x, float y, float R) {
        return x >= 0 && y >= 0 && x <= R && y <= R / 2;
    }

    public boolean isInTriangle(float x, float y, float R) {
        return x <= 0 && y <= 0 && y >= -x / 2 - R / 2;
    }
}

