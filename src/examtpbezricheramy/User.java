package examtpbezricheramy;
import java.util.ArrayList;
import java.time.LocalDate;

// Enumeration for different subscription plans
enum PlanSubsription {
    basic_plan,
    vip_plan
}

// Class representing a regular user, inherits from User class
class RegularUser extends User {
    
    // Constructor for RegularUser
    RegularUser(String firstname, String lastname, LocalDate birthday){
        super(firstname, lastname, birthday);
        this.plan = PlanSubsription.basic_plan;
        Library.users.add(this); // Add the regular user to the library's user list
    }
}

// Class representing a VIP user, inherits from User class
class VIPUser extends User {
    
    // Constructor for VIPUser
    VIPUser(String firstname, String lastname, LocalDate birthday){
        super(firstname, lastname, birthday);
        this.plan = PlanSubsription.vip_plan;
        Library.users.add(this); // Add the VIP user to the library's user list
    }
}

// Main User class
public class User {
    static int usernmbr = 0; // Counter for generating unique user IDs
    protected int id; // User ID
    protected String firstname, lastname; // User's first and last name
    protected LocalDate birthday; // User's birthday
    protected int nbrBooks = 0; // Number of books borrowed by the user
    protected ArrayList<Copy> borrowed; // List of copies borrowed by the user
    protected int dept = 0; // User's debt
    
    protected PlanSubsription plan; // User's subscription plan
    
    // Constructor for User class
    public User(String firstname, String lastname, LocalDate birthday){
        this.id = usernmbr;
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthday = birthday;
        this.borrowed = new ArrayList<Copy>() ; // Initialize the list of borrowed copies
        usernmbr++;
    }
    
    // String representation of the user
    public String toString() {
        return "the id is : " + id + " " + "\nthe name is : " + firstname + " " + lastname + "\nnumber of borrowed books :" + nbrBooks;
    }
    
    // Method to find a book by its title
    public void findBookPerTitle(String booktitle) {
        for (Book book : Library.bks) {
            if (book.title.equalsIgnoreCase(booktitle)) {
                System.out.println("Book found: " + book.title);
                System.out.println("Number of copies: " + book.copies.size());
                break;
            }
        }
    }
    
    // Method to find books by category
    public void findBooksPerCategory(Category category) {
        for (Book book : Library.bks) {
            if (book.category == category) {
                System.out.println(book.title + " | ");
            }
        }
    }
    
    // Method for a user to borrow a book
    public void Borrow(Agent agent, String bookName) {
        Library.FindBooks(bookName);
        Library.registerBorrowDemand(agent, this, bookName);
    }
}

