package dataset.strategy;

import dataset.DataSet;
import java.io.IOException;
import java.io.InputStream;


/**
 * Interface to the different upload strategies - The different existing files and their possible formats
 */
public interface LoadStrategy
{
  DataSet load(InputStream inputStream) throws IOException;

  void showDataSetInfo(DataSet dataset);

  void showAttributesInfo(DataSet dataset);

  void showDataSet(DataSet dataset);
}
