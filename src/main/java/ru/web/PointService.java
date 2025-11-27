package ru.web;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@ApplicationScoped
public class PointService {
    @Inject
    private DatabaseConnector dbConnector;

    public PointModel save(PointModel point) throws SQLException {
        String query = "INSERT INTO points (x, y, z, r, hit, \"current_time\", execution_time_ns) VALUES (?, ?, ?, ?, ?, ?, ?) RETURNING id";
        try{
            Connection connection = dbConnector.getConnection();
            var preparedStatement = connection.prepareStatement(query);
            preparedStatement.setDouble(1, point.getX());
            preparedStatement.setDouble(2, point.getY());
            preparedStatement.setDouble(3, point.getZ());
            preparedStatement.setDouble(4, point.getR());
            preparedStatement.setBoolean(5, point.isHit());
            preparedStatement.setString(6, point.getCurrentTime());
            preparedStatement.setLong(7, point.getExecutionTimeNs());
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                point.setId(rs.getLong(1));
            } else {
                throw new SQLException("Insert succeeded but no id returned");
            }
            return point;
        } catch (Exception e) {
            throw new SQLException("Error saving point: " + e.getMessage(), e);
        }
    }

    public List<PointModel> getAll(){
        String query = "SELECT id, x, y, z, r, hit, \"current_time\", execution_time_ns FROM points";
        try{
            Connection connection = dbConnector.getConnection();
            var preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<PointModel> points = new java.util.ArrayList<>();
            while (resultSet.next()) {
                PointModel point = new PointModel();
                point.setId(resultSet.getLong("id"));
                point.setX(resultSet.getDouble("x"));
                point.setY(resultSet.getDouble("y"));
                point.setZ(resultSet.getDouble("z"));
                point.setR(resultSet.getDouble("r"));
                point.setHit(resultSet.getBoolean("hit"));
                point.setCurrentTime(resultSet.getString("current_time"));
                point.setExecutionTimeNs(resultSet.getLong("execution_time_ns"));
                points.add(0, point);
            }
            return points;
        } catch (Exception e) {
            System.err.println("Error retrieving points: " + e.getMessage());
            e.printStackTrace();
            return java.util.Collections.emptyList();
        }
    }

    public void deleteAll() {
        String query = "TRUNCATE TABLE points";
        try{
            Connection connection = dbConnector.getConnection();
            var preparedStatement = connection.prepareStatement(query);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.err.println("Error deleting points: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
