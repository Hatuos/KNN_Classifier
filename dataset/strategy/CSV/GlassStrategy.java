package dataset.strategy.CSV;

import dataset.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


/**
 * Implementation for uploading the glass.csv file
 * In this case it is similar to iris.csv
 */
public class GlassStrategy extends DatasetCSV
{
    @Override
    public DataSet load(InputStream inputStream) throws IOException 
    {
        DataSet auxDataset = new DataSet();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) 
        {
          String line = reader.readLine();
          String split[] = line.split("\\,");
          auxDataset.setColumns(split.length);
      
          NumericAttribute numAtr[] = new NumericAttribute[split.length - 1];
          for (int i = 0; i < numAtr.length; i++)
            numAtr[i] = new NumericAttribute(split[i]);
      
          CategoryAttribute catAttr = new CategoryAttribute(split[split.length -1]);
      
          while ((line = reader.readLine()) != null) 
          {
            split = line.split("\\,");
       
            for (int i = 0; i < split.length - 1; i++)
              numAtr[i].addValue(Double.parseDouble(split[i]));
        
            catAttr.addValue(split[split.length -1]);   
          }
      
          auxDataset.setRows(numAtr[1].getValues().size() + 1);
      
          for (int i = 0; i < auxDataset.getColumns() - 1; i++)
            auxDataset.getNumAttributtesList().add(numAtr[i]);
      
          auxDataset.getCatAttributtesList().add(catAttr);
        }

        return auxDataset;
    }

    @Override
    public void showDataSetInfo(DataSet dataset) 
    {
      System.out.println("DataSet data");
      System.out.println("");
      System.out.println("Number of total rows: " + dataset.getRows());
      System.out.println("Number of cases: " + (dataset.getRows() - 1));
      System.out.println("Number of attributes: " + dataset.getColumns());
      System.out.println("");
    
      System.out.print("Attributes: ");

      for (int i = 0; i < dataset.getColumns() - 1; i++)
        System.out.print(dataset.getNumAttributtesList().get(i).getLabel() + " , ");

      System.out.println(dataset.getCatAttributtesList().get(0).getLabel());
    }

    @Override
    public void showAttributesInfo(DataSet dataset) 
    {
      for (int i = 0; i < dataset.getNumAttributtesList().size(); i++)
        dataset.getNumAttributtesList().get(i).showAttributeInformation();
    
      dataset.getCatAttributtesList().get(0).showAttributeInformation();
    }


    @Override
    public void showDataSet(DataSet dataset) 
    {
      for (int i = 0; i < dataset.getColumns() - 1; i++) 
        System.out.print(dataset.getNumAttributtesList().get(i).getLabel() + " , ");
    
      System.out.println(dataset.getCatAttributtesList().get(0).getLabel());

      for (int i = 0; i < dataset.getRows() - 1; i++)
      {
        for (int j = 0; j < dataset.getColumns() - 1; j++) 
          System.out.print(dataset.getNumAttributtesList().get(j).getValue(i) + " , ");
      
        System.out.println(dataset.getCatAttributtesList().get(0).getValue(i) + "");
      }
    }
    
}
