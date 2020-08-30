package com.zomentum.BookingMovies.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder  @AllArgsConstructor @NoArgsConstructor
public class BookingRequest {
	
	@Getter @Setter
	private String name;
	@Getter @Setter
	private String phoneNumber;
	@Getter @Setter
	private String ticketTime;
	@Getter @Setter
	private int ticketCount;
}
