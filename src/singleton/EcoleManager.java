package singleton;

import java.util.ArrayList;
import java.util.List;

import projetEcole.Parent;

public class EcoleManager {

	private static EcoleManager instance;
	private List<Parent> abonnes = new ArrayList<>();

	private EcoleManager() {
	}

	public static EcoleManager getInstance() {
		if (instance == null) {
			instance = new EcoleManager();
		}
		return instance;
	}

	public void ajouterParent(Parent parent) {
		if (!abonnes.contains(parent)) {
			abonnes.add(parent);
		}
	}

	public void retirerParent(Parent parent) {
		abonnes.remove(parent);
	}

	// Notifie tous les parents abonnés
	public void notifierTous(String message) {
		for (Parent parent : abonnes) {
			notifier(parent, message);
		}
	}

	// Notifie un parent spécifique
	public void notifier(Parent parent, String message) {
		// Ici branchement futur avec le patron Observer
		System.out.println("Notification à " + parent.getEmail() + " : " + message);
	}
}