package library_management.menu;

import java.sql.Connection;
import java.util.Scanner;

import library_management.book.menu.admin.AdminBookMenu;
import library_management.format.Color;
import library_management.format.Format;
import library_management.user.User;
import library_management.user.menu.admin.AdminManageUserMenu;

public class AdminMainMenu {
  private AdminBookMenu bookMenu;
  private AdminManageUserMenu userMenu;
  private Scanner scanner;

  public AdminMainMenu(Connection con, User user) {
    if (user == null) {
      throw new IllegalArgumentException("You are unauthenticated");
    }
    if (user.getRole() != User.Role.ADMIN) {
      throw new IllegalArgumentException("You are not authorized to access admin menu");
    }
    scanner = new Scanner(System.in);
    bookMenu = new AdminBookMenu(con, user);
    userMenu = new AdminManageUserMenu(con, user);
  }

  public void displayMainMenu() {
    String[] options = { "Book Menu", "User Menu", "Exit" };
    Format.displayMenu("Admin Menu", options);
  }

  public void processMenu() {
    int choice;
    do {
      displayMainMenu();
      System.out.print(Format.colorString("Enter your choice: ", Color.ANSI_BOLD_HIGH_INTENSITY_CYAN));
      try {
        choice = Integer.parseInt(scanner.nextLine());
      } catch (Exception e) {
        choice = -1;
      }

      switch (choice) {
        case 1:
          bookMenu.processMenu();
          break;
        case 2:
          userMenu.processMenu();
          break;
        case 3:
          Format.exitWithAnimate("Back to main menu");
          break;
        default:
          System.out.println(Format.colorString("Invalid choice", Color.ANSI_HIGH_INTENSITY_RED));
      }
    } while (choice != 3);
  }
}
