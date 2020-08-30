package com.zomentum.BookingMovies.test;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;
import java.time.Month;

import org.junit.Test;

import com.zomentum.BookingMovies.service.TicketingService;
import com.zomentum.BookingMovies.service.TicketingServiceImpl;

public class DeleteTicketTestCase {

		private TicketingService ticketingService= new TicketingServiceImpl();
		@Test
		public void test() {
			String ticketID="01d97ba0-e567-40df-a325-37da807b4ef7";
			String actual = ticketingService.deleteTicket(ticketID);
			String expected = "ticket has been deleted successfully";
			assertEquals(expected, actual);
		}
}
