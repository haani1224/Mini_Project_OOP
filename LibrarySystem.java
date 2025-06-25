import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

// Book Class
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

// Superclass User
class User {
    private String name;

    User( String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    // Provides a default implementation. Subclasses can override this method
    public void displayInfo() {
        System.out.println("\n---User Information---");
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

    Admin(String name, String adminID) {
        super( name);
        this.adminID = adminID;
    }

    public String getAdminID() {
        return adminID;
    }

    @Override
    // Provides specific implementation displayInfo() for Admin
    public void displayInfo() {
        super.displayInfo();
        System.out.println("User Type: Admin");
        System.out.println("Admin ID: " + adminID);
        System.out.println();
    }

    @Override
    public void displayPage(Library lib){
        System.out.println("\nWelcome, Admin " + getName());
        do{
            System.out.println("\nChoose your action:");
            System.out.println("1. Add New Book\n2. View Book List\n3. Display Information Details\n0. Logout");
            // System.out.print("\nEnter your choice: ");
            // choice = in.nextInt();
            
            try {
                System.out.print("\nEnter your choice: ");
                choice = in.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                in.nextLine();
                continue;
            }

            switch (choice) {
                case 1:
                    in.nextLine(); // Input buffering fix
                    System.out.print("Title: ");
                    String title = in.nextLine();
                    System.out.print("Author: ");
                    String author = in.nextLine();
                    try {
                        System.out.print("ISBN: ");
                        int isbn = in.nextInt();
                        lib.addBook(title, author, isbn);
                        System.out.println("Book added!");
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid ISBN format. Book not added.");
                        in.nextLine(); // Clear the invalid input
                    }
                    break;
                case 2:
                    lib.displayInfo();
                    break;
                case 3:
                    this.displayInfo();
                    break;
                case 0:
                    System.out.println("Logging out...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    continue;
            }

        } while (choice != 0);
    }
}

class Member extends User {
    Scanner in = new Scanner(System.in);
    int choice;
    private String memberID;

    Member(String name, String memberID) {
        super(name);
        this.memberID = memberID;
    }

    public String getMemberID() {
        return memberID;
    }

    @Override
    // Provides specific implementation displayInfo() for Member
    public void displayInfo() {
        super.displayInfo();
        System.out.println("User Type: Member");
        System.out.println("Member ID: " + memberID);
        System.out.println();
    }

    @Override
    public void displayPage(Library lib){
        System.out.println("\nWelcome, " + getName());
        do{
            System.out.println("\nChoose your action:");
            System.out.println("1. View Available Books\n2. Borrow A Book\n3. Display Information Details\n0. Logout");
            // System.out.print("\nEnter your choice: ");
            // choice = in.nextInt();
            try {
                System.out.print("\nEnter your choice: ");
                choice = in.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                in.nextLine();
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.println();
                    lib.listAvailableBook();
                    break;
                case 2:
                    System.out.println();
                    try {
                        System.out.print("Enter Book's ISBN No.: ");
                        int isbn = in.nextInt();
                        lib.borrowBook(isbn, this);
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid ISBN format.");
                        in.nextLine(); // Clear the invalid input
                    }
                    break;
                case 3:
                    this.displayInfo();
                    break; 
                case 0:
                    System.out.println("Logging out...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    continue;
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
    }    
    
    public void listAvailableBook() {
        System.out.println("\nList of Available Books:");
        for(int i = 0; i < books.size(); i++) {
            if (books.get(i).isAvailable()) {
                System.out.println((i + 1) + ". ");
                System.out.println("Title: " + books.get(i).getTitle());
                System.out.println("Author: " + books.get(i).getAuthor());
                System.out.println("ISBN No.: " + books.get(i).getIsbn());
                System.out.println();   
            } else {
                break;
            }
        }
        System.out.println("\nSorry, there is no available book yet. Please come again later!");
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
        Library lib = new Library("PSZ"); // Create a library

        // Add few books before start
        lib.addBook("Hello CPP", "Haani", 1234);
        lib.addBook("Hello JS", "Firzana", 1334);

        while(true){
            // Prompt user to select a type of user
            System.out.println("\nWelcome to the " + lib.getName() + " library!");
            System.out.println("Select user:");
            System.out.println("1. Admin");
            System.out.println("2. Member");
            System.out.println("0. Exit");
            
            int user = -1;
            while (true) {
                try {
                    System.out.print("\nEnter your choice: ");
                    user = in.nextInt();
                    if (user < 0 || user > 2) {
                        System.out.println("Invalid choice. Please enter 0, 1, or 2.");
                        continue;
                    }
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a number.");
                    in.nextLine(); // Clear the invalid input
                }
            }


            if (user == 0) {
                System.out.println("\nExiting system...");
                break; // Exit the loop 
            }

            User currentUser;

            if (user == 1) {
                currentUser = new Admin("Alice", "A0001");
            } else {
                currentUser = new Member( "Bob", "M0001");
            }

            currentUser.displayPage(lib); // Dynamically calls the correct method
        }

        in.close();
    }
}
