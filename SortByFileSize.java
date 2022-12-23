package FileClassifiction;
import DBConnection.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class SortByFileSize {
    public static Connection connection;
    // Connect to the MySQL server
    public static void  SortByFileSize() {
        try {
             connection = DBConnection.getInstance().getConnection();
            // Create the SELECT statement with the ORDER BY clause
            String sql = "SELECT * FROM fields ORDER BY fileSize";
            // Create a statement and execute the query
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            // Iterate over the result set and print the file types
            while (resultSet.next()) {
                String fileType = resultSet.getString("fileSize");
                System.out.println(fileType);
            }
            // Close the connection
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
}}
