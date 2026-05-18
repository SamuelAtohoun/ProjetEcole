package strategie;

import projetEcole.ModalitePaiement;

public interface StrategiePaiement {
    double calculerProchainVersement(ModalitePaiement modalite, double montantDejaPaye);
    int getNombreVersementsTotal(ModalitePaiement modalite);
}