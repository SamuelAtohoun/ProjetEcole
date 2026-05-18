package projetEcole;

import java.util.Date;

public class Inscrire {
	private Cours cours; // Clé primaire & étrangère id_cours
	private Enfant enfant; // Clé primaire & étrangère id_enfant
	private Date dateInscription;

	public Inscrire() {
	}

	public Cours getCours() {
		return cours;
	}

	public void setCours(Cours cours) {
		this.cours = cours;
	}

	public Enfant getEnfant() {
		return enfant;
	}

	public void setEnfant(Enfant enfant) {
		this.enfant = enfant;
	}

	public Date getDateInscription() {
		return dateInscription;
	}

	public void setDateInscription(Date dateInscription) {
		this.dateInscription = dateInscription;
	}
}