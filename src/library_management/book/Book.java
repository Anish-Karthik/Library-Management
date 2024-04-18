package library_management.book;
import java.time.LocalDateTime;

enum Status {
	AVAILABLE, BORROWED, LOST
}
public class Book {
	private int id;
	private String isbn;
	private String title;
	private int copies;
	private int availableCopies;
	private String author;
	private String publisher;
	private String location;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

	// Getters
	public int getId() {
		return id;
	}

	public String getIsbn() {
		return isbn;
	}

	public String getTitle() {
		return title;
	}

	public int getCopies() {
		return copies;
	}

	public int getAvailableCopies() {
		return availableCopies;
	}

	public String getAuthor() {
		return author;
	}

	public String getPublisher() {
		return publisher;
	}

	public String getLocation() {
		return location;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	// Setters
	public void setId(int id) {
		this.id = id;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setCopies(int copies) {
		this.copies = copies;
	}

	public void setAvailableCopies(int availableCopies) {
		this.availableCopies = availableCopies;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
}
