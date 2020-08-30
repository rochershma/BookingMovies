package com.zomentum.BookingMovies.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.zomentum.BookingMovies.model.User;
import com.zomentum.BookingMovies.service.TicketingService;
import com.zomentum.BookingMovies.service.TicketingServiceImpl;

public class GetUserWithTicketIdTestCase {

	private TicketingService ticketingService= new TicketingServiceImpl();
	@Test
	public void test() {
		//dhanesh	7073766733
		String ticketID="01d97ba0-e567-40df-a325-37da807b4ef7";
		User user = ticketingService.getUserWithTicketId(ticketID);
		String expectedName ="dhanesh";
		String expectedPhoneNumber="7073766733";
		assertEquals(expectedName, user.getUserName());
		assertEquals(expectedPhoneNumber, user.getUserPhoneNumber());
	}
		
}
