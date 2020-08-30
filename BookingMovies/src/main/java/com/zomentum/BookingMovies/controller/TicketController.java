package com.zomentum.BookingMovies.controller;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zomentum.BookingMovies.model.BookingRequest;
import com.zomentum.BookingMovies.model.DeleteRequest;
import com.zomentum.BookingMovies.model.Ticket;
import com.zomentum.BookingMovies.model.TicketResponse;
import com.zomentum.BookingMovies.model.UpdateRequest;
import com.zomentum.BookingMovies.model.User;
import com.zomentum.BookingMovies.model.UserResponse;
import com.zomentum.BookingMovies.service.TicketingService;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping(value = "/")
@ComponentScan("com.zomentum")
@Log4j
@NoArgsConstructor
public class TicketController {

	@Autowired
	@Getter
	@Setter
	private TicketingService ticketingService;

	@RequestMapping(value = "/getTickets", method = RequestMethod.GET)
	public @ResponseBody TicketResponse getTickets(@RequestParam ("ticketTime") String ticketTime) throws ParseException {

		LocalDateTime dateTime = LocalDateTime.parse(ticketTime);

		log.info("fetching the tickets for time : " + ticketTime);

		List<Ticket> ticketList = ticketingService.getAllTickets(dateTime);
		String message = "";
		log.info("binding the reponse");
		if (ticketList == null) {
			message = " not found any ticket with given ticket-time";
			ticketList = new ArrayList<Ticket>();
		} else
			{
				if(ticketList.size()==0)
					message = " not found any ticket with given ticket-time";
				else
				message = " following tickets have been found successfully";
			}

		TicketResponse ticketResponse = TicketResponse.builder().tickets(ticketList).message(message).build();
		log.info("reverting with reponse");
		return ticketResponse;
	}
	
	@RequestMapping(value = "/bookTickets", method = RequestMethod.POST)
	public @ResponseBody TicketResponse bookTickets(@RequestBody BookingRequest bookingRequest) throws ParseException {
		
		List<Ticket> ticketList=null;
		String message="";
		LocalDateTime ticketTime=LocalDateTime.parse(bookingRequest.getTicketTime());
		ticketList = ticketingService.bookTickets(bookingRequest.getName(), bookingRequest.getPhoneNumber(),ticketTime, bookingRequest.getTicketCount());
		
		if(ticketList==null)
		{
			message=" not enough seats left ";
			ticketList= new ArrayList<Ticket>();
		}
		else
			message = " following tickets have been booked successfully ";

		TicketResponse ticketResponse = TicketResponse.builder().tickets(ticketList).message(message).build();	
		log.info("reverting with reponse");
		return ticketResponse;
	}
	
	
	@RequestMapping(value = "/updateTicket", method = RequestMethod.PUT)
	public @ResponseBody String updateTicket (@RequestBody UpdateRequest updateRequest) throws ParseException {
	
		LocalDateTime newTicketTime=LocalDateTime.parse(updateRequest.getNewTicketTime());
		return ticketingService.updateTicket(updateRequest.getTicketId(),newTicketTime);
		
	}
		
		@RequestMapping(value = "/deleteTicket", method = RequestMethod.DELETE)
		public @ResponseBody String deleteTicket (@RequestBody  DeleteRequest deleteRequest) throws ParseException {
		return ticketingService.deleteTicket(deleteRequest.getTicketId());
		}
		
		@RequestMapping(value = "/getUserWithTicket", method = RequestMethod.GET)
		public @ResponseBody UserResponse getUserWithTicketId (@RequestParam ("ticketId") String ticketId) throws ParseException {
		 
			User user=null;
			String message=" ";
			user=ticketingService.getUserWithTicketId(ticketId);
			if(user==null)
			{
				message="No such ticket found";
			    user=new User();
			}
			else
				message="Below are the user details associated with this ticket Id : " + ticketId;	
			
			UserResponse userResponse=UserResponse.builder().message(message).userName(user.getUserName()).phoneNumber(user.getUserPhoneNumber()).build();
			log.info("reverting with reponse");
			 return userResponse;
			 
		}
}