package examtpbezricheramy;

import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;

public class Agent {
    Scanner sc = new Scanner(System.in);

    // Constants for fine amounts
    public final int basic_fine = 250;
    public final int vip_fine = 90;

    // Lists to store borrow demands and loaned books
    public ArrayList<BorrowDemand> borrow;
    public ArrayList<Loan> loan;

    // Constructor for Agent class
    public Agent() {
        borrow = new ArrayList<BorrowDemand>(); // i can't create a arrayliste de type opiration -> i create 2 liste one of BorrowDemands and the second for Loans 
        loan = new ArrayList<Loan>();
    }

    // Method to clear the borrow list at the start of the day
    public void ProcessStartofDay() {
        borrow.clear();
    }

    // Method to process the end of the day, display reports, and calculate debts
    public void ProcessEndofDay() {
        displayOperationReport();
        CalculateDebtOfLoanedUsers();
    }

    // Method to add a book to the library
    public void AddBook(Book bk) {
        Library.bks.add(bk);
    }

    // Method to add a new copy of a book
    public void AddNewCopy(String title) {
        Book book= Library.FindBooks(title);
        Copy copy = new Copy(book);
        book.copies.add(copy);
    }

    // Method to add a new user with scanners to the library
    public void AddNewUser() {
        System.out.println("give the first name :");
        String firstname = sc.nextLine();
        
        System.out.println("give the last name :");
        String lastname = sc.nextLine();
        System.out.println("give the birth date (yyyy-MM-dd):");
        LocalDate birthdate = LocalDate.parse(sc.nextLine());
        System.out.println("enter 1 for basic plan , 2 for vip");
        int plan = sc.nextInt();

        if (plan == 1) { // 1 for reguler user
            RegularUser user = new RegularUser(firstname, lastname, birthdate);
        } else {// 2 for VIP user
            VIPUser user = new VIPUser(firstname, lastname, birthdate);
        }
    }

    // Method to display the daily operation report
    public void displayOperationReport() {
        System.out.println("Operation Report : ");

    
        System.out.println("the borrow demands we have are :");
        for (BorrowDemand bd : this.borrow) {
            System.out.println(bd.toString());
        }
        System.out.println("the books loaned are :");
        for (Loan ln : this.loan) {
            System.out.println(ln.toString());
        }

    }

    // Method to calculate the debt of loaned users
    public void CalculateDebtOfLoanedUsers() {
        int days;
        for (Loan loan : loan) {
            loan.user_borrower.dept = 0; // this for in the start of the day the agent calcule all dept from 0 (i know it is not "optimiser")
            switch (loan.user_borrower.plan) {
                case basic_plan:
                    days = Library.daysBetweenDates(loan.start_loan, Library.current_day);
                    if (days > 14) {
                        loan.user_borrower.dept += (days - 14) * this.basic_fine;
                    }
                    break;
                case vip_plan:
                    days = Library.daysBetweenDates(loan.start_loan, Library.current_day);
                    if (days > 30) {
                        loan.user_borrower.dept += (days - 30) * this.vip_fine;
                    }
            }
        }
        Library.current_day.plusDays(1);
    }

 // Method to process the loaning of books
    public void Loan() {
        // Loop through each borrow demand
        for (BorrowDemand bd : this.borrow) {
            // Find the book associated with the borrow demand
            Book book = Library.FindBooks(bd.bookname);
            jmp: // Label for the jump (similar to goto in some other languages)
            // Loop through each copy of the book
            for (Copy bk : book.copies) {
                // Check if the copy is available
                if (bk.state.equals("Available")) {
                    // Switch based on the user's plan
                    switch (bd.user.plan) {
                        case basic_plan:
                            // Check conditions for lending to a basic plan user
                            if (bd.user.nbrBooks < 2 && bd.user.dept == 0) {
                                bd.user.borrowed.add(bk);
                                bd.user.nbrBooks++;
                                bk.state = "borrowed";
                                Loan ln = new Loan(bk, bd.user);
                                ln.start_loan = Library.current_day;
                                loan.add(ln);
                                // Print a message indicating the loan
                                System.out.println(ln.user_borrower.firstname + " lent " + bk.title);
                                // Break to jump out of the loop labeled by jmp
                                break jmp;
                            } else {
                                // Print a message indicating that the book cannot be loaned
                                System.out.println(bd.user.firstname+"can not  lent " + bk.title );
                                // Break to jump out of the loop labeled by jmp 
                                break jmp;
                            }
                        case vip_plan:
                            // Check conditions for lending to a VIP plan user
                            if (bd.user.nbrBooks < 5 && bd.user.dept == 0) {
                                bd.user.borrowed.add(bk);
                                bd.user.nbrBooks++;
                                bk.state = "borrowed";
                                Loan ln = new Loan(bk, bd.user);
                                ln.start_loan = Library.current_day;
                                loan.add(ln);
                                // Print a message indicating the loan
                                System.out.println(ln.user_borrower.firstname + " lent " + bk.title);
                                // Break to jump out of the loop labeled by jmp
                                break jmp;
                            } else {
                                // Print a message indicating that the book cannot be loaned
                                System.out.println(bd.user.firstname+"can not  lent " + bk.title );
                                // Break to jump out of the loop labeled by jmp
                                break jmp;
                            }
                    }
                }
            }
        }
    }
}
