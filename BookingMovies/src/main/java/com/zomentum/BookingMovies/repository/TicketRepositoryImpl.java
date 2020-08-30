package com.zomentum.BookingMovies.repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.TypedQuery;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.zomentum.BookingMovies.model.Ticket;
import com.zomentum.BookingMovies.model.User;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
@Repository("ticketRepository")
public class TicketRepositoryImpl extends AbstractRepository implements TicketRepository {

	@Autowired
	@Getter
	@Setter
	private UserRepository userRepository;

	@Override
	public List<Ticket> getAllTickets(LocalDateTime ticketTime) {

		List<Ticket> ticketList = new ArrayList<>();
		try {
			begin();
			log.debug("beginning the txn");
			TypedQuery<Ticket> query = getSession().createQuery("select t from Ticket t", Ticket.class);

			ticketList.addAll(query.getResultStream().filter(t -> t.getTicketTime().isEqual(ticketTime))
					.collect(Collectors.toList()));
			commit();
			log.debug("commiting the txn");

		} catch (HibernateException hibernateException) {
			rollback();
			log.error("Excpetion while getting tickets for time : " + ticketTime.toString() + " ", hibernateException);
		}
		return ticketList;
	}
	
	@Override
	public List<Ticket> bookTickets(User userNew, LocalDateTime ticketTime, int numberOftickets) {

		List<Ticket> list = new ArrayList<>();

		User user = null;

		try {
			user = userRepository.getUser(userNew.getUserPhoneNumber());
			if (user == null) {
				log.info("there exists no such user so creating a new user");
				user = userRepository.setUser(userNew.getUserName(), userNew.getUserPhoneNumber());
			}

			for (int i = 0; i < numberOftickets; i++) {
				Ticket ticket = Ticket.builder().ticketTime(ticketTime).build();
				ticket.setUser(user);
				user.getTicketList().add(ticket);
				list.add(ticket);
			}
			log.debug("beginning the txn");
			begin();
			getSession().saveOrUpdate(user);
			commit();
			log.debug("commiting the txn");

		} catch (HibernateException hibernateException) {
			rollback();
			log.error("Excpetion while booking the tickets ", hibernateException);
		}

		return list;

	}

	@Override
	public boolean deleteTicket(String ticketId) {
		Ticket ticket = null;
		User user = null;
		log.info("starting to delete the ticket with t-id : " + ticketId);
		try {
			begin();
			ticket = getSession().get(Ticket.class, ticketId);
			commit();
			if (ticket != null) {
				begin();
				user = ticket.getUser();
				user.getTicketList().remove(ticket);
				getSession().saveOrUpdate(user);
				commit();
				return true;
			} 
			else
				return false;

		} catch (HibernateException hibernateException) {
			rollback();
			log.error("Excpetion while deleting the ticket ID : " + ticketId + " ", hibernateException);
		}

		return false;
	}

	@Override
	public Ticket updateTicket(String ticketId, LocalDateTime newTicketTime) {
		Ticket ticket = null;
		log.info("t-id : " + ticketId + " &  newTime:  " + newTicketTime.toString());
		try {
			log.debug("beginning the txn");
			begin();
			ticket = getSession().get(Ticket.class, ticketId);

			if (ticket != null) {
				ticket.setTicketTime(newTicketTime);
				getSession().saveOrUpdate(ticket);
			}
			commit();
			log.debug("commiting the txn");

		} catch (HibernateException hibernateException) {
			rollback();
			log.error("Excpetion while updating the ticket ID : " + ticketId + " ", hibernateException);
		}
		return ticket;
	}

	@Override
	public List<Ticket> getTicketsOfUser(String userPhoneNumber) {
		
		List<Ticket> ticketsUserList = null;

		try {
			begin();
			log.info("beginning the txn");

			TypedQuery<Ticket> query = getSession().createQuery("select t from Ticket t", Ticket.class);

			ticketsUserList = query.getResultStream().filter(t -> t.getUser().getUserPhoneNumber().equalsIgnoreCase(userPhoneNumber))
					.collect(Collectors.toList());
			commit();
			log.info("commiting the txn");

		} catch (HibernateException he) {
			rollback();
			log.error("Excpetion in retreiving weekly package data for similar properties", he);
		}
		return ticketsUserList;
	}

	@Override
	public User getUserWithTicketId(String ticketId) {
		User user = null;
		Ticket ticket = null;

		try {
			log.info("beginning the txn");
			begin();

			ticket = getSession().get(Ticket.class, ticketId);

			if (ticket != null) {
				user = ticket.getUser();
			}
			commit();
			log.info("commiting the txn");

		} catch (HibernateException he) {
			rollback();
			log.error("Excpetion in getting user data for given Ticket ID package "+ticketId+" ", he);
		}
		return user;
	}
	
	@Override
	public List<Ticket> getAllTicketsBeforeTime(LocalDateTime ticketTime) {
    	
		List<Ticket> ticketList=null;
    			
    			try {
    				begin();
    				log.info("beginning the txn");
    				TypedQuery<Ticket> query = getSession().createQuery("select t from Ticket t", Ticket.class);
    				
    				ticketList = query.getResultStream()
    						.filter(t -> (t.getTicketTime().isBefore(ticketTime) || t.getTicketTime().isEqual(ticketTime))).collect(Collectors.toList());
    				commit();
    				log.info("commiting the txn");
    				
    			} 
    			catch (HibernateException he) {
    				rollback();
    				log.error("Excpetion in retreiving weekly package data for similar properties", he);
    			}
    			return ticketList;
    		}

}
