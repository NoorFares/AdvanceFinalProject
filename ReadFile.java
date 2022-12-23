package FileRepositroy;



import DBConnection.DBConnection;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static DBConnection.DBConnection.connection;
import static FileRepositroy.ExportFile.closeConnection;

public class ReadFile implements  FileAPIs{
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    InputStream inputStream = null;
    final static private  int ID_PARAMETER =1;
    @Override
    public void executeJob(String filename, String filePath) throws IOException, SQLException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        ;
        Connection connection = DBConnection.getInstance().getConnection();
        try {
         ResultSet resultSet= Query(filename);
            if (resultSet.next()) {
                inputStream = resultSet.getBinaryStream("fileData");
                System.out.println("File "+filename+" allowed Read");
            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }
    public ResultSet Query(String filename) throws SQLException {
        preparedStatement = connection.prepareStatement("SELECT fileData  FROM fields WHERE fileName = ?");
        preparedStatement.setString(ID_PARAMETER,filename );
        resultSet = preparedStatement.executeQuery();
        return resultSet;
    }
}