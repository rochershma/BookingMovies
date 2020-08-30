package com.zomentum.BookingMovies.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Component("ticketExpiryService")
@Builder
 @AllArgsConstructor @NoArgsConstructor
 @Log4j
public class TicketExpiryService {
	@Getter @Setter @Autowired
    private TicketingService ticketingService;

	@Scheduled(cron = "*/300 * * * * *" )
	public void jobToDeleteExpiredTickets()
	{
		log.debug("starting with cron...");
		String s=ticketingService.getAndDeleteExpiredTickets();
		log.info(s);
		log.info("cron successfully ran at "+LocalDateTime.now().toString());
	}
}
