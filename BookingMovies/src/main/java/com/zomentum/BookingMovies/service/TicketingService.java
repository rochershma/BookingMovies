package com.zomentum.BookingMovies.service;

import java.time.LocalDateTime;
import java.util.List;

import com.zomentum.BookingMovies.model.Ticket;
import com.zomentum.BookingMovies.model.User;

public interface TicketingService {

	List<Ticket> getAllTickets(LocalDateTime ticketTime);

	List<Ticket> bookTickets(String name, String phoneNumber, LocalDateTime ticketTime, int ticketCount);

	String deleteTicket(String ticketId);

	String updateTicket(String ticketId, LocalDateTime newTicketTime);

	User getUserWithTicketId(String ticketId);

	List<Ticket> getTicketsOfUser(String userPhoneNumber);
	
	String getAndDeleteExpiredTickets();
	
}