package state;

import projetEcole.Creneau;

public class EtatCreneauDisponible extends EtatCreneau {
	public EtatCreneauDisponible(Creneau creneau) {
		super(creneau);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void libererCreneau() {
		// TODO Auto-generated method stub
		System.out.println("Ce créneau est déjà disponible. Vous pouvez toujours y positonner des inscriptions");
	}

	@Override
	public void saturerCreneau() {
		// TODO Auto-generated method stub
		creneau.setEtatCreneau(new EtatCreneauComplet(creneau));
	}

}
