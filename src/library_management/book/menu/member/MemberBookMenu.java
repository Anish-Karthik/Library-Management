package library_management.book.menu.member;

import java.sql.Connection;
import java.util.ArrayList;

import library_management.book.BorrowedBook;
import library_management.book.menu.BookSearchMenu;
import library_management.book.menu.BorrowBookOperations;
import library_management.format.Color;
import library_management.format.Format;
import library_management.user.User;

public class MemberBookMenu extends BookSearchMenu {
  private BorrowBookOperations bookOperations;

  public MemberBookMenu(Connection con, User user) {
    super(con, user);
    if (user == null) {
      throw new IllegalArgumentException("You are unauthenticated");
    }
    if (user.getRole() != User.Role.MEMBER) {
      throw new IllegalArgumentException("You are not authorized to access member menu");
    }
    bookOperations = new BorrowBookOperations(con, user);
  }

  public void showMenu() {
    String[] options = { "Search Book", "Show all Books", "Borrow Book", "Return Book", "Show Borrowed Books",
        "Show Borrow History", "Exit" };
    Format.displayMenu("Member Book Menu", options);
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
          processSearchMenu();
          break;
        case 2:
          displayAllBooks();
          break;
        case 3:
          borrowBook();
          break;
        case 4:
          returnBook();
          break;
        case 5:
          showBorrowedBooks();
          break;
        case 6:
          showBorrowHistory();
          break;
        case 7:
          Format.exitWithAnimate("Back to main menu");
          break;
        default:
          System.out.println(Format.colorString("Invalid choice", Color.ANSI_HIGH_INTENSITY_RED));
      }
    } while (choice != 7);
  }

  public void borrowBook() {
    displayAllBooks();
    System.out.println(Format.surroundStringWithBox("Borrow Book", 40, Color.ANSI_UNDERLINE_BLUE,
        Color.ANSI_BOLD_HIGH_INTENSITY_BLACK));
    System.out.print(Format.colorString("Enter book id: ", Color.ANSI_HIGH_INTENSITY_BLACK));
    try {
      int bookId = scanner.nextInt();
      scanner.nextLine();
      bookOperations.borrowBook(bookId);
    } catch (Exception e) {
      System.out.println(Format.colorString("Invalid id or operation failed", Color.ANSI_HIGH_INTENSITY_RED));
    }
  }

  public void returnBook() {
    displayBorrowedBooks(bookOperations.getAllMyBorrowedBook());
    System.out.println(Format.surroundStringWithBox("Return Book", 40, Color.ANSI_UNDERLINE_BLUE,
        Color.ANSI_BOLD_HIGH_INTENSITY_BLACK));
    System.out.print(Format.colorString("Enter book id: ", Color.ANSI_HIGH_INTENSITY_BLACK));
    try {
      int bookId = scanner.nextInt();
      scanner.nextLine();
      bookOperations.returnBook(bookId);
    } catch (Exception e) {
      System.out.println(Format.colorString("Invalid id or operation failed", Color.ANSI_HIGH_INTENSITY_RED));
    }
  }

  public void showBorrowedBooks() {
    System.out.println(Format.surroundStringWithBox("Borrowed Books", 40, Color.ANSI_UNDERLINE_BLUE,
        Color.ANSI_BOLD_HIGH_INTENSITY_BLACK));
    ArrayList<BorrowedBook> borrowedBooks = bookOperations.getAllMyBorrowedBook();
    displayBorrowedBooks(borrowedBooks);
  }

  public void showBorrowHistory() {
    System.out.println(Format.surroundStringWithBox("Borrow History", 40, Color.ANSI_UNDERLINE_BLUE,
        Color.ANSI_BOLD_HIGH_INTENSITY_BLACK));
    ArrayList<BorrowedBook> borrowedBooks = bookOperations.getBorrowHistory();
    displayBorrowedBooks(borrowedBooks);
  }
}
