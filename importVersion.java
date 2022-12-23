import DBConnection.DBConnection;
import Encryption.Encryption;
import QueryDB.importQuery;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static FileRepositroy.ExportFile.closeConnection;
import static QueryDB.importQuery.getType;

public class importVersion {
    // Import the necessary classes and establish a connection to the database

    public static void main(String[] args) throws SQLException, FileNotFoundException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        // Connect to the database
        Connection conn = null;

            conn = DBConnection.getInstance().getConnection();
// Read the file
            File file = new File("C:\\Users\\Msys\\Desktop\\test.txt");
            InputStream is = new FileInputStream(file);
            byte[] ex = Encryption.encryptionFileName(file);

            // Check if the file already exists in the database
            String selectSql = "SELECT fileName ,fileVersion FROM fields WHERE fileName = ?";
            PreparedStatement selectStmt = conn.prepareStatement(selectSql);
            selectStmt.setString(1, file.getName());
            ResultSet rs = selectStmt.executeQuery();
        int version = 0;
            if (rs.next()) {
                // Retrieve the version number of the newest file and increase it by 1
                version = rs.getInt("fileVersion") + 1;
            }

      String query = "SELECT * FROM fields WHERE fileName = '" + file.getName() + "'";
        ResultSet resultSet = selectStmt.executeQuery(query);
        if (resultSet.next()) {
            int version1 = resultSet.getInt("fileVersion");
            String filename = file.getName();
            filename = file.getName() + "(" + version1 + 1 + ")";

            // Update the file name in the database to include the version number
            String updateSql = "UPDATE fields SET fileName = ? WHERE fileName = ? AND fileVersion= fileVersion+1";
            PreparedStatement updateStmt = conn.prepareStatement(updateSql);
            updateStmt.setString(2, file.getName());
            updateStmt.setString(1, file.getName() + "_" + version);

            updateStmt.executeUpdate();
            updateStmt.close();
            // if not found create the file

            importQuery.jobQuery(file);


        }

// Close the Prepared Statements and Connection

    }}


