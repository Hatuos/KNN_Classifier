package dataset;

import java.util.ArrayList;

/**
* <p>
*  Class for the creation and storage of numeric type attributes
*  </p>
*  
*  <p>
*   The class contains the necessary methods for:
*  <ul>
*  <li> Create a numeric attribute with a given label </li>
*  <li> Get and set the value of the numeric attribute label </li>
*  <li> Normalise in a certain way its set of values </li>
*  <li> Calculate and get parameters of interest of the attribute - Limits + Mean + SD </li>
*  <li> Determine and obtain the weight of the attribute </li>
*  <li> Display numerical attribute information on the screen </li>
*  </ul>
*  </p>
*  
*  @since 24/04/21
*  @version 1.0
*/ 

public class NumericAttribute extends Attribute<Double>
{
  // Attributes
  private double mean = 0.0;
  private double standardDeviation = 0.0;
  private double weight = 1.0;
  private Pair limits = new Pair();
  
  
  /**
   * Overloading of the constructor
   * @param label Attribute label
   */
  public NumericAttribute(String label) 
  {
    super(label, new ArrayList<Double>());
  }
  
  
  /**
   * Default normalisation of numeric attribute values
   * Normalisation between 0 and 1
   */
  public void normalize()
  {
    for (int i = 0; i < super.getValues().size(); i++)
     super.getValues().set(i,  normalizeValue(super.getValue(i), 0));
  }
  
  
  /**
   * Normalisation by specifying the particular method of normalisation of the numeric attribute values
   * @param mode mode of normalisation
   */
  public void normalize(int mode)
  {
    for (int i = 0; i < super.getValues().size(); i++)
      super.getValues().set(i, normalizeValue(super.getValue(i), mode));
  }
  

  /**
   * Normalises a specific value based on the attribute values
   * @param value 
   * @param mode mode of normalisation
   * @return Normalised value
   */
  public double normalizeValue(double value, int mode)
  {
    double aux = 0.0;
    
    if (mode == 0) // Normalisation between 0 and 1
      aux = (value - limits.getMin()) / (limits.getMax() - limits.getMin());
    else if (mode == 1) // standardisation
      aux = ((value - mean) / standardDeviation);
      
    return aux;
  }
  
  
  /**
   * Calculates the mean of the values of the attribute
   */
  public void checkMean()
  {
    double aux = 0.0;
    
    for (int i = 0; i < super.getValues().size(); i++)
      aux += super.getValue(i);
    
    this.mean = (aux / super.getValues().size());
  }
  
  
  /**
   * Calculates the standar deviation of the values of the attribute
   */
  public void checkStandardDeviation()
  {
    double aux = 0.0;
    
    for (int i = 0; i < super.getValues().size(); i++)
      aux += Math.pow(super.getValue(i) - mean, 2);
    
    this.standardDeviation = Math.sqrt(aux / (super.getValues().size() - 1));
  }


  /**
   * Determines the maximum and minimum values of the numeric attribute values
   */
  public void checkLimits()
  {
    for (int i = 0; i < super.getValues().size(); i++) 
      limits.checkValue(super.getValue(i));
  }
  
  
  /**
   * Sets minimum value of attribute
   * @param d minimum value of attribute
   */
  public void setMin(double d)
  {
    limits.setMin(d);
  }
  
  
  /**
   * Gets minimum value of attribute
   * @return minimum value of attribute
   */
  public double getMin()
  {
    return limits.getMin();
  }
  
  
  /**
   * Sets maximum value of attribute
   * @param d maximum value of attribute
   */
  public void setMax(double d)
  {
    limits.setMax(d);
  }
  
  
  /**
   * Gets maximum value of attribute
   * @return maximum value of attribute
   */
  public double getMax()
  {
    return limits.getMax();
  }
  
  
  /**
   * Returns mean of the attribute
   * @return mean of the attribute
   */
  public double getMean()
  {
    return this.mean;
  }
  
  
  /**
   * Returns standard deviation of the attribute
   * @return standard deviation of the attribute
   */
  public double getStandardDeviation()
  {
    return this.standardDeviation;
  }
  
  
  /**
   * Sets the weight of the attribute
   * @param weight weight of the attribute
   */
  public void setWeight(double weight)
  {
    this.weight = weight;
  }
  
  
  /**
   * Gets the weight of the attribute
   * @return weight of the attribute
   */
  public double getWeight()
  {
    return this.weight;
  }
  

  /**
   * Display numerical attribute information on the screen
   */
  @Override
  public void showAttributeInformation() 
  {
    System.out.println("Information of attribute: " + super.getLabel());
    System.out.println("Mean: " + mean);
    System.out.println("Standard Deviation: " + standardDeviation);
    System.out.println("Weight: " + weight);
    System.out.println("");
  }

}
