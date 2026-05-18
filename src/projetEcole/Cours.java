package projetEcole;

public class Cours {
	private String libelle;
	private ModalitePaiement modalite; 

	public Cours() {
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public ModalitePaiement getModalite() {
		return modalite;
	}

	public void setModalite(ModalitePaiement modalite) {
		this.modalite = modalite;
	}
}