import DBConnection.DBConnection;
import Encryption.Encryption;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.io.*;

import static QueryDB.importQuery.getType;

public class insertFile {
    public static void main(String[] args) {
        // Connect to the database
        Connection conn = null;
        try {
             conn = DBConnection.getInstance().getConnection();

            // Read the file
            File file = new File("C:\\Users\\Msys\\Desktop\\noor.txt");
            InputStream is = new FileInputStream(file);
            byte[] ex = Encryption.encryptionFileName(file);

            // Check if the file already exists in the table
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM fields WHERE fileName = ?");
            pstmt.setString(1, file.getName());
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                // File exists, update it
                pstmt = conn.prepareStatement("UPDATE fields SET fileData = ?, fileVersion = fileVersion + 1 WHERE fileName = ?");
                pstmt.setBinaryStream(1, is);
                pstmt.setString(2, file.getName());
                pstmt.executeUpdate();
            } else {
                // File does not exist, insert it
                String sql ="INSERT INTO fields( fileName, filePath, fileType,fileSize, fileVersion, fileData,encryptedFile) VALUES (?, ?, ?, ?, 1, ?, ?)";
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, file.getName());
                pstmt.setString(2, file.getPath());
                pstmt.setString(3, getType(file));
                pstmt.setLong(4, file.length());
                pstmt.setBinaryStream(5, is);
                pstmt.setString(6, ex.toString().toString());
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (IOException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException |
                 IllegalBlockSizeException | BadPaddingException e) {
            System.out.println(e.getMessage());
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }
}

