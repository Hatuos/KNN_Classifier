package distance;

/**
 * <p>
 * Class for calculating the Euclidean distance between 2 elements
 *  </p>
 *  
 *  <p>
 *  The class contains the necessary methods for:
 *  <ul>
 *  <li> Obtaining the Euclidean distance between 2 elements </li>
 *  <li> Get the name of the distance </li>
 *  </ul>
 *  </p>
 *  
 *  @since 24/04/21
 *  @version 1.0
 */ 

public final class EuclideanDistance implements Distance
{
  
  public double getDistance(double features1[], double features2[], double weights[]) 
  {
    double sum = 0;

    // The square root operation is eliminated in an effort to speed up the calculation
    // The resulting scores will have the same relative proportions after this modification and 
    // can still be used effectively within a machine learning algorithm for finding the most similar examples.
    for (int i = 0; i < features1.length; i++)
        sum += weights[i] * Math.pow(features1[i] - features2[i], 2);
    
    return sum;
  }
  
  
  public DistanceIdentification getDistanceName()
  {
    return DistanceIdentification.EUCLIDEAN;
  }
  
}
