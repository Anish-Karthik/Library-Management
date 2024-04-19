package library_management.menu;

import java.sql.Connection;
import java.util.Scanner;

import library_management.book.menu.member.MemberBookMenu;
import library_management.user.User;

public class MemberMainMenu {
  private MemberBookMenu bookMenu;
  private Scanner scanner;

  public MemberMainMenu(Connection con, User user) {
    if (user == null) {
      throw new IllegalArgumentException("You are unauthenticated");
    }
    if (user.getRole() != User.Role.MEMBER) {
      throw new IllegalArgumentException("You are not authorized to access member menu");
    }
    scanner = new Scanner(System.in);
    bookMenu = new MemberBookMenu(con, user);
  }

  public void displayMainMenu() {
    System.out.println("----------------------------------------------------");
    System.out.println("1. Book Menu");
    System.out.println("2. Exit");
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
          System.out.println("Exiting...");
          break;
        default:
          System.out.println("Invalid choice");
      }
    } while (choice != 2);
  }
}
