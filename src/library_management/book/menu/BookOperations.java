package library_management.book.menu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import library_management.book.Book;
import library_management.user.User;

public class BookOperations {
  private Connection con;

  BookOperations(Connection con, User user) {
    if (user == null) {
      throw new IllegalArgumentException("You are unauthenticated");
    }
    this.con = con;
  }

  private ArrayList<Book> storeBooks(ResultSet rs) throws SQLException {
    ArrayList<Book> books = new ArrayList<>();
    while (rs.next()) {
      Book book = new Book();
      book.setId(rs.getInt("id"));
      book.setTitle(rs.getString("title"));
      book.setIsbn(rs.getString("isbn"));
      book.setAuthor(rs.getString("author"));
      book.setPublisher(rs.getString("publisher"));
      book.setLocation(rs.getString("location"));
      book.setCopies(rs.getInt("copies"));
      book.setAvailableCopies(rs.getInt("available_copies"));
      books.add(book);
    }
    return books;
  }

  public void addBook(Book book) {
    // Add book to the database
    PreparedStatement st;
    try {
      st = con.prepareStatement(
          "insert into Book(title, isbn, author, publisher, location, copies, available_copies) values(?,?,?,?,?,?,?)");

      st.setString(1, book.getTitle()); 
      st.setString(2, book.getIsbn());
      st.setString(3, book.getAuthor());
      st.setString(4, book.getPublisher());
      st.setString(5, book.getLocation());
      st.setInt(6, book.getCopies());
      st.setInt(7, book.getAvailableCopies());
      st.executeUpdate();

    } catch (SQLException e) {
      System.out.println("BOOK:Error adding book");
      e.printStackTrace();
    }
  }

  public void updateBook(Book book) {
    // Update book in the database
    PreparedStatement st;
    try {
      st = con.prepareStatement(
          "update Book set title=?, isbn=?, author=?, publisher=?, location=?, copies=?, available_copies=? where id=?");

      st.setString(1, book.getTitle());
      st.setString(2, book.getIsbn());
      st.setString(3, book.getAuthor());
      st.setString(4, book.getPublisher());
      st.setString(5, book.getLocation());
      st.setInt(6, book.getCopies());
      st.setInt(7, book.getAvailableCopies());
      st.setInt(8, book.getId());
      st.executeUpdate();

    } catch (SQLException e) {
      System.out.println("BOOK:Error updating book");
      e.printStackTrace();
    }
  }

  public void deleteBook(Book book) {
    deleteBook(book.getId());
  }

  public void deleteBook(int id) {
    // Delete book from the database
    PreparedStatement st;
    try {
      st = con.prepareStatement("delete from Book where id=?");
      st.setInt(1, id);
      st.executeUpdate();
    } catch (SQLException e) {
      System.out.println("BOOK:Error deleting book");
      e.printStackTrace();
    }
  }

  public ArrayList<Book> searchById(int id) {
    Book bk = getBook(id);
    ArrayList<Book> books = new ArrayList<>();
    books.add(bk);
    return books;
  }

  public ArrayList<Book> searchByTitle(String title) {
    PreparedStatement st;
    try {
      st = con.prepareStatement("select * from Book where title like ?");
      st.setString(1, "%" + title + "%");
      ResultSet rs = st.executeQuery();
      ArrayList<Book> books = storeBooks(rs);
      return books;
    } catch (SQLException e) {
      System.out.println("BOOK:Error searching books");
      e.printStackTrace();
    }
    return null;
  }

  public ArrayList<Book> searchByAuthor(String author) {
    PreparedStatement st;
    try {
      st = con.prepareStatement("select * from Book where author like ?");
      st.setString(1, "%" + author + "%");
      ResultSet rs = st.executeQuery();
      ArrayList<Book> books = storeBooks(rs);
      return books;
    } catch (SQLException e) {
      System.out.println("BOOK:Error searching books");
      e.printStackTrace();
    }
    return null;
  }

  public ArrayList<Book> searchByPublisher(String publisher) {
    PreparedStatement st;
    try {
      st = con.prepareStatement("select * from Book where publisher like ?");
      st.setString(1, "%" + publisher + "%");
      ResultSet rs = st.executeQuery();
      ArrayList<Book> books = storeBooks(rs);
      return books;
    } catch (SQLException e) {
      System.out.println("BOOK:Error searching books");
      e.printStackTrace();
    }
    return null;
  }

  public ArrayList<Book> searchByLocation(String location) {
    PreparedStatement st;
    try {
      st = con.prepareStatement("select * from Book where location like ?");
      st.setString(1, "%" + location + "%");
      ResultSet rs = st.executeQuery();
      ArrayList<Book> books = storeBooks(rs);
      return books;
    } catch (SQLException e) {
      System.out.println("BOOK:Error searching books");
      e.printStackTrace();
    }
    return null;
  }

  public ArrayList<Book> searchByIsbn(String isbn) {
    PreparedStatement st;
    try {
      st = con.prepareStatement("select * from Book where isbn like ?");
      st.setString(1, "%" + isbn + "%");
      ResultSet rs = st.executeQuery();
      ArrayList<Book> books = storeBooks(rs);
      return books;
    } catch (SQLException e) {
      System.out.println("BOOK:Error searching books");
      e.printStackTrace();
    }
    return null;
  }

  public ArrayList<Book> searchBooks(String searchString) {
    PreparedStatement st;
    try {
      st = con.prepareStatement(
          "select * from Book where title like ? or author like ? or publisher like ? or location like ? or isbn like ?");
      st.setString(1, "%" + searchString + "%");
      st.setString(2, "%" + searchString + "%");
      st.setString(3, "%" + searchString + "%");
      st.setString(4, "%" + searchString + "%");
      st.setString(5, "%" + searchString + "%");
      ResultSet rs = st.executeQuery();
      ArrayList<Book> books = storeBooks(rs);
      return books;
    } catch (SQLException e) {
      System.out.println("BOOK:Error searching books");
      e.printStackTrace();
    }
    return null;
  }

  public Book getBook(int id) {
    PreparedStatement st;
    try {
      st = con.prepareStatement("select * from Book where id=?");
      st.setInt(1, id);
      ResultSet rs = st.executeQuery();
      if (rs.next()) {
        Book book = new Book();
        book.setId(rs.getInt("id"));
        book.setTitle(rs.getString("title"));
        book.setIsbn(rs.getString("isbn"));
        book.setAuthor(rs.getString("author"));
        book.setPublisher(rs.getString("publisher"));
        book.setLocation(rs.getString("location"));
        book.setCopies(rs.getInt("copies"));
        book.setAvailableCopies(rs.getInt("available_copies"));
        return book;
      }
      return null;
    } catch (SQLException e) {
      System.out.println("BOOK:Error deleting book");
      e.printStackTrace();
    }
    return null;
  }

  public ArrayList<Book> getBooks() {
    PreparedStatement st;
    try {
      st = con.prepareStatement("select * from Book");
      ResultSet rs = st.executeQuery();
      ArrayList<Book> books = storeBooks(rs);
      return books;
    } catch (SQLException e) {
      System.out.println("BOOK:Error getting books");
      e.printStackTrace();
    }
    return null;
  }

  // BORROW and RETURN Functionality to be added
}
