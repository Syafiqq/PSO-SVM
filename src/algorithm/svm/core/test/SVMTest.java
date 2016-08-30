package algorithm.svm.core.test;

import algorithm.svm.component.OneAgainstAll;
import algorithm.svm.component.Parameter;
import algorithm.svm.core.SVM;
import database.sqlite.DBComponent;
import dataset.DatasetGenerator;
import dataset.component.Status;
import dataset.component.Type;
import dataset.component.core.Dataset;
import dataset.component.core.DatasetConverter;
import dataset.component.stroke.StrokeData;
import dataset.component.stroke.exception.StrokeException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.LinkedHashMap;
import main.Main;
import org.junit.Before;
import org.junit.Test;
import org.junit.runners.Parameterized;

/**
 * Created by Muhammad Syafiq on 8/15/2016.
 * Email : Syafiq.rezpector@gmail.com
 * GitHub : Syafiqq
 */
@SuppressWarnings("Duplicates") public class SVMTest
{
    @Parameterized.Parameter
    private Dataset                                           dataset;
    @Parameterized.Parameter
    private DatasetConverter<LinkedHashMap<Integer, Integer>> encoder;
    @Parameterized.Parameter
    private DatasetConverter<LinkedHashMap<Integer, Integer>> decoder;
    @Parameterized.Parameter
    private DatasetGenerator                                  generator;

    @Before
    public void initialize()
    {
        Main.getMySqliteProperties();
        this.dataset = new Dataset();
        this.encoder = new DatasetConverter<>();
        this.decoder = new DatasetConverter<>();
        this.generator = new DatasetGenerator(this.dataset, this.encoder, this.decoder);
        this.generator.generateDataset();
    }

    @Test public void testCalculateTrainingVariance_000() throws StrokeException
    {
        final Parameter parameter = new Parameter(0.1, 0.2, 1000, this.dataset.getParameter().length, this.dataset.getStatuses().length, 10);
        final SVM       svm       = new SVM(parameter, this.dataset.getTraining());
        final double[]  variance  = svm.getVariance();
        System.out.println(Arrays.toString(variance));
    }

    @Test public void testCalculateKernel_000() throws StrokeException
    {
        final Parameter  parameter = new Parameter(0.1, 0.2, 1000, this.dataset.getParameter().length, this.dataset.getStatuses().length, 10);
        final SVM        svm       = new SVM(parameter, this.dataset.getTraining());
        final double[][] kernel    = svm.getKernel();
        for(double[] lv1 : kernel)
        {
            System.out.println(Arrays.toString(lv1));
        }
    }

    @Test public void testGenerateOneAgainstAllClass_000() throws StrokeException
    {
        final Parameter       parameter = new Parameter(0.1, 0.2, 1000, this.dataset.getParameter().length, this.dataset.getStatuses().length, 10);
        final SVM             svm       = new SVM(parameter, this.dataset.getTraining());
        final OneAgainstAll[] oaas      = svm.getOneAgainstAllLevel();
        for(final OneAgainstAll oaa : oaas)
        {
            System.out.println(Arrays.toString(oaa.getClassData()));
        }
    }

    @Test public void testGenerateOneAgainstAllAllowed_000() throws StrokeException
    {
        final Parameter parameter = new Parameter(0.1, 0.2, 1000, this.dataset.getParameter().length, this.dataset.getStatuses().length, 10);
        final SVM       svm       = new SVM(parameter, this.dataset.getTraining());
        for(final OneAgainstAll oaa : svm.getOneAgainstAllLevel())
        {
            System.out.println(Arrays.toString(oaa.getAllowedData()));
        }
    }

    @Test public void testGenerateMatrixD() throws StrokeException
    {
        final Parameter parameter = new Parameter(0.1, 0.2, 1000, this.dataset.getParameter().length, this.dataset.getStatuses().length, 10);
        final SVM       svm       = new SVM(parameter, this.dataset.getTraining());
        svm.doSequentialLearning();
        for(final OneAgainstAll oaa : svm.getOneAgainstAllLevel())
        {
            for(final double[] lv1 : oaa.getMatrixD())
            {
                for(final double lv2 : lv1)
                {
                    System.out.printf("%12.10g\t", lv2);
                }
                System.out.println();
            }
            System.out.println();
            System.out.println();
        }
    }

