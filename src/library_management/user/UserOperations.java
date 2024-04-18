package library_management.user;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import library_management.user.hash.Hash;

/*
CREATE TABLE
    User (
        id INT PRIMARY KEY AUTO_INCREMENT,
        name VARCHAR(100) NOT NULL,
        username VARCHAR(100) NOT NULL UNIQUE,
        password VARCHAR(255) NOT NULL, -- Store hashed password
        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
        updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
        role Enum ('ADMIN', 'MEMBER') DEFAULT 'MEMBER'
    );
*/
public class UserOperations {
  private Connection con;
  private User currentUser;

  public UserOperations(Connection con) {
    this.con = con;
  }

  public void addUser(User user) {
    PreparedStatement st;
    try {
      st = con.prepareStatement(
          "insert into User(name, username, password, role) values(?,?,?,?)");

      st.setString(1, user.getName());
      st.setString(2, user.getUsername());
      st.setString(3, user.getPassword());
      st.setString(4, user.getRole().toString());
      st.executeUpdate();

    } catch (SQLException e) {
      System.out.println("USER:Error adding user");
      e.printStackTrace();
    }
  }

  public void updateUser(User user) {
    PreparedStatement st;
    try {
      st = con.prepareStatement(
          "update User set name=?, username=?, password=?, role=? where id=?");

      st.setString(1, user.getName());
      st.setString(2, user.getUsername());
      st.setString(3, Hash.hashString(user.getPassword()));
      st.setString(4, user.getRole().toString());
      st.setInt(5, user.getId());
      st.executeUpdate();

    } catch (SQLException e) {
      System.out.println("USER:Error updating user");
      e.printStackTrace();
    } catch (NoSuchAlgorithmException e) {
      System.out.println("USER:Error hashing password");
      e.printStackTrace();
    }
  }

  public void deleteUser(User user) {
    deleteUser(user.getId());
  }

  public void deleteUser(int id) {
    // Delete user from the database
    PreparedStatement st;
    try {
      st = con.prepareStatement("delete from User where id=?");
      st.setInt(1, id);
      st.executeUpdate();
    } catch (SQLException e) {
      System.out.println("USER:Error deleting user");
      e.printStackTrace();
    }
  }

  public void signUpMember(User user) {
    // Set the role of the user to MEMBER
    user.setRole(User.Role.MEMBER);
    addUser(user);
  }

  public void loginUser(String username, String password) {
    // Check if the user exists in the database
    // If the user exists, set the currentUser to the user
    // If the user does not exist, throw an exception
    PreparedStatement st;
    try {
      st = con.prepareStatement("select * from User where username=? and password=?");
      st.setString(1, username);
      st.setString(2, Hash.hashString(password));
      st.executeQuery();
    } catch (SQLException e) {
      System.out.println("USER:Error logging in user");
      e.printStackTrace();
    } catch (NoSuchAlgorithmException e) {
      System.out.println("USER:Error hashing password");
      e.printStackTrace();
    }
  }

  public ArrayList<User> getUsers() {
    PreparedStatement st;
    ArrayList<User> users = new ArrayList<User>();
    try {
      st = con.prepareStatement("select * from User");
      ResultSet rs = st.executeQuery();
      while (rs.next()) {
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setName(rs.getString("name"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password"));
        user.setRole(User.Role.valueOf(rs.getString("role")));
        users.add(user);
      }
      return users;
    } catch (SQLException e) {
      System.out.println("USER:Error getting users");
      e.printStackTrace();
    }
    return users;
  }

  public ArrayList<User> getUserByRole(User.Role role) {
    PreparedStatement st;
    ArrayList<User> users = new ArrayList<User>();
    try {
      st = con.prepareStatement("select * from User where role = ?");
      st.setString(1, role.toString());
      ResultSet rs = st.executeQuery();
      while (rs.next()) {
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setName(rs.getString("name"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password"));
        user.setRole(User.Role.valueOf(rs.getString("role")));
        users.add(user);
      }
      return users;
    } catch (SQLException e) {
      System.out.println("USER:Error getting users");
      e.printStackTrace();
    }
    return users;
  }

  public ArrayList<User> search(String searchString) {
    // Search for users based on the search string
    // The search should be case-insensitive and should match the search string
    // in the name or username of the user
    PreparedStatement st;
    ArrayList<User> users = new ArrayList<User>();
    try {
      st = con.prepareStatement(
          "select * from User where lower(name) like ? or lower(username) like ?");
      st.setString(1, "%" + searchString.toLowerCase() + "%");
      st.setString(2, "%" + searchString.toLowerCase() + "%");
      ResultSet rs = st.executeQuery();
      while (rs.next()) {
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setName(rs.getString("name"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password"));
        user.setRole(User.Role.valueOf(rs.getString("role")));
        users.add(user);
      }
      return users;
    } catch (SQLException e) {
      System.out.println("USER:Error searching users");
      e.printStackTrace();
    }
    return users;
  }

  public User getCurrentUser() {
    return currentUser;
  }

  public void setCurrentUser(User currentUser) {
    this.currentUser = currentUser;
  }
}
