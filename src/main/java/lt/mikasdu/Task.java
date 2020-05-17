package lt.mikasdu;

import lt.mikasdu.ui.database.Database;
import lt.mikasdu.ui.database.SqlConnection;
import lt.mikasdu.ui.database.SqlStatement;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Task implements Database {
    private int id;
    private String taskDescription;
    private int userId;
    private String status;

    public Task() {
        setId(0);
    }
    private void setId(int id) {
        this.id = id;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public int getUserId() {
        return userId;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public void saveToDatabase() {
        SqlStatement sql = SqlStatement.TASK_ADD;
        SqlConnection.updateDatabase(sql, getTaskDescription(), getUserId(), getStatus());
    }

    @Override
    public void updateDatabase() {
        SqlStatement sql = SqlStatement.TASK_UPDATE;
        SqlConnection.updateDatabase(sql, getTaskDescription(), getUserId(), getStatus(), getId());
    }

    @Override
    public void setParam(ResultSet resultSet) throws SQLException {
        setId(resultSet.getInt("id"));
        setTaskDescription(resultSet.getString("description"));
        setUserId(resultSet.getInt("userId"));
        setStatus(resultSet.getString("status"));
    }

    @Override
    public String getObjectSqlStatement() {
        return SqlStatement.TASK_BY_ID.getStatement();
    }
}
