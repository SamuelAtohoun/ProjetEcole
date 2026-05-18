package projetEcole;

import java.util.Date;

public class Creneau {
	private Date date;
	private String heureDebut; // Ou type LocalTime
	private String heureFin; // Ou type LocalTime

	public Creneau() {
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getHeureDebut() {
		return heureDebut;
	}

	public void setHeureDebut(String heureDebut) {
		this.heureDebut = heureDebut;
	}

	public String getHeureFin() {
		return heureFin;
	}

	public void setHeureFin(String heureFin) {
		this.heureFin = heureFin;
	}
}
