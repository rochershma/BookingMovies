package com.zomentum.BookingMovies.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zomentum.BookingMovies.model.User;
import com.zomentum.BookingMovies.model.UserResponse;
import com.zomentum.BookingMovies.repository.UserRepository;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Service("userManagementService")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Log4j
public class UserManagementServiceImpl implements UserManagementService  {

	@Autowired
	@Getter
	@Setter
	private UserRepository userRepository;

	@Override
	public UserResponse addUser(String name, String phoneNumber) {
		User user = null;
		try {

			user = userRepository.getUser(phoneNumber);
			if (user != null)
				{
				log.info("User name : " + user.getUserName() + " Mobile-Number : " + user.getUserPhoneNumber() + " already exists");
				return  UserResponse.builder().message("below user already exists ").userName(user.getUserName()).phoneNumber(user.getUserPhoneNumber()).build();
				}
			else
				user = userRepository.setUser(name, phoneNumber);

		} catch (Exception exception) {
				log.error("Exception while trying to add a user",exception);
		}
		log.info("User Addition successful with name : "+name+" Mobile-Number : "+phoneNumber );
		return UserResponse.builder().message("User creation successful, details are below. ").userName(user.getUserName()).phoneNumber(user.getUserPhoneNumber()).build();
	}
	
	@Override
	public UserResponse getUser(String phoneNumber) {
		User user = null;
		try {
			user = userRepository.getUser(phoneNumber);
			if (user != null)
				{
				log.info("User with name : " + user.getUserName() + " Mobile-Number : " + user.getUserPhoneNumber() + " exists");
				return UserResponse.builder().message("User details are below. ").userName(user.getUserName()).phoneNumber(user.getUserPhoneNumber()).build();
				}

		} catch (Exception exception) {
				log.error("Exception while trying to get a user",exception);
		}
		log.info("No such User exists");
		return UserResponse.builder().message("The user does not exist. ").userName("").phoneNumber("").build();
	}

}
