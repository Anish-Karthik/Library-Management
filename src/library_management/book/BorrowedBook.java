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

  public static String[] getHeaders() {
		String[] headers = Book.getHeaders();
    String[] borrowedBookHeaders = { "Borrowed At", "Returned At" };
    String[] allHeaders = new String[headers.length + borrowedBookHeaders.length];
    System.arraycopy(headers, 0, allHeaders, 0, headers.length);
    System.arraycopy(borrowedBookHeaders, 0, allHeaders, headers.length, borrowedBookHeaders.length);
    return allHeaders;
	}

	public String[] getData() {
		String[] data = super.getData();
    String[] borrowedBookData = { borrowedAt.toString(), returnedAt==null? "Not Returned" :returnedAt.toString() };
    String[] allData = new String[data.length + borrowedBookData.length];
    System.arraycopy(data, 0, allData, 0, data.length);
    System.arraycopy(borrowedBookData, 0, allData, data.length, borrowedBookData.length);
    return allData;
  }

}
