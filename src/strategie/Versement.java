package strategie;

import java.util.Date;

import factory.MoyenPaiement;

public class Versement {
	private Date datePaiement;
	private double montant;
	private MoyenPaiement moyenPaiement;

	public Versement(Date datePaiement, double montant, MoyenPaiement moyenPaiement) {
		this.datePaiement = datePaiement;
		this.montant = montant;
		this.moyenPaiement = moyenPaiement;
	}

	public Date getDatePaiement() {
		return datePaiement;
	}

	public double getMontant() {
		return montant;
	}

	public MoyenPaiement getMoyenPaiement() {
		return moyenPaiement;
	}
}