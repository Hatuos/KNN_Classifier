package classifier;


/**
  * <p>
 *  Class to store the case studies in the classifications. In this case they are label 
 *  and distance keys used for the KNN classification.
 *  </p>
 *  
 *  <p>
 *  The class contains the necessary methods for:
 *  <ul>
 *  <li> Create a StudyCase with given values </li>
 *  <li> Getters and setters - Label + Distance </li>
 *  </ul>
 *  </p>
 *  
 *  @since 24/04/21
 *  @version 1.0
 */

public class StudyCase 
{
    // Attributes
  private String label;
  private double distance;

  
  public StudyCase (String label, double distance) 
  {
    this.label = label;
    this.distance = distance;
  }
  
  
  public void setLabel(String label)
  {
    this.label = label;
  }

  
  public String getLabel()
  {
    return label;
  }
  
  
  public void setDistance(double distance)
  {
    this.distance = distance;
  }
  
  
  public double getDistance()
  {
    return distance;
  }
  
}
