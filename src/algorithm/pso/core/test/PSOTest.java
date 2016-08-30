package algorithm.pso.core.test;

import algorithm.pso.component.Position;
import algorithm.pso.component.Setting;
import algorithm.pso.core.PSO;
import algorithm.svm.component.Parameter;
import algorithm.svm.core.SVMLearner;
import database.sqlite.DBComponent;
import dataset.DatasetGenerator;
import dataset.component.core.Dataset;
import dataset.component.core.DatasetConverter;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import main.Main;
import org.junit.Before;
import org.junit.Test;
import org.junit.runners.Parameterized;

/*
 * This <PSO-SVM_001> project in package <algorithm.pso.core.test> created by : 
 * Name         : syafiq
 * Date / Time  : 26 August 2016, 10:00 AM.
 * Email        : syafiq.rezpector@gmail.com
 * Github       : syafiqq
 */

public class PSOTest
{
    @Parameterized.Parameter
    private Setting                                           setting;
    @Parameterized.Parameter
    private Dataset                                           dataset;
    @Parameterized.Parameter
    private DatasetConverter<LinkedHashMap<Integer, Integer>> encoder;
    @Parameterized.Parameter
    private DatasetConverter<LinkedHashMap<Integer, Integer>> decoder;
    @Parameterized.Parameter
    private DatasetGenerator                                  generator;

    @SuppressWarnings("Duplicates") @Before
    public void initialize()
    {
        Main.getMySqliteProperties();
        this.dataset = new Dataset();
        this.encoder = new DatasetConverter<>();
        this.decoder = new DatasetConverter<>();
        this.generator = new DatasetGenerator(this.dataset, this.encoder, this.decoder);
        this.generator.generateDataset();
    }

    @Test
    public void testPSO()
    {
        this.setting = Setting.getInstance();
        this.setting.setLocalConfidence(2.05);
        this.setting.setSwarmConfidence(2.05);
        this.setting.setPsoIteration(100);
        this.setting.setSVMLearningCount(10);
        this.setting.setSwarmSize(1000);

        final PSO pso = new PSO(this.setting, this.generator);
        pso.run();
        final Position position = pso.gBest.getPosition();
        System.out.printf("Augmenting Factor = %.6g\n" +
                "Learning Rate = %.6g\n" +
                "Constant Factor = %.6g\n" +
                "Fitness = %.6g\n", position.getAugmentingFactor(), position.getLearningRate(), position.getConstantCost(), pso.gBest.getFitness());
        final Parameter  parameter = new Parameter(position.getAugmentingFactor(), position.getLearningRate(), position.getConstantCost(), this.dataset.getParameter().length, this.dataset.getStatuses().length, 5);
        final SVMLearner svm       = new SVMLearner(parameter, this.dataset.getTraining(), this.dataset.getTraining());
        svm.doSequentialLearning();
        svm.evaluateStrokeData();
    }

    @Test
    public void testPSOTesting()
    {
        final DBComponent dbComponent = new DBComponent();
        dbComponent.activate();

        this.setting = Setting.getInstance();
        this.setting.setPsoIteration(100);
        this.setting.setSVMLearningCount(10);
        this.setting.setSwarmSize(1000);
        for(float l = 0.0f, ls = 3.0f; (l += 0.5) < ls; )
        {
            for(float g = 0.0f, gs = 3.0f; (g += 0.5) < gs; )
            {
                for(float c = -1, cs = 2; ++c < cs; )
                {
                    this.setting.setLocalConfidence(l);
                    this.setting.setSwarmConfidence(g);
                    final PSO pso = new PSO(this.setting, this.generator);
                    pso.run();
                    final Position position = pso.gBest.getPosition();
                    String         query    = "INSERT INTO testing VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
                    try
                    {
                        final PreparedStatement statement = dbComponent.connection.prepareStatement(query);
                        statement.setDouble(1, position.getAugmentingFactor());
                        statement.setDouble(2, position.getLearningRate());
                        statement.setDouble(3, position.getConstantCost());
                        statement.setDouble(4, pso.gBest.getFitness());
                        statement.setDouble(5, setting.getLocalConfidence());
                        statement.setDouble(6, setting.getSwarmConfidence());
                        statement.setInt(7, setting.getPSOIteration());
                        statement.setInt(8, setting.getSVMLearningCount());
                        statement.setInt(9, setting.getSwarmSize());
                        statement.executeUpdate();
                        statement.close();
                    }
                    catch(SQLException e)
                    {
                        e.printStackTrace();
                        System.exit(-1);
                    }
                }
            }
        }
        dbComponent.deactivate();
    }
}

