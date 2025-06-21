import java.util.ArrayList;



class Book {
    private String title;
    private String author;
    private int isbn;
    private boolean isAvailable;

    Book(String title, String author, int isbn) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.isAvailable = true;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getIsbn() {
        return isbn;
    }

    public void setAvailable(boolean status) {
        this.isAvailable = status;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void displayInfo() {
        System.out.println("Title: " + title);
        System.out.println("Author: " + author);
        System.out.println("ISBN No: " + isbn);
        System.out.print("Status: ");
        if (isAvailable) {
            System.out.print("Available");   
        } else {
            System.out.print("Not Available");
        }
        System.out.println();
    }
}

class Library {
    private String name;
    private ArrayList<Book> books;

    Library(String name) {
        this.name = name;
        this.books = new ArrayList<>();
    }

    public void addBook(String title, String author, int isbn) {
        Book book = new Book(title, author, isbn);
        books.add(book);
    }
    
    public void listAvailableBook() {
        for(int i = 0; i < books.size(); i++) {
            if (books.get(i).isAvailable()) {
                System.out.println((i + 1) + ". ");
                System.out.println(books.get(i).getTitle());
                System.out.println(books.get(i).getAuthor());
                System.out.println(books.get(i).getIsbn());
                System.out.println();   
            }
        }
        
    }

    public void displayInfo() {
        System.out.println();
        System.out.println("Welcome to Library" + name);
        System.out.println("---------------------------");
        System.out.println("List of books in this library:");
        for(int i = 0; i < books.size(); i++) {
            System.out.println((i + 1) + ". ");
            books.get(i).displayInfo();
            System.out.println();
        }
    }
}

public class LibrarySystem {
    public static void main(String[] args) {
        // code to execute

        // testing code
        Library lib = new Library("PSZ");
        lib.addBook("Hello CPP", "Haani", 1234);
        lib.addBook("Hello JS", "Firzana", 1334);
        lib.displayInfo();
    }
}