    @Test public void testCalculateMultiplier_000() throws StrokeException
    {
        final Parameter parameter = new Parameter(0.1, 0.2, 1000, this.dataset.getParameter().length, this.dataset.getStatuses().length, 1000000);
        final SVM       svm       = new SVM(parameter, this.dataset.getTraining());
        svm.doSequentialLearning();
        for(final OneAgainstAll oaa : svm.getOneAgainstAllLevel())
        {
            for(final double multiplier : oaa.getMultiplier())
            {
                System.out.printf("%12.10g\t", multiplier);
            }
            System.out.println();
        }
    }

    @Test public void testCalculateBias_000() throws StrokeException
    {
        final Parameter parameter = new Parameter(0.1, 0.2, 1000, this.dataset.getParameter().length, this.dataset.getStatuses().length, 1000000);
        final SVM       svm       = new SVM(parameter, this.dataset.getTraining());
        svm.doSequentialLearning();
        for(final OneAgainstAll oaa : svm.getOneAgainstAllLevel())
        {
            System.out.printf("%12.10g\n", oaa.getBias());
        }
    }

    @Test public void testTestClassify_000() throws StrokeException
    {
        final Parameter parameter = new Parameter(0.1, 0.2, 1000, this.dataset.getParameter().length, this.dataset.getStatuses().length, 3);
        final SVM       svm       = new SVM(parameter, this.dataset.getTraining());
        svm.doSequentialLearning();
        System.out.println(svm.evaluateStrokeData(this.dataset.getTraining()[0]));
    }

    @Test public void testTestClassifyFromPSO_000() throws StrokeException
    {
        final Parameter parameter = new Parameter(4.841679653008757, 9.645298050294713, 191.21652655661936, this.dataset.getParameter().length, this.dataset.getStatuses().length, 3);
        final SVM       svm       = new SVM(parameter, this.dataset.getTraining());
        svm.doSequentialLearning();
        System.out.println(svm.evaluateStrokeData(this.dataset.getTraining()));
    }

    @Test public void testTestClassifyFromPSO_001() throws StrokeException
    {
        final Parameter parameter = new Parameter(0.4356515622535282, 0.0041381910362371125, 64.7661488010437, this.dataset.getParameter().length, this.dataset.getStatuses().length, 10);
        final SVM       svm       = new SVM(parameter, this.dataset.getTraining());
        svm.doSequentialLearning();
        System.out.println(svm.evaluateStrokeData(this.dataset.getTesting()));
    }

    @Test public void testDataTraining_000() throws StrokeException
    {
        final Parameter parameter = new Parameter(0.1, 0.2, 1000, this.dataset.getParameter().length, this.dataset.getStatuses().length, 3);
        final SVM       svm       = new SVM(parameter, this.dataset.getTraining());
        System.out.println(Arrays.toString(svm.getTraining()));
    }

    @Test
    public void testPSOTestingTraining()
    {
        final DBComponent dbComponent = new DBComponent();
        dbComponent.activate();
        String query = "SELECT ROWID AS 'id', testing.augmenting_factor, testing.learning_rate, testing.constant_cost, testing.svm_learing_count FROM testing WHERE testing.fitness = (SELECT MAX(testing.fitness) FROM testing LIMIT 1) ORDER BY ROWID ASC ";
        //String query = "SELECT data.status AS 'id', COUNT (data.ROWID) AS 'count' FROM data";
        try(final Statement statement = dbComponent.connection.createStatement();
            final ResultSet resultSet = statement.executeQuery(query))
        {
            while(resultSet.next())
            {
                final Parameter parameter = new Parameter(resultSet.getDouble("augmenting_factor"), resultSet.getDouble("learning_rate"), resultSet.getDouble("constant_cost"), this.dataset.getParameter().length, this.dataset.getStatuses().length, resultSet.getInt("svm_learing_count"));
                final SVM       svm       = new SVM(parameter, this.dataset.getTraining());
                svm.doSequentialLearning();
                System.out.println(resultSet.getInt("id") + " : " + svm.evaluateStrokeData(this.dataset.getTraining()));
            }
        }
        catch(SQLException e)
        {
            System.err.println("Generate Dataset Testing Size");
            System.exit(-1);
        }
        dbComponent.deactivate();
    }

