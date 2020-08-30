# BookingMovies
   Backend version of this application is exposing REST API.
## Requirements

>**JAVA**

>**Hibernate**

>**Spring MVC**

>**Maven**

>**MYSQL**

>**JUNIT**

## Installation
**JRE 1.8 or above**

**Eclipse 4.12.0**

**Mysql server**

**Apache Tomcat 8.5**

**Postman 7.31.1** 


## Application performs following tasks

- [x] **Book a ticket using a user’s name, phone number, and timings.**
- [x] **Update a ticket timing.**
- [x] **View all the tickets for a particular time.**
- [x] **Delete a particular ticket.**
- [x] **View the user’s details based on the ticket id.**
- [x] **Mark a ticket as expired if there is a diff of 8 hours between the ticket timing and current time.**
- [x] **Delete all the tickets which are expired automatically.**

### Constraint 
   For a particular time, maximum of 20 tickets can be booked.
### Use Case 1 
**BOOKING A TICKET (POST)**
```
Input format
{
	"name" : "manisha",
	"phoneNumber":"9827367123",
	"ticketTime":"2020-08-31T08:30",
	"ticketCount":4
}
```
***Description:*** If a user does not exist and the user hits the booking API, it first creates the user then, books the movies and revert with the response shown below.

BOOK TICKETS

![BOOK TICKETS ](https://github.com/rochershma/BookingMovies/blob/master1/book-tickets.PNG)


BOOKING TICKETS BUT NO SEAT LEFT
![BOOK TICKETS ](https://github.com/rochershma/BookingMovies/blob/master1/book-tickets-no-seats-left.PNG)


### Use Case 2
**UPDATE TICKET TIME (PUT)**
```
Input format
{
	"ticketId":"f8f37203-5cba-4ffa-9ef1-abb2e8c12c7a",
	"newTicketTime":"2020-08-30T08:30"
}
```
***Description:*** User will enter a ticket Id along with a new ticket time. In case if ticket does not exist in the system, it will simply revert with the message like below.

UPDATE TICKET TIME
![BOOK TICKETS ](https://github.com/rochershma/BookingMovies/blob/master1/updateTicket-update.PNG)




UPDATING TICKET TIME BUT NO SUCH TIME HAS BEEN FOUND
![BOOK TICKETS ](https://github.com/rochershma/BookingMovies/blob/master1/updateTicket-ticket-not-found.PNG)




### Use Case 3
**VIEW ALL  TICKETS FOR A PARTICULAR TIME (GET)**
```
Input format
http://localhost:8080/BookingMovies/getTickets?ticketTime=2020-08-30T08:40
```
***Description:*** User will give a ticket time, if any tickets are there for that time, it will return list of tickets, otherwise it will revert with the message.  

GETTING ALL TICKETS OF A PARTICULAR TIME
![BOOK TICKETS ](https://github.com/rochershma/BookingMovies/blob/master1/Get-All-Tickets-for-a-ticket-time.PNG)

### Use Case 4
**DELETE A PARTICULAR TICKET (DELETE)**
```
Input format
{
	"ticketId":"ef8e0205-7d96-4dda-8c72-34dad0bc5137"
}
```
***Description:*** User will input the ticket Id and System will delete the ticket if any such ticket would be found.

DELETING A TICKET
![BOOK TICKETS ](https://github.com/rochershma/BookingMovies/blob/master1/deleteTicket-deleted.PNG)



DELETING A TICKET BUT TICKET ID DOES NOT EXISTS
![BOOK TICKETS ](https://github.com/rochershma/BookingMovies/blob/master1/deleteTicket-ticket-not-found.PNG)





### Use Case 5
**VIEW THE USER’S DETAILS BASED ON THE TICKET ID (GET)**
```
Input format
http://localhost:8080/BookingMovies/getUserWithTicket?ticketId=cde637cd-f6b7-40d8-b631-64dd6e0949cd
```
***Description:*** User will input the ticket Id using get request. If there exists any such ticket, it will return the user's information like below. 

GETTING USER DETAILS WITH TICKET ID
![BOOK TICKETS ](https://github.com/rochershma/BookingMovies/blob/master1/getUserWithTicket.PNG)




### Use Case 6
**ADD USER (POST)**
```
Input format
{
	"userName":"test1",
	"phoneNumber":"7073766"
}
```
***Description:*** It will create a new user into the system. If a user exists into the system. then system will revert with the message. 

ADDING USER
![](https://github.com/rochershma/BookingMovies/blob/master1/adduser.PNG)



### Use Case 7
**GET USER (GET)**
```
Input format
http://localhost:8080/BookingMovies/getUser?phoneNumber=9604587612
```
***Description:*** It will give the information of the user provided a phone number of the user.

### ADDITIONAL FEATURE TO DELETE EXPIRED TICKETS AUTOMATICALLY

GETTING USER
![](https://github.com/rochershma/BookingMovies/blob/master1/getUser.PNG)

  The Feature of scheduled task in SPRING is used like below where a cron runs at every 5 minutes.

```
@Scheduled(cron = "*/300 * * * * *" )
	public void jobToDeleteExpiredTickets()
	{
		log.debug("starting with cron...");
		String s=ticketingService.getAndDeleteExpiredTickets();
		log.info(s);
		log.info("cron successfully ran at "+LocalDateTime.now().toString());
	}
```
   AUTOMATIC DELETION OF EXPIRED TICKETS (snapshot from server logs)
   
![](https://github.com/rochershma/BookingMovies/blob/master1/automatic-deletion-of-expired-tickets-cron.PNG)


### UNIT TESTING

unit testing has been done using JUNIT-4 and can be found in /src/main/java/com/zomentum/BookingMovies/test folder.



