package library_management.user.menu.admin;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Scanner;

import library_management.user.User;
import library_management.user.UserOperations;

public class DisplayUserMenu {
  public UserOperations userOps;
  public Scanner scanner;

  public DisplayUserMenu(Connection con) {
    scanner = new Scanner(System.in);
    userOps = new UserOperations(con);
  }

  public void displayUsers(ArrayList<User> users) {
    if (users == null || users.size() == 0) {
      System.out.println("No users found");
      return;
    }
    System.out.println("----------------------------------------------------");
    for (User user : users) {
      System.out.println("ID: " + user.getId());
      System.out.println("Name: " + user.getName());
      System.out.println("Username: " + user.getUsername());
      System.out.println("Role: " + user.getRole());
      // System.out.println("Created At: " + user.getCreatedAt());
      // System.out.println("Updated At: " + user.getUpdatedAt());
      System.out.println();
    }
    System.out.println("----------------------------------------------------");
  }

  public void displayAllUsers() {
    ArrayList<User> users = userOps.getUsers();
    displayUsers(users);
  }

  public void displayUsers(User.Role role) {
    ArrayList<User> users = userOps.getUserByRole(role);
    displayUsers(users);
  }
}
