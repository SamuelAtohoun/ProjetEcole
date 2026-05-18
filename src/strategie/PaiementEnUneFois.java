package strategie;

import projetEcole.ModalitePaiement;

public class PaiementEnUneFois implements StrategiePaiement {

	@Override
	public double calculerProchainVersement(ModalitePaiement modalite, double montantDejaPaye) {
		return modalite.getTarif();
	}

	@Override
	public int getNombreVersementsTotal(ModalitePaiement modalite) {
		return 1;
	}
}