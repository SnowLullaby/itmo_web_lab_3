package ru.web;

import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named("hitBean")
@ViewScoped
public class HitBean implements Serializable {
    @Setter @Getter
    private Double x = null;

    @Getter @Setter
    private Double y = null;

    private List<String> zValues = new ArrayList<>();

    @Setter @Getter
    private Double r = 3.0;

    @Setter @Getter
    private Double graphX = null;

    @Getter @Setter
    private Double graphY = null;

    @Setter @Getter
    private Double graphZ = null;

    @Inject
    private PointList pointList;

    public void submit() {
        if (graphZ != null && graphX != null && graphY != null) {
            long start = System.nanoTime();
            boolean hit = checkHit(graphX, graphY, graphZ, r);
            Point point = new Point(graphX, graphY, graphZ, r, hit, System.nanoTime() - start);
            pointList.addPoint(point);
            graphZ = null;
        } else {
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
    }

    private boolean checkHit(double x, double y, double z, double r) {
        boolean inSphere = (x <= 0 && y >= 0 && (x * x + y * y + z * z <= r / 2 * r / 2));
        boolean inBox = (x >= -r && x <= 0 && y >= -r / 2 && y <= 0 && z >= -r / 2 && z <= r / 2);
        boolean inCone = false;
        if (x >= 0 && x <= r) {
            double radiusAtX = (r - x) / r * (r / 2);
            inCone = (y * y + z * z <= radiusAtX * radiusAtX);
        }
        return inSphere || inBox || inCone;
    }

    public List<String> getZValues() { return zValues; }
    public void setZValues(List<String> zValues) { this.zValues = zValues; }

}