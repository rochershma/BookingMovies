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
	}
	
}
