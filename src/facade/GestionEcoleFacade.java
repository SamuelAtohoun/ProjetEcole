package facade;

import java.util.Date;
import java.util.List;

import Observer.PaymentObserver;
import factory.PaiementFactory;
import factory.UtilisateurFactory;
import projetEcole.Cours;
import projetEcole.Creneau;
import projetEcole.Enfant;
import projetEcole.Inscrire;
import projetEcole.ModalitePaiement;
import projetEcole.Paiement;
import projetEcole.Parent;
import projetEcole.Salle;
import projetEcole.Utilisateur;
import singleton.EcoleManager;
import state.EtatCreneauDisponible;
import strategie.Versement;

public class GestionEcoleFacade {

	// =====================
	// GESTION UTILISATEURS
	// =====================

	public Utilisateur creerUtilisateur(String role, String nom, String prenom, String email, String motDePasse) {
		Utilisateur u = UtilisateurFactory.creer(role);
		u.setNom(nom);
		u.setPrenom(prenom);
		u.setEmail(email);
		u.setMotDePass(motDePasse);
		return u;
	}

	public boolean authentifier(Utilisateur utilisateur, String email, String motDePasse) {
		return utilisateur.getEmail().equals(email) && utilisateur.getMotDePass().equals(motDePasse);
	}

	// =====================
	// GESTION ENFANTS
	// =====================

	public Enfant creerEnfant(String nom, String prenom, Parent parent) {
		Enfant enfant = new Enfant();
		enfant.setNom(nom);
		enfant.setPrenom(prenom);
		enfant.setParent(parent);
		return enfant;
	}

	// =====================
	// GESTION CRÉNEAUX
	// =====================

	public Creneau creerCreneau(Date date, String heureDebut, String heureFin) {
		Creneau creneau = new Creneau();
		creneau.setDate(date);
		creneau.setHeureDebut(heureDebut);
		creneau.setHeureFin(heureFin);
		creneau.setEtatCreneau(new EtatCreneauDisponible(creneau));
		return creneau;
	}

	public void saturer(Creneau creneau) {
		creneau.getEtatCreneau().saturerCreneau();
	}

	public void liberer(Creneau creneau) {
		creneau.getEtatCreneau().libererCreneau();
	}

	public boolean estDisponible(Creneau creneau) {
		return creneau.getEtatCreneau() instanceof EtatCreneauDisponible;
	}

	// =====================
	// GESTION INSCRIPTIONS
	// =====================

	public Inscrire inscrireEnfant(Enfant enfant, Cours cours, Creneau creneau, Salle salle) {
		if (!estDisponible(creneau)) {
			throw new IllegalStateException("Ce créneau est complet.");
		}

		Inscrire inscription = new Inscrire();
		inscription.setEnfant(enfant);
		inscription.setCours(cours);
		inscription.setCreneau(creneau);
		inscription.setDateInscription(new Date());

		// Notifie via le Singleton
		EcoleManager.getInstance().notifier(enfant.getParent(), "Inscription de " + enfant.getPrenom() + " confirmée.");

		return inscription;
	}

	// =====================
	// GESTION PAIEMENTS
	// =====================

	public Paiement creerPaiement(Parent parent, ModalitePaiement modalite, int nombreVersements) {
		Paiement paiement = new Paiement(modalite);
		paiement.setParent(parent);
		paiement.setStrategie(PaiementFactory.creerStrategie(nombreVersements));

		// Abonne le parent aux notifications de la modalité
		modalite.addObserver((PaymentObserver) parent);

		// Enregistre le parent dans le Singleton
		EcoleManager.getInstance().ajouterParent(parent);

		return paiement;
	}

	public void effectuerVersement(Paiement paiement, String moyenPaiement) {
		paiement.effectuerVersement(PaiementFactory.creerMoyen(moyenPaiement));
	}

	public double getMontantRestant(Paiement paiement) {
		return paiement.getMontantRestant();
	}

	public List<Versement> getHistoriqueVersements(Paiement paiement) {
		return paiement.getVersements();
	}
}