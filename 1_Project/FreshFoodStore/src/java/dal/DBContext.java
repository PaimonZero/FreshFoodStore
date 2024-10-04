package dal;

import java.sql.*;

public class DBContext implements DatabaseInfo {

    protected Connection connection;
    protected PreparedStatement preStatement;
    protected ResultSet resultSet;

    public DBContext() {
    }

    public Connection getConnection() {
        try {
            Class.forName(DRIVERNAME);
        } catch (ClassNotFoundException e) {
            System.err.println("(At DBContext)_Error loading driver!" + e);
        }
        try {
            Connection con = DriverManager.getConnection(DBURL, USERDB, PASSDB);
            return con;
        } catch (SQLException e) {
            System.err.println("(At DBContext)_Error: " + e);
        }
        return null;
    }

    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            System.err.println("Error " + e.getMessage() + " at DBContext");
        }
    }
    //-------------------------------------------------------------------------------------------------//
    public static void main(String[] args) {
        DBContext db = new DBContext();
        Connection con = db.getConnection();
        System.out.println(con);
    }
}
