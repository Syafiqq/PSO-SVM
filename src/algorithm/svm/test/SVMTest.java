package algorithm.svm.test;

import algorithm.svm.OneAgainstAll;
import algorithm.svm.Parameter;
import algorithm.svm.SVM;
import dataset.DatasetGenerator;
import dataset.component.core.Dataset;
import dataset.component.core.DatasetConverter;
import dataset.component.stroke.exception.StrokeException;
import main.Main;
import org.junit.Before;
import org.junit.Test;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.LinkedHashMap;

/**
 * Created by Muhammad Syafiq on 8/15/2016.
 * Email : Syafiq.rezpector@gmail.com
 * GitHub : Syafiqq
 */
@SuppressWarnings("Duplicates") public class SVMTest
{
    @Parameterized.Parameter
    private Dataset dataset;
    @Parameterized.Parameter
    private DatasetConverter<LinkedHashMap<Integer, Integer>> encoder;
    @Parameterized.Parameter
    private DatasetConverter<LinkedHashMap<Integer, Integer>> decoder;
    @Parameterized.Parameter
    private DatasetGenerator generator;

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
        final SVM svm = new SVM(parameter, this.dataset.getTraining());
        final double[] variance = svm.getVariance();
        System.out.println(Arrays.toString(variance));
    }

    @Test public void testCalculateKernel_000() throws StrokeException
    {
        final Parameter parameter = new Parameter(0.1, 0.2, 1000, this.dataset.getParameter().length, this.dataset.getStatuses().length, 10);
        final SVM svm = new SVM(parameter, this.dataset.getTraining());
        final double[][] kernel = svm.getKernel();
        for(double[] lv1 : kernel)
        {
            System.out.println(Arrays.toString(lv1));
        }
    }

    @Test public void testGenerateOneAgainstAllClass_000() throws StrokeException
    {
        final Parameter parameter = new Parameter(0.1, 0.2, 1000, this.dataset.getParameter().length, this.dataset.getStatuses().length, 10);
        final SVM svm = new SVM(parameter, this.dataset.getTraining());
        final OneAgainstAll[] oaas = svm.getOneAgainstAllLevel();
        for(final OneAgainstAll oaa : oaas)
        {
            System.out.println(Arrays.toString(oaa.getClassData()));
        }
    }

    @Test public void testGenerateOneAgainstAllAllowed_000() throws StrokeException
    {
        final Parameter parameter = new Parameter(0.1, 0.2, 1000, this.dataset.getParameter().length, this.dataset.getStatuses().length, 10);
        final SVM svm = new SVM(parameter, this.dataset.getTraining());
        for(final OneAgainstAll oaa : svm.getOneAgainstAllLevel())
        {
            System.out.println(Arrays.toString(oaa.getAllowedData()));
        }
    }

    @Test public void testGenerateMatrixD() throws StrokeException
    {
        final Parameter parameter = new Parameter(0.1, 0.2, 1000, this.dataset.getParameter().length, this.dataset.getStatuses().length, 10);
        final SVM svm = new SVM(parameter, this.dataset.getTraining());
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
        final SVM svm = new SVM(parameter, this.dataset.getTraining());
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
        final SVM svm = new SVM(parameter, this.dataset.getTraining());
        svm.doSequentialLearning();
        for(final OneAgainstAll oaa : svm.getOneAgainstAllLevel())
        {
            System.out.printf("%12.10g\n", oaa.getBias());
        }
    }

    @Test public void testTestClassify_000() throws StrokeException
    {
        final Parameter parameter = new Parameter(0.1, 0.2, 1000, this.dataset.getParameter().length, this.dataset.getStatuses().length, 3);
        final SVM svm = new SVM(parameter, this.dataset.getTraining());
        svm.doSequentialLearning();
        System.out.println(svm.evaluateStrokeData(this.dataset.getTesting()));
    }

    @Test public void testDataTraining_000() throws StrokeException
    {
        final Parameter parameter = new Parameter(0.1, 0.2, 1000, this.dataset.getParameter().length, this.dataset.getStatuses().length, 3);
        final SVM svm = new SVM(parameter, this.dataset.getTraining());
        System.out.println(Arrays.toString(svm.getTraining()));
    }
}