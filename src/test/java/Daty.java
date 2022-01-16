import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Daty {
    String data1;
    String data2;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    public Daty(String data1, String data2) {
        this.data1 = data1;
        this.data2 = data2;
    }
    LocalDate[] getLocalDate() {
        LocalDate parse = LocalDate.parse(data1, formatter);
        LocalDate parse1 = LocalDate.parse(data2, formatter);
        return new LocalDate[]{parse,parse1};
    }

}
