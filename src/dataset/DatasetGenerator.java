package dataset;

import dataset.component.Status;
import dataset.component.Stroke;
import dataset.component.Type;
import dataset.component.core.Dataset;
import dataset.component.core.DatasetBuilder;
import dataset.component.core.DatasetConverter;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;

/**
 * Created by Muhammad Syafiq on 8/12/2016.
 * Email : Syafiq.rezpector@gmail.com
 * GitHub : Syafiqq
 */
public class DatasetGenerator extends DatasetBuilder<Dataset, DatasetConverter<LinkedHashMap<Integer, ArrayList<Integer>>>, DatasetConverter<LinkedHashMap<ArrayList<Integer>, Integer>>>
{
    public DatasetGenerator(Dataset dataset, DatasetConverter<LinkedHashMap<Integer, ArrayList<Integer>>> encoder, DatasetConverter<LinkedHashMap<ArrayList<Integer>, Integer>> decoder)
    {
        super(dataset, encoder, decoder);
    }

    @Override public void generateDataset()
    {
        Runtime runtime = Runtime.getRuntime();
        runtime.runFinalization();
        runtime.gc();
        super.dbComponent.activate();
        this.generateDatasetType();
        this.generateDatasetStatus();
        this.generateDataTraining();
        this.generateDataTesting();
        super.dbComponent.deactivate();
        runtime.runFinalization();
        runtime.gc();
    }

    private void generateDataTesting()
    {
        final LinkedHashMap<Integer, ArrayList<Integer>> statusEncoder = super.encoder.getStatus();
        /*
         * Get Status Size
         * */
        String query = "SELECT data.status as 'id', COUNT (data.ROWID) AS 'count' FROM data WHERE data.type = (SELECT DISTINCT type.ROWID FROM type WHERE type.name = 'Testing' LIMIT 1) GROUP BY data.status ORDER BY data.status ASC";
        int[] size = new int[super.dataset.getStatuses().length];
        try(final Statement statement = this.dbComponent.connection.createStatement();
            final ResultSet resultSet = statement.executeQuery(query))
        {
            while(resultSet.next())
            {
                size[statusEncoder.get(resultSet.getInt("id")).get(0)] = resultSet.getInt("count");
            }
        }
        catch(SQLException e)
        {
            System.err.println("Generate Dataset Testing Size");
            System.exit(-1);
        }

        int cumulativeSum = Arrays.stream(size).sum();

        final Stroke[][] testing = new Stroke[size.length][];
        for(int sizeIndex = -1, sizeSize = size.length; ++sizeIndex < sizeSize;)
        {
            testing[sizeIndex] = new Stroke [size[sizeIndex]];
            size[sizeIndex] = 0;
        }
        final LinkedHashMap<Integer, ArrayList<Integer>> encoder = new LinkedHashMap<>(cumulativeSum);
        final LinkedHashMap<ArrayList<Integer>, Integer> decoder = new LinkedHashMap<>(cumulativeSum);

        /*
         * Get Type
         * */
        query = "SELECT data.ROWID AS 'id', * FROM data WHERE data.type = (SELECT DISTINCT type.ROWID FROM type WHERE type.name = 'Testing' LIMIT 1) ORDER BY data.status ASC";
        try(final Statement statement = this.dbComponent.connection.createStatement();
            final ResultSet resultSet = statement.executeQuery(query))
        {
            while(resultSet.next())
            {
                final ArrayList<Integer> depthEncoder = new ArrayList<>(2);
                final ArrayList<Integer> depthDecoder = new ArrayList<>(2);
                depthEncoder.add(statusEncoder.get(resultSet.getInt("status")).get(0));
                depthEncoder.add(size[depthEncoder.get(0)]++);
                depthDecoder.add(depthEncoder.get(1));
                depthDecoder.add(depthEncoder.get(0));

                testing[depthEncoder.get(0)][depthEncoder.get(1)] = new Stroke(resultSet.getInt("age"), resultSet.getDouble("cholesterol"), resultSet.getDouble("hdl"), resultSet.getDouble("ldl"), resultSet.getDouble("triglyceride"), resultSet.getInt("status"));
                encoder.put(resultSet.getInt("id"), depthEncoder);
                decoder.put(depthDecoder, resultSet.getInt("id"));

                super.dataset.setTesting(testing);
                super.encoder.setTesting(encoder);
                super.decoder.setTesting(decoder);
            }
        }
        catch(SQLException e)
        {
            System.err.println("Generate Dataset Testing");
            System.exit(-1);
        }
    }

