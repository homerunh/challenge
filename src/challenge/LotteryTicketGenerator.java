/**
 *  Ryan Harrigan
 */
package challenge;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * This is the LotteryTicketGenerator Class.  Its purpose is to :
 * 
 * Generate unique lottery tickets, given a type.
 * 
 * @author Ryan Harrigan
 * @version Apr 30, 2016
 *
 */
public class LotteryTicketGenerator {
  
  //Map of lottery ticket templates.  
  private Map<LotteryTicketType, int[][]> templates = null;
  
  /**
   * Default constructor.
   * 
   * Initialize and populate the templates for ALL lottery ticket types.
   */
  public LotteryTicketGenerator() {
    this.templates = new HashMap<LotteryTicketType, int[][]>();
    templates.put(LotteryTicketType.PICK3, TicketConstants.PICK_3_TEMPLATE);
    templates.put(LotteryTicketType.PICK4, TicketConstants.PICK_4_TEMPLATE);
    templates.put(LotteryTicketType.PICK5, TicketConstants.PICK_5_TEMPLATE);
  }
  
  /*********************** CLASS METHODS *********************************/
  
  /**
   * Generate a unique lottery ticket of the given type.
   * 
   * @param - typeOfTicketToGenerate - the type of ticket we want to generate
   * @param - ticketsAlreadySold - all the tickets already sold.
   */
  public LotteryTicket generateUniqueLotteryTicket(LotteryTicketType typeOfTicketToGenerate, Map<String, LotteryTicket> ticketsAlreadySold) {
    //Generate a ticket of the specified type with random numbers
    LotteryTicket ticket = generateTicket(typeOfTicketToGenerate); 

    //verify that the ticket is unique.
    while(!isTicketUnique(ticket, ticketsAlreadySold)) {
      //if the ticket is not unique, try again.
      ticket = generateTicket(typeOfTicketToGenerate);
    }
    //if the ticket is unique, we're done!
    return ticket;
  }
  
  /*********************** PRIVATE HELPER METHODS *********************************/
  
  /**
   * Helper method.
   * 
   * Generate a lottery ticket of the desired type.
   * 
   * @param - ticketTypeToGenerate - the type of ticket we want to generate
   * @return - a ticket of the desired type.  May or may not be unique.
   */
  private LotteryTicket generateTicket(LotteryTicketType ticketTypeToGenerate) {
    LotteryTicket generatedTicket = null;
    switch(ticketTypeToGenerate) {
      case PICK3:
        generatedTicket = new Pick3Ticket(generateTicketNumbers(ticketTypeToGenerate));
        break;
        
      case PICK4:
        generatedTicket = new Pick4Ticket(generateTicketNumbers(ticketTypeToGenerate));
        break;
        
      case PICK5:
        generatedTicket = new Pick5Ticket(generateTicketNumbers(ticketTypeToGenerate));
        break;
        
      default:
        System.out.println("This ticket type is undefined!");
        break;
    }
    
    return generatedTicket;
  }
  
  /**
   * Given a type of lottery ticket, return a valid array of integers
   * representing the numbers in specified lottery ticket type.
   * 
   * @param - the lottery ticket type
   * @return - an integer array lottery ticket numbers
   */
  private int[] generateTicketNumbers(LotteryTicketType ticketType) {
    //First, get the template for the given ticket type
    int[][] someTicketTemplate = templates.get(ticketType);
    
    //get length of outter array  --> the cardinality of numbers... 
    // pick 3 has 3 numbers,
    // pick 4 has 4 numbers, etc...
    int ticketNumberLength = someTicketTemplate.length;
    
    //initialize the array of numbers that is being generated.
    int[] generatedTicketNumbers = new int[ticketNumberLength];
    
    //declare upper and lower bound variables
    int lowerBound = -1;
    int upperBound = -1;
    
    for (int i = 0; i < ticketNumberLength; i++) {
      // The template is an array where the upper and lower bounds of each number
      // is specified.
      lowerBound = someTicketTemplate[i][0];
      upperBound = someTicketTemplate[i][1];
      // generate a number that is (inclusively) between the upper and lower bound.
      generatedTicketNumbers[i] = RandomNumberUtil.randomInt(lowerBound, upperBound);
    }
    
    return generatedTicketNumbers;
  }
  
  /**
   * Given a ticket and a list of tickets already sold, 
   * Determine if a ticket is unique or not...  
   * 
   * In other words, determine if the ticket has NOT yet been sold.
   * @param - the ticket we are checking for uniqueness
   * @param - the map containing the tickets that have already been sold
   * @return true - if the ticket is unique AND it has NOT been sold
   *         false - if the ticket is NOT unique AND it has already been sold.
   */
  private boolean isTicketUnique(LotteryTicket possiblyUniqueTicket, Map<String, LotteryTicket> ticketsAlreadySold) {
    //if the ticket IS unique, then the ticket has NOT been sold yet...  
    return ticketsAlreadySold.get(possiblyUniqueTicket.toString()) == null;
  }
  
}
