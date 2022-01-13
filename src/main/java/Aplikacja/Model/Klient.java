package Aplikacja.Model;

import java.util.*;

public class Klient extends Uzytkownik {

	public Klient(String imie, String nazwisko, String email, String haslo) {
		super(imie, nazwisko, email, haslo);
	}

	private ArrayList<Rezerwacja> rezerwacje = new ArrayList<>();

	public void setRezerwacje(ArrayList<Aplikacja.Model.Rezerwacja> val) {
		this.rezerwacje = val;
	}

	public ArrayList<Aplikacja.Model.Rezerwacja> getRezerwacje() {
		return this.rezerwacje;
	}
	
	public void dodajRezerwacje(Rezerwacja r)
	{
		rezerwacje.add(r);
	}

}