    private void generateDataTraining()
    {
        final LinkedHashMap<Integer, ArrayList<Integer>> statusEncoder = super.encoder.getStatus();
        /*
         * Get Status Size
         * */
        String query = "SELECT data.status as 'id', COUNT (data.ROWID) AS 'count' FROM data WHERE data.type = (SELECT DISTINCT type.ROWID FROM type WHERE type.name = 'Training' LIMIT 1) GROUP BY data.status ORDER BY data.status ASC";
        int[] size = new int[super.dataset.getStatuses().length];
        try(final Statement statement = this.dbComponent.connection.createStatement();
            final ResultSet resultSet = statement.executeQuery(query))
        {
            while(resultSet.next())
            {
                size[statusEncoder.get(resultSet.getInt("id")).get(0)] = resultSet.getInt("count");
            }
        }
        catch(SQLException e)
        {
            System.err.println("Generate Dataset Training Size");
            System.exit(-1);
        }

        int cumulativeSum = Arrays.stream(size).sum();

        final Stroke[][] trainings = new Stroke[size.length][];
        for(int sizeIndex = -1, sizeSize = size.length; ++sizeIndex < sizeSize;)
        {
            trainings[sizeIndex] = new Stroke [size[sizeIndex]];
            size[sizeIndex] = 0;
        }
        final LinkedHashMap<Integer, ArrayList<Integer>> encoder = new LinkedHashMap<>(cumulativeSum);
        final LinkedHashMap<ArrayList<Integer>, Integer> decoder = new LinkedHashMap<>(cumulativeSum);

        /*
         * Get Type
         * */
        query = "SELECT data.ROWID AS 'id', * FROM data WHERE data.type = (SELECT DISTINCT type.ROWID FROM type WHERE type.name = 'Training' LIMIT 1) ORDER BY data.status ASC";
        try(final Statement statement = this.dbComponent.connection.createStatement();
            final ResultSet resultSet = statement.executeQuery(query))
        {
            while(resultSet.next())
            {
                final ArrayList<Integer> depthEncoder = new ArrayList<>(2);
                final ArrayList<Integer> depthDecoder = new ArrayList<>(2);
                depthEncoder.add(statusEncoder.get(resultSet.getInt("status")).get(0));
                depthEncoder.add(size[depthEncoder.get(0)]++);
                depthDecoder.add(depthEncoder.get(1));
                depthDecoder.add(depthEncoder.get(0));

                trainings[depthEncoder.get(0)][depthEncoder.get(1)] = new Stroke(resultSet.getInt("age"), resultSet.getDouble("cholesterol"), resultSet.getDouble("hdl"), resultSet.getDouble("ldl"), resultSet.getDouble("triglyceride"), resultSet.getInt("status"));
                encoder.put(resultSet.getInt("id"), depthEncoder);
                decoder.put(depthDecoder, resultSet.getInt("id"));

                super.dataset.setTraining(trainings);
                super.encoder.setTraining(encoder);
                super.decoder.setTraining(decoder);
            }
        }
        catch(SQLException e)
        {
            System.err.println("Generate Dataset Training");
            System.exit(-1);
        }
    }

    private void generateDatasetStatus()
    {
        /*
         * Get Status Size
         * */
        String query = "SELECT COUNT (ROWID) AS 'count' FROM status";
        int size = -1;
        try(final Statement statement = this.dbComponent.connection.createStatement();
            final ResultSet resultSet = statement.executeQuery(query))
        {
            size = resultSet.getInt("count");
        }
        catch(SQLException e)
        {
            System.err.println("Generate Dataset Status Size");
            System.exit(-1);
        }

        final Status[] statuses = new Status[size];
        final LinkedHashMap<Integer, ArrayList<Integer>> encoder = new LinkedHashMap<>(size);
        final LinkedHashMap<ArrayList<Integer>, Integer> decoder = new LinkedHashMap<>(size);

        /*
         * Get Type
         * */
        query = "SELECT ROWID AS 'id', status.name from status";
        try(final Statement statement = this.dbComponent.connection.createStatement();
            final ResultSet resultSet = statement.executeQuery(query))
        {
            for(int resultSetIndex = 0; resultSet.next(); ++resultSetIndex)
            {
                final ArrayList<Integer> depth = new ArrayList<>(1);
                depth.add(resultSetIndex);
                statuses[resultSetIndex] = new Status(resultSet.getString("name"));
                encoder.put(resultSet.getInt("id"), depth);
                decoder.put(depth, resultSet.getInt("id"));

                super.dataset.setStatuses(statuses);
                super.encoder.setStatus(encoder);
                super.decoder.setStatus(decoder);
            }
        }
        catch(SQLException e)
        {
            System.err.println("Generate Dataset Status");
            System.exit(-1);
        }
    }

    private void generateDatasetType()
    {
        /*
         * Get Type Size
         * */
        String query = "SELECT COUNT (ROWID) AS 'count' FROM type";
        int size = -1;
        try(final Statement statement = this.dbComponent.connection.createStatement();
            final ResultSet resultSet = statement.executeQuery(query))
        {
            size = resultSet.getInt("count");
        }
        catch(SQLException e)
        {
            System.err.println("Generate Dataset Type Size");
            System.exit(-1);
        }

        final Type[] types = new Type[size];
        final LinkedHashMap<Integer, ArrayList<Integer>> encoder = new LinkedHashMap<>(size);
        final LinkedHashMap<ArrayList<Integer>, Integer> decoder = new LinkedHashMap<>(size);

        /*
         * Get Type
         * */
        query = "SELECT ROWID AS 'id', type.name from type";
        try(final Statement statement = this.dbComponent.connection.createStatement();
            final ResultSet resultSet = statement.executeQuery(query))
        {
            for(int resultSetIndex = 0; resultSet.next(); ++resultSetIndex)
            {
                final ArrayList<Integer> depth = new ArrayList<>(1);
                depth.add(resultSetIndex);
                types[resultSetIndex] = new Type(resultSet.getString("name"));
                encoder.put(resultSet.getInt("id"), depth);
                decoder.put(depth, resultSet.getInt("id"));

                super.dataset.setTypes(types);
                super.encoder.setType(encoder);
                super.decoder.setType(decoder);
            }
        }
        catch(SQLException e)
        {
            System.err.println("Generate Dataset Type");
            System.exit(-1);
        }
    }
}
