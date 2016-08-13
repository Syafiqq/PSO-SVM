package dataset.component.core;

import dataset.component.Status;
import dataset.component.Type;
import dataset.component.stroke.StrokeData;

import java.util.Arrays;

/**
 * Created by Muhammad Syafiq on 8/12/2016.
 * Email : Syafiq.rezpector@gmail.com
 * GitHub : Syafiqq
 */
public class Dataset
{
    private StrokeData[] training;
    private StrokeData[] testing;
    private Type[] types;
    private Status[] statuses;

    public Dataset()
    {
        this.training = null;
        this.testing = null;
        this.types = null;
        this.statuses = null;
    }

    public StrokeData[] getTraining()
    {
        return training;
    }

    public void setTraining(final StrokeData[] training)
    {
        this.training = training;
    }

    public StrokeData[] getTesting()
    {
        return testing;
    }

    public void setTesting(final StrokeData[] testing)
    {
        this.testing = testing;
    }

    public Type[] getTypes()
    {
        return types;
    }

    public void setTypes(final Type[] types)
    {
        this.types = types;
    }

    public Status[] getStatuses()
    {
        return statuses;
    }

    public void setStatuses(final Status[] statuses)
    {
        this.statuses = statuses;
    }

    @Override public String toString()
    {
        return "Dataset{" +
                "training=" + Arrays.toString(training) +
                ", testing=" + Arrays.toString(testing) +
                ", types=" + Arrays.toString(types) +
                ", statuses=" + Arrays.toString(statuses) +
                '}';
    }
}
