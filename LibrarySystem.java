import java.util.ArrayList;
import java.util.Scanner;



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

class User {
    private String userID;
    private String name;

    User(String userID, String name) {
        this.userID = userID;
        this.name = name;
    }

    public String getUserID() {
        return userID;
    }

    public String getName() {
        return name;
    }

    // Provides a default implementation. Subclasses can override this method
    public void displayInfo() {
        System.out.println("\n---User Information---");
        System.out.println("User ID: " + userID);
        System.out.println("Name: " + name);
    }
}

class Admin extends User {
    private String adminID;

    Admin(String userID, String name, String adminID) {
        super(userID, name);
        this.adminID = adminID;
    }

    public String getAdminID() {
        return adminID;
    }

    @Override
    // Provides specific implementation displayInfo() for Admin
    public void displayInfo() {
        super.displayInfo();
        System.out.println("\n---Admin Information---");
        System.out.println("Admin ID: " + adminID);
        System.out.println("Name: " + getName());
        System.out.println();
    }
}

class Member extends User {
    private String memberID;

    Member(String userID, String name, String memberID) {
        super(userID, name);
        this.memberID = memberID;
    }

    public String getMemberID() {
        return memberID;
    }

    @Override
    // Provides specific implementation displayInfo() for Member
    public void displayInfo() {
        super.displayInfo();
        System.out.println("\n---Member Information---");
        System.out.println("Member ID: " + memberID);
        System.out.println("Name: " + getName());
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

    public void addUser(User user) {
        System.out.println("Adding: " + user.getName());
        user.displayInfo();
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
        // Scanner sc = new Scanner(System.in);
        // code to execute

        // testing code
        Library lib = new Library("PSZ");
        lib.addBook("Hello CPP", "Haani", 1234);
        lib.addBook("Hello JS", "Firzana", 1334);
        lib.displayInfo();

        System.out.println("\nAdding Users (Admin/Member) :");
        Admin admin1 = new Admin("A001", "Alice", "Admin01");
        Member member1 = new Member("M001", "Bob", "Member01");

        lib.addUser(admin1);
        lib.addUser(member1);
    }
}