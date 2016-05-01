/**
 *  Ryan Harrigan
 */
package challenge;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 
 * This is the LotteryMachine Class.  Its purpose is to :
 * 
 * Represent the lottery ticket machine.
 * 
 * @author Ryan Harrigan
 * @version Apr 21, 2016
 *
 */
public class LotteryMachine {
  //Track the sold tickets:
  //key is the LotteryTicket.toString()
  //value is the LotteryTicket itself.
  private Map<String, LotteryTicket> soldPick3Tickets = null;
  private Map<String, LotteryTicket> soldPick4Tickets = null;
  private Map<String, LotteryTicket> soldPick5Tickets = null;
  
  //Track all lottery customers:
  //key is the customer ID
  //value is the customer himself/herself
  private Map<Integer, LotteryCustomer> allLotteryCustomers = null;
  
  //Track all owner of each and every lottery ticket sold:
  //key is the LotteryTicket.toString()
  //value is the LotteryCustomer himself/herself
  private Map<String, LotteryCustomer> lotteryTicketOwners = null;
  
  //Track winning lottery tickets... can be 1 and only 1 winner per type.
  //key is the (winning) lottery ticket
  //value is the (winning) lottery customer
  private Map<LotteryTicket, LotteryCustomer> lotteryWinners = null;
  
  //Has a Lottery Ticket Generator
  private LotteryTicketGenerator ticketGenerator = null;
  
  /**
   * Default constructor
   * 
   * Initialize class variables.
   */
  public LotteryMachine() {
    setSoldPick3Tickets(new HashMap<String, LotteryTicket>());
    setSoldPick4Tickets(new HashMap<String, LotteryTicket>());
    setSoldPick5Tickets(new HashMap<String, LotteryTicket>());
    setAllLotteryCustomers(new HashMap<Integer, LotteryCustomer>());
    setLotteryTicketOwners(new HashMap<String, LotteryCustomer>());
    setLotteryWinners(new HashMap<LotteryTicket, LotteryCustomer>());
    setTicketGenerator(new LotteryTicketGenerator());
  }

  /*********************** CLASS METHODS ***************************************/
  
  /**
   * Sell all the tickets in the Lottery Machine
   * @param customerGenerator
   */
  public void sellTickets(LotteryCustomerGenerator customerGenerator) {
    //local variables
    LotteryCustomer someCustomer = null;
    LotteryTicketType randomType = null;
    LotteryTicket ticket = null;
    
    //while the machine is not sold out
    while (!isMachineSoldOut()) {
      //generate a customer
      someCustomer = customerGenerator.generateLotteryCustomer();
      int desiredTickets = someCustomer.getTicketsDesired();
      
      //add the customer
      allLotteryCustomers.put(someCustomer.getCustomerId(), someCustomer);
      for (int i = 0; i < desiredTickets && !isMachineSoldOut(); i++) {
        //generate a ticket
        randomType = getValidRandomTicketType(someCustomer);
        ticket = ticketGenerator.generateUniqueLotteryTicket(randomType, getSoldTickets(randomType));
        
        //do bookkeeping:
        //mark ticket as sold
        getSoldTickets(randomType).put(ticket.toString(), ticket);
        //add it to the customer's list of purchased tickets
        someCustomer.addLotteryTicket(ticket);
        //and remember the lottery ticket's owner
        getLotteryTicketOwners().put(ticket.toString(), someCustomer);
      }
    }
  }

  /**
   * Randomly choose winning lottery tickets for each ticket type; 
   * specifically, one winning ticket of each type.
   * 
   */
  public void completeDrawing() {
    //pick winning tickets at random
    LotteryTicketType[] allTicketTypes = LotteryTicketType.values();
    ArrayList<LotteryTicket> soldTickets = new ArrayList<LotteryTicket>();
    LotteryTicket winningTicket = null;
    LotteryCustomer winningCustomer = null;
    
    for (LotteryTicketType type : allTicketTypes) {
      //get all the tickets sold for a ticket type
      soldTickets.addAll(getSoldTickets(type).values());
      //pick one ticket at random to be the winner
      winningTicket = soldTickets.get(RandomNumberUtil.randomInt(0, soldTickets.size() - 1));
      //find the winning customer
      winningCustomer = lotteryTicketOwners.get(winningTicket.toString());
      //track winning ticket and customer
      lotteryWinners.put(winningTicket, winningCustomer);
      //repeat for next lottery ticket type
      soldTickets.clear();
    }
    
  }

