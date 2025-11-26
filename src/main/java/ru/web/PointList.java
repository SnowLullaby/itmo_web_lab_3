package ru.web;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Named("pointList")
@ApplicationScoped
public class PointList implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private final List<Point> points = new ArrayList<>();

    public synchronized void addPoint(Point point) {
        points.add(0, point);
    }

    public synchronized List<Point> getPoints() {
        return Collections.unmodifiableList(points);
    }

    public synchronized void clear() {
        points.clear();
    }

}