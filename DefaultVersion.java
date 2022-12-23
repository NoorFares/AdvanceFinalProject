import AdapterDesignPattern.com.Encryptable;
import AdapterDesignPattern.com.FileNameEncryptor;
import AdapterDesignPattern.com.FileNameEncryptorAdapter;
import DBConnection.DBConnection;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*;

import static FileRepositroy.ExportFile.closeConnection;
import static QueryDB.importQuery.getType;
public class DefaultVersion {

    public static void main(String[] args) throws SQLException, FileNotFoundException {
        Connection conn = null;
        File f=new File("C:\\Users\\Msys\\Desktop\\noor.txt");
        conn = DBConnection.getInstance().getConnection();
        Encryptable encryptable = new FileNameEncryptorAdapter(new FileNameEncryptor());
        String encryptedFileName = encryptable.encrypt(f.getName());
        // Read the current version number of the file
        String query = "SELECT fileVersion FROM fields WHERE fileName = ?";
        int version = 0;
        try {
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, f.getName()); // Replace with the id of the file you want to read
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                version = rs.getInt(1) + 1;
            }
                // Insert the new file into the database

                String sql = "INSERT INTO fields ( fileName, filePath, fileType,fileSize, fileVersion, fileData,encryptedFile) VALUES ( ?, ?, ?, ?, ?, ?,?)";
// Insert the encrypted filename, file information, and file data into the database
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, f.getName());
                pstmt.setString(2, f.getPath());
                pstmt.setString(3, getType(f));
                pstmt.setLong(4, f.length());
                pstmt.setInt(5, version);
                pstmt.setBinaryStream(6, new FileInputStream(f));
                pstmt.setString(7, encryptedFileName);
                pstmt.executeUpdate();
            } finally {
            closeConnection();

        }

    }

}


