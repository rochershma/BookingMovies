package com.zomentum.BookingMovies.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder  @AllArgsConstructor @NoArgsConstructor
public class UserResponse {
	
	@Getter @Setter
	private String message;
	@Getter @Setter
	private String userName;
	@Getter @Setter
	private String phoneNumber;
}
