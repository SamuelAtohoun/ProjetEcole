package projetEcole;

public class Programmer {
    private Creneau creneau; // Clé primaire & étrangère id_creneau
    private Cours cours;     // Clé primaire & étrangère id_cours
    private Salle salle;     // Clé primaire & étrangère id_salle

    public Programmer() {}

    public Creneau getCreneau() { return creneau; }
    public void setCreneau(Creneau creneau) { this.creneau = creneau; }
    public Cours getCours() { return cours; }
    public void setCours(Cours cours) { this.cours = cours; }
    public Salle getSalle() { return salle; }

	public void setSalle(Salle salle) {
		this.salle = salle;
	}
}