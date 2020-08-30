package com.zomentum.BookingMovies.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.persistence.TypedQuery;

import org.hibernate.HibernateException;
import org.springframework.stereotype.Repository;

import com.zomentum.BookingMovies.model.Ticket;
import com.zomentum.BookingMovies.model.User;

import lombok.extern.log4j.Log4j;

@Log4j
@Repository("userRepository")
public class UserRepositoryImpl extends AbstractRepository implements UserRepository {

	@Override
	public User setUser(String name, String phoneNumber) {
		User user = new User();
		try {
			begin();

			user = User.builder().userName(name).userPhoneNumber(phoneNumber).build();

			UUID uniqueKey = UUID.randomUUID();

			user.setUserId(uniqueKey.toString());

			user.setTicketList(new ArrayList<Ticket>());
			
			getSession().saveOrUpdate(user);

			commit();

			return user;

		} catch (HibernateException hibernateException) {
			rollback();
			log.error("Hibernate Excpetion while saving user's data : ", hibernateException);
			log.debug("user with name : " + name + " was not able to persist to DB");
		}

		return user;
	}

	@Override
	public User getUser(String phoneNumber) {
		try {
			begin();
			TypedQuery<User> query = getSession().createQuery("select u from User u", User.class);

			final String phoneNumberfinal = phoneNumber;
			List<User> usersList = null;
			usersList = query.getResultStream().filter(u -> u.getUserPhoneNumber().equalsIgnoreCase(phoneNumberfinal))
					.collect(Collectors.toList());

			commit();
			if (usersList.size() == 0)
				return null;
			return usersList.get(0);

		} catch (HibernateException hibernateException) {
			rollback();
			log.error("Excpetion while retreiving user's data for user with phoneNum :" + phoneNumber + " ",
					hibernateException);
		}

		return null;
	}

}
