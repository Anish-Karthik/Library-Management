package library_management.book.menu;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Scanner;

import library_management.book.Book;

public class DisplayBookMenu {
  public BookOperations bookOps;
  public Scanner scanner;

  public DisplayBookMenu(Connection con) {
    scanner = new Scanner(System.in);
    bookOps = new BookOperations(con);
  }

  public void displayBooks(ArrayList<Book> books) {
    if (books == null || books.size() == 0) {
      System.out.println("No books found");
      return;
    }
    for (Book book : books) {
      System.out.println("----------------------------------------------------");
      System.out.println("ID: " + book.getId());
      System.out.println("Title: " + book.getTitle());
      System.out.println("ISBN: " + book.getIsbn());
      System.out.println("Author: " + book.getAuthor());
      System.out.println("Publisher: " + book.getPublisher());
      System.out.println("Location: " + book.getLocation());
      System.out.println("Copies: " + book.getCopies());
      System.out.println("Available Copies: " + book.getAvailableCopies());
      // System.out.println("Created At: " + book.getCreatedAt());
      // System.out.println("Updated At: " + book.getUpdatedAt());
      System.out.println();
    }
    System.out.println("----------------------------------------------------");
  }

  public void displayAllBooks() {
    ArrayList<Book> books = bookOps.getBooks();
    displayBooks(books);
  }
}
