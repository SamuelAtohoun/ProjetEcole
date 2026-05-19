package state;

import projetEcole.Creneau;

public class EtatCreneauComplet extends EtatCreneau {

	public EtatCreneauComplet(Creneau creneau) {
		super(creneau);
		// TODO Auto-generated constructor stub
	}

	@Override
	public
	void libererCreneau() {
		// TODO Auto-generated method stub
		creneau.setEtatCreneau(new EtatCreneauDisponible(creneau));
	}

	@Override
	public
	void saturerCreneau() {
		// TODO Auto-generated method stub
		System.out.println("Créneau déjà saturé.");
	}

}
