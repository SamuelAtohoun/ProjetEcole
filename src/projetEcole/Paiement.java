package projetEcole;

import Observer.PaymentObserver;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import strategie.StrategiePaiement;
import strategie.Versement;

public class Paiement {
	private Parent parent;
	private ModalitePaiement modalite;
	private StrategiePaiement strategie; // <-- contexte Strategy
	private List<Versement> versements; // <-- historique des versements
	private List<PaymentObserver> observers;

	public Paiement(ModalitePaiement modalite) {
		this.modalite = modalite;
		this.versements = new ArrayList<>();
		observers = new ArrayList<>();
	}
	
	// Ajouter un observer
    public void addObserver(PaymentObserver observer) {

        observers.add(observer);
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
	public void effectuerVersement() {
		double montant = strategie.calculerProchainVersement(modalite, getMontantDejaPaye());
		versements.add(new Versement(new Date(), montant));
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