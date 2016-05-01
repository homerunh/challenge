# Lottery Machine Challenge

### How to run my Lottery Machine

- From a command prompt:
	- Navigate to the `src/` directory of this project
	- Compile the java classes with this following command
		```
		javac -cp . challenge/*.java
		```
		- Run the Main function in the LotterySimulator class using the following command:
		``` 
		java -cp . challenge.LotterySimulator
		```

- From Eclipse IDE:
	- Run the `challenge` package as a Java Application.

### My Implementation Details
- `LotterySimulator` has the `main()` function that runs the Lottery Simulation.
    - It sells all the tickets.
    - Completes a drawing.
    - and produces a report.
- `LotteryTicket` is an abstract base class for the various 'PICK' lottery tickets.
- `Pick3Ticket`, `Pick4Ticket`, `Pick5Ticket` all extend `LotteryTicket`.
    - and they each override the `toString()` method. 
- `LotteryTicketType` is an `Enum` that defines _**ALL**_ the ticket types.  This is important to the functionality when we do anything to all the ticket types, such as drawing the winning tickets.
- There are numerous `Constants` files that also help drive the execution of this simulation.  This is so that we can easily change things about how the Lottery Machine or the Simulation should behave in the future.
- There are `Generators` that manage creation of `LotteryCustomers` and `LotteryTicket`s.
- Each customer will always purchase at least 1 ticket, even though the problem statement says: 
    >Customers _**may**_ want to purchase between 1 and 5 lottery tickets.
- Each `LotteryTicket` has an array of numbers that represent the lottery ticket.  The problem statement says we should just generate a _single_ random number for the lottery tickets, but I went with the array implementation trying to think ahead a little bit.
    - I felt this implementation decision was more flexible when thinking about adding new functionality (ticket types) in the future.
    - for example, if we had to support PowerBall tickets...  We could still use `LotteryTicket` as the base class and create a new `PowerBall` class which has an array of numbers representing the PowerBall ticket.  For a power ball ticket, each number can range between 0 and 70-something (I think?) and the power ball number has its own special range (between 1 and 64 or something?).  I could look up the powerball specifics, but I put my time towards coding instead. :shipit: 

### Assumptions made

- A (Unique) Customer will only attempt to purchase a sold out ticket type ONE time.  For all subsequent attempted ticket purchases, a customer will only purchase tickets from remaining ticket types.  
    -  For example, here is a possible scenario:  Only Pick5 tickets remain (Pick3 and Pick4 are both sold out).  A customer randomly decides (attempts) to purchase a Pick3 ticket (from Pick3, Pick4, and Pick5 as the possible types of tickets).  Next, the customer randomly decides (attempts) to purchase a Pick4 ticket (from Pick4 and Pick5 tickets as the possible ticket types).  Finally, the customer 'randomly' decides to purchase a Pick5 ticket (from Pick5 as the only available ticket type).
