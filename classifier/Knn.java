package classifier;

import dataset.*;
import distance.*;
import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
* <p>
*  Class for the implementation of a KNN classifier
*  </p>
*  
*  <p>
*   The class contains the necessary methods for:
*  <ul>
*  <li> Create a classifier that uses a given distance </li>
*  <li> Load the necessary data for KNN from an initial database - Training Set </li>
*  <li> Classify value vectors </li>
*  <li> Obtain and determine the distance to be used  </li>
*  <li> Obtain and determine the parameter k  </li>
*  <li> Obtain and determine the method of case weighing  </li>
*  <li> Obtain and determine the classification rule  </li>
*  <li> Display information about classifier parameters on the screen  </li>
*  <li> Determine accuracy of database-driven predictions </li>
*  </ul>
*  </p>
*  
*  @since 24/04/21
*  @version 1.0
*/ 


public class Knn extends Classifier
{
  // Attributes
  private int k;
  private Distance distance;
  private int weighingMode;
  private int sortMode;
  private int rowsNumber;
  private int columnsNumber;
  private double weights[];
  private  ArrayList<NumericAttribute> trainingNumAttributes = new ArrayList<>();
  private  ArrayList<CategoryAttribute> trainingCatAttributes = new ArrayList<>();
  
  
  public Knn(Distance distance, int k, int weighingMode, int sortMode)
  {
    this.distance = distance;
    this.k = k;
    this.weighingMode = weighingMode;
    this.sortMode = sortMode;
  }
  
  
  @Override
  public void fit(ArrayList<NumericAttribute> numAtrList, ArrayList<CategoryAttribute> catAtrList, int rNum, int cNum) 
  {
    trainingNumAttributes = numAtrList;
    trainingCatAttributes = catAtrList;

    rowsNumber = rNum;
    columnsNumber = cNum;
    
    weights = new double[trainingNumAttributes.size()];
    
    for (int i = 0; i < trainingNumAttributes.size(); i++)
      weights[i] = trainingNumAttributes.get(i).getWeight();
  }
  
 
  /**
   * Gets the map of distances from smallest to largest with respect to a point of origin.
   * @param features Value vector - Point of origin
   * @return Map of distances
   */
  private Map<Double, List<String>> distanceLabelMap(double[] features) 
  {
    Map<Double, List<String>> map = new TreeMap<>(); 
    
    for (int i = 0; i < rowsNumber - 1; i++) 
    {
      double studyCase[] = new double[trainingNumAttributes.size()];
      
      for (int j = 0; j < trainingNumAttributes.size(); j++)
        studyCase[j] = trainingNumAttributes.get(j).getValue(i);
      
      double distance = this.distance.getDistance(features, studyCase, weights);
      
      List<String> labels = map.getOrDefault(distance, new ArrayList<>());
      labels.add(trainingCatAttributes.get(0).getValue(i));
      
      map.put(distance, labels);
    }
    
    // Print generated distances map
    //for (Map.Entry<Double, List<String>> entry : map.entrySet()) 
     //System.out.println("Key: " + entry.getKey() + ". Value: " + entry.getValue());
   //System.out.println("");
 
    return map;
  }
  
  
  /**
   * Gets a list of K nearest neighbours sorted from nearest to furthest.
   * @param map Map of distances
   * @param k Parameter K
   * @return List with K nearest neighbours
   */
  private List<StudyCase> kNearestNeighbors(Map<Double, List<String>> map, int k) 
  {
    List<StudyCase> list = new ArrayList<>();
      
    outerloop:
    for (Map.Entry<Double, List<String>> labels : map.entrySet())
      for (int i = 0; i < labels.getValue().size(); i++)
        if (list.add(new StudyCase(labels.getValue().get(i), labels.getKey())) && list.size() >= k)
          break outerloop;
    
    // Print list of K nearest neighbours
    //for (StudyCase s : list) 
      //System.out.print("[ "+ s.getLabel() + "," + s.getDistance() + " ]");

    return list;
  }
  
  
  @Override
  public String classify(double[] features, DataSet dataset, int mode) 
  {
    Map<Double, List<String>> distanceLabelMap = distanceLabelMap(dataset.normalizeVector(features, mode));
    List<StudyCase> kNearestNeighbors = kNearestNeighbors(distanceLabelMap, this.k);
    return mode(kNearestNeighbors);
  }
  
  
  @Override
  public String classify(double[] features, DataSet dataset) 
  {
    Map<Double, List<String>> distanceLabelMap = distanceLabelMap(features);
    List<StudyCase> kNearestNeighbors = kNearestNeighbors(distanceLabelMap, this.k);
    return mode(kNearestNeighbors);
  }
  
  
  @Override
  public void classify(InputStream inputStream, DataSet dataset, int mode) throws IOException 
  {
    try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) 
    {
      String line;
      while ((line = reader.readLine()) != null) 
      {
        String split[] = line.split("\\,");
        double features[] = new double[split.length - 1];
        
        for(int i = 0; i < features.length; i++)
          features[i] = Double.parseDouble(split[i]);
        
        System.out.println(classify(features, dataset, mode));
      }
    }
  }
  
  
  @Override
  public void classify(InputStream inputStream, DataSet dataset) throws IOException 
  {
    try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) 
    {
      String line;
      while ((line = reader.readLine()) != null) 
      {
        String split[] = line.split("\\,");
        double features[] = new double[split.length - 1];
        
        for(int i = 0; i < features.length; i++)
          features[i] = Double.parseDouble(split[i]);
        
        System.out.println(classify(features, dataset));
      }
    }
  }
  
  /**
   * Calculates the weight of each case analysed and returns the final prediction.
   * @param list List of K nearest neighbours
   * @return Final prediction
   */
  public String mode(List<StudyCase> list) 
  {
    Map<String, Double> map = new HashMap<>();
    double maxWeight = 0.0;
    double totalWeight = 0.0;
    int counter = 1;
    String maxWeightLabel = null;
    
    for (StudyCase s : list) 
    {
      double weight = 0.0;
   
      if (weighingMode == 0) // All cases are of equal weight
        weight += map.getOrDefault(s.getLabel(), 0.0) + (double) 1/(list.size());
      else if (weighingMode == 1) // Proximity weight assignment
       weight += map.getOrDefault(s.getLabel(), 0.0) + (double) 1/s.getDistance();
      else if (weighingMode == 2) // Manual case weight assignment
      {
        double aux;
        Scanner input = new Scanner(System.in);
        try 
        {
          System.out.println("Enter case weight " + counter + ": ");
          //input = new Scanner(System.in);
          aux = input.nextDouble();
        } finally {
          input.close();
        }
        
        
        weight = map.getOrDefault(s.getLabel(), 0.0) + aux;
        counter++;
      }
      
      if (weight > maxWeight) 
      {
        maxWeight = weight;
        maxWeightLabel = s.getLabel();
      }
      map.put(s.getLabel(), weight);
    }
    
    for (double i : map.values()) 
      totalWeight += i;
    
    // Print map of total weights associated with each case type
   //for (Map.Entry<String, Double> entry : map.entrySet()) 
     // System.out.println("Key: " + entry.getKey() + ". Value: " + entry.getValue());
    //System.out.println("Peso total: " + totalWeight);
    
    return calculateThreshold(maxWeightLabel, maxWeight, totalWeight);
  }
  
  
  /**
   * Returns the final prediction depending on whether it exceeds a certain threshold
   * @param <T>
   * @param maxWeightLabel Type of case that carries the most weight in the classification
   * @param maxWeight Weight of the case with the most weight
   * @param totalWeight Sum of the weights of all the cases studied
   * @return Final prediction
   */
  private <T> T calculateThreshold(T maxWeightLabel, double maxWeight ,double totalWeight)
  {
    
    double defaultThreshold = 0.5;
    
    if (sortMode == 0) // Simple majority
      return maxWeightLabel;
    else
    {
      System.out.println("Default Threshold value: 0.5 - Do you want the default threshold value?: y/n");
      Scanner input = new Scanner(System.in);
      char c;
      try 
      {
        c = input.next().charAt(0);
      
      
        if (c == 'n')
        {
          System.out.print("Enter threshold value for rating between 0 and 1: ");
        
          do
          {
            defaultThreshold = input.nextDouble();
          } while (defaultThreshold < 0.0 || defaultThreshold > 1.0);
        }
      } finally {
        input.close();
      }
      
      if ((maxWeight/totalWeight) > defaultThreshold)
        return maxWeightLabel;
      else
      {
        System.out.println("No case exceeds the established threshold: " + defaultThreshold);
        System.out.print("Case with greater weight: " + maxWeight/totalWeight + " - ");
        return maxWeightLabel;
      }
    }
  }
  
  
  /**
   * Returns the distance used
   * @return distance used
   */
  public Distance getDistance() 
  {
    return distance;
  }
  
  
  /**
   * Sets distance to be used
   * @param d Distance to be used
   */
  public void setDistance(Distance d) 
  {
    this.distance = d;
  }
  
  
  /**
   * Returns value of parameter K
   * @return value of parameter K
   */
  public int getK()
  {
    return this.k;
  }
  
  
  /**
   * Sets value of parameter K
   * @param k value of parameter K
   */
  public void setK(int k)
  {
    this.k = k;
  }
  
  
  /**
   * Returns case weighing method
   * @return case weighing method
   */
  public int getWeighingMode()
  {
    return this.weighingMode;
  }
  
  
  /**
   * Establishes case weighing method
   * @param w Value of the case weighing method
   */
  public void setWeighingMode(int w)
  {
    this.weighingMode = w;
  }
  
  
  /**
   * Returns classification rule
   * @return Classification rule
   */
  public int getSortMode()
  {
    return this.sortMode;
  }
  
  
  /**
   * Establishes classification rule
   * @param w classification rule value
   */
  public void setSortMode(int s)
  {
    this.sortMode = s;
  }
  
 
  /**
   * Displays on-screen information on the classifier parameters
   */
  public void showParameters()
  {
    System.out.println("Information on KNN parameters");
    System.out.println("");
    
    System.out.println("Value of parameter k: " + this.k);
    System.out.println("Distance used: " + distance.getDistanceName());
    
    System.out.print("Case weighing: ");
    if (weighingMode == 0)
      System.out.print("Equal voting rights");
    else if (weighingMode == 1)
      System.out.print("Proximity weight assignment");
    else if (weighingMode == 2)
      System.out.print("Manual case weight assignment");
    System.out.println("");
    
    System.out.print("Classification rule: ");
    if (sortMode == 0)
      System.out.print("Simple majority");
    else if (sortMode == 1)
      System.out.print("Most voted class above a certain threshold");
  }
  
  
  /**
   * Internal classification method to obtain accuracy against a database
   * @param features Value vector
   * @return Classification of the value vector
   */
  private String classify(double[] features) 
  {
    Map<Double, List<String>> distanceLabelMap = distanceLabelMap(features);
    List<StudyCase> kNearestNeighbors = kNearestNeighbors(distanceLabelMap, this.k);
    return mode(kNearestNeighbors);
  }
  
  
  /**
   * It allows us to obtain an estimate of the accuracy of our predictions.
   * @param dataset Source database
   * @return Algorithm successes / Total cases
   */
  public double accuracy(DataSet dataset) 
  {
    double correct = 0;
    
    for (int i = 0; i < rowsNumber - 1; i++)
    {
      double aux[] = new double[columnsNumber - 1];
      
      for (int j = 0; j < columnsNumber - 1; j++)
        aux[j] = trainingNumAttributes.get(j).getValue(i);
      
      if (classify(aux).equals(trainingCatAttributes.get(0).getValue(i)))
       correct++;
    }
    return (correct / Double.valueOf(rowsNumber - 1));
  }



  
}
