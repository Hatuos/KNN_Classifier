package dataset.strategy.CSV;

import dataset.DataSet;
import dataset.strategy.LoadStrategy;
import java.io.IOException;
import java.io.InputStream;


/**
 * Abstract class for CSV type files
 */
public abstract class DatasetCSV implements LoadStrategy
{
    public abstract DataSet load(InputStream inputStream) throws IOException;

    public abstract void showDataSetInfo(DataSet dataset);

    public abstract  void showAttributesInfo(DataSet dataset);

    public abstract void showDataSet(DataSet dataset);
}
