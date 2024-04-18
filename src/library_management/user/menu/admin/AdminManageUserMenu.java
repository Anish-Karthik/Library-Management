package library_management.user.menu.admin;

import java.sql.Connection;
import java.util.ArrayList;

import library_management.user.User;

public class AdminManageUserMenu extends DisplayUserMenu {
  public AdminManageUserMenu(Connection con) {
    super(con);
  }

  public void showMenu() {
    System.out.println("Admin User Menu");
    System.out.println("1. Add User");
    System.out.println("2. Update User");
    System.out.println("3. Delete User");
    System.out.println("4. Search User");
    System.out.println("5. Show All Users");
    System.out.println("6. Show all admins");
    System.out.println("7. Show all members");
    System.out.println("8. Exit");
  }

  public void processMenu() {
    int choice;
    do {
      showMenu();
      System.out.print("Enter your choice:");
      choice = scanner.nextInt();
      scanner.nextLine();
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
          System.out.println("Back to main menu...");
          break;
        default:
          System.out.println("Invalid choice");
      }
    } while (choice != 8);
  }

  public void addUser() {
    // Add user to the database
    User user = new User();
    System.out.print("Enter name:");
    String name = scanner.nextLine();
    while (name.isEmpty()) {
      System.out.println("Name cannot be empty. Please enter again:");
      name = scanner.nextLine();
    }
    user.setName(name);

    System.out.print("Enter username:");
    String username = scanner.nextLine();
    while (username.isEmpty()) {
      System.out.println("Username cannot be empty. Please enter again:");
      username = scanner.nextLine();
    }
    user.setUsername(username);

    System.out.print("Enter password:");
    String password = scanner.nextLine();
    while (password.isEmpty()) {
      System.out.println("Password cannot be empty. Please enter again:");
      password = scanner.nextLine();
    }
    user.setPassword(password);

    System.out.print("Enter role (ADMIN or MEMBER):");
    String role = scanner.nextLine().toUpperCase();
    while (!role.equals("ADMIN") && !role.equals("MEMBER")) {
      System.out.println("Role must be either ADMIN or MEMBER. Please enter again:");
      role = scanner.nextLine().toUpperCase();
    }
    user.setRole(User.Role.valueOf(role));

    userOps.addUser(user);
  }

  public void updateUser() {
    // Update user in the database
    User user = new User();
    System.out.print("Enter user id to update:");
    int id = scanner.nextInt();
    scanner.nextLine();
    user.setId(id);

    System.out.print("Enter name:");
    String name = scanner.nextLine();
    while (name.isEmpty()) {
      System.out.println("Name cannot be empty. Please enter again:");
      name = scanner.nextLine();
    }
    user.setName(name);

    System.out.print("Enter username:");
    String username = scanner.nextLine();
    while (username.isEmpty()) {
      System.out.println("Username cannot be empty. Please enter again:");
      username = scanner.nextLine();
    }
    user.setUsername(username);

    System.out.print("Enter password:");
    String password = scanner.nextLine();
    while (password.isEmpty()) {
      System.out.println("Password cannot be empty. Please enter again:");
      password = scanner.nextLine();
    }
    user.setPassword(password);

    System.out.println("Enter role (ADMIN or MEMBER):");
    String role = scanner.nextLine().toUpperCase();
    while (!role.equals("ADMIN") && !role.equals("MEMBER")) {
      System.out.println("Role must be either ADMIN or MEMBER. Please enter again:");
      role = scanner.nextLine().toUpperCase();
    }
    user.setRole(User.Role.valueOf(role));

    userOps.updateUser(user);
  }

  public void deleteUser() {
    // Delete user from the database
    System.out.print("Enter user id to delete:");
    int id = scanner.nextInt();
    userOps.deleteUser(id);
  }

  public void searchUser() {
    // Search user in the database
    System.out.print("Search User:");
    String search = scanner.nextLine();
    ArrayList<User> users = userOps.search(search);
    displayUsers(users);
  }
}
