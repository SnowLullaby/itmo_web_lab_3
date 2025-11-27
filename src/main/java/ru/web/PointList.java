package ru.web;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serial;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static ru.web.PointMapper.toModel;

@Named("pointList")
@ApplicationScoped
public class PointList implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    @Inject
    private PointService pointService;

    private final List<PointModel> points = new ArrayList<>();

    @PostConstruct
    public void init() {
        try {
            points.addAll(pointService.getAll());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public synchronized void addPoint(Point point) {
        try {
            PointModel savedPoint = pointService.save(toModel(point));
            points.add(0, savedPoint);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public synchronized List<PointModel> getPoints() {
        return Collections.unmodifiableList(points);
    }

    public synchronized void clear() {
        pointService.deleteAll();
        points.clear();
    }

}