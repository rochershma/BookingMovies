package com.zomentum.BookingMovies.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zomentum.BookingMovies.model.Ticket;
import com.zomentum.BookingMovies.model.User;
import com.zomentum.BookingMovies.repository.TicketRepository;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Service("ticketingService")
@NoArgsConstructor
@Log4j
@AllArgsConstructor
public class TicketingServiceImpl implements TicketingService {

	@Autowired
	@Setter
	@Getter
	private TicketRepository ticketRepository;

	@Override
	public List<Ticket> getAllTickets(LocalDateTime ticketTime) {
		return ticketRepository.getAllTickets(ticketTime);
	}

	@Override
	public List<Ticket> bookTickets(String name, String phoneNumber, LocalDateTime ticketTime, int ticketCount) {
		// phone number is valid//
		// ticket time is more than the current time. invalid input

		int totalBookedTickets = getAllTickets(ticketTime).size();
		if (totalBookedTickets >= 20) {
			log.info("not enough seats left, exiting...");
			return null;
		}

		int remainingTickets = 20 - totalBookedTickets;

		if (remainingTickets < ticketCount) {
			log.info("not enough seats left, exiting...");
			return null;
		}

		User user = User.builder().userName(name).userPhoneNumber(phoneNumber).build();

		List<Ticket> bookedTickets = null;

		bookedTickets = ticketRepository.bookTickets(user, ticketTime, ticketCount);

		return bookedTickets;

	}

	@Override
	public String deleteTicket(String ticketId) {
		boolean deleted = ticketRepository.deleteTicket(ticketId);
		if (deleted)
			return "ticket has been deleted successfully";
		else
			return "the ticket is not found";
			

	}

	@Override
	public String updateTicket(String ticketId, LocalDateTime newTicketTime) {
		Ticket ticket = null;
		ticket = ticketRepository.updateTicket(ticketId, newTicketTime);
		if (ticket == null)
			return "ticket has not found";
		else
			return "ticket with ticket ID: " + ticketId + " has been successfully updated with new ticket-time : "
					+ newTicketTime.toString();
	}

	@Override
	public User getUserWithTicketId(String ticketId) {
		return ticketRepository.getUserWithTicketId(ticketId);
	}

	@Override
	public List<Ticket> getTicketsOfUser(String userPhoneNumber) {
		return ticketRepository.getTicketsOfUser(userPhoneNumber);

	}
	
	public String getAndDeleteExpiredTickets()
	{
		LocalDateTime expiredDateTime= LocalDateTime.now().minusHours(8);
		
		List<Ticket> list=ticketRepository.getAllTicketsBeforeTime(expiredDateTime);
		
		if(list==null)
			return "No tickets are eligible to be expired";
		
		else
		{
			int size=list.size();
			for(int i=0;i<list.size();i++)
			{
				deleteTicket(list.get(i).getTicketId());
			}
			return size +  " expired tickets have been deleted succesfully";	
		}
				
	}
}
