package com.zomentum.BookingMovies.test;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;
import java.time.Month;

import org.junit.Test;

import com.zomentum.BookingMovies.service.TicketingService;
import com.zomentum.BookingMovies.service.TicketingServiceImpl;

public class UpdateTicketTestCase {
	private TicketingService ticketingService= new TicketingServiceImpl();
	@Test
	public void test() {
		LocalDateTime newTicketTime= LocalDateTime.of(2020,Month.AUGUST,31,14,00);
		String actual = ticketingService.updateTicket("01d97ba0-e567-40df-a325-37da807b4ef7", newTicketTime);
		String expected = "ticket with ticket ID: " + "01d97ba0-e567-40df-a325-37da807b4ef7" + " has been successfully updated with new ticket-time : "
				+ newTicketTime.toString();
		assertEquals(expected, actual);
	}
}
