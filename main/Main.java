package main;

import classifier.*;
import dataset.*;
import dataset.strategy.CSV.GlassStrategy;
import dataset.strategy.CSV.IrisStrategy;
import distance.*;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * <p>
 *  Main class for the execution of our classifiers
 *  </p>
 *  
 *  @since 24/04/21
 *  @version 1.0
 */


public class Main
{

  public static void glassSimulation() throws IOException
  {
    InputStream inputstream = new FileInputStream("Glass.csv route...");
    
    DataSet trainDataSet = new DataSet(new GlassStrategy());
    Classifier classifier = new Knn(new EuclideanDistance(), 5, 0, 0);

    trainDataSet.load(inputstream);
    trainDataSet.calculateParameters();
    trainDataSet.normalize(0);

    //trainDataSet.showDataSetInfo(trainDataSet);
    //System.out.println("");
    //trainDataSet.showAttributesInfo(trainDataSet);
    //System.out.println("");
    //trainDataSet.showDataSet(trainDataSet);
    //System.out.println("");

    classifier.fit(trainDataSet);
    classifier.showParameters();
    System.out.println("");
    System.out.println("");

    double v[] = {1.51514,14.01,2.68,3.5,69.89,1.68,5.87,2.2,0}; // Value Vector - Case to be classified
    System.out.println(classifier.classify(v,trainDataSet,0));

    //System.out.println(classifier.classify(v,trainDataSet)); // Classify without standardisation
    //System.out.println("");

    System.out.println(classifier.accuracy(trainDataSet));

  }


  public static void irisSimulation() throws IOException
  {
    InputStream inputstream = new FileInputStream("Iris.csv route...");
    DataSet trainDataSet = new DataSet(new IrisStrategy());
    Classifier classifier = new Knn(new EuclideanDistance(), 5, 0, 0);

    trainDataSet.load(inputstream);
    trainDataSet.calculateParameters();
    trainDataSet.normalize(0);

    //trainDataSet.showDataSetInfo(trainDataSet);
    //System.out.println("");
    //trainDataSet.showAttributesInfo(trainDataSet);
    //System.out.println("");
    //trainDataSet.showDataSet(trainDataSet);
    //System.out.println("");

    classifier.fit(trainDataSet);
    classifier.showParameters();
    System.out.println("");
    System.out.println("");

    double v[] = {6.3,3.4,5.6,2.4}; // Value Vector - Case to be classified
    System.out.println(classifier.classify(v,trainDataSet,0));

    //System.out.println(classifier.classify(v,trainDataSet)); // Classify without standardisation
    //System.out.println("");

    System.out.println(classifier.accuracy(trainDataSet));

  }

  public static void main(String[] args) throws IOException
  {
    irisSimulation();
    glassSimulation();
  }

  
}