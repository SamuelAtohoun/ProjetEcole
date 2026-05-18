package projetEcole;

public class Gestionnaire extends Utilisateur {
	private Utilisateur utilisateur;

	public Gestionnaire() {
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}
}