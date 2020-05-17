package lt.mikasdu.ui.database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lt.mikasdu.Task;

import java.sql.*;

public class SqlConnection {
    private static Connection getConnection() {
        try {
            Class.forName("org.sqlite.JDBC");
            return DriverManager.getConnection("jdbc:sqlite::resource:lt/mikasdu/DB/database.db");
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }


    public static Database getUserByName(String userName, Database dbObj) {
        try (Connection conn = getConnection()) {
            if (conn != null) {
                PreparedStatement pstmt = conn.prepareStatement(dbObj.getObjectSqlStatement());
                pstmt.setString(1, userName);
                ResultSet resultSet = pstmt.executeQuery();
                dbObj.setParam(resultSet);
                resultSet.close();
            } else {
                System.out.println("ALERT CONNECTION");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return dbObj;
    }

    public static Database getObjectById(int id, Database dbObj) {
        try (Connection conn = getConnection()) {
            if (conn != null) {
                PreparedStatement pstmt = conn.prepareStatement(dbObj.getObjectSqlStatement());
                pstmt.setInt(1, id);
                ResultSet resultSet = pstmt.executeQuery();
                dbObj.setParam(resultSet);
                resultSet.close();
            } else {
                System.out.println("CONNECTION");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return dbObj;
    }

    public static void updateDatabase(SqlStatement sqlStatement, Object... arg) {
        try (Connection conn = getConnection()) {
            if (conn != null) {
                PreparedStatement pstmt = conn.prepareStatement(sqlStatement.getStatement());
                for (int i = 0; i < arg.length; i++)
                    pstmt.setObject(i + 1, arg[i]);
                pstmt.executeUpdate();
            } else {
                System.out.println("Connection error");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }



    public static ObservableList<Task> getTasksByUserId(int userId, int userLevel) {
        ObservableList<Task> taskObservableList = FXCollections.observableArrayList();
        String sql = SqlStatement.TASK_BY_USER.getStatement();
        PreparedStatement pstmt;
        try (Connection conn = getConnection()) {
            if (conn != null) {

                if (userLevel <= 1) {
                    pstmt = conn.prepareStatement(SqlStatement.TASK_ALL.getStatement());
                } else {
                    pstmt = conn.prepareStatement(sql);
                    pstmt.setInt(1, userId);
                }
                ResultSet resultSet = pstmt.executeQuery();
                while (resultSet.next()) {
                    Task task = new Task();
                    getObjectById(resultSet.getInt("id"), task);
                    taskObservableList.add(task);
                }
            } else {
                System.out.println("CONNECTION");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return taskObservableList;
    }
}