    @Test public void testTestClassifyFromPSO_002() throws StrokeException
    {
        final Parameter parameter = new Parameter(0.38030738581689905, 0.0046219959609422046, 49.12982396243143, this.dataset.getParameter().length, this.dataset.getStatuses().length, 10);
        final SVM       svm       = new SVM(parameter, this.dataset.getTraining());
        svm.doSequentialLearning();
        for(final StrokeData data : this.dataset.getTraining())
        {
            System.out.println(svm.doClassify(data.getParameterComponent()));
        }
        for(final StrokeData data : this.dataset.getTesting())
        {
            System.out.println(svm.doClassify(data.getParameterComponent()));
        }
    }

    @Test public void testTestClassifyFromPSO_003() throws StrokeException
    {
        for(final StrokeData training : this.dataset.getTraining())
        {
            for(final double data : training.getParameterComponent().getParameter())
            {
                System.out.print(data);
                System.out.print(", ");
            }
            System.out.println();
        }
        for(final StrokeData training : this.dataset.getTesting())
        {
            for(final double data : training.getParameterComponent().getParameter())
            {
                System.out.print(data);
                System.out.print(", ");
            }
            System.out.println();
        }
    }

    @Test public void generateForAndroid() throws StrokeException
    {
        final Parameter parameter = new Parameter(0.38030738581689905, 0.0046219959609422046, 49.12982396243143, this.dataset.getParameter().length, this.dataset.getStatuses().length, 10);
        final SVM       svm       = new SVM(parameter, this.dataset.getTraining());
        svm.doSequentialLearning();

        //this.printType(this.dataset);
        this.printStatus(this.dataset);
        System.out.println();
        System.out.println();
        System.out.println();
        this.printDataParam(this.dataset);
        //this.printVariance(svm);
        //this.printDataTraining(svm);
        //this.printOneAgainstAll(svm);
    }

    private void printDataParam(Dataset dataset)
    {
        for(final dataset.component.Parameter parameter : dataset.getParameter())
        {
            System.out.print("\"\"\"");
            System.out.print(parameter.getKey());
            System.out.print("\"\"\", \"");
            System.out.print(parameter.getName());
            System.out.println("\"");

        }
    }

    private void printStatus(Dataset dataset)
    {
        for(final Status status : dataset.getStatuses())
        {
            System.out.print("\"\"\"");
            System.out.print(status.getKey());
            System.out.print("\"\"\", \"");
            System.out.print(status.getName());
            System.out.println("\"");
        }
    }

    private void printType(Dataset dataset)
    {
        for(final Type type : dataset.getTypes())
        {
            System.out.print("\"\"\"");
            System.out.print(type.getKey());
            System.out.print("\"\"\", \"");
            System.out.print(type.getName());
            System.out.println("\"");
        }
    }

    private void printOneAgainstAll(SVM svm)
    {
        for(final OneAgainstAll oaa1 : svm.getOneAgainstAllLevel())
        {
            System.out.println(oaa1.getBias());
            System.out.println(Arrays.toString(oaa1.getAllowedData()));
            final int[]    clazz = oaa1.getClassData();
            final double[] mult  = oaa1.getMultiplier();
            for(int i = -1, is = clazz.length; ++i < is; )
            {
                System.out.print(clazz[i]);
                System.out.print(", ");
                System.out.println(mult[i]);
            }
            System.out.println();
            System.out.println();
            System.out.println();
        }
    }

    private void printDataTraining(SVM svm)
    {
        for(final StrokeData training : svm.getTraining())
        {
            for(final double data : training.getParameterComponent().getParameter())
            {
                System.out.print(data);
                System.out.print(", ");
            }
            System.out.println();
        }
    }

    private void printVariance(SVM svm)
    {
        for(final double variance : svm.getVariance())
        {
            System.out.print(variance);
            System.out.print(", ");
        }
        System.out.println();
    }
}