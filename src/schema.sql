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

CREATE TABLE
    Borrowing (
        id INT PRIMARY KEY AUTO_INCREMENT,
        book_id INT,
        member_id INT,
        borrowed_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
        returned_at TIMESTAMP,
        FOREIGN KEY (book_id) REFERENCES Book (id),
        FOREIGN KEY (member_id) REFERENCES User (id)
    );