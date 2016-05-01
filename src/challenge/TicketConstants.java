/**
 *  Ryan Harrigan
 */
package challenge;

/**
 * 
 * This is the TicketConstants Class.  Its purpose is to :
 *
 * Define constants of LotteryTickets.
 * 
 * @author Ryan Harrigan
 * @version Apr 21, 2016
 *
 */
public class TicketConstants {
  
  // The problem definition says for Pick3, Pick4, and Pick5 tickets,
  // all digits shall be between 0-9.
  public static final int PICK_DIGIT_MINIMUM = 0;
  public static final int PICK_DIGIT_MAXIMUM = 9;
  
  // Taken a step farther, we can take the rules above, 
  // and define 'ticket templates'.  The templates identify
  // upper and lower bounds for each number that is a part of a
  // particular lottery ticket.  These templates can be used to 
  // to drive ticket generation from a single method, making it easy
  // to define different kind of tickets (e.g. powerball tickets), but still
  // use the same method to generate new kinds of tickets.
  public static final int[][] PICK_3_TEMPLATE = { {PICK_DIGIT_MINIMUM, PICK_DIGIT_MAXIMUM}, 
                                                  {PICK_DIGIT_MINIMUM, PICK_DIGIT_MAXIMUM}, 
                                                  {PICK_DIGIT_MINIMUM, PICK_DIGIT_MAXIMUM} };
  
  public static final int[][] PICK_4_TEMPLATE = { {PICK_DIGIT_MINIMUM, PICK_DIGIT_MAXIMUM}, 
                                                  {PICK_DIGIT_MINIMUM, PICK_DIGIT_MAXIMUM}, 
                                                  {PICK_DIGIT_MINIMUM, PICK_DIGIT_MAXIMUM}, 
                                                  {PICK_DIGIT_MINIMUM, PICK_DIGIT_MAXIMUM} };
  
  public static final int[][] PICK_5_TEMPLATE = { {PICK_DIGIT_MINIMUM, PICK_DIGIT_MAXIMUM}, 
                                                  {PICK_DIGIT_MINIMUM, PICK_DIGIT_MAXIMUM}, 
                                                  {PICK_DIGIT_MINIMUM, PICK_DIGIT_MAXIMUM}, 
                                                  {PICK_DIGIT_MINIMUM, PICK_DIGIT_MAXIMUM}, 
                                                  {PICK_DIGIT_MINIMUM, PICK_DIGIT_MAXIMUM} };
}
