package Aplikacja.Model;

import java.time.LocalDate;
import java.util.*;

public class Uzytkownik {

	protected int numer;
	protected String imie;
	protected String nazwisko;
	protected String email;
	protected String haslo;
	protected ArrayList<Komunikat> komunikaty = new ArrayList<>();
	protected static int counter = 0;

	public Uzytkownik(String imie, String nazwisko, String email, String haslo)
	{
		this.numer = ++counter;
		this.imie = imie;
		this.nazwisko = nazwisko;
		this.email = email;
		this.haslo = haslo;
	}
	
	public void setNumer(int val) {
		this.numer = val;
	}

	public int getNumer() {
		return this.numer;
	}

	public void setImie(String val) {
		this.imie = val;
	}

	public String getImie() {
		return this.imie;
	}

	public void setNazwisko(String val) {
		this.nazwisko = val;
	}

	public String getNazwisko() {
		return this.nazwisko;
	}

	public void setEmail(String val) {
		this.email = val;
	}

	public String getEmail() {
		return this.email;
	}

	public void setKomunikaty(ArrayList<Komunikat> val) {
		this.komunikaty = val;
	}

	public ArrayList<Komunikat> getKomunikaty() {
		return this.komunikaty;
	}
	
	public String getHaslo()
	{
		return this.haslo;
	}

	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("Imie: " + imie + ", Nazwisko : " + nazwisko + ", Email: " + email);
		return sb.toString();
	}
	
	public void odbierzKomunikat(Komunikat k)
	{
		komunikaty.add(k);
	}
}