  /**
   * Place a description of your method here.
   * 
   *  How many customers purchased tickets?
   *  What type of tickets did each customer purchase?
   *  Did customers attempt to purchase sold out ticket types?
   *  Which customers won the drawing for each ticket type?
   *  Which numbers were selected during the drawing?
   * 
   */
  public void produceReports() {
    customerCountReport();
    allCustomerDetails();
    winningLotteryTicketsReport();
    winningCustomersReport();
  }
  
  /*********************** PRIVATE HELPER METHODS*********************************/
  
  /**
   * Determines if Pick3 tickets are still available.
   * 
   * @return true if Pick3 tickets are still available
   *         false otherwise
   */
  private boolean isPick3StillAvailable() {
    return getSoldPick3Tickets().keySet().size() < LotteryMachineConstants.PICK3_TICKETS;
  }
  
  /**
   * Determines if Pick4 tickets are still available.
   * 
   * @return true if Pick4 tickets are still available
   *         false otherwise
   */
  private boolean isPick4StillAvailable() {
    return getSoldPick4Tickets().keySet().size() < LotteryMachineConstants.PICK4_TICKETS; 
  }
  
  /**
   * Determines if Pick5 tickets are still available.
   * 
   * @return true if Pick5 tickets are still available
   *         false otherwise
   */
  private boolean isPick5StillAvailable() {
    return getSoldPick5Tickets().keySet().size() < LotteryMachineConstants.PICK5_TICKETS;
  }
  
  /**
   * Given a particular lottery ticket type, determine if it is
   * still available.
   * 
   * @param type - the lottery ticket type in question
   * @return availability of the ticket type
   */
  private boolean isTicketTypeStillAvailable(LotteryTicketType type) {
    switch(type) {
      case PICK3:
        return isPick3StillAvailable();
      case PICK4:
        return isPick4StillAvailable();
      case PICK5:
        return isPick5StillAvailable();
      default:
        return false;
    }
  }
  
  /**
   * Given the particular lottery ticket type, get all the sold tickets.
   * 
   * @param type - the lottery ticket type
   * @return the Map of sold tickets with matching type
   */
  private Map<String, LotteryTicket> getSoldTickets(LotteryTicketType type) {
    switch(type) {
      case PICK3:
        return getSoldPick3Tickets();
      case PICK4:
        return getSoldPick4Tickets();
      case PICK5:
        return getSoldPick5Tickets();
      default:
        return null;
    }
  }
  
  /**
   * Determine if the lottery machine is sold out.
   * 
   * @return true if no tickets remain or
   *         false otherwise
   */
  private boolean isMachineSoldOut() {
    return !isPick3StillAvailable() && !isPick4StillAvailable() && !isPick5StillAvailable();
  }
  
  /**
   * Given a particular customer, choose a lottery ticket type at random.
   * If the chosen type is sold out, choose another type at random.
   * 
   * @param customer - the lottery ticket customer
   * @return randomType - a random lottery ticket type that is not sold out
   */
  private LotteryTicketType getValidRandomTicketType(LotteryCustomer customer) {
    LotteryTicketType randomType = null;
    //choose a random type
    randomType = customer.chooseRandomTicketType();
    
    //if the ticket type is not available
    while (!isTicketTypeStillAvailable(randomType)) {
      //mark it as sold out in the eyes of the customer,
      customer.markTicketTypeSoldOut(randomType);
      //and try again
      randomType = customer.chooseRandomTicketType();
    }
    //return a valid, non-sold-out type.
    return randomType;
  }
  
