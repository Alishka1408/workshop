package core.db.executor;

import core.db.connection.DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QueriesExecutor {

    private final Connection connection;

    public QueriesExecutor(DataBase dataBase) {
        try {
            connection = DriverManager.getConnection(dataBase.getDbUrl(), dataBase.getUser(), dataBase.getPassword());
            System.out.println("You successfully connected to database now");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ResultSet executeQuery(String query) throws SQLException {
        return connection.createStatement().executeQuery(query);
    }

    public void execute(String query) throws SQLException {
        connection.createStatement().execute(query);
    }
}