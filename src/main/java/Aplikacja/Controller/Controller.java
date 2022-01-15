package Aplikacja.Controller;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import Aplikacja.Model.*;
import View.*;

public class Controller {

    private static ArrayList<Uzytkownik> administratorzy = new ArrayList<>();
    private static ArrayList<Komunikat> komunikaty = new ArrayList<>();
    private static ArrayList<Klient> klienci = new ArrayList<>();
    public static ArrayList<Pokoj> pokoje = new ArrayList<>();
    public static ArrayList<Rezerwacja> rezerwacje = new ArrayList<>();
    private static Uzytkownik aktualnyUzytkownik;
    private static int counter = 0;

    private static Widok widok;

    public static void zglosRezerwacje() {
        try {
            String[] data = widok.podajDaneZglosRezerwacje(pokoje);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            Rezerwacja rezerwacja = new Rezerwacja(
                    counter++,
                    "Zgloszona",
                    (Klient) aktualnyUzytkownik,
                    LocalDate.parse(data[0], formatter),
                    LocalDate.parse(data[1], formatter),
                    szukajPokoj(Integer.parseInt(data[2])));
            szukajPokoj(Integer.parseInt(data[2])).dodajRezerwacje(rezerwacja);
            rezerwacje.add(rezerwacja);
            
            String[] daneKomunikatu = new String[3];
            daneKomunikatu[0] = Integer.toString(rezerwacja.getNumer());
            daneKomunikatu[1] = "Zgloszenie rezerwacji";
            daneKomunikatu[2] = "Rezerwacja " + Integer.toString(rezerwacja.getNumer()) + " zostala zgloszona";
            Komunikat kom = generujKomunikat(daneKomunikatu);
            kom.setOdbiorca(administratorzy.get(0));
            kom.setNadawca(aktualnyUzytkownik);
            wyslijKomunikat(kom);
        } catch (Exception ex) {
            widok.wyswietlBlad(ex.getMessage());
        }
    }

    public static void zglosUsunRezerwacje() {
        try {
            int data = widok.podajDaneZglosUsunRezerwacje();
            
            String[] daneKomunikatu = new String[3];
            daneKomunikatu[0] = Integer.toString(data);
            daneKomunikatu[1] = "Zgloszenie usuniecia rezerwacji";
            daneKomunikatu[2] = "Zgloszenie anulowania rezerwacji pod numerem: " + Integer.toString(szukajRezerwacje(data).getNumer());
            Komunikat kom = generujKomunikat(daneKomunikatu);
            kom.setOdbiorca(administratorzy.get(0));
            kom.setNadawca(aktualnyUzytkownik);
            wyslijKomunikat(kom);
        } catch (Exception ex) {
            widok.wyswietlBlad(ex.getMessage());
        }
    }

