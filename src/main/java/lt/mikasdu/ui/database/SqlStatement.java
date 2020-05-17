package lt.mikasdu.ui.database;

public enum SqlStatement {
    USER_SINGLE("SELECT * FROM users WHERE name = ?;"),
    TASK_BY_USER("SELECT * FROM tasks WHERE userId =?;"),
    TASK_BY_ID("SELECT * FROM tasks WHERE id=?;"),
    TASK_UPDATE("UPDATE tasks SET description = ?, userId = ?, status = ? WHERE id = ?"),
    TASK_ADD("INSERT INTO tasks (description, userId, status) VALUES (?, ?, ?);"),
    TASK_ALL("SELECT * FROM tasks;");

    String statement;

    SqlStatement(String statement) {
        this.statement = statement;
    }

    public String getStatement() {
        return statement;
    }
}
