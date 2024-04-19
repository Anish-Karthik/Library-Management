package library_management.menu;

import java.io.Console;
import java.sql.Connection;
import java.util.Scanner;

import library_management.format.Color;
import library_management.format.Format;
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
    System.out.print(Format.surroundStringWithBox("Welcome To Anish's Library management System", 40, Color.ANSI_BLUE,
        Color.ANSI_YELLOW, Color.ANSI_BACKGROUND_HIGH_INTENSITY_PURPLE));
    String[] options = { "Member Signup", "Member Login", "Admin Login", "Exit" };
    Format.displayMenu("Main Menu", options);
  }

  public void processMenu() {
    int choice;
    do {
      displayMainMenu();
      System.out
          .print(Format.colorString("Enter your choice: ", Color.ANSI_BOLD_HIGH_INTENSITY_CYAN));
      try {
        choice = Integer.parseInt(scanner.nextLine());
      } catch (Exception e) {
        choice = -1;
      }

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
          Format.exitWithAnimate("Exiting Library management System");
          break;
        default:
          System.out.println(Format.colorString("Invalid choice", Color.ANSI_HIGH_INTENSITY_RED));
      }
    } while (choice != 4);
  }

  public void signup() {
    User user = new User();
    System.out.print(Format.colorString("Enter your name: ", Color.ANSI_HIGH_INTENSITY_BLACK));
    String name = scanner.nextLine();
    while (name.isEmpty()) {
      System.out
          .println(Format.colorString("Name cannot be empty. Please enter your name: ", Color.ANSI_HIGH_INTENSITY_RED));
      name = scanner.nextLine();
    }
    user.setName(name);

    System.out.print(Format.colorString("Enter your username: ", Color.ANSI_HIGH_INTENSITY_BLACK));
    String username = scanner.nextLine();
    while (username.isEmpty()) {
      System.out.println(
          Format.colorString("Username cannot be empty. Please enter your username: ", Color.ANSI_HIGH_INTENSITY_RED));
      username = scanner.nextLine();
    }
    user.setUsername(username);

    System.out.print(Format.colorString("Enter your password: ", Color.ANSI_HIGH_INTENSITY_BLACK));
    Console console = System.console();
    char[] passwordArray = console.readPassword();
    String password = new String(passwordArray);
    while (password.isEmpty()) {
      System.out.println(
          Format.colorString("Password cannot be empty. Please enter your password: ", Color.ANSI_HIGH_INTENSITY_RED));
      password = scanner.nextLine();
    }
    user.setPassword(password);
    userOperations.signUpMember(user);
  }

  public void login(User.Role role) {
    System.out.print(Format.colorString("Enter your username: ", Color.ANSI_HIGH_INTENSITY_BLACK));
    String username = scanner.nextLine();
    System.out.print(Format.colorString("Enter your password: ", Color.ANSI_HIGH_INTENSITY_BLACK));
    Console console = System.console();
    char[] passwordArray = console.readPassword();
    String password = new String(passwordArray);
    User user = userOperations.loginUser(username, password);
    if (user != null) {
      System.out.println(Format.colorString("Logged In successfully", Color.ANSI_HIGH_INTENSITY_GREEN));
      if (role == User.Role.MEMBER) {
        try {
          MemberMainMenu memberMenu = new MemberMainMenu(con, user);
          memberMenu.processMenu();
        } catch (Exception e) {
          System.out.println(Format.colorString(e.getMessage(), Color.ANSI_HIGH_INTENSITY_RED));
        }
      } else if (role == User.Role.ADMIN) {
        try {
          AdminMainMenu adminMenu = new AdminMainMenu(con, user);
          adminMenu.processMenu();
        } catch (Exception e) {
          System.out.println(Format.colorString(e.getMessage(), Color.ANSI_HIGH_INTENSITY_RED));
        }
      }
    } else {
      System.out.println(Format.colorString("Invalid username or password", Color.ANSI_HIGH_INTENSITY_RED));
    }
  }

}
