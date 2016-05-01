/**
 *  Ryan Harrigan
 *  PID: homerunh
 *  Student ID#: 904932498
 *  CS xxxx
 *  
 *  On my honor:
 *
 *  - I have not discussed the Java language code in my program with
 *  anyone other than my instructor or the teaching assistants
 *  assigned to this course.
 *
 *  - I have not used Java language code obtained from another student,
 *  or any other unauthorized source, either modified or unmodified.
 *
 *  - If any Java language code or documentation used in my program
 *  was obtained from another source, such as a text book or course
 *  notes, that has been clearly noted with a proper citation in
 *  the comments of my program.
 *
 *  - I have not designed this program in such a way as to defeat or
 *  interfere with the normal operation of the Automated Grader.
 *
 *  Pledge: On my honor, I have neither given nor received unauthorized
 *  aid on this assignment.
 *
 *  Ryan Harrigan
 *
 *
 */


import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import challenge.LotteryCustomer;

/**
 * 
 * This is the LotteryCustomerTest Class.  Its purpose is to :
 * 
 * 
 * @author Ryan Harrigan
 * @version Apr 21, 2016
 *
 */
public class LotteryCustomerTest {
  
  LotteryCustomer c1 = null;
  //-------------------------- Class Method --------------------------------------
  /**
   * Place a description of your method here.
   * @throws java.lang.Exception
   */
  @Before
  public void setUp() throws Exception {
    c1 = new LotteryCustomer(1);
  }

  /**
   * Test method for {@link challenge.LotteryCustomer#LotteryCustomer(int)}.
   */
  @Test
  public void testLotteryCustomer() {
    assertEquals( 1, c1.getCustomerId() );
  }

  /**
   * Test method for {@link challenge.LotteryCustomer#getCustomerId()}.
   */
  @Test
  public void testGetCustomerId() {
    assertEquals( 1, c1.getCustomerId() );
  }

}
