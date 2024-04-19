package library_management.menu;

import java.sql.Connection;
import java.util.Scanner;

import library_management.book.menu.admin.AdminBookMenu;
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
    if(user.getRole() != User.Role.ADMIN) {
      throw new IllegalArgumentException("You are not authorized to access admin menu");
    }
    scanner = new Scanner(System.in);
    bookMenu = new AdminBookMenu(con, user);
    userMenu = new AdminManageUserMenu(con, user);
  }

  public void displayMainMenu() {
    System.out.println("----------------------------------------------------");
    System.out.println("1. Book Menu");
    System.out.println("2. User Menu");
    System.out.println("3. Exit");
    System.out.println("----------------------------------------------------");
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
          bookMenu.processMenu();
          break;
        case 2:
          userMenu.processMenu();
          break;
        case 3:
          System.out.println("Exiting...");
          break;
        default:
          System.out.println("Invalid choice");
      }
    } while (choice != 3);
  }
}
