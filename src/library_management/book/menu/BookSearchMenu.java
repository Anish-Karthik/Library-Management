package library_management.book.menu;

import java.sql.Connection;
import java.util.ArrayList;

import library_management.book.Book;
import library_management.format.Color;
import library_management.format.Format;
import library_management.user.User;

public class BookSearchMenu extends DisplayBookMenu {

  public BookSearchMenu(Connection con, User user) {
    super(con, user);
    if (user == null) {
      throw new IllegalArgumentException("You are unauthenticated");
    }
  }

  public void searchMenu() {
    String[] options = { "Search by title", "Search by author", "Search by publisher", "Search by location",
        "Search by isbn", "Generic Search", "Back to book menu" };
    Format.displayMenu("Search Book menu", options);
  }

  public void processSearchMenu() {
    int choice;
    do {
      searchMenu();
      System.out.print(Format.colorString("Enter your choice: ", Color.ANSI_BOLD_HIGH_INTENSITY_CYAN));
      try {
        choice = Integer.parseInt(scanner.nextLine());
      } catch (Exception e) {
        choice = -1;
      }
      ArrayList<Book> books;
      switch (choice) {
        case 1:
          System.out.print(Format.colorString("Enter title: ", Color.ANSI_HIGH_INTENSITY_BLACK));
          String title = scanner.nextLine();
          books = bookOps.searchByTitle(title);
          displayBooks(books);
          break;
        case 2:
          System.out.print(Format.colorString("Enter author: ", Color.ANSI_HIGH_INTENSITY_BLACK));
          String author = scanner.nextLine();
          books = bookOps.searchByAuthor(author);
          System.out.println("Books found(" + books.size() + ")");
          displayBooks(books);
          break;
        case 3:
          System.out.print(Format.colorString("Enter publisher: ", Color.ANSI_HIGH_INTENSITY_BLACK));
          String publisher = scanner.nextLine();
          books = bookOps.searchByPublisher(publisher);
          System.out.println("Books found(" + books.size() + ")");
          displayBooks(books);
          break;
        case 4:
          System.out.print(Format.colorString("Enter location: ", Color.ANSI_HIGH_INTENSITY_BLACK));
          String location = scanner.nextLine();
          books = bookOps.searchByLocation(location);
          System.out.println("Books found(" + books.size() + ")");
          displayBooks(books);
          break;
        case 5:
          System.out.print(Format.colorString("Enter isbn: ", Color.ANSI_HIGH_INTENSITY_BLACK));
          String isbn = scanner.nextLine();
          books = bookOps.searchByIsbn(isbn);
          System.out.println("Books found(" + books.size() + ")");
          displayBooks(books);
          break;
        case 6:
          System.out.print(Format.colorString("Enter search string: ", Color.ANSI_HIGH_INTENSITY_BLACK));
          String searchString = scanner.nextLine();
          books = bookOps.searchBooks(searchString);
          System.out.println("Books found(" + books.size() + ")");
          displayBooks(books);
          break;
        case 7:
          Format.exitWithAnimate("Going to Book menu");
          return;
        default:
          System.out.println(Format.colorString("Invalid choice", Color.ANSI_HIGH_INTENSITY_RED));
      }
    } while (choice != 7);
  }
}
