package QueryDB;

import AdapterDesignPattern.com.Encryptable;
import AdapterDesignPattern.com.FileNameEncryptor;
import AdapterDesignPattern.com.FileNameEncryptorAdapter;
import DBConnection.DBConnection;
import Encryption.Encryption;

import javax.crypto.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static FileRepositroy.ExportFile.closeConnection;

public class importQuery {

    public static void jobQuery(File file) throws SQLException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException, FileNotFoundException {
        PreparedStatement pstmt = null;
        Connection connection = null;
        Encryptable encryptable = new FileNameEncryptorAdapter(new FileNameEncryptor());
        String encryptedFileName = encryptable.encrypt(file.getName());

        try {
            connection = DBConnection.getInstance().getConnection();
            byte[] ex = Encryption.encryptionFileName(file);
            String sql = "INSERT INTO fields ( fileName, filePath, fileType,fileSize, fileVersion, fileData,encryptedFile) VALUES ( ?, ?, ?, ?, ?, ?,?)";
// Insert the encrypted filename, file information, and file data into the database
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, file.getName());
            pstmt.setString(2, file.getPath());
            pstmt.setString(3, getType(file));
            pstmt.setLong(4, file.length());
            pstmt.setInt(5, 0);
            pstmt.setBinaryStream(6, new FileInputStream(file));

            pstmt.setString(7, encryptedFileName);
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Step 5: Close the resources

                // Close the connections
                closeConnection();
            }}

    public static String getType(File file){
        String extension = "";
        int i = file.getName().lastIndexOf('.');
        if (i > 0)
            extension = file.getName().substring(i+1);
        return extension;
    }

}
