/**
 *  Ryan Harrigan
 */
package challenge;

/**
 * 
 * This is the LotteryCustomerGenerator Class.  Its purpose is to :
 * 
 * Generate Lottery Customers.  The strategy here is to:
 * 1. Create a lottery customer whose customer ID is the current counter value.
 * 2. Increment the counter
 * 
 * Therefore, the first customer will have the customer ID of '0'.
 * AND we can always figure out how many customers have been created
 * if we just return the current value of the customer counter.
 * 
 * 
 * @author Ryan Harrigan
 * @version Apr 30, 2016
 *
 */
public class LotteryCustomerGenerator {
  private int customerCounter = 0;
  
  /**
   * Default constructor
   * 
   * Initialize the customer counter
   */
  public LotteryCustomerGenerator() {
    this.customerCounter = 0;
  }
  
  /**
   * Generate a new lottery customer by returning a new customer and
   * incrementing the counter.
   * 
   * @return a new lottery customer
   */
  public LotteryCustomer generateLotteryCustomer() {
    return new LotteryCustomer(customerCounter++);
  }
  
  /**
   * Get the current number of lottery customers
   * 
   * @return the current number of lottery customers
   */
  public int getCustomerCounter() {
    return customerCounter;
  }
}
