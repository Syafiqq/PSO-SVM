package dataset.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by Muhammad Syafiq on 8/12/2016.
 * Email : Syafiq.rezpector@gmail.com
 * GitHub : Syafiqq
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({DatasetGeneratorTestForType.class, DatasetGeneratorTestForStatus.class, DatasetGeneratorTestForDataTraining.class, DatasetGeneratorTestForDataTesting.class})
public class AllComponentTest
{
}
