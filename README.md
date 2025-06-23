# Mini Project OOP - Library Management System

### Group Name: Michelin
1. [Lubna Al Haani Binti Radzuan](https://github.com/haani1224) (A23CS0107)
2. [Nur Firzana Binti Badrus Hisham](https://github.com/firzanabadrus) (A23CS0156)

<hr>

### Project Description
This project implements a basic command-line-based Library Management System in Java. The system allows users to interact with a digital library, managing books and members. It features two main user roles which is Admin and Member to interact with the library.
- Admin can add new books, view all books, and view their own information.
- Member can view available books, borrow books using ISBN numbers, and view their own information.
- The program makes use of object-oriented principles, including inheritance, polymorphism, and exception handling, to build a modular and maintainable system.

<hr>

### UML Diagram
![UML Diagram](images/UML_MiniProject_OOP.png)

<hr>

### Implementation of Chapter 5
### Implementation of Chapter 6
### Implementation of Chapter 7 - Inheritance
Inheritance allows Admin and Member to inherit common attributes and methods from the User superclass. This avoids code duplication and supports scalability.
- Both Admin and Member override the displayInfo() and displayPage() methods.
- Common attributes like userID and name are defined in User.

### Implementation of Chapter 8 - Polymorphism
Polymorphism means the same method name can behave differently based on the object.
- In this project:
- Both Admin and Member override the displayInfo() and displayPage() methods.
- Even if we call these methods using a User reference, the correct version is executed based on whether the object is an Admin or a Member.
  
### Implementation of Chapter 9 - Exception Handling
Exception handling is used to prevent the program from crashing due to invalid input (e.g., entering letters instead of numbers).
This is used in:
- The main menu
- Admin and Member menu selections
- ISBN entry for adding/borrowing books

This ensures a smooth user experience and input validation.
