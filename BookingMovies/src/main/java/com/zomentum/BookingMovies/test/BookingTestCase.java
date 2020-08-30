package com.zomentum.BookingMovies.test;


import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

import org.junit.Test;

import com.zomentum.BookingMovies.model.Ticket;
import com.zomentum.BookingMovies.service.TicketingService;
import com.zomentum.BookingMovies.service.TicketingServiceImpl;

public class BookingTestCase {

	private TicketingService ticketingService= new TicketingServiceImpl();
	
	@Test
	public void test() {
		LocalDateTime ticketTime= LocalDateTime.of(2020,Month.AUGUST,31,12,00);
		List<Ticket> listTickets = ticketingService.bookTickets("manisha", "7073766730", ticketTime, 5);
		int actual=listTickets==null?0:listTickets.size();
		int expected = 5;
		assertEquals(expected, actual);
	}
}