  /**
   * How many customers purchased tickets?
   */
  private void customerCountReport() {
    System.out.println("Total Number Of Lottery Customers: " +  getAllLotteryCustomers().size() + "\n");
  }
  
  /**
   * Print details for each and every lottery customer including:
   *   1. what type of lottery tickets did each customer buy?
   *   2. did the customer attempt to purchase sold out ticket types?
   *   
   *   but I'll just go ahead and print everything about each customer...
   */
  private void allCustomerDetails() {
    System.out.println("************ ALL LOTTERY CUSTOMER DETAILS ************\n*");
    for (LotteryCustomer customer : getAllLotteryCustomers().values()) {
      System.out.println("*  Customer ID: " + customer.getCustomerId());
      System.out.println("*  Customer desired " + customer.getTicketsDesired() +" tickets and purchased " + customer.getPurchasedTickets().size()  + " tickets." );
      System.out.println("*  Customer purchased:");
      for (Entry<LotteryTicketType, Integer> count : countTicketTypesAndAmounts(customer.getPurchasedTickets()).entrySet()) {
        System.out.println("*\t" + count.getValue() + " " + count.getKey() + " tickets");
      }
      System.out.println(soldOutTicketsReport(customer) + "\n*\n*");
      
      
    }
    System.out.println("******************************************************\n");
  }
  
  /**
   * Given a list of purchased tickets, return a map where the key is
   * the type of ticket and the value is the number of tickets purchased
   * for the key's type.
   * 
   * 
   * @param a list of purchased lottery tickets
   * @return a map with counts of lottery tickets by type
   */
  private Map<LotteryTicketType, Integer> countTicketTypesAndAmounts(List<LotteryTicket> purchasedTickets){
    Map<LotteryTicketType, Integer> countMap = new HashMap<LotteryTicketType, Integer>();
    //initialize map with all types and zero counts
    for (LotteryTicketType type : LotteryTicketType.values()) {
      countMap.put(type, 0);
    }
    //populate map with types and number purchased of each type.
    for (LotteryTicket ticket : purchasedTickets) {
      countMap.put(ticket.getType(), countMap.get(ticket.getType()) + 1);
    }
    return countMap;
  }
  
  /**
   * Given a lottery customer, produce a string that describes which
   * sold out ticket type(s) the customer attempted to purchase, if any.
   * 
   * @param the lottery customer
   * @return a String describing the sold out ticket purchase attempts.
   */
  private String soldOutTicketsReport(LotteryCustomer customer) {
    String reportString = customer.getSoldOutTicketTypesAttempted().isEmpty() ? 
        "*  This customer did not attempt to purchase any sold out tickets." :
        "*  This customer attempted to purchase these sold out ticket types:\n" +
        "*\t" + soldOutTicketsString(customer.getSoldOutTicketTypesAttempted());
    
    return reportString;
  }
  
