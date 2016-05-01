/**
 *  Ryan Harrigan
 */
package challenge;

/**
 * 
 * This is the LotterySimulator Class.  Its purpose is to :
 * 
 * 1. Simulate selling all the lottery tickets in the machine,
 * 2. Have a "drawing" where we pick some winning tickets,
 * 3. Produce a report with specific information from the simulation:
 * 
 *  How many customers purchased tickets?
 *  What type of tickets did each customer purchase?
 *  Did customers attempt to purchase sold out ticket types?
 *  Which customers won the drawing for each ticket type?
 *  Which numbers were selected during the drawing?
 * 
 * 
 * @author Ryan Harrigan
 * @version Apr 21, 2016
 *
 */
public class LotterySimulator {

  //-------------------------- Class Method --------------------------------------
  /**
   * Runs the Lottery Simulation.
   * 
   * @param args
   */
  public static void main(String[] args) {
    System.out.println("Beginning Lottery Simulation...");
    LotteryMachine lotteryMachine = new LotteryMachine();
    LotteryCustomerGenerator customerGenerator = new LotteryCustomerGenerator();
    
    //sell the tickets
    lotteryMachine.sellTickets(customerGenerator);
    System.out.println("\n1.  Selling ticket ...  COMPLETE!");
  
    //pick winners in drawing
    lotteryMachine.completeDrawing();
    System.out.println("\n2.  Drawing Winning Tickets ...  COMPLETE!!");
    
    System.out.println("\n3.  Summary of Lottery Activities:\n");
    //produce report(s)
    lotteryMachine.produceReports();
    
  }

}
