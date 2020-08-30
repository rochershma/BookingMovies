package com.zomentum.BookingMovies.repository;

import com.zomentum.BookingMovies.model.User;

public interface UserRepository {

	User setUser(String name, String phoneNumber);

	User getUser(String phoneNumber);

}