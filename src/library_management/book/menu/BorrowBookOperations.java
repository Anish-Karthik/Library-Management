package library_management.book.menu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import library_management.book.BorrowedBook;
import library_management.user.User;

public class BorrowBookOperations {

  private Connection con;
  private User currentUser;

  public BorrowBookOperations(Connection con, User currentUser) {
    if (currentUser == null) {
      throw new IllegalArgumentException("You are unauthenticated");
    }
    if (currentUser.getRole() != User.Role.MEMBER) {
      throw new IllegalArgumentException("You are not authorized to access member menu");
    }
    this.con = con;
    this.currentUser = currentUser;
  }

  public BorrowBookOperations(Connection con) {
    this.con = con;
  }

  public void borrowBook(int bookId) {
    borrowBook(bookId, currentUser.getId());
  }

  private void borrowBook(int bookId, int memberId) {
    PreparedStatement st;
    try {
      PreparedStatement checkIfBookAvailable = con
          .prepareStatement("SELECT * FROM Book WHERE id = ? AND available_copies > 0");
      checkIfBookAvailable.setInt(1, bookId);
      if (!checkIfBookAvailable.executeQuery().next()) {
        System.out.println("Book not available for borrowing");
        return;
      }
      st = con.prepareStatement("INSERT INTO Borrowing (book_id, member_id) VALUES (?, ?)");
      st.setInt(1, bookId);
      st.setInt(2, memberId);
      st.executeUpdate();

      PreparedStatement reduceAvailableCopies = con
          .prepareStatement("UPDATE Book SET available_copies = available_copies - 1 WHERE id = ?");
      reduceAvailableCopies.setInt(1, bookId);
      reduceAvailableCopies.executeUpdate();
      System.out.println("Book borrowed successfully");
    } catch (Exception e) {
      System.out.println("Error borrowing book: " + e.getMessage());
    }
  }

  public void returnBook(int bookId) {
    returnBook(bookId, currentUser.getId());
  }

  public void returnBook(int bookId, int memberId) {
    PreparedStatement st;
    try {
      st = con.prepareStatement("UPDATE Borrowing SET returned_at = CURRENT_DATE WHERE book_id = ? AND member_id = ?");
      st.setInt(1, bookId);
      st.setInt(2, memberId);
      st.executeUpdate();

      PreparedStatement increaseAvailableCopies = con
          .prepareStatement("UPDATE Book SET available_copies = available_copies + 1 WHERE id = ?");
      increaseAvailableCopies.setInt(1, bookId);
      increaseAvailableCopies.executeUpdate();
      System.out.println("Book returned successfully");
    } catch (Exception e) {
      System.out.println("Error returning book: " + e.getMessage());
    }
  }

  public ArrayList<BorrowedBook> getAllMyBorrowedBook() {
    return getAllMyBorrowedBook(currentUser.getId());
  }

  private ArrayList<BorrowedBook> getAllMyBorrowedBook(int memberId) {
    PreparedStatement st;
    try {
      st = con.prepareStatement(
          "SELECT b.id, b.title, b.author, b.publisher, b.location, b.copies, b.available_copies, br.borrowed_at, br.returned_at FROM Book b JOIN Borrowing br ON b.id = br.book_id WHERE br.member_id = ? AND returned_at IS NULL");
      st.setInt(1, memberId);
      ResultSet rs = st.executeQuery();
      ArrayList<BorrowedBook> borrowedBooks = new ArrayList<>();
      while (rs.next()) {
        BorrowedBook borrowedBook = new BorrowedBook();
        borrowedBook.setId(rs.getInt("id"));
        borrowedBook.setTitle(rs.getString("title"));
        borrowedBook.setAuthor(rs.getString("author"));
        borrowedBook.setPublisher(rs.getString("publisher"));
        borrowedBook.setLocation(rs.getString("location"));
        borrowedBook.setCopies(rs.getInt("copies"));
        borrowedBook.setAvailableCopies(rs.getInt("available_copies"));
        borrowedBook.setBorrowedAt(rs.getDate("borrowed_at"));
        borrowedBook.setReturnedAt(rs.getDate("returned_at"));
        borrowedBooks.add(borrowedBook);
      }
      return borrowedBooks;
    } catch (Exception e) {
      System.out.println("Error getting borrowed books: " + e.getMessage());
    }
    return null;
  }

  public ArrayList<BorrowedBook> getBorrowHistory() {
    return getBorrowHistory(currentUser.getId());
  }

  private ArrayList<BorrowedBook> getBorrowHistory(int memberId) {
    PreparedStatement st;
    try {
      st = con.prepareStatement(
          "SELECT b.id, b.title, b.author, b.publisher, b.location, b.copies, b.available_copies, br.borrowed_at, br.returned_at FROM Book b JOIN Borrowing br ON b.id = br.book_id WHERE br.member_id = ?");
      st.setInt(1, memberId);
      ResultSet rs = st.executeQuery();
      ArrayList<BorrowedBook> borrowedBooks = new ArrayList<>();
      while (rs.next()) {
        BorrowedBook borrowedBook = new BorrowedBook();
        borrowedBook.setId(rs.getInt("id"));
        borrowedBook.setTitle(rs.getString("title"));
        borrowedBook.setAuthor(rs.getString("author"));
        borrowedBook.setPublisher(rs.getString("publisher"));
        borrowedBook.setLocation(rs.getString("location"));
        borrowedBook.setCopies(rs.getInt("copies"));
        borrowedBook.setAvailableCopies(rs.getInt("available_copies"));
        borrowedBook.setBorrowedAt(rs.getDate("borrowed_at"));
        borrowedBook.setReturnedAt(rs.getDate("returned_at"));
        borrowedBooks.add(borrowedBook);
      }
      return borrowedBooks;
    } catch (Exception e) {
      System.out.println("Error getting borrow history: " + e.getMessage());
    }
    return null;
  }
}
