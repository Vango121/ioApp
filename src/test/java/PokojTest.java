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
    instances[0] = new Pokoj(0,50,true);
    instances[1] = new Pokoj(1,30,false);
    dane = new Dane();
    }

    private static Stream<Arguments> addFixture() {
        return Stream.of(
                Arguments.of((Object) dane.rezerwacje));
    }

    @ParameterizedTest
    @MethodSource("addFixture")
    void testDodajRezerwacje(Rezerwacja[] rezerwacje){
        for (int i = 0; i <2; i++) {
            for (int j = 0; j <4; j++) {
                instances[i].dodajRezerwacje(rezerwacje[j]);
                Rezerwacja rezerwacja1 = instances[i].getRezerwacje().get(j);
                assertSame(rezerwacja1,rezerwacje[j]);
            }
        }
    }

}
