import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

@Suite
@SuiteDisplayName("All tests")
@SelectClasses({ControllerTest.class,PokojTest.class, KlientTest.class})
public class AllTestsSuite {
}
