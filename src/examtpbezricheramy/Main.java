package examtpbezricheramy;


import java.time.LocalDate;
import java.util.Scanner;

 

public class Main {
	public static void main(String[] args) {
		
       
        Agent agent = new Agent();
        Scanner sc = new Scanner(System.in);
        
        
        RegularUser alice = new RegularUser("Alice", "Smith", LocalDate.parse("2000-05-15"));
        VIPUser John = new VIPUser("John", "Doe", LocalDate.parse("2002-08-30"));

        Book b1 = new Book("The Lost City", "Sarah", Category.Comics);
        Copy b1c1 = new Copy(b1);
        Copy b1c2 = new Copy(b1);
        Copy b1c3 = new Copy(b1); 
        Book b2 = new Book("Secrets of the Universe", "Alex", Category.Comics); 
        Copy b2c1 = new Copy(b2);
        Copy b2c2 = new Copy(b2);
        Copy b2c3 = new Copy(b2); 
        
        
        int a=-1;
        while(a!=0) {
        	System.out.println("Library Management System : \n1- Add a new user\n2-  Add a new book to the library\n3- add a copy of a book\n4- start the day\n5- launch borrow demand\n6- end of the day\n7- search for a book\n0- exit");
        	a = sc.nextInt();
        	switch (a){
        		case 1: 
        				agent.AddNewUser();
        				System.out.println("added the user :\n"+Library.users.get(Library.users.size()-1));
        			break;
        		case 2: 
        				Book book = new Book();
        				System.out.println("give the title :");
        				sc.nextLine();
        				book.title = sc.nextLine();
        				System.out.println("give the writer :");
        				book.writer = sc.nextLine();
        				int b=0;
        				System.out.println("give number of category\n1-for Fiction \n2- for Comics  \n3-for  BioMemo \n4-for Religion\n5-for ArtPhotography\n6-for Health\n7-for History\n8-for Novel :");
        				b= sc.nextInt();
        				switch (b){
        				case 1:
        					book.category = Category.Fiction;
        					break;
        				case 2:
        					book.category = Category.Comics;
        					break;
        				case 3:
        					book.category = Category.BioMemo;
        					break;
        				case 4:
        					book.category = Category.Religion;
        					break;	
        				case 5:
        					book.category = Category.ArtPhotography;
        					break;
        				case 6:
        					book.category = Category.Health;
        					break;	
        				case 7:
        					book.category = Category.History;
        					break;	
        				case 8:
        					book.category = Category.Novel;
        					break;		
        					}
        				
        				agent.AddBook(book);
        			break;
        		case 3: 
        			
        			agent.AddNewCopy(sc.nextLine());
        			break;
        			
        			
        		case 4: agent.ProcessStartofDay();
        			break;
        			
        			
        			
		        case 5:
		        	System.out.println(" borrow demand is lanching\n ");
		        	System.out.println("give the ID of the user :  ");
						int ID = sc.nextInt();
						boolean bool =false;
						for(User user: Library.users) {
							if(user.id == ID) {
								System.out.println("give book title :");
								sc.nextLine();
								String title=sc.nextLine();
								user.Borrow(agent, title);
								bool=true;
								break;
							}
						}
						if(bool==false) {
							System.out.println("user not found");
						}
        				break;
        				
        		case 6:
				        agent.Loan();
        				agent.ProcessEndofDay();
						
        			break;
        			
        		case 7: System.out.println("give the name of the book :");
						sc.nextLine();
	        			Book bk = Library.FindBooks(sc.nextLine());
	        			break;
        				
        				
        	}
        }
        
        
        
        
        
		
	}
}	
