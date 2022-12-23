package SystemUsble;

import java.io.File;
import java.sql.SQLException;

public interface Operation {
    void execute(File file) throws SQLException;

}
