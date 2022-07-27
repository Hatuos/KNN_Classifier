package dataset;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
* <p>
*  Class for creating and storing attributes of categorical type
*  </p>
*  
*  <p>
*  The class contains the necessary methods for:
*  <ul>
*  <li> Create a categorical attribute with a given label </li>
*  <li> Display categorical attribute information on screen </li>
*  </ul>
*  </p>
*  
*  @since 24/04/21
*  @version 1.0
*/ 

public class CategoryAttribute extends Attribute<String> 
{

  /**
   * Builder overload
   * @param label attribute label
   */
  public CategoryAttribute(String label) 
  {
    super(label, new ArrayList<String>());
  }
  
  
  /**
   * Display categorical attribute information on screen
   */
  @Override
  public void showAttributeInformation() 
  {

    System.out.println("Information of attribute: " + super.getLabel());

    Set<String> uniqueValues = new HashSet<String>(super.getValues());
    System.out.println("Number of different values for the attribute: " + uniqueValues.size());
    System.out.println("Relative values and frequencies: ");
    
    for (Iterator<String> it = uniqueValues.iterator(); it.hasNext();) 
    {
      String f = it.next();
      double count = Collections.frequency(super.getValues(), f);
      System.out.println(f + ": " + Double.valueOf(count/super.getValues().size()));
    }
    System.out.println("");
  }
  
}
