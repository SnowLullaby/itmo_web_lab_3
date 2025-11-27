package ru.web;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class PointModel {
    private Long id;
    private Double x;
    private Double y;
    private Double z;
    private Double r;
    private boolean hit;
    private String currentTime;
    private long executionTimeNs;
}
