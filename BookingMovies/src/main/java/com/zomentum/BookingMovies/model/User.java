package com.zomentum.BookingMovies.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user")
@Builder @NoArgsConstructor @AllArgsConstructor
public class User {
	@Id
	@Column(name = "user_id", nullable = false, unique = true)
	@Getter
	@Setter
	@JsonIgnore
	private String userId;
	
	@Column(name = "user_name", nullable = false)
	@Getter
	@Setter
	private String userName;
	
	@Column(name = "user_phone_number", nullable = false)
	@Getter
	@Setter
	private String userPhoneNumber;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	@Setter
	@Getter
	@JsonIgnore
	private List<Ticket> ticketList;
	
}