package projetEcole;

import java.util.Date;

public class Paiement {
	private Date datePaiement;
	private double montant;
	private Parent parent;
	private ModalitePaiement modalite;

	public Paiement() {
	}

	public Date getDatePaiement() {
		return datePaiement;
	}

	public void setDatePaiement(Date datePaiement) {
		this.datePaiement = datePaiement;
	}

	public double getMontant() {
		return montant;
	}

	public void setMontant(double montant) {
		this.montant = montant;
	}

	public Parent getParent() {
		return parent;
	}

	public void setParent(Parent parent) {
		this.parent = parent;
	}

	public ModalitePaiement getModalite() {
		return modalite;
	}

	public void setModalite(ModalitePaiement modalite) {
		this.modalite = modalite;
	}
}