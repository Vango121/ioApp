import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class KlientTest {

    private Dane dane;
    @BeforeAll
    void init(){
        dane = new Dane();
    }

    @Test
    void testGetter(){
        for (int i = 0; i <dane.klients.size(); i++) {
            assertEquals(dane.imiona[i],dane.klients.get(i).getImie());
        }
        for (int i = 0; i <dane.klients.size(); i++) {
            assertNotEquals(dane.klients.get(i).getImie(),dane.klients.get(i).getNazwisko());
        }
    }

    @ParameterizedTest
    @ValueSource(ints = {0,1,2,3,4,5})
    void testEquals(int number){
        for (int i = number; i<6;i++){
            if(i==number) assertTrue(dane.klients.get(i).equals(dane.klients.get(number)));
            else assertFalse(dane.klients.get(i).equals(dane.klients.get(number)));
        }
    }

}
