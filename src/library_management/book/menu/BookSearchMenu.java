package library_management.book.menu;

import java.sql.Connection;
import java.util.ArrayList;

import library_management.book.Book;

public class BookSearchMenu extends DisplayBookMenu {

  public BookSearchMenu(Connection con) {
    super(con);
  }

  public void searchMenu() {
    System.out.println("1. Search by title");
    System.out.println("2. Search by author");
    System.out.println("3. Search by publisher");
    System.out.println("4. Search by location");
    System.out.println("5. Search by isbn");
    System.out.println("6. Generic Search");
    System.out.println("7. Back to book menu");
  }

  public void processSearchMenu() {
    int choice;
    do {
      searchMenu();
      System.out.print("Enter your choice:");
      choice = scanner.nextInt();
      scanner.nextLine();
      ArrayList<Book> books;
      switch (choice) {
        case 1:
          System.out.print("Enter title:");
          String title = scanner.nextLine();
          books = bookOps.searchByTitle(title);
          displayBooks(books);
          break;
        case 2:
          System.out.print("Enter author:");
          String author = scanner.nextLine();
          books = bookOps.searchByAuthor(author);
          System.out.println("Books found(" + books.size() + ")");
          displayBooks(books);
          break;
        case 3:
          System.out.print("Enter publisher:");
          String publisher = scanner.nextLine();
          books = bookOps.searchByPublisher(publisher);
          System.out.println("Books found(" + books.size() + ")");
          displayBooks(books);
          break;
        case 4:
          System.out.print("Enter location:");
          String location = scanner.nextLine();
          books = bookOps.searchByLocation(location);
          System.out.println("Books found(" + books.size() + ")");
          displayBooks(books);
          break;
        case 5:
          System.out.print("Enter isbn:");
          String isbn = scanner.nextLine();
          books = bookOps.searchByIsbn(isbn);
          System.out.println("Books found(" + books.size() + ")");
          displayBooks(books);
          break;
        case 6:
          System.out.print("Enter search string:");
          String searchString = scanner.nextLine();
          books = bookOps.searchBooks(searchString);
          System.out.println("Books found(" + books.size() + ")");
          displayBooks(books);
          break;
        case 7:
          System.out.println("Going to Book menu...");
          return;
        default:
          System.out.println("Invalid choice");
      }
    } while (choice != 7);
  }
}
