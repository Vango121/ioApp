import Aplikacja.Model.Klient;
import Aplikacja.Model.Pokoj;
import Aplikacja.Model.Rezerwacja;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Dane {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
   LocalDate getLocalDate(){
       LocalDate parse = LocalDate.parse("11/01/2020", formatter);
       return parse;
    }
    public ArrayList<Klient> klients = new ArrayList<>(){
        {
            add(new Klient("Marcel","Barski","email","haslo"));
            add(new Klient("Karol","Kowalski","eemail","hass"));
            add(new Klient("Adam", "Lambert","adam@lambert","olivier"));
            add(new Klient("Krystian","Karski","email","haslo"));
            add(new Klient("Kacper","Kaminski","eemail","hass"));
            add(new Klient("Frieddie", "Lambbert","adamb@lambbert","olivier"));
        }
    };
    public String[] imiona = {"Marcel","Karol", "Adam", "Krystian", "Kacper", "Frieddie"};
    public Pokoj[] pokoje = {new Pokoj(0,50,true),new Pokoj(1,30,false)};
    public Rezerwacja[] rezerwacje = {new Rezerwacja(0,"status",klients.get(0), getLocalDate(),getLocalDate(), pokoje[0]),
            new Rezerwacja(1,"status1",klients.get(1), getLocalDate(),getLocalDate(), pokoje[0]),
            new Rezerwacja(2,"status2",klients.get(2), getLocalDate(),getLocalDate(), pokoje[1]),
            new Rezerwacja(3,"status4",klients.get(3), getLocalDate(),getLocalDate(), pokoje[1])};

}