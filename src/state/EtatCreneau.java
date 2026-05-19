package state;

import projetEcole.Creneau;

public abstract class EtatCreneau {
	protected Creneau creneau;

	public EtatCreneau(Creneau creneau) {
		this.creneau = creneau;
	}

	public Creneau getCreneau() {
		return creneau;
	}

	public void setCreneau(Creneau creneau) {
		this.creneau = creneau;
	}

	public abstract void saturerCreneau();

	public abstract void libererCreneau();
}