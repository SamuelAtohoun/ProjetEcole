package projetEcole;

import java.util.Date;

import state.EtatCreneau;

public class Creneau {
	private Date date;
	private String heureDebut; // Ou type LocalTime
	private String heureFin; // Ou type LocalTime
	private EtatCreneau etatCreneau;

	public EtatCreneau getEtatCreneau() {
		return etatCreneau;
	}

	public void setEtatCreneau(EtatCreneau etatCreneau) {
		this.etatCreneau = etatCreneau;
	}

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

	public void saturerCreneau() {
		etatCreneau.saturerCreneau();
	}

	public void libererCreneau() {
		etatCreneau.libererCreneau();
	}
}
