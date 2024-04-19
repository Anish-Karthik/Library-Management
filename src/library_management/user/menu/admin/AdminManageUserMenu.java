package library_management.user.menu.admin;

import java.sql.Connection;
import java.util.ArrayList;

import library_management.format.Color;
import library_management.format.Format;
import library_management.user.User;

public class AdminManageUserMenu extends DisplayUserMenu {
  public AdminManageUserMenu(Connection con, User user) {
    super(con);
    if (user == null) {
      throw new IllegalArgumentException("You are unauthenticated");
    }
    if (user.getRole() != User.Role.ADMIN) {
      throw new IllegalArgumentException("You are not authorized to access admin menu");
    }
  }

  public void showMenu() {
    String[] options = { "Add User", "Update User", "Delete User", "Search User", "Show all Users", "Show all admins",
        "Show all members", "Exit" };
    Format.displayMenu("Admin User Menu", options);
  }

  public void processMenu() {
    int choice;
    do {
      showMenu();
      System.out.print(Format.colorString("Enter your choice: ", Color.ANSI_BOLD_HIGH_INTENSITY_CYAN));
      try {
        choice = Integer.parseInt(scanner.nextLine());
      } catch (Exception e) {
        choice = -1;
      }

      switch (choice) {
        case 1:
          addUser();
          break;
        case 2:
          updateUser();
          break;
        case 3:
          deleteUser();
          break;
        case 4:
          searchUser();
          break;
        case 5:
          displayAllUsers();
          break;
        case 6:
          displayUsers(User.Role.ADMIN);
          break;
        case 7:
          displayUsers(User.Role.MEMBER);
          break;
        case 8:
          Format.exitWithAnimate("Back to main menu");
          break;
        default:
          System.out.println(Format.colorString("Invalid choice", Color.ANSI_HIGH_INTENSITY_RED));
      }
    } while (choice != 8);
  }

  public void addUser() {
    // Add user to the database
    User user = new User();
    System.out.print(Format.colorString("Enter name: ", Color.ANSI_HIGH_INTENSITY_BLACK));
    String name = scanner.nextLine();
    while (name.isEmpty()) {
      System.out
          .println(Format.colorString("Name cannot be empty. Please enter again: ", Color.ANSI_HIGH_INTENSITY_RED));
      name = scanner.nextLine();
    }
    user.setName(name);

    System.out.print(Format.colorString("Enter username: ", Color.ANSI_HIGH_INTENSITY_BLACK));
    String username = scanner.nextLine();
    while (username.isEmpty()) {
      System.out
          .println(Format.colorString("Username cannot be empty. Please enter again: ", Color.ANSI_HIGH_INTENSITY_RED));
      username = scanner.nextLine();
    }
    user.setUsername(username);

    System.out.print(Format.colorString("Enter password: ", Color.ANSI_HIGH_INTENSITY_BLACK));
    String password = scanner.nextLine();
    while (password.isEmpty()) {
      System.out
          .println(Format.colorString("Password cannot be empty. Please enter again: ", Color.ANSI_HIGH_INTENSITY_RED));
      password = scanner.nextLine();
    }
    user.setPassword(password);

    System.out.print(Format.colorString("Enter role (ADMIN or MEMBER): ", Color.ANSI_HIGH_INTENSITY_BLACK));
    String role = scanner.nextLine().toUpperCase();
    while (!role.equals("ADMIN") && !role.equals("MEMBER")) {
      System.out.println(Format.colorString("Role must be either ADMIN or MEMBER. Please enter again: ",
          Color.ANSI_HIGH_INTENSITY_RED));
      role = scanner.nextLine().toUpperCase();
    }
    user.setRole(User.Role.valueOf(role));

    userOps.addUser(user);
  }

  public void updateUser() {
    // Update user in the database
    displayAllUsers();
    System.out.print(Format.colorString("Enter user id to update: ", Color.ANSI_HIGH_INTENSITY_BLACK));
    try {
      int id = scanner.nextInt();
      scanner.nextLine();
      User user = userOps.getUser(id);

      System.out.print(Format.colorString("Enter name (" + user.getName() + "): ", Color.ANSI_HIGH_INTENSITY_BLACK));
      String name = scanner.nextLine();
      if (!name.isEmpty()) {
        user.setName(name);
      }

      System.out
          .print(Format.colorString("Enter username (" + user.getUsername() + "): ", Color.ANSI_HIGH_INTENSITY_BLACK));
      String username = scanner.nextLine();
      if (!username.isEmpty()) {
        user.setUsername(username);
      }

      System.out.print(Format.colorString("Enter password: ", Color.ANSI_HIGH_INTENSITY_BLACK));
      String password = scanner.nextLine();
      if (!password.isEmpty()) {
        user.setPassword(password);
      }

      System.out.print(
          Format.colorString("Enter role (ADMIN or MEMBER) (" + user.getRole() + "): ",
              Color.ANSI_HIGH_INTENSITY_BLACK));
      String role = scanner.nextLine().toUpperCase();
      if (!role.isEmpty() && (role.equals("ADMIN") || role.equals("MEMBER"))) {
        user.setRole(User.Role.valueOf(role));
      }

      userOps.updateUser(user);
    } catch (Exception e) {
      System.out.println(Format.colorString("Invalid id or operation failed", Color.ANSI_HIGH_INTENSITY_RED));
    }
  }

  public void deleteUser() {
    displayAllUsers();
    // Delete user from the database
    System.out.print(Format.colorString("Enter user id to delete: ", Color.ANSI_HIGH_INTENSITY_BLACK));
    try {
      int id = scanner.nextInt();
      userOps.deleteUser(id);
    } catch (Exception e) {
      System.out.println(Format.colorString("Invalid id or operation failed", Color.ANSI_HIGH_INTENSITY_RED));
    }
  }

  public void searchUser() {
    // Search user in the database
    System.out.print(Format.colorString("Enter your username: ", Color.ANSI_HIGH_INTENSITY_BLACK));
    String search = scanner.nextLine();
    ArrayList<User> users = userOps.search(search);
    displayUsers(users);
  }
}
