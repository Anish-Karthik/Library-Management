package library_management.menu;

import java.sql.Connection;
import java.util.Scanner;

import library_management.user.User;
import library_management.user.UserOperations;

public class UserMainMenu {
  private UserOperations userOperations;
  private Connection con;
  private Scanner scanner;

  public UserMainMenu(Connection con) {
    this.con = con;
    scanner = new Scanner(System.in);
    userOperations = new UserOperations(con);
  }

  public void displayMainMenu() {
    System.out.println("----------------------------------------------------");
    System.out.println("1. Member Signup");
    System.out.println("2. Member Login");
    System.out.println("3. Admin Login");
    System.out.println("4. Exit");
  }

  public void processMenu() {
    int choice;
    do {
      displayMainMenu();
      System.out.print("Enter your choice:");
      choice = scanner.nextInt();
      scanner.nextLine();
      switch (choice) {
        case 1:
          signup();
          break;
        case 2:
          login(User.Role.MEMBER);
          break;
        case 3:
          login(User.Role.ADMIN);
          break;
        case 4:
          System.out.println("Exiting...");
          break;
        default:
          System.out.println("Invalid choice");
      }
    } while (choice != 4);
  }

  public void signup() {
    User user = new User();
    System.out.print("Enter your name:");
    String name = scanner.nextLine();
    while (name.isEmpty()) {
      System.out.println("Name cannot be empty. Please enter your name:");
      name = scanner.nextLine();
    }
    user.setName(name);

    System.out.print("Enter your username:");
    String username = scanner.nextLine();
    while (username.isEmpty()) {
      System.out.println("Username cannot be empty. Please enter your username:");
      username = scanner.nextLine();
    }
    user.setUsername(username);

    System.out.print("Enter your password:");
    String password = scanner.nextLine();
    while (password.isEmpty()) {
      System.out.println("Password cannot be empty. Please enter your password:");
      password = scanner.nextLine();
    }
    user.setPassword(password);
    userOperations.signUpMember(user);
  }

  public void login(User.Role role) {
    System.out.print("Enter your username:");
    String username = scanner.nextLine();
    System.out.print("Enter your password:");
    String password = scanner.nextLine();
    User user = userOperations.loginUser(username, password);
    if (user != null) {
      System.out.println("Login successful");
      if (role == User.Role.MEMBER) {
        try {
          MemberMainMenu memberMenu = new MemberMainMenu(con, user);
          memberMenu.processMenu();
        } catch (Exception e) {
          System.out.println(e.getMessage());
        }
      } else if (role == User.Role.ADMIN) {
        try {
          AdminMainMenu adminMenu = new AdminMainMenu(con, user);
          adminMenu.processMenu();
        } catch (Exception e) {
          System.out.println(e.getMessage());
        }
      }
    } else {
      System.out.println("Invalid username or password");
    }
  }

}
