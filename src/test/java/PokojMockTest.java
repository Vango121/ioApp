import Aplikacja.Model.Pokoj;
import Aplikacja.Model.Rezerwacja;
import mockit.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PokojMockTest {

    Dane dane;

    @BeforeAll
    void init(){
        dane = new Dane();
    }


    @Tested
    private Pokoj pokoj;

    @Injectable
    int numer = 0;

    @Injectable
    float powierzchnia = 50f;

    @Injectable
    boolean balkon = true;

    @Capturing
    Daty daty;

    @Test
    public void testSprawdzZgodnoscTerminow(){
        new MockUp<Daty>(){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            @Mock
            LocalDate[] getLocalDate() {
                LocalDate parse = LocalDate.parse("14/01/2020", formatter);
                LocalDate parse1 = LocalDate.parse("15/01/2020", formatter);
                return new LocalDate[]{parse,parse1};
            }
        };

        new Expectations(){{
            daty.getLocalDate(); result=dane.daty.get(0);
        }};
        assertTrue(pokoj.sprawdzZgodnoscTerminow(daty.getLocalDate()[0],daty.getLocalDate()[1]));
    }

}
