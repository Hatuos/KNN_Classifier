package classifier;

import dataset.*;
import java.util.ArrayList;
import java.io.IOException;
import java.io.InputStream;

/**
* <p>
*  Abstract class for the classifier object
*  </p>
*  
*  <p>
*  The class contains the necessary methods for:
*  <ul>
*  <li> Load data from a DataSet to the classifier </li>
*  <li> Classify vectors of values </li>
*  <li> Determine accuracy of database-driven predictions </li>
*  </ul>
*  </p>
*  
*  @since 24/04/21
*  @version 1.0
*/ 

public abstract class Classifier
{
  
  /**
   * Loads data from DataSet to the Classifier 
   * @param dataset Base de datos a cargar
   */
  public final void fit(DataSet dataset) 
  {
    fit(dataset.getNumAttributtesList(), dataset.getCatAttributtesList(), dataset.getRows(), dataset.getColumns());
  }

  
  /**
   * Loads data from DataSet to the Classifier 
   * @param numAtrList List of numeric attributes
   * @param catAtrList List of categorical attributes
   * @param rNum Rows number
   * @param cNum Columns number
   */
  public abstract void fit(ArrayList<NumericAttribute> numAtrList, ArrayList<CategoryAttribute> catAtrList,int rNum, int cNum);
  
  
  /**
   * Classifies a vector of values using normalisation
   * @param features Value vector
   * @param dataset Database underpinning standardisation and classification
   * @param mode Normalisation mode
   * @return Classification of the value vector
   */
  public abstract String classify(double[] features, DataSet dataset, int mode);
  
  
  /**
   * Classifies a vector of values without normalisation
   * @param features Value vector
   * @param dataset Database underpinning standardisation and classification
   * @return Classification of the value vector
   */
  public abstract String classify(double[] features, DataSet dataset); 
  
  
  /**
   * Classifies set of value vectors using normalisation
   * @param inputStream Source file of the securities to be classified
   * @param dataset Database underpinning standardisation and classification
   * @param mode Normalisation mode
   * @throws IOException Source file exception
   */
  public abstract void classify(InputStream inputStream, DataSet dataset, int mode) throws IOException;
  
  
  /**
   * Classifies set of value vectors without normalisation
   * @param inputStream Source file of the securities to be classified
   * @param dataset Database underpinning standardisation and classification
   * @throws IOException Source file exception
   */
  public abstract void classify(InputStream inputStream, DataSet dataset) throws IOException;
  
  
  /**
   * Returns accuracy of predictions
   * @param dataset Database used to compare results
   * @return Algorithm successes / Total cases
   */
  public abstract double accuracy(DataSet dataset); 


  public abstract void showParameters();
  
}
