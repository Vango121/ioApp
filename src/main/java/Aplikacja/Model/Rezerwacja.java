package Aplikacja.Model;

import java.time.LocalDate;
import java.time.*;

public class Rezerwacja {

	private int numer;
	private String status;
	private Klient klient;
	private LocalDate poczatek;
	private LocalDate koniec;
	private Pokoj pokoj;

	public Rezerwacja(int numer, String status, Klient klient, LocalDate poczatek, LocalDate koniec, Pokoj pokoj) {
		this.numer = numer;
		this.status = status;
		this.klient = klient;
		this.poczatek = poczatek;
		this.koniec = koniec;
		this.pokoj = pokoj;
	}

	public Pokoj getPokoj() {
		return this.pokoj;
	}

	public void setPokoj(Pokoj val) {
		this.pokoj = val;
	}

	public void setStatus(String val) {
		this.status = val;
	}

	public String getStatus() {
		return this.status;
	}

	public String toString() {
		throw new UnsupportedOperationException();
	}

	public Klient getKlient() {
		return this.klient;
	}

	public void setKlient(Klient klient) {
		this.klient = klient;
	}

	public LocalDate getPoczatek() {
		return this.poczatek;
	}

	public void setPoczatek(LocalDate val) {
		this.poczatek = val;
	}

	public LocalDate getKoniec() {
		return this.koniec;
	}

	public void setKoniec(LocalDate val) {
		this.koniec = val;
	}

	public void setNumer(int nr) {
		this.numer = nr;
	}

	public int getNumer() {
		return this.numer;
	}
}
