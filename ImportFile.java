package FileRepositroy;
import QueryDB.importQuery;

import javax.crypto.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
public class ImportFile implements FileAPIs{
    public File file=null;;
    public Connection connection;
    PreparedStatement pstmt = null;
    @Override
    public void executeJob(String filename, String filePath) throws IOException, SQLException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
            file = new File(filePath);
            readFile(filePath);
          importQuery.jobQuery(file);

    }
    private static byte[] readFile(String filePath) {
        try (FileInputStream fis = new FileInputStream(filePath)) {
            byte[] data = new byte[fis.available()];
            fis.read(data);
            return data;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
