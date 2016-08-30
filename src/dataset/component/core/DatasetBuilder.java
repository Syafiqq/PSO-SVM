package dataset.component.core;

import database.sqlite.DBComponent;

/**
 * Created by Muhammad Syafiq on 8/12/2016.
 * Email : Syafiq.rezpector@gmail.com
 * GitHub : Syafiqq
 */
public abstract class DatasetBuilder<TDataset extends Dataset, TDatasetConverterEncoder extends DatasetConverter<?>, TDatasetConverterDecoder extends DatasetConverter<?>>
{
    protected final TDataset                 dataset;
    protected final TDatasetConverterEncoder encoder;
    protected final TDatasetConverterDecoder decoder;
    protected final DBComponent              dbComponent;

    public DatasetBuilder(TDataset dataset, TDatasetConverterEncoder encoder, TDatasetConverterDecoder decoder)
    {
        this.dataset = dataset;
        this.encoder = encoder;
        this.decoder = decoder;
        this.dbComponent = new DBComponent();
    }

    public abstract void generateDataset();

    public TDataset getDataset()
    {
        return dataset;
    }

    public TDatasetConverterEncoder getEncoder()
    {
        return encoder;
    }

    public TDatasetConverterDecoder getDecoder()
    {
        return decoder;
    }
}
