package SystemUsble;


import DBConnection.DBConnection;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteOperation implements Operation {
    final static private  int ID_PARAMETER =1;
    @Override
    public void execute(File file) {
        String sql = "DELETE FROM fields WHERE fileName = ?";
        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(ID_PARAMETER, file.getName());
            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println(rowsAffected + " row(s) deleted");
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
    }
}