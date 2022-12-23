package FileRepositroy;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import DBConnection.DBConnection;
import java.sql.*;
import java.io.*;
public class ExportFile implements
        FileAPIs {

   public  ResultSet resultSet = null;

    @Override
    public void executeJob(String filename, String filePath) throws IOException, SQLException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        PreparedStatement preparedStatement = null;

        try {
            // Connect to the database
            Connection connection = DBConnection.getInstance().getConnection(); // Set the values for the select query
            String sql = "SELECT FileData FROM fields WHERE fileName = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, filename); // Replace 1 with the primary key or unique identifier for the row
            // Execute the select query
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                // Extract the file data from the ResultSet object
                InputStream inputStream = resultSet.getBinaryStream("FileData");
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                byte[] buffer = new byte[4096];
                int bytesRead = -1;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
                byte[] fileBytes = outputStream.toByteArray();
                inputStream.close();
                outputStream.close();
                FileOutputStream fileOutputStream = new FileOutputStream("C:\\Users\\Msys\\Desktop\\t.txt");
                // Write the file data to a file
                fileOutputStream.write(fileBytes);
                System.out.println(outputStream);
                fileOutputStream.close();
                System.out.println("File exported successfully");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close the connections
            closeConnection();
        }

    }
    public static void closeConnection(){
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        try {
            if (connection != null&&preparedStatement != null&&resultSet != null) {
                connection.close();
                preparedStatement.close();
                resultSet.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void readContent() throws SQLException, IOException {
        if (resultSet.next()) {
            // Extract the file data from the ResultSet object
            InputStream inputStream = resultSet.getBinaryStream("FileData");
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[4096];
            int bytesRead = -1;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            byte[] fileBytes = outputStream.toByteArray();
            inputStream.close();
            outputStream.close();
    }
}}
