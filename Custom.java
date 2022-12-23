package FileClassifiction;

import DBConnection.DBConnection;

import java.sql.*;

public class Custom {
    public static Connection connection;
    // Connect to the MySQL server
    public static void  SortByCategory(String str) {
        try {
            connection = DBConnection.getInstance().getConnection();
            String sql = "SELECT * FROM fields ORDER BY ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, str);  // sets the value for the first placeholder
            ResultSet rs = stmt.executeQuery();
            // Create the SELECT statement with the ORDER BY clause
            // Iterate over the result set and print the file types
            while (rs.next()) {
                String fileType = rs.getString(str);
                System.out.println(fileType);
            }
            // Close the connection
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}

