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
  
  public static int randomInt(int min, int max) {
    return random.nextInt((max - min) + 1) + min;
  }
}
