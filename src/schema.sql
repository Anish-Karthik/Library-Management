DROP DATABASE IF EXISTS library;

CREATE DATABASE library;

USE library;

CREATE TABLE
    Book (
        id INT PRIMARY KEY AUTO_INCREMENT,
        title VARCHAR(100) NOT NULL,
        isbn VARCHAR(20) NOT NULL UNIQUE,
        author VARCHAR(100) NOT NULL,
        publisher VARCHAR(100) NOT NULL,
        location VARCHAR(100) NOT NULL,
        copies INT NOT NULL,
        available_copies INT NOT NULL,
        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
        updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
    );

CREATE TABLE
    User (
        id INT PRIMARY KEY AUTO_INCREMENT,
        name VARCHAR(100) NOT NULL,
        username VARCHAR(100) NOT NULL UNIQUE,
        password VARCHAR(255) NOT NULL, -- Store hashed password
        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
        updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
        role Enum ('ADMIN', 'MEMBER') DEFAULT 'MEMBER'
    );

DROP TABLE if exists Borrowing;
CREATE TABLE
    Borrowing (
        id INT PRIMARY KEY AUTO_INCREMENT,
        book_id INT,
        member_id INT,
        borrowed_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
        returned_at TIMESTAMP,
        CONSTRAINT FK_book_id FOREIGN KEY (book_id) REFERENCES Book (id),
        CONSTRAINT FK_member_id FOREIGN KEY (member_id) REFERENCES User (id)
    );

-- Create a trigger to check if the same book is borrowed by the same member and not returned yet
DELIMITER //
CREATE TRIGGER check_before_borrow
BEFORE INSERT ON Borrowing
FOR EACH ROW
BEGIN
    DECLARE borrow_count INT;
    SELECT COUNT(*) INTO borrow_count FROM Borrowing WHERE book_id = NEW.book_id AND member_id = NEW.member_id AND returned_at IS NULL;
    IF borrow_count > 0 THEN 
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Cannot borrow the same book that is not returned yet.';
    END IF;
END;//
DELIMITER ;