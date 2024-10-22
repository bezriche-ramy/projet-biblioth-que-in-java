package examtpbezricheramy;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.time.LocalDate;

// Enum for different book categories
enum Category {
	Fiction(1),
	Comics(2),
	BioMemo(3),
	Religion(4),
	ArtPhotography(5),
	Health(6),
	History(7),
	Novel(8);
	

	
	int number;
	Category(int number){
		this.number=number; //Unique identifier for the categories 
	}
}

// Base class for resources
class Resource {
	protected String id; // Unique identifier for the resource
}

// Class representing a book
class Book extends Resource {
	protected int nbrofbook=0; // Counter for the number of books
	protected String title; // Title of the book
	protected String writer; // Author of the book
	protected Category category; // Category of the book
	protected ArrayList<Copy> copies; // List of copies of the book
	
	// Default constructor
	public Book() {
		this.title = "None";
		this.id = "0";
		this.copies = new ArrayList<Copy>();
		Library.bks.add(this); // Add the book to the library's book list
	}
	
	// Parameterized constructor
	public Book(String title, String writer, Category category) {
		this.id = "B"+nbrofbook+"C"+category.number; // Generate a unique ID for the book (I remove  the fonctiion of the  name )
		this.title = title;
		this.writer=writer;
		this.category = category;
		this.copies = new ArrayList<Copy>();
		Library.bks.add(this); // Add the book to the library's book list
		nbrofbook++;
	}
}

// Class representing a copy of a book
class Copy extends Resource {
	protected int nbrofcopy=0; // Counter for the number of copies
	protected String title; // Title of the book
	protected String writer; // Author of the book
	protected Category category; // Category of the book
	protected String state; // State of the copy (available or not)
	
	// Constructor for creating a copy of a book
	public Copy(Book bk) {
		this.id = "E"+this.nbrofcopy+bk.id; // Generate a unique ID for the copy
		this.title = bk.title;
		this.writer = bk.writer;
		this.category = bk.category;
		this.state = "Available";
		bk.copies.add(this); // Add the copy to the book's list of copies
		this.nbrofcopy++;
	}
}

// Base class for library operations
class Operation {
	
}

// Class representing a loan operation
class Loan extends Operation {
	protected Copy copy_loaned; // Copy being loaned
	protected User user_borrower; // User borrowing the copy
	protected LocalDate start_loan; // Start date of the loan
	
	// Constructor for creating a loan
	public Loan(Copy cp, User user) {
		this.copy_loaned = cp;
		this.user_borrower = user;
		LocalDate start_loan = Library.current_day;
		cp.state = "borrowed"; // Change the state of the copy to borrowed
	}
	
	// String representation of the loan
	public String toString() {  
		return user_borrower.firstname+" "+user_borrower.lastname+" lent a book called " +copy_loaned.title;
	}
}

class BorrowDemand extends Operation {
	public String bookname; // Name of the book in the demand
	public User user; // User making the demand
	
	// Constructor for creating a borrow demand
	public BorrowDemand(String bookname, User user) {
		this.bookname=bookname;
		this.user =user;
	}
	
	// String representation of the borrow demand
	public String toString() {
		return user.firstname +" "+ user.lastname + " He asked for a borrow demand  for  "+bookname;
	}
}

// Main library class
public class Library {
	
	// Method to calculate the number of days between two dates for the fonction "CalculateDebtOfLoanedUsers"
	public static int daysBetweenDates(LocalDate startDate, LocalDate endDate) {
	     return (int) ChronoUnit.DAYS.between(startDate, endDate);//Preset function in import java.time.LocalDate;
	}
	
	// Current day in reality
	static public LocalDate current_day = LocalDate.now();
	
	// Lists to store books and users in the library
	static public ArrayList<Book> bks = new ArrayList<Book>();
	static public ArrayList<User> users = new ArrayList<User>();
	
	// Method to find books by name
	static Book FindBooks(String bookName) {//i was change the return value to book (i need it in the agent ..........)
        for (Book book : bks) {
            if (book.title.equalsIgnoreCase(bookName)) {
                System.out.println("the Book "+book.title+" been found and the writer is " + book.writer);
                return book;
            }
        }
        return null;
    }
	
	// Method to register a borrow demand
	public static void registerBorrowDemand(Agent agent, User user, String bookName) {
		BorrowDemand bd = new BorrowDemand(bookName, user);
		agent.borrow.add(bd);
	}
}
