package factory;

import projetEcole.Gestionnaire;
import projetEcole.Parent;
import projetEcole.Utilisateur;

public class UtilisateurFactory {

	private UtilisateurFactory() {
	}

	public static Utilisateur creer(String role) {
		switch (role.toUpperCase()) {
		case "PARENT":
			return new Parent();
		case "GESTIONNAIRE":
			return new Gestionnaire();
		default:
			throw new IllegalArgumentException(
					"Rôle non reconnu : \"" + role + "\". Valeurs valides : PARENT, GESTIONNAIRE");
		}
	}
}