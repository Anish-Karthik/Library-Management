package library_management.book;

import java.sql.Date;

public class BorrowedBook extends Book {
  private Date borrowedAt;
  private Date returnedAt;

  public Date getBorrowedAt() {
    return borrowedAt;
  }

  public void setBorrowedAt(Date date) {
    this.borrowedAt = date;
  }

  public Date getReturnedAt() {
    return returnedAt;
  }

  public void setReturnedAt(Date date) {
    this.returnedAt = date;
  }
}
