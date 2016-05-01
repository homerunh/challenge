/**
 *  Ryan Harrigan
 */
package challenge;

import java.util.Random;

/**
 * 
 * This is the RandomNumberUtil Class.  Its purpose is to :
 * 
 * 
 * @author Ryan Harrigan
 * @version Apr 21, 2016
 *
 */
public class RandomNumberUtil {
  private static Random random = new Random();
  
  /**
   * Given a lower and upper bound (inclusive),
   * Generate a random integer number.
   * 
   * @param min - the lowest possible random number to be generated
   * @param max - the highest possible random number to be generated
   * @return a random integer such that:  min <= NUMBER <= max is always true
   */
  public static int randomInt(int min, int max) {
    return random.nextInt((max - min) + 1) + min;
  }
}
