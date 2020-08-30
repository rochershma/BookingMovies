package com.zomentum.BookingMovies.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zomentum.BookingMovies.model.UserRequest;
import com.zomentum.BookingMovies.model.UserResponse;
import com.zomentum.BookingMovies.service.UserManagementService;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping(value = "/")
@ComponentScan("com.zomentum")
@Log4j
@NoArgsConstructor
public class UserController {

	@Autowired
	@Getter
	@Setter
	private UserManagementService userManagementService;
	
	@RequestMapping(value = "/getUser", method = RequestMethod.GET)
	public @ResponseBody UserResponse getUser(@RequestParam("phoneNumber") String phoneNumber) {
		
		return userManagementService.getUser(phoneNumber);
	}
	
	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
	public @ResponseBody UserResponse addUser(@RequestBody UserRequest userRequest) {

		return userManagementService.addUser(userRequest.getUserName(), userRequest.getPhoneNumber());	
	}
	
}