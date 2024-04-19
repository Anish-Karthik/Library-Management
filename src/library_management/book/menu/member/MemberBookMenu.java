package library_management.book.menu.member;

import java.sql.Connection;
import java.util.ArrayList;

import library_management.book.BorrowedBook;
import library_management.book.menu.BookSearchMenu;
import library_management.book.menu.BorrowBookOperations;
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
    System.out.println("Member Book Menu");
    System.out.println("1. Search Book");
    System.out.println("2. Show all Books");
    System.out.println("3. Borrow Book");
    System.out.println("4. Return Book");
    System.out.println("5. Show Borrowed Books");
    System.out.println("6. Show Borrow History");
    System.out.println("7. Exit");
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
          System.out.println("Back to main menu...");
          break;
        default:
          System.out.println("Invalid choice");
      }
    } while (choice != 7);
  }

  public void borrowBook() {
    System.out.println("Borrow Book");
    System.out.print("Enter book id:");
    int bookId = scanner.nextInt();
    scanner.nextLine();
    bookOperations.borrowBook(bookId);
  }

  public void returnBook() {
    System.out.println("Return Book");
    System.out.print("Enter book id:");
    int bookId = scanner.nextInt();
    scanner.nextLine();
    bookOperations.returnBook(bookId);
  }

  public void showBorrowedBooks() {
    System.out.println("Borrowed Books");
    ArrayList<BorrowedBook> borrowedBooks = bookOperations.getAllMyBorrowedBook();
    displayBorrowedBooks(borrowedBooks);
  }

  public void showBorrowHistory() {
    System.out.println("Borrow History");
    ArrayList<BorrowedBook> borrowedBooks = bookOperations.getBorrowHistory();
    displayBorrowedBooks(borrowedBooks);
  }
}
