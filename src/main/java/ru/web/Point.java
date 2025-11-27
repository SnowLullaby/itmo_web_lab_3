package ru.web;

import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Setter
@Getter
@Named("pointBean")
@ViewScoped
public class Point implements Serializable {
    private double x;
    private double y;
    private double z;
    private double r;
    private boolean hit;
    private String currentTime;
    private long executionTimeNs;

    @Serial
    private static final long serialVersionUID = 1L;

    public Point() {}

    public Point(double x, double y, double z, double r, boolean hit, long executionTimeNs) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.r = r;
        this.hit = hit;
        this.executionTimeNs = executionTimeNs;
        this.currentTime = java.time.LocalDateTime.now()
                .format(java.time.format.DateTimeFormatter.ofPattern("dd.MM.yy HH:mm:ss"));
    }

}