package strategie;

import projetEcole.ModalitePaiement;

public class PaiementEchelonne implements StrategiePaiement {

	@Override
	public double calculerProchainVersement(ModalitePaiement modalite, double montantDejaPaye) {
		double versement = modalite.getTarif() / modalite.getNombrePaiementMax();
		double resteAPayer = modalite.getTarif() - montantDejaPaye;

		if (resteAPayer <= 0) {
			throw new IllegalStateException("Ce cours est déjà entièrement payé.");
		}

		// Dernier versement : on paie exactement ce qui reste (évite les erreurs
		// d'arrondi)
		return Math.min(versement, resteAPayer);
	}

	@Override
	public int getNombreVersementsTotal(ModalitePaiement modalite) {
		return modalite.getNombrePaiementMax();
	}
}