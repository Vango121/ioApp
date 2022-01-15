
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;


@Suite
@SuiteDisplayName("Rezerwacje suite")
@SelectClasses({ControllerTest.class,PokojTest.class})
public class RezerwacjeTestSuite {
}
