package dataset;

import java.util.ArrayList;
import dataset.strategy.LoadStrategy;
import java.io.IOException;
import java.io.InputStream;

/**
* <p>
*  Class for storing and operating a database
*  </p>
*  
*  <p>
*  The class contains the necessary methods for:
*  <ul>
*  <li> Loading a database into memory using a specific strategy </li>
*  <li> Calculate parameters of interest in the database - limits + mean + SD </li>
*  <li> Setting weights of database attributes </li>
*  <li> Normalise in a certain way the numerical values of the attributes of the database </li>
*  <li> Normalising a vector of values in a given way  </li>
*  <li> Get your attribute lists  </li>
*  <li> Get and set the number of columns and rows in the database </li>
*  <li> Display database information on screen </li>
*  <li> Display database attribute information on the screen </li>
*  <li> Display the database loaded in memory on the screen. </li>
*  </ul>
*  </p>
*  
*  @since 24/04/21
*  @version 1.0
*/ 

public class DataSet
{
  // Attributes
  private int rowsNumber;
  private int columnsNumber;
  private  ArrayList<NumericAttribute> numAttributtes = new ArrayList<>();
  private  ArrayList<CategoryAttribute> catAttributtes = new ArrayList<>();
  private LoadStrategy loadStrategy; // Methods of reading different files to load them into memory
  

  public DataSet()
  {

  }


  public DataSet(LoadStrategy loadStrategy)
  {
    this.loadStrategy = loadStrategy;
  }


  public void setLoadStrategy(LoadStrategy loadStrategy)
  {
    this.loadStrategy = loadStrategy;
  }


  /**
   * Loads a database into memory
   * @param inputStream Database to be loaded
   * @throws IOException Read bbdd exception
   */
  public void load(InputStream inputStream) throws IOException
  {
    DataSet auxDataSet = loadStrategy.load(inputStream);

    this.rowsNumber = auxDataSet.getRows();
    this.columnsNumber = auxDataSet.getColumns();
    this.numAttributtes = auxDataSet.getNumAttributtesList();
    this.catAttributtes = auxDataSet.getCatAttributtesList();
  }

  
  /**
   * Calculates parameters of each numeric attribute
   */
  public void calculateParameters()
  {
    for (int i = 0; i < numAttributtes.size(); i++)
    {
      numAttributtes.get(i).checkLimits();
      numAttributtes.get(i).checkMean();
      numAttributtes.get(i).checkStandardDeviation();
    }
  }
  
  
  /**
   * Sets the weight of each numeric attribute.
   * @param v Weight vector
   */
  public void setWeights(double v[])
  {
    for (int i = 0; i < v.length; i++)
      numAttributtes.get(i).setWeight(v[i]);
  }
  
  
  /**
   * Normalises by default the values of each numeric attribute
   * Normalisation between 0 and 1
   */
  public void normalize()
  {
    for (int i = 0; i < numAttributtes.size(); i++) 
      numAttributtes.get(i).normalize();
  }
  
  
  /**
   * Normalises the values of each numerical attribute in a certain way
   * @param mode Normalisation mode
   */
  public void normalize(int mode)
  {
    for (int i = 0; i < numAttributtes.size(); i++) 
      numAttributtes.get(i).normalize(mode);
  }
  
  
  /**
   * Normalises a vector of values in a certain way
   * @param d vector of values
   * @param mode Normalisation mode
   * @return Normalised vector
   */
  public double[] normalizeVector(double[] d, int mode)
  {
    double[] aux = new double[columnsNumber -1];
    
    for (int i = 0; i < columnsNumber - 1; i++)
      aux[i] = numAttributtes.get(i).normalizeValue(d[i], mode);
  
    return aux;
  }
  
  
  
  public ArrayList<NumericAttribute> getNumAttributtesList()
  {
    return numAttributtes;
  }
  
  
  public ArrayList<CategoryAttribute> getCatAttributtesList()
  {
    return catAttributtes;
  } 


  public void setRows(int n)
  {
    this.rowsNumber = n;
  }


  public void setColumns(int n)
  {
    this.columnsNumber = n;
  }
  
 
  public int getRows()
  {
    return rowsNumber;
  }
 

  public int getColumns()
  {
    return columnsNumber;
  }
  
  
  /**
   * Displays database information on the screen
   */
  public void showDataSetInfo(DataSet dataset)
  {
    loadStrategy.showAttributesInfo(dataset);
  }
  
  
  /**
   * Displays attributes information on the screen
   */
  public void showAttributesInfo(DataSet dataset)
  {
    loadStrategy.showAttributesInfo(dataset);
  }
  
  
  /**
   * Displays the database loaded in memory on the screen.
   */
  public void showDataSet(DataSet dataset)
  {
    loadStrategy.showDataSet(dataset);
  }
 
}
