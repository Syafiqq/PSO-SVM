package algorithm.svm.core.test;

import algorithm.svm.component.Parameter;
import algorithm.svm.core.SVMLearner;
import dataset.DatasetGenerator;
import dataset.component.core.Dataset;
import dataset.component.core.DatasetConverter;
import dataset.component.stroke.exception.StrokeException;
import java.util.LinkedHashMap;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import main.Main;
import org.junit.Before;
import org.junit.Test;
import org.junit.runners.Parameterized;

/**
 * Created by Muhammad Syafiq on 8/25/2016.
 * Email : Syafiq.rezpector@gmail.com
 * GitHub : Syafiqq
 */
public class SVMLearnerTest
{
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

    @Test public void testTestClassifyException() throws StrokeException
    {
        final Parameter  svmParameter = new Parameter(0.1, 0.2, 1000, this.dataset.getParameter().length, this.dataset.getStatuses().length, 1000000);
        final SVMLearner svm          = new SVMLearner(svmParameter, this.dataset.getTraining(), this.dataset.getTesting());
        svm.doSequentialLearning();
        System.out.println(svm.evaluateStrokeData(this.dataset.getTesting()));
    }

    @Test public void testTestClassify_000() throws StrokeException
    {
        final Parameter  svmParameter = new Parameter(0.1, 0.2, 1000, this.dataset.getParameter().length, this.dataset.getStatuses().length, 1000000);
        final SVMLearner svm          = new SVMLearner(svmParameter, this.dataset.getTraining(), this.dataset.getTesting());
        svm.doSequentialLearning();
        System.out.println(svm.evaluateStrokeData());
    }

    @Test public void testTestClassifyWithIterationWithDebug() throws StrokeException
    {
        final Parameter  svmParameter = new Parameter(0.1, 0.2, 1000, this.dataset.getParameter().length, this.dataset.getStatuses().length, 3);
        final SVMLearner svm          = new SVMLearner(svmParameter, this.dataset.getTraining(), this.dataset.getTesting());
        Random           random       = ThreadLocalRandom.current();
        for(int i = 0, is = 100; ++i < is; )
        {
            svm.doSequentialLearning();
            System.out.println(svm.evaluateStrokeData());
            svmParameter.updateParameter(random.nextDouble(), random.nextDouble(), random.nextDouble() * 1000);
        }

    }

    @Test public void testTestClassifyWithIterationWithoutDebug() throws StrokeException
    {
        final Parameter  svmParameter = new Parameter(0.1, 0.2, 1000, this.dataset.getParameter().length, this.dataset.getStatuses().length, 3);
        final SVMLearner svm          = new SVMLearner(svmParameter, this.dataset.getTraining(), this.dataset.getTesting());
        Random           random       = ThreadLocalRandom.current();
        for(int i = 0, is = 10000; ++i < is; )
        {
            svm.doSequentialLearning();
            svm.evaluateStrokeData();
            svmParameter.updateParameter(random.nextDouble(), random.nextDouble(), random.nextDouble() * 1000);
        }
    }
}