package dataset;

/**
 * <p>
 *  Class for calculating pairs of minimum and maximum values.
 *  </p>
 *  
 *  <p>
 *  The class contains the necessary methods for:
 *  <ul>
 *  <li> Create a value pair with default values </li>
 *  <li> Check whether a value can be the new minimum or maximum </li>
 *  <li> Establish and obtain the minimum and maximum values </li>
 *  <li> Display the 2 stored values </li>
 *  </ul>
 *  </p>
 *  
 *  @since 24/04/21
 *  @version 1.0
 */

public class Pair
{
  // Attributes
  private double min;
  private double max;

  
  public Pair() 
  {
    min = Double.MAX_VALUE;
    max = 0.0;
  }
  
  
  /**
   * Checks whether a value can be the new minimum or maximum
   * @param d Value to be checked
   */
  public void checkValue(double d)
  {
    if (d < min)
      min = d;
    if (d > max)
      max = d;
  }
  
  
  /**
   * Set the minimum value
   * @param d minimum value
   */
  public void setMin(double d)
  {
    min = d;
  }

  
 /**
 * Get the minimum value
 * @return minimum value
 */
  public double getMin() 
  { 
    return min; 
  }
  
  
  /**
   * Set the maximum value
   * @param d maximum value
   */
  public void setMax(double d)
  {
    max = d;
  }
  
  
  /**
   * Get the maximum value
   * @return maximum value
   */
  public double getMax() 
  { 
    return max; 
  }
  
  
  /**
   * Display the 2 stored values on the screen
   */
  public void showPair()
  {
    System.out.println( "Min.Value: " + min +", Max.Value: " + max);
  }

}
