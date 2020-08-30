package com.zomentum.BookingMovies.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor @AllArgsConstructor @Builder
public class UpdateRequest {
	@Getter @Setter 
	private String ticketId;
	@Getter @Setter 
	private String newTicketTime;

}