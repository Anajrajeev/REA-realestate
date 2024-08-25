package realestate;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PropertyDAO {
    public void addProperty(Property property) {
        String sql = "INSERT INTO properties (location, price, bedrooms, bathrooms, square_feet, user_id) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, property.getLocation());
            pstmt.setFloat(2, property.getPrice());
            pstmt.setInt(3, property.getBedrooms());
            pstmt.setInt(4, property.getBathrooms());
            pstmt.setInt(5, property.getSquareFeet());
            pstmt.setInt(6, property.getUserId());
            pstmt.executeUpdate();
            System.out.println("Property added successfully.");
        } catch (SQLException e) {
            System.out.println("Error adding property: " + e.getMessage());
        }
    }

    public List<Property> searchProperties(String location, float minPrice, float maxPrice, int minBedrooms, int minBathrooms, int minSquareFeet) {
        List<Property> properties = new ArrayList<>();
        String sql = "SELECT * FROM properties WHERE location = ? AND price BETWEEN ? AND ? AND bedrooms >= ? AND bathrooms >= ? AND square_feet >= ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, location);
            pstmt.setFloat(2, minPrice);
            pstmt.setFloat(3, maxPrice);
            pstmt.setInt(4, minBedrooms);
            pstmt.setInt(5, minBathrooms);
            pstmt.setInt(6, minSquareFeet);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Property property = new Property();
                property.setId(rs.getInt("id"));
                property.setLocation(rs.getString("location"));
                property.setPrice(rs.getFloat("price"));
                property.setBedrooms(rs.getInt("bedrooms"));
                property.setBathrooms(rs.getInt("bathrooms"));
                property.setSquareFeet(rs.getInt("square_feet"));
                property.setUserId(rs.getInt("user_id"));
                properties.add(property);
            }
        } catch (SQLException e) {
            System.out.println("Error searching properties: " + e.getMessage());
        }
        return properties;
    }
}
