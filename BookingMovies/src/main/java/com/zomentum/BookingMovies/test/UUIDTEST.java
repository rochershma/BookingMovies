package com.zomentum.BookingMovies.test;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.UUID;

public class UUIDTEST {
	
	  public static void main(String[] args) {
	    UUID uniqueKey = UUID.randomUUID();
	    LocalDateTime dateTime=null;
	    
	    dateTime=LocalDateTime.parse("2018-03-30T13:34");
	    
	    System.out.println(LocalDateTime.now().minusHours(8));
	    
	    

	    System.out.println(dateTime);
	    
	  }
	}

