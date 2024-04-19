package library_management.book.menu;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Scanner;

import library_management.book.Book;
import library_management.book.BorrowedBook;
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
      System.out.println("No books found");
      return;
    }
    for (Book book : books) {
      System.out.println("----------------------------------------------------");
      displayBook(book);
      System.out.println("----------------------------------------------------");
      System.out.println();
    }
  }

  public void displayBook(Book book) {
    if (book == null) {
      System.out.println("No book found");
      return;
    }
    System.out.println("ID: " + book.getId());
    System.out.println("Title: " + book.getTitle());
    System.out.println("ISBN: " + book.getIsbn());
    System.out.println("Author: " + book.getAuthor());
    System.out.println("Publisher: " + book.getPublisher());
    System.out.println("Location: " + book.getLocation());
    System.out.println("Copies: " + book.getCopies());
    System.out.println("Available Copies: " + book.getAvailableCopies());
  }

  public void displayBorrowedBook(BorrowedBook book) {
    displayBook(book);
    System.out.println("Borrowed At: " + book.getBorrowedAt());
    System.out
        .println(book.getReturnedAt() == null ? "Book not returned" : ("Book returned at " + book.getReturnedAt()));
  }

  public void displayBorrowedBooks(ArrayList<BorrowedBook> borrowedBooks) {
    if (borrowedBooks == null || borrowedBooks.size() == 0) {
      System.out.println("No books found");
      return;
    }
    for (BorrowedBook book : borrowedBooks) {
      System.out.println("----------------------------------------------------");
      displayBorrowedBook(book);
      System.out.println("----------------------------------------------------");
      System.out.println();
    }
  }

  public void displayAllBooks() {
    ArrayList<Book> books = bookOps.getBooks();
    displayBooks(books);
  }
}
