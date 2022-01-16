import Aplikacja.Model.Klient;
import Aplikacja.Model.Pokoj;
import Aplikacja.Model.Rezerwacja;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Dane {
    public Dane() {
    }

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    LocalDate getLocalDate(String date) {
        LocalDate parse = LocalDate.parse(date, formatter);
        return parse;
    }
    public ArrayList<LocalDate[]> daty = new ArrayList<>(){
        {
            add(new LocalDate[]{getLocalDate("14/01/2020"),getLocalDate("15/01/2020")});
            add(new LocalDate[]{getLocalDate("16/01/2020"),getLocalDate("17/01/2020")});
            add(new LocalDate[]{getLocalDate("14/01/2020"),getLocalDate("13/01/2020")});
            add(new LocalDate[]{getLocalDate("15/01/2020"),getLocalDate("16/01/2020")});
        }
    };

    public ArrayList<Klient> klients = new ArrayList<>() {
        {
            add(new Klient("Marcel", "Barski", "email", "haslo"));
            add(new Klient("Karol", "Kowalski", "eemail", "hass"));
            add(new Klient("Adam", "Lambert", "adam@lambert", "olivier"));
            add(new Klient("Krystian", "Karski", "email", "haslo"));
            add(new Klient("Kacper", "Kaminski", "eemail", "hass"));
            add(new Klient("Frieddie", "Lambbert", "adamb@lambbert", "olivier"));
        }
    };
    public String[] imiona = {"Marcel", "Karol", "Adam", "Krystian", "Kacper", "Frieddie"};
    public Pokoj[] pokoje = {new Pokoj(0, 50, true), new Pokoj(1, 30, false)};
    public Rezerwacja[] rezerwacje = {new Rezerwacja(0, "status", klients.get(0), getLocalDate("13/01/2020"), getLocalDate("15/01/2020"), pokoje[0]),
            new Rezerwacja(1, "status1", klients.get(1), getLocalDate("11/02/2020"), getLocalDate("15/02/2020"), pokoje[0]),
            new Rezerwacja(2, "status2", klients.get(2), getLocalDate("16/03/2020"), getLocalDate("19/02/2020"), pokoje[1]),
            new Rezerwacja(3, "status4", klients.get(3), getLocalDate("11/01/2020"), getLocalDate("09/01/2020"), pokoje[1])};

}