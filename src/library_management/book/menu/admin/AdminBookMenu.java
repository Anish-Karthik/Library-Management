package library_management.book.menu.admin;

import java.sql.Connection;

import library_management.book.Book;
import library_management.book.menu.BookSearchMenu;
import library_management.format.Color;
import library_management.format.Format;
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
    String[] options = { "Add Book", "Update Book", "Delete Book", "Search Book", "Show all Books", "Exit" };
    Format.displayMenu("Admin Book Menu", options);
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
          Format.exitWithAnimate("Back to main menu");
          break;
        default:
          System.out.println(Format.colorString("Invalid choice", Color.ANSI_HIGH_INTENSITY_RED));
      }
    } while (choice != 6);
  }

  public void addBook() {
    System.out.println(Format.surroundStringWithBox("Add Book", 40, Color.ANSI_UNDERLINE_BLUE,
        Color.ANSI_BOLD_HIGH_INTENSITY_BLACK));
    Book book = new Book();
    System.out.print(Format.colorString("Enter title: ", Color.ANSI_HIGH_INTENSITY_BLACK));
    book.setTitle(scanner.nextLine());
    System.out.print(Format.colorString("Enter Isbn: ", Color.ANSI_HIGH_INTENSITY_BLACK));
    book.setIsbn(scanner.nextLine());
    System.out.print(Format.colorString("Enter author: ", Color.ANSI_HIGH_INTENSITY_BLACK));
    book.setAuthor(scanner.nextLine());
    System.out.print(Format.colorString("Enter publisher: ", Color.ANSI_HIGH_INTENSITY_BLACK));
    book.setPublisher(scanner.nextLine());
    System.out.print(Format.colorString("Enter location: ", Color.ANSI_HIGH_INTENSITY_BLACK));
    book.setLocation(scanner.nextLine());
    System.out.print(Format.colorString("Enter copies: ", Color.ANSI_HIGH_INTENSITY_BLACK));
    book.setCopies(scanner.nextInt());
    System.out.print(Format.colorString("Enter available copies: ", Color.ANSI_HIGH_INTENSITY_BLACK));
    book.setAvailableCopies(scanner.nextInt());
    bookOps.addBook(book);
    System.out.println(Format.colorString("Book added successfully", Color.ANSI_HIGH_INTENSITY_GREEN));
  }

  public void deleteBook() {
    displayAllBooks();
    System.out.println(Format.surroundStringWithBox("Delete Book", 40, Color.ANSI_UNDERLINE_BLUE,
        Color.ANSI_BOLD_HIGH_INTENSITY_BLACK));
    System.out.print(Format.colorString("Enter book id to delete: ", Color.ANSI_HIGH_INTENSITY_BLACK));
    try {
      int id = scanner.nextInt();
      bookOps.deleteBook(id);
      System.out.println(Format.colorString("Book deleted successfully", Color.ANSI_HIGH_INTENSITY_GREEN));
    } catch (Exception e) {
      System.out.println(Format.colorString("Invalid id or operation failed", Color.ANSI_HIGH_INTENSITY_RED));
    }
  }

  public void updateBook() {
    displayAllBooks();
    System.out.println(Format.surroundStringWithBox("Update Book", 40, Color.ANSI_UNDERLINE_BLUE,
        Color.ANSI_BOLD_HIGH_INTENSITY_BLACK));
    System.out.print(Format.colorString("Enter book id to update: ", Color.ANSI_HIGH_INTENSITY_BLACK));
    try {
      int id = scanner.nextInt();
      scanner.nextLine();
      Book book = bookOps.getBook(id);

      System.out.print(Format.colorString("Enter title (" + book.getTitle() + "): ", Color.ANSI_HIGH_INTENSITY_BLACK));
      String title = scanner.nextLine();
      book.setTitle(title.isEmpty() ? book.getTitle() : title);

      System.out.print(Format.colorString("Enter Isbn (" + book.getIsbn() + "): ", Color.ANSI_HIGH_INTENSITY_BLACK));
      String isbn = scanner.nextLine();
      book.setIsbn(isbn.isEmpty() ? book.getIsbn() : isbn);

      System.out
          .print(Format.colorString("Enter author (" + book.getAuthor() + "): ", Color.ANSI_HIGH_INTENSITY_BLACK));
      String author = scanner.nextLine();
      book.setAuthor(author.isEmpty() ? book.getAuthor() : author);

      System.out
          .print(
              Format.colorString("Enter publisher (" + book.getPublisher() + "): ", Color.ANSI_HIGH_INTENSITY_BLACK));
      String publisher = scanner.nextLine();
      book.setPublisher(publisher.isEmpty() ? book.getPublisher() : publisher);

      System.out
          .print(Format.colorString("Enter location (" + book.getLocation() + "): ", Color.ANSI_HIGH_INTENSITY_BLACK));
      String location = scanner.nextLine();
      book.setLocation(location.isEmpty() ? book.getLocation() : location);

      System.out
          .print(Format.colorString("Enter copies (" + book.getCopies() + "): ", Color.ANSI_HIGH_INTENSITY_BLACK));
      String copiesStr = scanner.nextLine();
      int copies = copiesStr.isEmpty() ? book.getCopies() : Integer.parseInt(copiesStr);
      book.setCopies(copies);

      System.out.print(Format.colorString("Enter available copies (" + book.getAvailableCopies() + "): ",
          Color.ANSI_HIGH_INTENSITY_BLACK));
      String availableCopiesStr = scanner.nextLine();
      int availableCopies = availableCopiesStr.isEmpty() ? book.getAvailableCopies()
          : Integer.parseInt(availableCopiesStr);
      book.setAvailableCopies(availableCopies);

      bookOps.updateBook(book);
      System.out.println(Format.colorString("Book updated successfully", Color.ANSI_HIGH_INTENSITY_GREEN));
    } catch (Exception e) {
      System.out.println(Format.colorString("Invalid id or operation failed", Color.ANSI_HIGH_INTENSITY_RED));
    }
  }
}
