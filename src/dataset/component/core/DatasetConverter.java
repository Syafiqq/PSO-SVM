package dataset.component.core;

/**
 * Created by Muhammad Syafiq on 8/12/2016.
 * Email : Syafiq.rezpector@gmail.com
 * GitHub : Syafiqq
 */
public class DatasetConverter<DataType>
{
    private DataType training;
    private DataType testing;
    private DataType status;
    private DataType type;

    public DatasetConverter()
    {
        this.training = null;
        this.testing = null;
        this.status = null;
        this.type = null;
    }

    public DataType getTraining()
    {
        return training;
    }

    public void setTraining(DataType training)
    {
        this.training = training;
    }

    public DataType getTesting()
    {
        return testing;
    }

    public void setTesting(DataType testing)
    {
        this.testing = testing;
    }

    public DataType getStatus()
    {
        return status;
    }

    public void setStatus(DataType status)
    {
        this.status = status;
    }

    public DataType getType()
    {
        return type;
    }

    public void setType(DataType type)
    {
        this.type = type;
    }

    @Override public String toString()
    {
        return "DatasetConverter{" +
                "training=" + training +
                ", testing=" + testing +
                ", status=" + status +
                ", type=" + type +
                '}';
    }
}
