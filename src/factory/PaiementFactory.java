package factory;

import strategie.PaiementEchelonne;
import strategie.PaiementEnUneFois;
import strategie.StrategiePaiement;

public class PaiementFactory {

	private PaiementFactory() {
	}

	public static StrategiePaiement creerStrategie(int nombreVersements) {
		if (nombreVersements <= 0) {
			throw new IllegalArgumentException(
					"Le nombre de versements doit être supérieur à 0. Reçu : " + nombreVersements);
		}
		if (nombreVersements == 1) {
			return new PaiementEnUneFois();
		}
		return new PaiementEchelonne();
	}

	public static MoyenPaiement creerMoyen(String moyen) {
		switch (moyen.toUpperCase()) {
		case "CARTE":
			return new CarteBancaire();
		case "VIREMENT":
			return new Virement();
		case "ESPECES":
			return new Espece();
		default:
			throw new IllegalArgumentException(
					"Moyen de paiement non reconnu : \"" + moyen + "\". Valeurs valides : CARTE, VIREMENT, ESPECES");
		}
	}
}