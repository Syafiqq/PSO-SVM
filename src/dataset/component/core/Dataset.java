package dataset.component.core;

import dataset.component.Status;
import dataset.component.Stroke;
import dataset.component.Type;

import java.util.Arrays;

/**
 * Created by Muhammad Syafiq on 8/12/2016.
 * Email : Syafiq.rezpector@gmail.com
 * GitHub : Syafiqq
 */
public class Dataset
{
    private Stroke[][] training;
    private Stroke[][] testing;
    private Type[] types;
    private Status[] statuses;

    public Dataset()
    {
        this.training = null;
        this.testing = null;
        this.types = null;
        this.statuses = null;
    }

    public Stroke[][] getTraining()
    {
        return training;
    }

    public void setTraining(final Stroke[][] training)
    {
        this.training = training;
    }

    public Stroke[][] getTesting()
    {
        return testing;
    }

    public void setTesting(final Stroke[][] testing)
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
