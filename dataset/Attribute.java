package dataset;

import java.util.ArrayList;

/**
 * <p>
 *  Abstract class for the Attribute object
 *  </p>
 *  
 *  <p>
 *  The class contains the necessary methods for:
 *  <ul>
 *  <li> Create an attribute with a given label </li>
 *  <li> Get and set the value of the attribute label </li>
 *  <li> Get and set values from your values set </li>
 *  <li> Get attribute information </li>
 *  </ul>
 *  </p>
 *  
 *  @since 24/04/21
 *  @version 1.0
 */ 

public abstract class Attribute<T> 
{
  // Attributes
  private String label;
  private ArrayList<T> values;


  /**
   * Builder overload
   * @param label label value
   */
  Attribute(String label, ArrayList<T> list) 
  {
    this.label = label;
    this.values = new ArrayList<>();
  }
        
        
  /**
   * Returns the value of the label
   * @return value of the label
   */
  public String getLabel()
  {
    return label;
  }
  
  
  /**
   * Sets the value of the label
   * @param label new value of the label
   */
  public void setLabel(String label)
  {
    this.label = label;
  }
  
  
  /**
   * Gets specific value of the attribute values
   * @param index specific attribute value
   * @return specific value of the attribute values
   */
  public T getValue(int index)
  {
    return values.get(index);
  }

  
  /**
   * Sets specific value of the attribute values
   * @param index specific attribute value
   * @param value new value
   */
  public void setValue(int index, T value)
  {
    values.set(index, value);
  }
  

  /**
   * Get list of attribute values
   * @return list of attribute values
   */
  public ArrayList<T> getValues()
  {
    return values;
  }


  /**
   * Add new value to attribute values
   * @param value new value
   */
  public void addValue(T value)
  {
    values.add(value);
  }
  

  /**
   * Display attribute information on screen
   */
  public abstract void showAttributeInformation();
  
}
