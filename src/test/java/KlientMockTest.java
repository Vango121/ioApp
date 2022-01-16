import Aplikacja.Model.Klient;
import Aplikacja.Model.Rezerwacja;
import mockit.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class KlientMockTest {
    Dane dane;

    @BeforeAll
    void init(){
        dane = new Dane();
    }

    @Tested
    Klient klient;

    @Injectable
    String imie = "Marcel";

    @Injectable
    String nazwisko = "Barski";

    @Injectable
    String email = "email";

    @Injectable
    String haslo = "haslo";

    @Test
    void testEquals() {
        new Expectations(){{
            klient.equals(dane.klients.get(0)); result = true;
            klient.equals(dane.klients.get(1)); result = false;
        }};
            assertTrue(klient.equals(dane.klients.get(0)));
            assertFalse(klient.equals(dane.klients.get(1)));
    }

    @Test
    void testDodajRezerwacje(){
        klient.dodajRezerwacje(dane.rezerwacje[0]);
        new Expectations(){{
            klient.getRezerwacje().size(); result = 1;
        }};
        assertEquals(1,klient.getRezerwacje().size());
    }
}
