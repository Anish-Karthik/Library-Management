package library_management.menu;

import java.sql.Connection;
import java.util.Scanner;

import library_management.book.menu.member.MemberBookMenu;
import library_management.format.Color;
import library_management.format.Format;
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
    String[] options = new String[] { "Book Menu", "Exit" };
    Format.displayMenu("Member Menu", options);
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
          bookMenu.processMenu();
          break;
        case 2:
          Format.exitWithAnimate("Back to main menu");
          break;
        default:
          System.out.println(Format.colorString("Invalid choice", Color.ANSI_HIGH_INTENSITY_RED));
      }
    } while (choice != 2);
  }
}
