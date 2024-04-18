package library_management;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB {
  // Additional fields specific to DB, if any

  // Private instance, so that it can be
  // accessed by only by getInstance() method
  private static DB instance;
  public Connection con;

  // Constructor
  private DB() throws SQLException {
    // Initialize the database
    String url = "jdbc:mysql://127.0.0.1:3306/library";
    String username = "admin";
    String password = "admin";
    this.con = DriverManager.getConnection(url,username,password);
  }

  // Static method to create instance of Singleton class
  public static DB getInstance() throws SQLException {
    if (instance == null) {
      instance = new DB();
    }

    return instance;
  }

  // Additional methods specific to DB, if any
}
