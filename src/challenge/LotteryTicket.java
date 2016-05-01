/**
 *  Ryan Harrigan
 */
package challenge;

/**
 * 
 * This is the LotteryTicket Class.  Its purpose is to :
 * 
 * Represent a lottery ticket
 * 
 * @author Ryan Harrigan
 * @version Apr 21, 2016
 *
 */
public abstract class LotteryTicket {
  private final LotteryTicketType type;
  private final int[] lotteryNumbers;
  
  
  protected LotteryTicket(LotteryTicketType type, int[] lotteryNumbers) {
    this.type = type;
    this.lotteryNumbers = lotteryNumbers;
  }

  // ----------------------------------------------------------
  /**
   * @return the type
   */
  protected LotteryTicketType getType() {
    return type;
  }

  // ----------------------------------------------------------
  /**
   * @return the lotteryNumbers
   */
  protected int[] getLotteryNumbers() {
    return lotteryNumbers;
  }
  
  /**
   * Get the cardinality of this lottery ticket:
   * 
   * eg. A pick 3 ticket has 3 numbers
   *     A pick 4 ticket has 4 numbers
   *     A pick 5 ticket has 5 numbers ... etc.
   * 
   * @return the cardinality
   */
  protected int getLotteryNumbersCount() {
    return lotteryNumbers.length;
  }
  
  /**
   * Describe the lottery ticket as a String.
   * 
   * @return - String representation of this ticket.
   */
  public String toString() {
    int length = getLotteryNumbersCount();
    StringBuilder sb = new StringBuilder();
    sb.append("{ ");
    for (int i = 0; i < length - 1; i++) {
      sb.append(lotteryNumbers[i] + ", ");
    }
    sb.append(lotteryNumbers[length -1] +" }");
    
    return sb.toString();
  }
}
