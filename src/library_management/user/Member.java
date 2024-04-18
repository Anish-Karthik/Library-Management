package library_management.user;

import library_management.book.Book;

public class Member extends AuthUser {
  private Book[] borrowedBooks;
  public Book[] getBorrowedBooks() {
    return borrowedBooks;
  }

  public void setBorrowedBooks(Book[] borrowedBooks) {
    this.borrowedBooks = borrowedBooks;
  }
}
