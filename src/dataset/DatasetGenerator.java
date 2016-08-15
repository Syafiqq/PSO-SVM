package dataset;

import dataset.component.Parameter;
import dataset.component.Status;
import dataset.component.Type;
import dataset.component.core.Dataset;
import dataset.component.core.DatasetBuilder;
import dataset.component.core.DatasetConverter;
import dataset.component.stroke.StrokeData;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedHashMap;

/**
 * Created by Muhammad Syafiq on 8/12/2016.
 * Email : Syafiq.rezpector@gmail.com
 * GitHub : Syafiqq
 */
public class DatasetGenerator extends DatasetBuilder<Dataset, DatasetConverter<LinkedHashMap<Integer, Integer>>, DatasetConverter<LinkedHashMap<Integer, Integer>>>
{
    public DatasetGenerator(Dataset dataset, DatasetConverter<LinkedHashMap<Integer, Integer>> encoder, DatasetConverter<LinkedHashMap<Integer, Integer>> decoder)
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
        this.generateDatasetParameter();
        this.generateDataTraining();
        this.generateDataTesting();
        super.dbComponent.deactivate();
        runtime.runFinalization();
        runtime.gc();
    }


    private void generateDataTesting()
    {
        /*
         * Get Data Testing Size
         * */
        String query = "SELECT data.status AS 'id', COUNT (data.ROWID) AS 'count' FROM data WHERE data.type = (SELECT DISTINCT type.ROWID FROM type WHERE type.name = 'Testing' LIMIT 1) ORDER BY data.status ASC";
        int size = -1;
        try(final Statement statement = this.dbComponent.connection.createStatement();
            final ResultSet resultSet = statement.executeQuery(query))
        {
            size = resultSet.getInt("count");
        }
        catch(SQLException e)
        {
            System.err.println("Generate Dataset Testing Size");
            System.exit(-1);
        }

        final StrokeData[] testing = new StrokeData[size];
        final LinkedHashMap<Integer, Integer> encoder = new LinkedHashMap<>(size);
        final LinkedHashMap<Integer, Integer> decoder = new LinkedHashMap<>(size);
        final LinkedHashMap<Integer, Integer> statusEncoder = super.encoder.getStatus();

        /*
         * Get Data Testing
         * */
        query = "SELECT data.ROWID AS 'id', * FROM data WHERE data.type = (SELECT DISTINCT type.ROWID FROM type WHERE type.name = 'Testing' LIMIT 1) ORDER BY data.status ASC";
        try(final Statement statement = this.dbComponent.connection.createStatement();
            final ResultSet resultSet = statement.executeQuery(query))
        {
            for(int resultSetIndex = 0; resultSet.next(); ++resultSetIndex)
            {
                testing[resultSetIndex] = new StrokeData(resultSet.getInt("age"), resultSet.getDouble("cholesterol"), resultSet.getDouble("hdl"), resultSet.getDouble("ldl"), resultSet.getDouble("triglyceride"), statusEncoder.get(resultSet.getInt("status")));
                encoder.put(resultSet.getInt("id"), resultSetIndex);
                decoder.put(resultSetIndex, resultSet.getInt("id"));
            }

            super.dataset.setTesting(testing);
            super.encoder.setTesting(encoder);
            super.decoder.setTesting(decoder);
        }
        catch(SQLException e)
        {
            System.err.println("Generate Dataset Testing");
            System.exit(-1);
        }
    }

    private void generateDataTraining()
    {
        /*
         * Get Data Training Size
         * */
        String query = "SELECT data.status AS 'id', COUNT (data.ROWID) AS 'count' FROM data WHERE data.type = (SELECT DISTINCT type.ROWID FROM type WHERE type.name = 'Training' LIMIT 1) ORDER BY data.status ASC";
        int size = -1;
        try(final Statement statement = this.dbComponent.connection.createStatement();
            final ResultSet resultSet = statement.executeQuery(query))
        {
            size = resultSet.getInt("count");
        }
        catch(SQLException e)
        {
            System.err.println("Generate Dataset Training Size");
            System.exit(-1);
        }

        final StrokeData[] trainings = new StrokeData[size];
        final LinkedHashMap<Integer, Integer> encoder = new LinkedHashMap<>(size);
        final LinkedHashMap<Integer, Integer> decoder = new LinkedHashMap<>(size);
        final LinkedHashMap<Integer, Integer> statusEncoder = super.encoder.getStatus();

        /*
         * Get Data Testing
         * */
        query = "SELECT data.ROWID AS 'id', * FROM data WHERE data.type = (SELECT DISTINCT type.ROWID FROM type WHERE type.name = 'Training' LIMIT 1) ORDER BY data.status ASC";
        try(final Statement statement = this.dbComponent.connection.createStatement();
            final ResultSet resultSet = statement.executeQuery(query))
        {
            for(int resultSetIndex = 0; resultSet.next(); ++resultSetIndex)
            {
                trainings[resultSetIndex] = new StrokeData(resultSet.getInt("age"), resultSet.getDouble("cholesterol"), resultSet.getDouble("hdl"), resultSet.getDouble("ldl"), resultSet.getDouble("triglyceride"), statusEncoder.get(resultSet.getInt("status")));
                encoder.put(resultSet.getInt("id"), resultSetIndex);
                decoder.put(resultSetIndex, resultSet.getInt("id"));
            }

            super.dataset.setTraining(trainings);
            super.encoder.setTraining(encoder);
            super.decoder.setTraining(decoder);
        }
        catch(SQLException e)
        {
            System.err.println("Generate Dataset Training");
            System.exit(-1);
        }
    }

    private void generateDatasetParameter()
    {
         /*
         * Get Parameter Size
         * */
        String query = "SELECT COUNT (ROWID) AS 'count' FROM parameter";
        int size = -1;
        try(final Statement statement = this.dbComponent.connection.createStatement();
            final ResultSet resultSet = statement.executeQuery(query))
        {
            size = resultSet.getInt("count");
        }
        catch(SQLException e)
        {
            System.err.println("Generate Dataset Parameter Size");
            System.exit(-1);
        }

        final Parameter[] parameters = new Parameter[size];
        final LinkedHashMap<Integer, Integer> encoder = new LinkedHashMap<>(size);
        final LinkedHashMap<Integer, Integer> decoder = new LinkedHashMap<>(size);

        /*
         * Get Status
         * */
        query = "SELECT ROWID AS 'id', parameter.name FROM parameter";
        try(final Statement statement = this.dbComponent.connection.createStatement();
            final ResultSet resultSet = statement.executeQuery(query))
        {
            for(int resultSetIndex = 0; resultSet.next(); ++resultSetIndex)
            {
                parameters[resultSetIndex] = new Parameter(resultSet.getString("name"));
                encoder.put(resultSet.getInt("id"), resultSetIndex);
                decoder.put(resultSetIndex, resultSet.getInt("id"));
            }

            super.dataset.setParameter(parameters);
            super.encoder.setParameter(encoder);
            super.decoder.setParameter(decoder);
        }
        catch(SQLException e)
        {
            System.err.println("Generate Dataset Parameter");
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
        final LinkedHashMap<Integer, Integer> encoder = new LinkedHashMap<>(size);
        final LinkedHashMap<Integer, Integer> decoder = new LinkedHashMap<>(size);

        /*
         * Get Status
         * */
        query = "SELECT ROWID AS 'id', status.name FROM status";
        try(final Statement statement = this.dbComponent.connection.createStatement();
            final ResultSet resultSet = statement.executeQuery(query))
        {
            for(int resultSetIndex = 0; resultSet.next(); ++resultSetIndex)
            {
                statuses[resultSetIndex] = new Status(resultSet.getString("name"));
                encoder.put(resultSet.getInt("id"), resultSetIndex);
                decoder.put(resultSetIndex, resultSet.getInt("id"));
            }

            super.dataset.setStatuses(statuses);
            super.encoder.setStatus(encoder);
            super.decoder.setStatus(decoder);
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
        final LinkedHashMap<Integer, Integer> encoder = new LinkedHashMap<>(size);
        final LinkedHashMap<Integer, Integer> decoder = new LinkedHashMap<>(size);

        /*
         * Get Type
         * */
        query = "SELECT ROWID AS 'id', type.name FROM type";
        try(final Statement statement = this.dbComponent.connection.createStatement();
            final ResultSet resultSet = statement.executeQuery(query))
        {
            for(int resultSetIndex = 0; resultSet.next(); ++resultSetIndex)
            {
                types[resultSetIndex] = new Type(resultSet.getString("name"));
                encoder.put(resultSet.getInt("id"), resultSetIndex);
                decoder.put(resultSetIndex, resultSet.getInt("id"));
            }

            super.dataset.setTypes(types);
            super.encoder.setType(encoder);
            super.decoder.setType(decoder);
        }
        catch(SQLException e)
        {
            System.err.println("Generate Dataset Type");
            System.exit(-1);
        }
    }
}
