import Aplikacja.Model.Pokoj;
import Aplikacja.Model.Rezerwacja;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.MethodName.class)
public class PokojTest {
    static Dane dane;
    static Pokoj instances[];

    @BeforeAll
    void init() {
        instances = new Pokoj[2];
        dane = new Dane();
        instances[0] = dane.pokoje[0];
        instances[1] = dane.pokoje[1];
    }

    private static Stream<Arguments> addFixture() {
        return Stream.of(
                Arguments.of((Object) dane.rezerwacje));
    }

    @ParameterizedTest
    @MethodSource("addFixture")
    void testDodajRezerwacje(Rezerwacja[] rezerwacje) {
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 4; j++) {
                instances[i].dodajRezerwacje(rezerwacje[j]);
                Rezerwacja rezerwacja1 = instances[i].getRezerwacje().get(j);
                assertSame(rezerwacja1, rezerwacje[j]);
            }
        }
        assertEquals(4, instances[0].getRezerwacje().size());
        assertEquals(4, instances[1].getRezerwacje().size());
    }

    @ParameterizedTest
    @MethodSource("addFixture")
    void testSprawdzRezerwacje(Rezerwacja[] rezerwacje) {
        //instances[0] - pokoj[0] sprawdz
        for (int i = 0; i <4; i++) {
            if(i<2){
                assertEquals(instances[0],rezerwacje[i].getPokoj());
            }else{
                assertNotEquals(instances[0],rezerwacje[i].getPokoj());
            }
        }
        for (int i = 0; i <4; i++) {
            if(i<2){
                assertNotEquals(instances[1],rezerwacje[i].getPokoj());
            }else{
                assertEquals(instances[1],rezerwacje[i].getPokoj());
            }
        }
    }
}
