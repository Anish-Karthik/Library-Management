package library_management.user.menu.admin;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Scanner;

import library_management.format.Color;
import library_management.format.Format;
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
      System.out.println(Format.colorString("No users found", Color.ANSI_HIGH_INTENSITY_BLACK));
      return;
    }

    String[] headers = User.getHeaders();
    String[][] userData = new String[users.size()][headers.length];
    for (int i = 0; i < users.size(); i++) {
      userData[i] = users.get(i).getData();
    }
    Format.displayTable(headers, userData);
  }

  public void displayAllUsers() {
    ArrayList<User> users = userOps.getUsers();
    System.out.println(Format.surroundStringWithBox("Displaying All Users", 40, Color.ANSI_UNDERLINE_BLUE,
        Color.ANSI_BOLD_HIGH_INTENSITY_BLACK));
    displayUsers(users);
  }

  public void displayUsers(User.Role role) {
    ArrayList<User> users = userOps.getUserByRole(role);

    System.out.println(Format.surroundStringWithBox("Displaying All " + role.name(), 40, Color.ANSI_UNDERLINE_BLUE,
        Color.ANSI_BOLD_HIGH_INTENSITY_BLACK));
    displayUsers(users);
  }
}
