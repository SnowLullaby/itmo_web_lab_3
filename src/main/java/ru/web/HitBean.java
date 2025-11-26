package ru.web;

import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named("hitBean")
@ViewScoped
public class HitBean implements Serializable {

    private Double x = null;
    private Double y = null;
    private List<String> zValues = new ArrayList<>();
    private Double r = null;

    @Inject
    private PointList pointList;

    public void submit() {
        if (zValues.isEmpty() || x == null || y == null || r == null) {
            return;
        }

        long start = System.nanoTime();
        for (String zStr : zValues) {
            double z = Double.parseDouble(zStr);
            boolean hit = checkHit(x, y, z, r);
            Point point = new Point(x, y, z, r, hit, System.nanoTime() - start);
            pointList.addPoint(point);
        }
        zValues.clear();
    }

    private boolean checkHit(double x, double y, double z, double r) {
        // логика проверки
        return true;
    }

    public Double getX() { return x; }
    public void setX(Double x) { this.x = x; }

    public Double getY() { return y; }
    public void setY(Double y) { this.y = y; }

    public List<String> getZValues() { return zValues; }
    public void setZValues(List<String> zValues) { this.zValues = zValues; }

    public Double getR() { return r; }
    public void setR(Double r) { this.r = r; }
}