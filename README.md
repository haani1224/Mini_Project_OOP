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
![UML Diagram](UML_MiniProject_OOP.png)
(explain uml ni sikit)

<hr>

(letak contoh code sekali kat tiap implementation)
### Implementation of Chapter 5 - ArrayList
ArrayList is implemented in the Library class. 
- ArrayList of Book class is declared in the Library class and initialized in the Library class constructor.

### Implementation of Chapter 6 - Association/Aggregation/Composition
#### Association
"Uses a" relationship.
In this project, User class and its child, is associated to the Library class because its receive a Library object and call its method.

#### Composition
"Has a" relationship with a strong ownership.
In this project, Book class is owned by the Library class since the lifetime of books are dependent on Library class. The Library class is responsible for creating and managing the lifecycle of Book objects. Book does not exist outside the context of the Library.

### Implementation of Chapter 7 - Inheritance

![Inheritance](OOP_MP_Code/4_inheritance1.png)
<img src="OOP_MP_Code/4_inheritance2.png)" width="300"/> <img src="OOP_MP_Code/4_inheritance3.png" width="300"/>

Inheritance allows Admin and Member to inherit common attributes and methods from the User superclass. This avoids code duplication and supports scalability.

![inheritance](OOP_MP_Code/4_inheritance4.png)
![inheritance](OOP_MP_Code/4_inheritance5.png)
<img src="OOP_MP_Code/4_inheritance4.png" width="300"/> <img src="OOP_MP_Code/4_inheritance5.png" width="300"/>

- Both Admin and Member override the displayInfo() and displayPage() methods.
- Common attributes like name are defined in User.

### Implementation of Chapter 8 - Polymorphism
Polymorphism means the same method name can behave differently based on the object.
- In this project:

<img src="OOP_MP_Code/6_polymorphA.png" width="300"/> <img src="OOP_MP_Code/6_polymorphM.png" width="300"/>
<img src="OOP_MP_Code/3_association+4_polymorphism.png" width="300"/> <img src="OOP_MP_Code/3_association+4_polymorphism2.png" width="300"/>

- Both Admin and Member override the displayInfo() and displayPage() methods.
- Even if we call these methods using a User reference, the correct version is executed based on whether the object is an Admin or a Member.
  
### Implementation of Chapter 9 - Exception Handling
Exception handling is used to prevent the program from crashing due to invalid input (e.g., entering letters instead of numbers).
This is used in:
- The main menu
- Admin and Member menu selections
- ISBN entry for adding/borrowing books

This ensures a smooth user experience and input validation.
