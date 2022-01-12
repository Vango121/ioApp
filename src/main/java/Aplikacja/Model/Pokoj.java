package Aplikacja.Model;

import java.time.LocalDate;
import java.util.*;

public class Pokoj {

	private int numer;
	private float powierzchnia;
	private boolean balkon;
	private ArrayList<Rezerwacja> rezerwacje = new ArrayList<>();

	public Pokoj(int numer, float powierzchnia, boolean balkon)
	{
		this.numer = numer;
		this.powierzchnia = powierzchnia;
		this.balkon = balkon;
	}
	
	public int getNumer() {
		return this.numer;
	}

	public void setNumer(int val) {
		this.numer = val;
	}

	public void setBalkon(boolean val) {
		this.balkon = val;
	}

	public boolean getBalkon() {
		return this.balkon;
	}

	public void setPowierzchnia(float val) {
		this.powierzchnia = val;
	}

	public float getPowierzchnia() {
		return this.powierzchnia;
	}

	public void setRezerwacje(ArrayList<Rezerwacja> val) {
		this.rezerwacje = val;
	}

	public ArrayList<Rezerwacja> getRezerwacje() {
		return this.rezerwacje;
	}
	
	public boolean sprawdzZgodnoscTerminow(LocalDate p, LocalDate k)
	{
		for(Rezerwacja r : rezerwacje)
		{
			if((p.isAfter(r.getPoczatek()) && p.isBefore(r.getKoniec())) || (k.isAfter(r.getPoczatek()) && k.isBefore(r.getKoniec())))
				return false;	
		}
		return true;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Numer pokoju: " + Integer.toString(numer) + '\n');
		sb.append("Powierzchnia: " + String.valueOf(powierzchnia) + '\n');
		sb.append("Balkon: " + (balkon == true ? "Tak" : "Nie"));
		return sb.toString();
	}
	
	public void dodajRezerwacje(Rezerwacja r)
	{
		rezerwacje.add(r);
	}
	
	public void usunRezerwacje(Rezerwacja r)
	{
		rezerwacje.remove(r);
	}

}
