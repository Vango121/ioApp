package Aplikacja.Model;

public class Komunikat {

	private String naglowek;
	private String tresc;
	private int numerRezerwacji;
	private Uzytkownik nadawca;
	private Uzytkownik odbiorca;

	public void setNaglowek(String val) {
		this.naglowek = val;
	}

	public String getNaglowek() {
		return this.naglowek;
	}

	public void setTresc(String val) {
		this.tresc = val;
	}

	public String getTresc() {
		return this.tresc;
	}

	public void setNumerRezerwacji(int val) {
		this.numerRezerwacji = val;
	}

	public int getNumerRezerwacji() {
		return this.numerRezerwacji;
	}

	public void setNadawca(Uzytkownik val) {
		this.nadawca = val;
	}

	public Uzytkownik getNadawca() {
		return this.nadawca;
	}

	public void setOdbiorca(Uzytkownik val) {
		this.odbiorca = val;
	}

	public Uzytkownik getOdbiorca() {
		return this.odbiorca;
	}
	
	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("Naglowek: " + naglowek + '\n');
		sb.append("Tresc: " + tresc + '\n');
		sb.append("Numer powiazanej rezerwacji: " + Integer.toString(numerRezerwacji) + '\n');
		sb.append("Odbiorca: " + '\n');
		sb.append(odbiorca.toString() + '\n');
		sb.append("Nadawca: " + '\n');
		sb.append(nadawca.toString());
		return sb.toString();
	}
}
