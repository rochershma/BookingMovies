package com.zomentum.BookingMovies.service;

import com.zomentum.BookingMovies.model.UserResponse;

public interface UserManagementService {

	UserResponse addUser(String name, String phoneNumber);

	UserResponse getUser(String phoneNumber);

}