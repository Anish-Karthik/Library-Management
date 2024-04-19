package library_management.book.menu.admin;

import java.sql.Connection;

import library_management.book.Book;
import library_management.book.menu.BookSearchMenu;
import library_management.user.User;

public class AdminBookMenu extends BookSearchMenu {

  public AdminBookMenu(Connection con, User user) {
    super(con, user);
    if (user == null) {
      throw new IllegalArgumentException("You are unauthenticated");
    }
    if (user.getRole() != User.Role.ADMIN) {
      throw new IllegalArgumentException("You are not authorized to access admin menu");
    }
  }

  public void showMenu() {
    System.out.println("Admin Book Menu");
    System.out.println("1. Add Book");
    System.out.println("2. Update Book");
    System.out.println("3. Delete Book");
    System.out.println("4. Search Book");
    System.out.println("5. Show all Books");
    System.out.println("6. Exit");
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
          addBook();
          break;
        case 2:
          updateBook();
          break;
        case 3:
          deleteBook();
          break;
        case 4:
          processSearchMenu();
          break;
        case 5:
          displayAllBooks();
          break;
        case 6:
          System.out.println("Back to main menu...");
          break;
        default:
          System.out.println("Invalid choice");
      }
    } while (choice != 6);
  }

  public void addBook() {
    Book book = new Book();
    System.out.print("Enter title:");
    book.setTitle(scanner.nextLine());
    System.out.print("Enter isbn:");
    book.setIsbn(scanner.nextLine());
    System.out.print("Enter author:");
    book.setAuthor(scanner.nextLine());
    System.out.print("Enter publisher:");
    book.setPublisher(scanner.nextLine());
    System.out.print("Enter location:");
    book.setLocation(scanner.nextLine());
    System.out.print("Enter copies:");
    book.setCopies(scanner.nextInt());
    System.out.print("Enter available copies:");
    book.setAvailableCopies(scanner.nextInt());
    bookOps.addBook(book);
    System.out.println("Book added successfully");
  }

  public void deleteBook() {
    System.out.print("Enter book id to delete:");
    int id = scanner.nextInt();
    bookOps.deleteBook(id);
    System.out.println("Book deleted successfully");
  }

  public void updateBook() {
    Book book = new Book();
    System.out.print("Enter book id to update:");
    int id = scanner.nextInt();
    scanner.nextLine();
    book.setId(id);
    System.out.print("Enter title:");
    book.setTitle(scanner.nextLine());
    System.out.print("Enter isbn:");
    book.setIsbn(scanner.nextLine());
    System.out.print("Enter author:");
    book.setAuthor(scanner.nextLine());
    System.out.print("Enter publisher:");
    book.setPublisher(scanner.nextLine());
    System.out.print("Enter location:");
    book.setLocation(scanner.nextLine());
    System.out.print("Enter copies:");
    book.setCopies(scanner.nextInt());
    System.out.print("Enter available copies:");
    book.setAvailableCopies(scanner.nextInt());
    bookOps.updateBook(book);
    System.out.println("Book updated successfully");
  }
}