    public static void zglosModyfikujRezerwacje() {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String[] data = widok.podajDaneZglosModyfikujRezerwacje();
            int index = Integer.parseInt(data[0]);

            String[] daneKomunikatu = new String[3];
            daneKomunikatu[0] = Integer.toString(index);
            daneKomunikatu[1] = "Zgloszenie modyfikacji rezerwacji";
            daneKomunikatu[2] = "Numer modyfikowanej rezerwacji: " + Integer.toString(index) + " Nowy okres: " + data[1] + "-" + data[2];
            Komunikat kom = generujKomunikat(daneKomunikatu);
            kom.setOdbiorca(administratorzy.get(0));
            kom.setNadawca(aktualnyUzytkownik);
            wyslijKomunikat(kom);
        } catch (Exception ex) {
            widok.wyswietlBlad(ex.getMessage());
        }
    }

    public static void zatwierdzRezerwacje(int val) {//
        Rezerwacja rezerwacja = szukajRezerwacje(val);
        rezerwacja.getPokoj().dodajRezerwacje(rezerwacja);
        if (rezerwacja != null) {
            rezerwacja.setStatus("Zatwierdzona");
            String[] daneKomunikatu = new String[3];
            daneKomunikatu[0] = Integer.toString(val);
            daneKomunikatu[1] = "Informacja od hotelu";
            daneKomunikatu[2] = "Rezerwacja " + val + " zostala zatwierdzona";
            Komunikat kom = generujKomunikat(daneKomunikatu);
            kom.setOdbiorca(rezerwacja.getKlient());
            kom.setNadawca(aktualnyUzytkownik);
            wyslijKomunikat(kom);
        }
    }

    public static void usunRezerwacje(int val) {
        try {
            boolean isSucceded = false;
            Rezerwacja rezerwacja = szukajRezerwacje(val);
            if (rezerwacja != null) {
                Pokoj pokoj = rezerwacja.getPokoj();
                pokoj.usunRezerwacje(rezerwacja);
                rezerwacja.setStatus("Usunięta");
                isSucceded = true;
            }
            String[] daneKomunikatu = new String[3];
            daneKomunikatu[0] = Integer.toString(val);
            daneKomunikatu[1] = isSucceded ? "Aktualizacja sie udala" : "Aktualizacja sie nie udala";
            daneKomunikatu[2] = "Rezerwacja " + Integer.toString(val) + " zostala usunieta";
            Komunikat kom = generujKomunikat(daneKomunikatu);
            kom.setOdbiorca(rezerwacja.getKlient());
            kom.setNadawca(aktualnyUzytkownik);
            wyslijKomunikat(kom);
        } catch (Exception ex) {
            widok.wyswietlBlad(ex.getMessage());
        }
    }

    public static void modyfikujRezerwacje(int val, LocalDate p, LocalDate k) {
        try {
            boolean isSucceded = false;
            Rezerwacja rezerwacja = szukajRezerwacje(val);
            String[] daneKomunikatu = new String[3];
            if (rezerwacja != null) {
                daneKomunikatu[0] = Integer.toString(val);
                Pokoj pokoj = rezerwacja.getPokoj();
                if (pokoj.sprawdzZgodnoscTerminow(p, k)) {
                    rezerwacja.setPoczatek(p);
                    rezerwacja.setKoniec(k);
                    isSucceded = true;
                    daneKomunikatu[2] = "Termin rezerwacji numer: " + Integer.toString(val) + " Okres: " + p.toString() + "-" + k.toString() + " zostal zatwierdzony";
                } else {
                    daneKomunikatu[2] = "Termin rezerwacji: " + Integer.toString(val) + " nie jest dostepny";
                }
            } else {
                daneKomunikatu[2] = "Rezerwacja o podanym numerze nie istnieje";
            }

            daneKomunikatu[1] = isSucceded ? "Aktualizacja sie udala" : "Aktualizacja sie nie udala";

            Komunikat kom = generujKomunikat(daneKomunikatu);
            kom.setOdbiorca(rezerwacja.getKlient());
            kom.setNadawca(aktualnyUzytkownik);
            wyslijKomunikat(kom);
        } catch (Exception ex) {
            widok.wyswietlBlad(ex.getMessage());
        }
    }

    public static Rezerwacja szukajRezerwacje(int numer) {
        for (Rezerwacja r : rezerwacje) {
            if (r.getNumer() == numer) {
                return r;
            }
        }
        return null;
    }

    public static Pokoj szukajPokoj(int val) {
        for (Pokoj p : pokoje) {
            if (p.getNumer() == val) {
                return p;
            }
        }
        return null;
    }

    public static Komunikat generujKomunikat(String[] val) {
        Komunikat komunikat = new Komunikat();
        komunikat.setNumerRezerwacji(Integer.parseInt(val[0]));
        komunikat.setNaglowek(val[1]);
        komunikat.setTresc(val[2]);
        return komunikat;
    }

    public static void wyslijKomunikat(Komunikat kom) {
        if (kom != null) {
            komunikaty.add(kom);
            System.out.println(""+komunikaty.size());
            Uzytkownik odbiorca = kom.getOdbiorca();
            odbiorca.odbierzKomunikat(kom);
        }
    }

    public static void main(String[] args) throws ParseException {
        Uzytkownik pracownik1 = new Uzytkownik("Maciej", "Spaleniak", "mspaleniak@gmail.com", "12345");
        administratorzy.add(pracownik1);
        Klient klient1 = new Klient("Kamil", "Sliwinski", "ksliwinski@gmail.com", "56789");
        klienci.add(klient1);
        Pokoj pokoj1 = new Pokoj(1, 25.05F, true);
        Pokoj pokoj2 = new Pokoj(2, 30.12F, false);
        Pokoj pokoj3 = new Pokoj(3, 15.55F, true);
        pokoje.add(pokoj1);
        pokoje.add(pokoj2);
        pokoje.add(pokoj3);

        widok = new Widok();
        zacznijAplikacje();
    }

    private static void zacznijAplikacje() throws ParseException {
        do {
            String[] daneLogowania = widok.wybierzUprawnienia();
            int operation = 0;
            switch (Integer.parseInt(daneLogowania[0])) {
                case 1:
                    Optional<Uzytkownik> administrator = administratorzy.stream().filter(e -> e.getHaslo().equals(daneLogowania[2]) && e.getEmail().equals(daneLogowania[1])).findFirst();
                    if (administrator.isPresent()) {
                        aktualnyUzytkownik = administrator.get();
                        widok.wyswietlWiadomosc("Aktualny uzytkownik ustawiony");
                        do {
                            operation = widok.menu(1);
                            switch (operation) {
                                case 1:
                                    for (Komunikat k : komunikaty) {
                                    	if (k.getOdbiorca() == aktualnyUzytkownik) System.out.println(k.toString());
                                    }
                                    break;
                                case 2:
                                    zatwierdzRezerwacje(widok.podajDaneRezerwacje());
                                    break;
                                case 3:
                                    usunRezerwacje(widok.podajDaneRezerwacje());
                                    break;
                                case 4:
                                    String[] data = widok.podajDaneModyfikujRezerwacje();
                                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                                    modyfikujRezerwacje(Integer.parseInt(data[0]), LocalDate.parse(data[1], formatter), LocalDate.parse(data[2], formatter));
                                    break;
                                default:
                                    break;
                            }
                        }
                        while (operation != 5);
                    } else {
                        widok.wyswietlBlad("Nie ma takiego uzytkownika");
                    }
                    break;
                case 2:
                    Optional<Klient> klient = klienci.stream().filter(e -> e.getHaslo().equals(daneLogowania[2]) && e.getEmail().equals(daneLogowania[1])).findFirst();
                    if (klient.isPresent()) {
                        aktualnyUzytkownik = klient.get();
                        widok.wyswietlWiadomosc("Aktualny uzytkownik ustawiony");
                        do {
                            operation = widok.menu(2);
                            switch (operation) {
                                case 1:
                                    for (Komunikat k : komunikaty) {
                                        if (k.getOdbiorca() == aktualnyUzytkownik) System.out.println(k.toString());
                                    }
                                    break;
                                case 2:
                                    zglosRezerwacje();
                                    break;
                                case 3:
                                    zglosUsunRezerwacje();
                                    break;
                                case 4:
                                    zglosModyfikujRezerwacje();
                                    break;
                                default:
                                    break;
                            }
                        }
                        while (operation != 5);
                    } else {
                        widok.wyswietlBlad("Nie ma takiego uzytkownika");
                    }
                    break;
                default:
                    break;
            }
        }
        while (true);
    }
}
