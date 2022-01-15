import Aplikacja.Controller.Controller;
import Aplikacja.Model.Pokoj;
import Aplikacja.Model.Rezerwacja;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.platform.suite.api.IncludeTags;
import org.junit.platform.suite.api.Suite;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.stream.Stream;


@Suite
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ControllerTest {
    static Dane dane;

    @BeforeAll
    void init() {
        dane = new Dane();
        Controller.rezerwacje.addAll(List.of(dane.rezerwacje));
        Controller.pokoje.addAll(List.of(dane.pokoje));
    }

    private static Stream<Arguments> addFixture() {
        return Stream.of(
                Arguments.of((Object) dane.rezerwacje));
    }

    @ParameterizedTest
    @MethodSource("addFixture")
    void testSzukajRezerwacje(Rezerwacja[] rezerwacje){
        for (int i = 0; i <rezerwacje.length; i++) {
            assertEquals(rezerwacje[i],Controller.szukajRezerwacje(i));
        }
    }
    @ParameterizedTest
    @ValueSource(ints = {0,1,2,3})
    void testZatwierdzRezerwacje(int number){
        Controller.zatwierdzRezerwacje(number);
        assertEquals("Zatwierdzona",Controller.rezerwacje.get(number).getStatus());
    }
    @ParameterizedTest
    @ValueSource(ints = {0,1})
    void testSzukajPokoj(int number){
        Controller.szukajPokoj(number);
        assertEquals(dane.pokoje[number].getNumer(),Controller.szukajPokoj(number).getNumer());
    }
}
