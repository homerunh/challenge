/**
 *  Ryan Harrigan
 */
package challenge;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 
 * This is the LotteryCustomer Class.  Its purpose is to :
 * 
 * Represent a lottery customer by keeping track of a customer's:
 * 1. Unique customer identifier (in the form of a customer id).
 * 2. The random number of tickets the customer desires
 * 3. A list of the cusomer's purchased lottery tickets
 * 4. A list of lottery ticket types that a customer might choose to buy.
 * 5. A list of sold out lottery ticket types that they customer attempted to buy. 
 * 
 * @author Ryan Harrigan
 * @version Apr 21, 2016
 *
 */
public class LotteryCustomer {

  private final int customerId;
  private final int ticketsDesired;
  private List<LotteryTicket> purchasedTickets;
  
  //List of ticket types that the customer will randomly (attempt to) purchase
  private List<LotteryTicketType> possibleTicketPurchaseTypes;
  
  //List of ticket types that the customer attempted to purchase, but were sold out.
  private List<LotteryTicketType> soldOutTicketTypesAttempted;
  
  public LotteryCustomer(int customerId) {
    this.customerId = customerId;
    this.ticketsDesired = RandomNumberUtil.randomInt(CustomerConstants.MINIMUM_DESIRED_TICKETS, CustomerConstants.MAXIMUM_DESIRED_TICKETS);
    this.purchasedTickets = new ArrayList<LotteryTicket>(ticketsDesired);
    List<LotteryTicketType> ticketTypes = new ArrayList<LotteryTicketType>();
    ticketTypes.add(LotteryTicketType.PICK3);
    ticketTypes.add(LotteryTicketType.PICK4);
    ticketTypes.add(LotteryTicketType.PICK5);
    this.possibleTicketPurchaseTypes = ticketTypes;
    this.soldOutTicketTypesAttempted = new ArrayList<LotteryTicketType>();
  }

  /*********************** CLASS METHODS *****************************************/
    
  /**
   * Add a lottery ticket to the customer's list of Purchased tickets,
   * but only when the customer has not yet reached the number of desired
   * tickets.
   * 
   * @param ticketToAdd - the ticket we are attempting to add to the list
   * @return true - if the ticket was successfully added
   *         false - if the ticket was NOT added,
   *                 this happens when the customer has already purchased 
   *                 the desired number of lottery tickets.
   */
  public boolean addLotteryTicket(LotteryTicket ticketToAdd) {
    //Ensure customer does not exceed desired number of tickets
    if (this.purchasedTickets.size() >= this.getTicketsDesired()) {
      return false;
    }
    //If safe, add the lottery ticket,
    //and indicate success.
    this.purchasedTickets.add(ticketToAdd);
    return true;
  }
  
  /**
   * Choose a random type of lottery ticket using private helper method.
   * 
   * @return a random lottery ticket type from class variable.
   */
  public LotteryTicketType chooseRandomTicketType() {
    return chooseRandomTicketType(possibleTicketPurchaseTypes);
  }
  
  /**
   * Mark a particular lottery ticket type as sold out by:
   * 1. removing the sold out type from the list of possible purchase types
   * 2. adding the sold out type to the list of sold out ticket types.
   * 
   * @param - the lottery ticket type that is sold out.
   */
  public void markTicketTypeSoldOut(LotteryTicketType type) {
    int typeIndex = possibleTicketPurchaseTypes.indexOf(type);
    if (typeIndex != -1) {
      soldOutTicketTypesAttempted.add(possibleTicketPurchaseTypes.remove(typeIndex));
    } 
  }
  
  /*********************** PRIVATE HELPER METHODS*********************************/
  
  /**
   * Given a list of LotteryTicketTypes, choose one at random.
   * 
   * @param - a list of LotteryTicketTypes
   * @return - a random LotteryTicketType from the list
   */
  private LotteryTicketType chooseRandomTicketType(List<LotteryTicketType> types) {
    return types.get(RandomNumberUtil.randomInt(0, types.size() - 1 ));
  }
  
  /*********************** GETTERS & SETTERS *************************************/
  
  // ----------------------------------------------------------
  /**
   * @return the customerId
   */
  public int getCustomerId() {
    return customerId;
  }

  // ----------------------------------------------------------
  /**
   * @return the ticketsDesired
   */
  public int getTicketsDesired() {
    return ticketsDesired;
  }

  // ----------------------------------------------------------
  /**
   * @return the purchasedTickets
   */
  public List<LotteryTicket> getPurchasedTickets() {
    return purchasedTickets;
  }
  
  // ----------------------------------------------------------
  /**
   * @return the soldOutTicketTypesAttempted
   */
  public List<LotteryTicketType> getSoldOutTicketTypesAttempted() {
    return soldOutTicketTypesAttempted;
  }  

  // ----------------------------------------------------------
  /**
   * @param purchasedTickets the purchasedTickets to set
   */
  public void setPurchasedTickets(List<LotteryTicket> purchasedTickets) {
    this.purchasedTickets = purchasedTickets;
  }

}
