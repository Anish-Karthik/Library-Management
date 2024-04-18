package library_management.borrowing;

import java.time.LocalDateTime;

public class Borrowing {
    private int id;
    private int bookId;
    private int memberId;
    private LocalDateTime borrowedAt;
    private LocalDateTime returnedAt;

    // Getters and Setters and parameterized constructor
    
    public Borrowing(int id, int bookId, int memberId, LocalDateTime borrowedAt, LocalDateTime returnedAt) {
        this.id = id;
        this.bookId = bookId;
        this.memberId = memberId;
        this.borrowedAt = borrowedAt;
        this.returnedAt = returnedAt;
    }

    public Borrowing() {
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
      this.id = id;
    }

    public int getBookId() {
      return bookId;
    }

    public void setBookId(int bookId) {
      this.bookId = bookId;
    }

    public int getMemberId() {
      return memberId;
    }

    public void setMemberId(int memberId) {
      this.memberId = memberId;
    }

    public LocalDateTime getBorrowedAt() {
      return borrowedAt;
    }

    public void setBorrowedAt(LocalDateTime borrowedAt) {
      this.borrowedAt = borrowedAt;
    }

    public LocalDateTime getReturnedAt() {
      return returnedAt;
    }

}

