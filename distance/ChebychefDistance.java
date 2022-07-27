package distance;

/**
 * <p>
 * Class for calculating the Chebychef distance between 2 elements
 *  </p>
 *  
 *  <p>
 *  The class contains the necessary methods for:
 *  <ul>
 *  <li> Obtaining the Chebychef distance between 2 elements </li>
 *  <li> Get the name of the distance </li>
 *  </ul>
 *  </p>
 *  
 *  @since 24/04/21
 *  @version 1.0
 */ 

public final class ChebychefDistance implements Distance
{
  
  public double getDistance(double features1[], double features2[], double weights[]) 
  {
    double distance = 0.0;
    
    for (int i = 0; i < features1.length; i++) 
    {
      double d = weights[i] * Math.abs(features1[i] - features2[i]);
      
      if (distance < d)
        distance = d;
    }
    return distance;
  }
  
  
  public DistanceIdentification getDistanceName()
  {
   return DistanceIdentification.CHEBYCHEF;
  }
  
}
