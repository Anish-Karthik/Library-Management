package library_management.user;

import library_management.book.BorrowedBook;

public class Member extends User {
  private BorrowedBook[] borrowedBooks;
  public BorrowedBook[] getBorrowedBooks() {
    return borrowedBooks;
  }

  public void setBorrowedBooks(BorrowedBook[] borrowedBooks) {
    this.borrowedBooks = borrowedBooks;
  }
}
