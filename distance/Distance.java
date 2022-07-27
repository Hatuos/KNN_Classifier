package distance;

/**
 * <p>
 *  Interface representing the Distance object
 *  </p>
 *  
 *  <p>
 *  The interface sets out the methods necessary to:
 *  <ul>
 *  <li> Obtain the distance between 2 elements </li>
 *  <li> Get the name of the distance used </li>
 *  </ul>
 *  </p>
 *  
 *  @since 24/04/21
 *  @version 1.0
 */ 

public interface Distance
{
  /**
   * Calculate the distance between 2 elements
   * @param features1 Set of values of the first element
   * @param features2 Set of values of the second element
   * @param weights Weights assigned to each source element value
   * @return distance between 2 elements
   */
  double getDistance(double features1[], double features2[], double weights[]);
  
  
  /**
   * Returns the name of the distance used
   * @return name of the distance used
   */
  DistanceIdentification getDistanceName();
}
