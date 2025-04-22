import java.util.*;

class Book {
    String title;
    String author;
    String isbn;
    int publicationYear;
    int availableCopies;

    public Book(String title, String author, String isbn, int publicationYear, int availableCopies) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.publicationYear = publicationYear;
        this.availableCopies = availableCopies;
    }

    public boolean borrowBook() {
        if (availableCopies > 0) {
            availableCopies--;
            return true;
        }
        return false;
    }

    public void returnBook() {
        availableCopies++;
    }

    @Override
    public String toString() {
        return title + " by " + author + " (ISBN: " + isbn + ", Year: " + publicationYear + ", Available: " + availableCopies + ")";
    }
}

class Reader {
    String name;
    String readerID;
    String registrationDate;
    String phone;
    List<BorrowingRecord> history = new ArrayList<>();

    public Reader(String name, String readerID, String registrationDate, String phone) {
        this.name = name;
        this.readerID = readerID;
        this.registrationDate = registrationDate;
        this.phone = phone;
    }

    public boolean canBorrow() {
        long currentBorrows = history.stream().filter(r -> r.returnDate == null).count();
        return currentBorrows < 3;
    }

    public void addRecord(BorrowingRecord record) {
        history.add(record);
    }

    public void showHistory() {
        System.out.println("Borrowing History for " + name + ":");
        for (BorrowingRecord r : history) {
            System.out.println(r);
        }
    }
}

class BorrowingRecord {
    Book book;
    Reader reader;
    String borrowDate;
    String returnDate;

    public BorrowingRecord(Book book, Reader reader, String borrowDate) {
        this.book = book;
        this.reader = reader;
        this.borrowDate = borrowDate;
    }

    public void returnBook(String returnDate) {
        this.returnDate = returnDate;
        book.returnBook();
    }

    @Override
    public String toString() {
        return "Book: " + book.title + ", Borrowed on: " + borrowDate + (returnDate != null ? ", Returned on: " + returnDate : " [Not yet returned]");
    }
}

class Library {
    List<Book> books = new ArrayList<>();
    List<Reader> readers = new ArrayList<>();

    public void addBook(Book book) {
        books.add(book);
    }

    public void registerReader(Reader reader) {
        readers.add(reader);
    }

    public void borrowBook(String readerID, String isbn, String borrowDate) {
        Reader reader = findReaderByID(readerID);
        Book book = findBookByISBN(isbn);

        if (reader != null && book != null) {
            if (reader.canBorrow() && book.borrowBook()) {
                BorrowingRecord record = new BorrowingRecord(book, reader, borrowDate);
                reader.addRecord(record);
                System.out.println("Book borrowed successfully.");
            } else {
                System.out.println("Cannot borrow book: limit reached or no copies available.");
            }
        }
    }

    public void returnBook(String readerID, String isbn, String returnDate) {
        Reader reader = findReaderByID(readerID);
        if (reader != null) {
            for (BorrowingRecord r : reader.history) {
                if (r.book.isbn.equals(isbn) && r.returnDate == null) {
                    r.returnBook(returnDate);
                    System.out.println("Book returned successfully.");
                    return;
                }
            }
        }
        System.out.println("Borrow record not found.");
    }

    private Reader findReaderByID(String readerID) {
        for (Reader r : readers) {
            if (r.readerID.equals(readerID)) return r;
        }
        return null;
    }

    private Book findBookByISBN(String isbn) {
        for (Book b : books) {
            if (b.isbn.equals(isbn)) return b;
        }
        return null;
    }

    public void showReaderHistory(String readerID) {
        Reader reader = findReaderByID(readerID);
        if (reader != null) {
            reader.showHistory();
        } else {
            System.out.println("Reader not found.");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Library library = new Library();

        Book b1 = new Book("Effective Java", "Joshua Bloch", "1234567890", 2018, 3);
        Book b2 = new Book("Clean Code", "Robert C. Martin", "0987654321", 2008, 2);

        Reader r1 = new Reader("Alice", "R001", "2024-01-01", "0912345678");

        library.addBook(b1);
        library.addBook(b2);
        library.registerReader(r1);

        library.borrowBook("R001", "1234567890", "2025-04-01");
        library.borrowBook("R001", "0987654321", "2025-04-02");
        library.returnBook("R001", "1234567890", "2025-04-10");
        library.showReaderHistory("R001");
    }
}
