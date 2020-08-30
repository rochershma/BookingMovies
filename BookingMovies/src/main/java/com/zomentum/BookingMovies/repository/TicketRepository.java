package com.zomentum.BookingMovies.repository;

import java.time.LocalDateTime;
import java.util.List;

import com.zomentum.BookingMovies.model.Ticket;
import com.zomentum.BookingMovies.model.User;

public interface TicketRepository {

	List<Ticket> getAllTickets(LocalDateTime ticketTime);

	List<Ticket> bookTickets(User userNew, LocalDateTime ticketTime, int numberOftickets);

	boolean deleteTicket(String ticketId);

	Ticket updateTicket(String ticketId, LocalDateTime newTicketTime);

	List<Ticket> getTicketsOfUser(String userPhoneNumber);

	User getUserWithTicketId(String ticketId);
	
	List<Ticket> getAllTicketsBeforeTime(LocalDateTime ticketTime);

}