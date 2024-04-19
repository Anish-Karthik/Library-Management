package library_management.book.menu;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Scanner;

import library_management.book.Book;
import library_management.book.BorrowedBook;
import library_management.format.Color;
import library_management.format.Format;
import library_management.user.User;

public class DisplayBookMenu {
  public BookOperations bookOps;
  public Scanner scanner;

  public DisplayBookMenu(Connection con, User user) {
    if (user == null) {
      throw new IllegalArgumentException("You are unauthenticated");
    }
    scanner = new Scanner(System.in);
    bookOps = new BookOperations(con, user);
  }

  public void displayBooks(ArrayList<Book> books) {
    if (books == null || books.size() == 0) {
      System.out.println(Format.colorString("No books found", Color.ANSI_HIGH_INTENSITY_BLACK));
      return;
    }
    System.out.println(Format.surroundStringWithBox("Displaying Books", 40, Color.ANSI_UNDERLINE_BLUE,
        Color.ANSI_BOLD_HIGH_INTENSITY_BLACK));
    String[] headers = Book.getHeaders();
    String[][] bookData = new String[books.size()][headers.length];
    for (int i = 0; i < books.size(); i++) {
      bookData[i] = books.get(i).getData();
    }
    Format.displayTable(headers, bookData);
  }

  public void displayBorrowedBooks(ArrayList<BorrowedBook> borrowedBooks) {
    if (borrowedBooks == null || borrowedBooks.size() == 0) {
      System.out.println("No books found");
      return;
    }
    System.out.println(Format.surroundStringWithBox("Displaying Borrowed Book", 40, Color.ANSI_UNDERLINE_BLUE,
        Color.ANSI_BOLD_HIGH_INTENSITY_BLACK));
    String[] headers = BorrowedBook.getHeaders();
    String[][] bookData = new String[borrowedBooks.size()][headers.length];
    for (int i = 0; i < borrowedBooks.size(); i++) {
      bookData[i] = borrowedBooks.get(i).getData();
    }
    Format.displayTable(headers, bookData);
  }

  public void displayAllBooks() {
    ArrayList<Book> books = bookOps.getBooks();
    displayBooks(books);
  }
}
