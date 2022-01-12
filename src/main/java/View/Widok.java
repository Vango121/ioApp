package View;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import Aplikacja.Model.Pokoj;
import java.util.*;

public class Widok {

    private static Scanner scanner = new Scanner(System.in);

    public int menu(int permission) {
        switch (permission) {
            case 1:
                System.out.println("Dostępne operacje:");
                System.out.println("1 - Zobacz wiadomości:");
                System.out.println("2 - Zatwierdz rezerwacje:");
                System.out.println("3 - Usun rezerwacje:");
                System.out.println("4 - Modyfikuj rezerwacje:");
                System.out.println("5 - Wyjdz");
                break;
            case 2:
                System.out.println("Dostępne operacje:");
                System.out.println("1 - Zobacz wiadomosci");
                System.out.println("2 - Zgłoś rezerwację:");
                System.out.println("3 - Zgłoś rezygnację z rezerwacji:");
                System.out.println("4 - Zgłoś modyfikację rezerwacji:");
                System.out.println("5 - Wyjdz");
                break;
        }
        return scanner.nextInt();
    }

    public String[] wybierzUprawnienia() {
        String[] daneLogowania = new String[3];
        System.out.println("Wybierz poziom uprawnień");
        System.out.println("1 - Administrator");
        System.out.println("2 - Klient");
        daneLogowania[0] = String.valueOf(scanner.nextInt());
        scanner.nextLine();
        System.out.println("Wprowadz Email");
        daneLogowania[1] = scanner.nextLine();
        System.out.println("Wprowadz Haslo");
        daneLogowania[2] = scanner.nextLine();
        return daneLogowania;
    }

    public void wyswietlBlad(String error) {
        System.out.println("BLAD SYSTEMU: " + error);
    }

    public void wyswietlWiadomosc(String msg) {
        System.out.println("WIADOMOSC: " + msg);
    }

    public int podajDaneRezerwacje() {
        System.out.println("Podaj numer rezerwacji ktora chcesz zatwierdzic/anulowac");
        return scanner.nextInt();
    }

    public String[] podajDaneModyfikujRezerwacje() throws ParseException {
        String[] dane = new String[3];
        System.out.println("Podaj numer rezerwacji: ");
        dane[0] = String.valueOf(scanner.nextInt());
        System.out.println("Podaj termin poczatku: ");
        scanner.nextLine();
        dane[1] = scanner.nextLine();
        System.out.println("Podaj termin koncowy: ");
        dane[2] = scanner.nextLine();
        return dane;
    }

    public void wyswietlFormularzPrzykladowy() {
        System.out.println("Podaj termin początku:" + '\n');
        System.out.println("10/12/2021");
        System.out.println("Podaj termin końcowy:" + '\n');
        System.out.println("13/12/2021");
        System.out.println("Dostępne pokoje: (Lista pokoi bedzie wyswietlona)" + '\n');
        System.out.println("Wprowadz numer pokoju: " + '\n');
        System.out.println("5");
    }

    public String[] podajDaneZglosRezerwacje(ArrayList<Pokoj> pokoje) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        scanner.nextLine();
        String[] dane = new String[3];
        System.out.println("Podaj termin poczatku:" + '\n');
        dane[0] = scanner.nextLine();
        System.out.println("Podaj termin koncowy:" + '\n');
        dane[1] = scanner.nextLine();
        System.out.println("Dostepne pokoje: " + '\n');
        for (Pokoj p : pokoje) {
            if (p.sprawdzZgodnoscTerminow(LocalDate.parse(dane[0], formatter), LocalDate.parse(dane[1], formatter)))
                System.out.println(p.toString());
        }
        System.out.println("Wprowadz numer pokoju: " + '\n');
        dane[2] = scanner.nextLine();
        return dane;
    }

    public int podajDaneZglosUsunRezerwacje() {
        System.out.println("Podaj numer rezerwacji z ktorej chcesz zrezygnowac");
        return scanner.nextInt();
    }

    public String[] podajDaneZglosModyfikujRezerwacje() throws ParseException {
        scanner.nextLine();
        String[] dane = new String[3];
        System.out.println("Podaj numer rezerwacji ktorą chcesz zmodyfikować");
        dane[0] = scanner.nextLine();
        System.out.println("Podaj nowy termin poczatkowy");
        dane[1] = scanner.nextLine();
        System.out.println("Podaj nowy termin koncowy");
        dane[2] = scanner.nextLine();
        return dane;
    }
}
