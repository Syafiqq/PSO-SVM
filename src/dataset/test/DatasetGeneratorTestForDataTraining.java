package dataset.test;

import dataset.DatasetGenerator;
import dataset.component.Stroke;
import dataset.component.core.Dataset;
import dataset.component.core.DatasetConverter;
import main.Main;
import org.junit.Before;
import org.junit.Test;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;

import static org.junit.Assert.*;

/**
 * Created by Muhammad Syafiq on 8/12/2016.
 * Email : Syafiq.rezpector@gmail.com
 * GitHub : Syafiqq
 */
@SuppressWarnings("Duplicates") public class DatasetGeneratorTestForDataTraining
{
    @Parameterized.Parameter
    private Dataset dataset;
    @Parameterized.Parameter
    private DatasetConverter<LinkedHashMap<Integer, ArrayList<Integer>>> encoder;
    @Parameterized.Parameter
    private DatasetConverter<LinkedHashMap<ArrayList<Integer>, Integer>> decoder;
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

    @Test
    public void testStatus01()
    {
        final Stroke[][] trainings = this.dataset.getTraining();
        for(Stroke[] training : trainings)
        {
            System.out.println(Arrays.toString(training));
        }
    }

    @Test
    public void testStatusEncoder01()
    {
        System.out.println(this.encoder.getTraining().toString());
    }

    @Test
    public void testStatusDecoder01()
    {
        System.out.println(this.decoder.getTraining().toString());
    }
}