package lt.mikasdu;

import lt.mikasdu.ui.database.Database;
import lt.mikasdu.ui.database.SqlStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class User implements Database {
    private int id;
    private String name;
    private String password;
    private int level; // 1 admin, 2 user

    public User(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public int getLevel() {
        return level;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public void saveToDatabase() {

    }

    @Override
    public void updateDatabase() {

    }

    @Override
    public void setParam(ResultSet resultSet) throws SQLException {
        setId(resultSet.getInt("id"));
        setName(resultSet.getString("name"));
        setPassword(resultSet.getString("password"));
        setLevel(resultSet.getInt("level"));
    }

    @Override
    public String getObjectSqlStatement() {
        return SqlStatement.USER_SINGLE.getStatement();
    }
}
