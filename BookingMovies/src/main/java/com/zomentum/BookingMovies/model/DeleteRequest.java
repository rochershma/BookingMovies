package com.zomentum.BookingMovies.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder @AllArgsConstructor @NoArgsConstructor
public class DeleteRequest {
	
	@Getter @Setter
	private String ticketId;

}
