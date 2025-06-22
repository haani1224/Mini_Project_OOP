import java.util.ArrayList;
import java.util.Scanner;

class Book {
    private String title;
    private String author;
    private int isbn;
    private boolean isAvailable;
    private Member borrower;

    Book(String title, String author, int isbn) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.isAvailable = true;
        this.borrower = null;
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

    public Member getBorrower() {
        return borrower;
    }

    public void setAvailable(boolean status) {
        this.isAvailable = status;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setBorrower(Member borrower) {
        this.borrower = borrower;
    }

    public void displayInfo() {
        System.out.println("Title: " + title);
        System.out.println("Author: " + author);
        System.out.println("ISBN No: " + isbn);
        System.out.print("Status: ");
        if (isAvailable) {
            System.out.print("Available");   
        } else {
            System.out.print("Not Available\n");
            System.out.println("Borrower: " + borrower.getName() + ", MemberID: " + borrower.getMemberID());
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

    public void displayPage(Library lib){
        System.out.println("Welcome" + name + " to The " + lib.getName() + " Library!!");
    }
}

class Admin extends User {
    Scanner in = new Scanner(System.in);
    int choice;
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

    @Override
    public void displayPage(Library lib){
        System.out.println("\nWelcome, Admin " + getName());
        do{
            System.out.println("Choose your action:");
            System.out.println("1. Add New Book\n2.View Book List\n0.Logout");
            System.out.print("Enter your choice: ");
            choice = in.nextInt();

            switch (choice) {
                case 1:
                    in.nextLine(); // Input buffering fix
                    System.out.print("Title: ");
                    String title = in.nextLine();
                    System.out.print("Author: ");
                    String author = in.nextLine();
                    System.out.print("ISBN: ");
                    int isbn = in.nextInt();
                    lib.addBook(title, author, isbn);
                    System.out.println("Book added!");
                    break;
                case 2:
                    lib.displayInfo();
                    break;
            }

        } while (choice != 0);
    }
}

class Member extends User {
    Scanner in = new Scanner(System.in);
    int choice;
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

    @Override
    public void displayPage(Library lib){
        System.out.println("\nWelcome, " + getName());
        do{
            System.out.println();
            System.out.println("Choose your action:");
            System.out.println("1. View Available Books\n2.Borrow A Book\n0.Logout");
            System.out.print("Enter your choice: ");
            choice = in.nextInt();

            switch (choice) {
                case 1:
                    System.out.println();
                    lib.listAvailableBook();
                    break;
                case 2:
                    System.out.println();
                    System.out.print("Enter Book's ISBN No.: ");
                    int isbn = in.nextInt();
                    lib.borrowBook(isbn, this);
                    break;
            }

        } while (choice != 0);
    }
}

class Library {
    private String name;
    private ArrayList<Book> books;

    Library(String name) {
        this.name = name;
        this.books = new ArrayList<>();
    }

    public String getName(){
        return name;
    }

    public void addBook(String title, String author, int isbn) {
        Book book = new Book(title, author, isbn);
        books.add(book);
    }

    public void borrowBook(int isbn, Member member) {
        try {
            boolean bookFound = false;
    
            for (Book book : books) {
                if (book.getIsbn() == isbn) {
                    bookFound = true;

                    if (book.isAvailable()) {
                        book.setAvailable(false);
                        book.setBorrower(member);
                        System.out.println("\nBook '" + book.getTitle() + "' has been borrowed by " + member.getName());
                    } else {
                        System.out.println("\nBook is not available.");
                    }
                    break; // stop looping after finding the book
                }
            }
    
            if (!bookFound) {
                System.out.println("\nBook with ISBN " + isbn + " not found.");
            }
    
        } catch (Exception e) {
            System.out.println("\nAn error occurred while borrowing the book.");
        }
    }    
    
    public void listAvailableBook() {
        for(int i = 0; i < books.size(); i++) {
            if (books.get(i).isAvailable()) {
                System.out.println("\nList of Available Books:");
                System.out.println((i + 1) + ". ");
                System.out.println("Title: " + books.get(i).getTitle());
                System.out.println("Author: " + books.get(i).getAuthor());
                System.out.println("ISBN No.: " + books.get(i).getIsbn());
                System.out.println();   
            }
        }
        
    }

    public void addUser(User user) {
        System.out.println("Adding: " + user.getName());
        user.displayInfo();
    }

    public void displayInfo() {
        System.out.println("\nWelcome to Library" + name);
        System.out.println("---------------------------");

        if (books.size() == 0){
            System.out.println("\nSorry, This library does not have books yet.");
        } else {
            System.out.println("\nList of books in this library:");
            for(int i = 0; i < books.size(); i++) {
                System.out.println((i + 1) + ". ");
                books.get(i).displayInfo();
                System.out.println();
            }
        }
    }
}

public class LibrarySystem {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        // code to execute (real program)
        Library lib = new Library("PSZ");
        while(true){
            System.out.println("\nWelcome to the " + lib.getName() + " library!");
            System.out.println("Select user:");
            System.out.println("1. Admin");
            System.out.println("2. Member");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            int user = in.nextInt();

            if (user == 0) {
                System.out.println("\nExiting system...");
                break; // ✅ Exit the loop so we can reach in.close()
            }

            User currentUser;

            if (user == 1) {
                currentUser = new Admin("A001", "Alice", "Admin01");
            } else {
                currentUser = new Member("M001", "Bob", "Member01");
            }

            currentUser.displayPage(lib); // Dynamically calls the correct method
        }

        in.close();

        // testing code
        // lib.addBook("Hello CPP", "Haani", 1234);
        // lib.addBook("Hello JS", "Firzana", 1334);
        // lib.displayInfo();

        // System.out.println("\nAdding Users (Admin/Member) :");
        // Admin admin1 = new Admin("A001", "Alice", "Admin01");
        // Member member1 = new Member("M001", "Bob", "Member01");

        // lib.addUser(admin1);
        // lib.addUser(member1);
    }
}