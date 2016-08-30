package dataset.test;

import dataset.DatasetGenerator;
import dataset.component.core.Dataset;
import dataset.component.core.DatasetConverter;
import java.util.Arrays;
import java.util.LinkedHashMap;
import main.Main;
import org.junit.Before;
import org.junit.Test;
import org.junit.runners.Parameterized;

/**
 * Created by Muhammad Syafiq on 8/12/2016.
 * Email : Syafiq.rezpector@gmail.com
 * GitHub : Syafiqq
 */
@SuppressWarnings("Duplicates") public class DatasetGeneratorTestForDataTesting
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

    @Test
    public void testStatus01()
    {
        System.out.println(Arrays.toString(this.dataset.getTesting()));
    }

    @Test
    public void testStatusEncoder01()
    {
        System.out.println(this.encoder.getTesting().toString());
    }

    @Test
    public void testStatusDecoder01()
    {
        System.out.println(this.decoder.getTesting().toString());
    }
}