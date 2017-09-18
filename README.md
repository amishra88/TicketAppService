# Ticket Service Application  
This application facilitates the discovery, temporary hold, and final reservation of seats within a high-demand performance
venue.
This service provides the following functions:
* Find the number of seats available within the venue
Note: available seats are seats that are neither held nor reserved.
* Find and hold the best available seats on behalf of a customer (input recieved by the customer)
* Reserve and commit a specific group of held seats for a customer
NOTE:Each ticket expires within 5 seconds.If the customer does not make up his/her mind within 5 secs

# Assumptions:
* SeatInfo Class 
  * This class holds the Seat informations in a static hash map and initializes
  it.The hash map contains seatId as the key(of type String) and seat status as
  value(of type enum - Available , Held and Reserved)
  Hashmap has been taken because of its retrieval efficiency as there will be frequent retrival of data in this scenario.
  Key of the hashmap - seatsIds are always going to be unique and never change.
  
* SeatHold Class 
   * This class holds customer info,the array of seatIds customer wants to hold
   and when he holds it,a seatholdId is generated when the seats are held.If
   customer confirms the seats ,a confirmation number is generated.
   
* TicketServiceImpl Class
  * This class implements ticket service interface and does all the operations like evaluate available seats at any time ,
  hold seats and reserve seats and sets the properties of SeatHold object.

 

## Getting Started
To run this project:
* Clone the git Repo 
* Do a Maven -Install , Clean and Build 

### Prerequisites
* Any JAVA IDE(Eclipse,STS,NetBeans etc) and JDK (1.7 or above)
* Maven

## Running the tests
* Go to the Junit class AppTest.java
* Run the class 
* This application can also be tested by running main and giving inputs when prompted by console

* These test cover Happy Path and Error Scenarios:
 * Test for ticket service Happy Path
 * Test for Error Scenario when the time waited is more than 5 secs after seat is held
 * Test for Reset method where the seats are reset to Available from Held

## Authors

* **Archana Mishra** - *Initial work* - [TicketAppService](https://github.com/amishra88/TicketAppService)

