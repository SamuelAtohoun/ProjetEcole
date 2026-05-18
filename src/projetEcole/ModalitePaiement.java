package projetEcole;

import java.util.Date;

public class ModalitePaiement {
	private Date dateEcheance;
	private double tarif;
	private int nombrePaiementMax;
	private double reduction;

	public ModalitePaiement() {
	}

	public Date getDateEcheance() {
		return dateEcheance;
	}

	public void setDateEcheance(Date dateEcheance) {
		this.dateEcheance = dateEcheance;
	}

	public double getTarif() {
		return tarif;
	}

	public void setTarif(double tarif) {
		this.tarif = tarif;
	}

	public int getNombrePaiementMax() {
		return nombrePaiementMax;
	}

	public void setNombrePaiementMax(int nombrePaiementMax) {
		this.nombrePaiementMax = nombrePaiementMax;
	}

	public double getReduction() {
		return reduction;
	}

	public void setReduction(double reduction) {
		this.reduction = reduction;
	}
}