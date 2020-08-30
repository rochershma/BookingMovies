import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.zomentum.BookingMovies.model.Ticket;
import com.zomentum.BookingMovies.model.User;
import com.zomentum.BookingMovies.service.TicketingService;
import com.zomentum.BookingMovies.service.UserManagementService;

public class App {

	public static void main(String[] args) {
		
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
		
		UserManagementService ums= applicationContext.getBean(UserManagementService.class,"userManagementService");	
		User user= null;
		List<Ticket> list= new ArrayList<>();
		
		
//		System.out.println(ums.addUser("mani", 7073766));
		
		TicketingService ts= applicationContext.getBean(TicketingService.class,"ticketingService");
		
		LocalDateTime ldt= LocalDateTime.of(2020, Month.AUGUST,30,8,10);
		
		
		
		List<Ticket> lt= null;
		//lt=ts.bookTickets("mani", 7073766, ldt, 3);
		
	
		
		/*
		 * User user1=ts.getUserWithTicketId("f8f37203-5cba-4ffa-9ef1-abb2e8c12c7b");
		 * if(user1!=null)
		 * System.out.println(user1.getUserId()+" :  "+user1.getUserName()+" :  "+user1.
		 * getUserPhoneNumber()+" :  "+user1.getTicketList().size()); else
		 * System.out.println("no user found");
		 */
		/*
		 * if(lt!=null) { for(Ticket t:lt) {
		 * System.out.println(t.getTicketId()+" "+t.getTicketTime().toString()+" "+t.
		 * getUser().getUserName()+" "); } }
		 */
				
		//System.out.println(ums.getUser("7073766"));
		
		//lt=ts.bookTickets("mani", 7073766, ldt, 18);
		
		
		//System.out.println( ts.updateTicket("f8f37203-5cba-4ffa-9ef1-abb2e8c12c7a", LocalDateTime.of(2020, Month.AUGUST,30,8,20)));
		
		/*
		 * lt=ts.getAllTickets(ldt);
		 * 
		 * if(lt!=null) { for(Ticket t:lt) {
		 * System.out.println(t.getTicketId()+" "+t.getTicketTime().toString()+" "+t.
		 * getUser().getUserName()+" "); } }
		 */
		System.out.println(ts.deleteTicket("ef8e0205-7d96-4dda-8c72-34dad0bc5137"));
		User user2= null;
		List<Ticket> list2= new ArrayList<>();
		
		
	//	list2=ts.bookTickets("test1", 9602832, LocalDateTime.of(2020, Month.AUGUST,29,8,10),5);
		
		
	}
	
}
