/**
 *  Ryan Harrigan
 */
package challenge;

/**
 * 
 * This is the Pick4Ticket Class.  Its purpose is to :
 * 
 * Represent a Pick4 ticket.
 * 
 * @author Ryan Harrigan
 * @version Apr 21, 2016
 *
 */
public class Pick4Ticket extends LotteryTicket {

  /**
   * Create a new Pick4Ticket object.
   *
   * @param lotteryNumbers
   */
  public Pick4Ticket(int[] lotteryNumbers) {
    super(LotteryTicketType.PICK4, lotteryNumbers);
  }

  /**
   * Describe the lottery ticket as a String.
   * 
   * This is different than the super class because we want to represent
   * this ticket as a 'single number'.
   * 
   * @return - String representation of this ticket.
   */
  @Override
  public String toString() {
    int length = getLotteryNumbersCount();
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < length; i++) {
      sb.append(getLotteryNumbers()[i]);
    }
    
    return sb.toString();
  }
}
