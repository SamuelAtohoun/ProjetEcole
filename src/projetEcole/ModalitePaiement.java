package projetEcole;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Observer.PaymentObserver;
import Observer.Subject;
import strategie.StrategiePaiement;

public class ModalitePaiement implements Subject {

	private Date dateEcheance;
	private double tarif;
	private int nombrePaiementMax;
	private StrategiePaiement strategie;
	private List<PaymentObserver> observers = new ArrayList<>();

	@Override
	public void addObserver(PaymentObserver observer) {
		observers.add(observer);
	}

	@Override
	public void removeObserver(PaymentObserver observer) {
		observers.remove(observer);
	}

	@Override
	public void notifierObservers(String message) {
		for (PaymentObserver observer : observers) {
			observer.onChangement(message);
		}
	}

	// Appelé quand une échéance change
	public void setDateEcheance(Date dateEcheance) {
		this.dateEcheance = dateEcheance;
		notifierObservers("Nouvelle échéance de paiement : " + dateEcheance);
	}

	// Appelé quand le tarif change
	public void setTarif(double tarif) {
		this.tarif = tarif;
		notifierObservers("Le tarif a été mis à jour : " + tarif + " €");
	}

	// Getters / Setters sans notification
	public Date getDateEcheance() {
		return dateEcheance;
	}

	public double getTarif() {
		return tarif;
	}

	public int getNombrePaiementMax() {
		return nombrePaiementMax;
	}

	public void setNombrePaiementMax(int nombrePaiementMax) {
		this.nombrePaiementMax = nombrePaiementMax;
	}

	public StrategiePaiement getStrategie() {
		return strategie;
	}

	public void setStrategie(StrategiePaiement strategie) {
		this.strategie = strategie;
	}
}