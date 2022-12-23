import DBConnection.DBConnection;
import FileClassifiction.Custom;
import FileClassifiction.SortByFileSize;
import FileClassifiction.SortByFileType;
import FileRepositroy.*;
import SystemUsble.DeleteOperation;
import SystemUsble.Operation;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.File;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.SQLException;
public class Main {
    public static void main(String[] args) throws SQLException, NoSuchPaddingException, IllegalBlockSizeException, IOException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        Connection connection = DBConnection.getInstance().getConnection();
        File f=new File("C:\\Users\\Msys\\Desktop\\Adv.pdf");
        FileAPIs importf=new ImportFile();
     //importf.executeJob(f.getName(),f.getPath());
        FileAPIs read=new ReadFile();
        //read.executeJob(f.getName(),f.getPath());
       FileAPIs fileAPIs=new DeleteFile();
      //SortByFileSize.SortByFileSize();
    //fileAPIs.executeJob(f.getName(),f.getPath());
      //  ImportFileInformationToMySQL.importFile(f.getName(),f.getPath());
        FileAPIs Export=new ExportFile();
      //  Custom.SortByCategory("fileName");
     //Export.executeJob(f.getName(),f.getPath());
        System.out.println("Hello world!");
        Operation operation=new DeleteOperation();
       operation.execute(f);
    }
}