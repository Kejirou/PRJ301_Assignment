package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.BaseModel;

public abstract class DBContext<T extends BaseModel> {

    protected Connection connection;

    protected Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            try {
                String user = "tuan1";
                String pass = "tuan123456";
                String url = "jdbc:sqlserver://localhost:1433;"
                           + "databaseName=FALL2025_Assignment_Main;"
                           + "encrypt=true;trustServerCertificate=true;";
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                connection = DriverManager.getConnection(url, user, pass);
                System.out.println("✅ Database connected successfully!" + connection.getCatalog());
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return connection;
    }

    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Abstract CRUD methods
    public abstract ArrayList<T> list();
    public abstract T get(int id);
    public abstract void insert(T model);
    public abstract void update(T model);
    public abstract void delete(T model);
}
