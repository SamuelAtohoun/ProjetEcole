package projetEcole;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import factory.MoyenPaiement;
import strategie.StrategiePaiement;
import strategie.Versement;

public class Paiement {
	private Parent parent;
	private ModalitePaiement modalite;
	private StrategiePaiement strategie;
	private List<Versement> versements;

	public Paiement(ModalitePaiement modalite) {
		this.modalite = modalite;
		this.versements = new ArrayList<>();
	}

	// Calcule le total déjà versé
	public double getMontantDejaPaye() {
		double total = 0;
		for (Versement v : versements) {
			total += v.getMontant();
		}
		return total;
	}

	// Calcule ce qui reste à payer
	public double getMontantRestant() {
		return modalite.getTarif() - getMontantDejaPaye();
	}

	// Effectue le prochain versement
	public void effectuerVersement(MoyenPaiement moyenPaiement) {
		if (getMontantRestant() <= 0) {
			throw new IllegalStateException("Ce paiement est déjà soldé.");
		}
		double montant = strategie.calculerProchainVersement(modalite, getMontantDejaPaye());
		versements.add(new Versement(new Date(), montant, moyenPaiement));
		modalite.notifierObservers("Versement de " + montant + " € effectué. Reste : " + getMontantRestant() + " €");
	}

	// Getters / Setters
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

	public StrategiePaiement getStrategie() {
		return strategie;
	}

	public void setStrategie(StrategiePaiement strategie) {
		this.strategie = strategie;
	}

	public List<Versement> getVersements() {
		return versements;
	}
}