package ru.web;

public class PointMapper {
    private PointMapper() {}

    public static PointModel toModel(Point point) {
        PointModel model = new PointModel();
        model.setX(point.getX());
        model.setY(point.getY());
        model.setZ(point.getZ());
        model.setR(point.getR());
        model.setHit(point.isHit());
        model.setCurrentTime(point.getCurrentTime());
        model.setExecutionTimeNs(point.getExecutionTimeNs());
        return model;
    }

    public static Point toEntity(PointModel model) {
        Point point = new Point();
        point.setX(model.getX());
        point.setY(model.getY());
        point.setZ(model.getZ());
        point.setR(model.getR());
        point.setHit(model.isHit());
        point.setCurrentTime(model.getCurrentTime());
        point.setExecutionTimeNs(model.getExecutionTimeNs());
        return point;
    }
}
