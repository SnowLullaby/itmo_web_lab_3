package ru.web;

import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import java.io.Serial;
import java.io.Serializable;

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

    public double getX() { return x; }
    public void setX(double x) { this.x = x; }

    public double getY() { return y; }
    public void setY(double y) { this.y = y; }

    public double getZ() { return z; }
    public void setZ(double z) { this.z = z; }

    public double getR() { return r; }
    public void setR(double r) { this.r = r; }

    public boolean isHit() { return hit; }
    public void setHit(boolean hit) { this.hit = hit; }

    public String getCurrentTime() { return currentTime; }
    public void setCurrentTime(String currentTime) { this.currentTime = currentTime; }

    public long getExecutionTimeNs() { return executionTimeNs; }
    public void setExecutionTimeNs(long executionTimeNs) { this.executionTimeNs = executionTimeNs; }
}