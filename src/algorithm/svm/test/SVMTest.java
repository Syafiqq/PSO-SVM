package algorithm.svm.test;

import algorithm.svm.SVM;
import dataset.DatasetGenerator;
import dataset.component.core.Dataset;
import dataset.component.core.DatasetConverter;
import main.Main;
import org.junit.Before;
import org.junit.Test;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.LinkedHashMap;

import static org.junit.Assert.*;

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

    @Test public void testSVM_calculateTrainingVariance()
    {
        final SVM svm = new SVM(0.1, 0.2, 1000, this.dataset.getTraining(), this.dataset.getTesting());
        System.out.println();
    }

    @Test public void testSVMAugmentingFactor()
    {
        final SVM svm = new SVM(0.1, 0.2, 1000, this.dataset.getTraining(), this.dataset.getTesting());
        System.out.println(svm.getAugmentingFactor());
    }

    @Test public void testSVMLearingRate()
    {
        final SVM svm = new SVM(0.1, 0.2, 1000, this.dataset.getTraining(), this.dataset.getTesting());
        System.out.println(svm.getLearningRate());
    }

    @Test public void testSVMConstantCost()
    {
        final SVM svm = new SVM(0.1, 0.2, 1000, this.dataset.getTraining(), this.dataset.getTesting());
        System.out.println(svm.getConstantCost());
    }

    @Test public void testSVMDataTraining()
    {
        final SVM svm = new SVM(0.1, 0.2, 1000, this.dataset.getTraining(), this.dataset.getTesting());
        System.out.println(Arrays.toString(svm.getTraining()));
    }

    @Test public void testSVMDataTesting()
    {
        final SVM svm = new SVM(0.1, 0.2, 1000, this.dataset.getTraining(), this.dataset.getTesting());
        System.out.println(Arrays.toString(svm.getTesting()));
    }
}