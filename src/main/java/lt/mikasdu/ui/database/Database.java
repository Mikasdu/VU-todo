package lt.mikasdu.ui.database;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface Database {
    void saveToDatabase();

    void updateDatabase();

    void setParam(ResultSet resultSet) throws SQLException;

    String getObjectSqlStatement();
}
