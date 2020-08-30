
package com.zomentum.BookingMovies.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder @NoArgsConstructor @AllArgsConstructor
public class TicketResponse {

	@Setter
	@Getter
	private String message;
	@Setter
	@Getter
	private List<Ticket> tickets;

}