  /**
   * Given a list of lottery ticket types, produce a String that is 
   * a comma delimited representation of the list.
   * 
   * List must have at least one type in it!
   * 
   * @param a list of lottery ticket types
   * @return comma delimited String representation of the list
   */
  private String soldOutTicketsString(List<LotteryTicketType> types) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < types.size() - 1; i++) {
      sb.append(types.get(i) + ", ");
    }
    sb.append(types.get(types.size() - 1));
    return sb.toString();
  }
  
  /**
   * Which numbers were selected during the drawing?
   */
  private void winningLotteryTicketsReport() {
    System.out.println("****************** WINNING TICKETS ********************\n*");
    for (LotteryTicket ticket : getLotteryWinners().keySet() ) {
      System.out.println("* " + ticket.getType() +" winning numbers: " + ticket.toString());
    }
    System.out.println("*******************************************************\n");
  }
  
  /**
   * Which customers won the drawing for each ticket type?
   */
  private void winningCustomersReport() {
    System.out.println("****************** WINNING TICKET HOLDERS *************\n*");
    for (Entry<LotteryTicket, LotteryCustomer> entry : getLotteryWinners().entrySet()) {
      System.out.println("* Customer ID #" + entry.getValue().getCustomerId() + 
          " won the " + entry.getKey().getType() + " drawing " +
          "with the winning numbers: " + entry.getKey().toString());
    }
    System.out.println("*******************************************************\n");
  }
  
  
  /*********************** GETTERS & SETTERS ***************************************/
  
  // ----------------------------------------------------------
  /**
   * @return the soldPick3Tickets
   */
  public Map<String, LotteryTicket> getSoldPick3Tickets() {
    return soldPick3Tickets;
  }

  // ----------------------------------------------------------
  /**
   * @param soldPick3Tickets the soldPick3Tickets to set
   */
  public void setSoldPick3Tickets(
      HashMap<String, LotteryTicket> soldPick3Tickets) {
    this.soldPick3Tickets = soldPick3Tickets;
  }

  // ----------------------------------------------------------
  /**
   * @return the soldPick4Tickets
   */
  public Map<String, LotteryTicket> getSoldPick4Tickets() {
    return soldPick4Tickets;
  }

  // ----------------------------------------------------------
  /**
   * @param soldPick4Tickets the soldPick4Tickets to set
   */
  public void setSoldPick4Tickets(
      HashMap<String, LotteryTicket> soldPick4Tickets) {
    this.soldPick4Tickets = soldPick4Tickets;
  }

  // ----------------------------------------------------------
  /**
   * @return the soldPick5Tickets
   */
  public Map<String, LotteryTicket> getSoldPick5Tickets() {
    return soldPick5Tickets;
  }

  // ----------------------------------------------------------
  /**
   * @param soldPick5Tickets the soldPick5Tickets to set
   */
  public void setSoldPick5Tickets(
      HashMap<String, LotteryTicket> soldPick5Tickets) {
    this.soldPick5Tickets = soldPick5Tickets;
  }
  
  // ----------------------------------------------------------
  /**
   * @return the allLotteryCustomers
   */
  public Map<Integer, LotteryCustomer> getAllLotteryCustomers() {
    return allLotteryCustomers;
  }

  // ----------------------------------------------------------
  /**
   * @param allLotteryCustomers the allLotteryCustomers to set
   */
  public void setAllLotteryCustomers(Map<Integer, LotteryCustomer> allLotteryCustomers) {
    this.allLotteryCustomers = allLotteryCustomers;
  }
  
  // ----------------------------------------------------------
  /**
   * @return the ticketGenerator
   */
  public LotteryTicketGenerator getTicketGenerator() {
    return ticketGenerator;
  }

  // ----------------------------------------------------------
  /**
   * @param ticketGenerator the ticketGenerator to set
   */
  public void setTicketGenerator(LotteryTicketGenerator ticketGenerator) {
    this.ticketGenerator = ticketGenerator;
  }

  // ----------------------------------------------------------
  /**
   * @return the lotteryTicketOwners
   */
  public Map<String, LotteryCustomer> getLotteryTicketOwners() {
    return lotteryTicketOwners;
  }

  // ----------------------------------------------------------
  /**
   * @param lotteryTicketOwners the lotteryTicketOwners to set
   */
  public void setLotteryTicketOwners(Map<String, LotteryCustomer> lotteryTicketOwners) {
    this.lotteryTicketOwners = lotteryTicketOwners;
  }

  // ----------------------------------------------------------
  /**
   * @return the lotteryWinners
   */
  public Map<LotteryTicket, LotteryCustomer> getLotteryWinners() {
    return lotteryWinners;
  }

  // ----------------------------------------------------------
  /**
   * @param lotteryWinners the lotteryWinners to set
   */
  public void setLotteryWinners(Map<LotteryTicket, LotteryCustomer> lotteryWinners) {
    this.lotteryWinners = lotteryWinners;
  }

}
