package main;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import facade.GestionEcoleFacade;
import projetEcole.Cours;
import projetEcole.Creneau;
import projetEcole.Enfant;
import projetEcole.Gestionnaire;
import projetEcole.Inscrire;
import projetEcole.ModalitePaiement;
import projetEcole.Paiement;
import projetEcole.Parent;
import projetEcole.Salle;
import projetEcole.Utilisateur;

public class Main {

	static GestionEcoleFacade facade = new GestionEcoleFacade();
	static Scanner scanner = new Scanner(System.in);

	// Données en mémoire
	static List<Utilisateur> utilisateurs = new ArrayList<>();
	static List<Enfant> enfants = new ArrayList<>();
	static List<Cours> coursList = new ArrayList<>();
	static List<Creneau> creneaux = new ArrayList<>();
	static List<Inscrire> inscriptions = new ArrayList<>();
	static List<Paiement> paiements = new ArrayList<>();
	static Utilisateur utilisateurConnecte = null;

	public static void main(String[] args) {
		System.out.println("=== Bienvenue dans le système de gestion des cours de soutien ===");
		menuPrincipal();
	}

	// =====================
	// MENU PRINCIPAL
	// =====================

	static void menuPrincipal() {
		while (true) {
			System.out.println("\n--- MENU PRINCIPAL ---");
			System.out.println("1. Créer un compte");
			System.out.println("2. Se connecter");
			System.out.println("0. Quitter");
			System.out.print("Choix : ");
			String choix = scanner.nextLine();

			switch (choix) {
			case "1":
				creerCompte();
				break;
			case "2":
				seConnecter();
				break;
			case "0":
				System.out.println("Au revoir !");
				return;
			default:
				System.out.println("Choix invalide.");
			}
		}
	}

	// =====================
	// AUTHENTIFICATION
	// =====================

	static void creerCompte() {
		System.out.println("\n--- CRÉER UN COMPTE ---");
		System.out.print("Rôle (PARENT / GESTIONNAIRE) : ");
		String role = scanner.nextLine();
		System.out.print("Nom : ");
		String nom = scanner.nextLine();
		System.out.print("Prénom : ");
		String prenom = scanner.nextLine();
		System.out.print("Email : ");
		String email = scanner.nextLine();
		System.out.print("Mot de passe : ");
		String mdp = scanner.nextLine();

		try {
			Utilisateur u = facade.creerUtilisateur(role, nom, prenom, email, mdp);
			utilisateurs.add(u);
			System.out.println("Compte créé avec succès pour " + prenom + " " + nom + " (" + role + ")");
		} catch (IllegalArgumentException e) {
			System.out.println("Erreur : " + e.getMessage());
		}
	}

	static void seConnecter() {
		System.out.println("\n--- CONNEXION ---");
		System.out.print("Email : ");
		String email = scanner.nextLine();
		System.out.print("Mot de passe : ");
		String mdp = scanner.nextLine();

		for (Utilisateur u : utilisateurs) {
			if (facade.authentifier(u, email, mdp)) {
				utilisateurConnecte = u;
				System.out.println("Connecté en tant que " + u.getPrenom());
				if (u instanceof Gestionnaire) {
					menuGestionnaire();
				} else if (u instanceof Parent) {
					menuParent((Parent) u);
				}
				return;
			}
		}
		System.out.println("Email ou mot de passe incorrect.");
	}

	// =====================
	// MENU GESTIONNAIRE
	// =====================

	static void menuGestionnaire() {
		while (true) {
			System.out.println("\n--- MENU GESTIONNAIRE ---");
			System.out.println("1. Créer un cours");
			System.out.println("2. Créer un créneau");
			System.out.println("3. Voir les inscriptions");
			System.out.println("4. Voir les paiements");
			System.out.println("5. Saturer un créneau");
			System.out.println("6. Libérer un créneau");
			System.out.println("0. Se déconnecter");
			System.out.print("Choix : ");
			String choix = scanner.nextLine();

			switch (choix) {
			case "1":
				creerCours();
				break;
			case "2":
				creerCreneau();
				break;
			case "3":
				voirInscriptions();
				break;
			case "4":
				voirPaiements();
				break;
			case "5":
				saturerCreneau();
				break;
			case "6":
				libererCreneau();
				break;
			case "0":
				utilisateurConnecte = null;
				return;
			default:
				System.out.println("Choix invalide.");
			}
		}
	}

	static void creerCours() {
		System.out.println("\n--- CRÉER UN COURS ---");
		System.out.print("Libellé du cours : ");
		String libelle = scanner.nextLine();
		System.out.print("Tarif (€) : ");
		double tarif = Double.parseDouble(scanner.nextLine());
		System.out.print("Nombre de versements max (1-6) : ");
		int nbVersements = Integer.parseInt(scanner.nextLine());

		ModalitePaiement modalite = new ModalitePaiement();
		modalite.setTarif(tarif);
		modalite.setNombrePaiementMax(nbVersements);

		Cours cours = new Cours();
		cours.setLibelle(libelle);
		cours.setModalite(modalite);
		coursList.add(cours);

		System.out.println(
				"Cours \"" + libelle + "\" créé. Tarif : " + tarif + " € en " + nbVersements + " versement(s).");
	}

	static void creerCreneau() {
		System.out.println("\n--- CRÉER UN CRÉNEAU ---");
		System.out.print("Heure de début (ex: 09:00) : ");
		String debut = scanner.nextLine();
		System.out.print("Heure de fin (ex: 11:00) : ");
		String fin = scanner.nextLine();

		Creneau creneau = facade.creerCreneau(new Date(), debut, fin);
		creneaux.add(creneau);
		System.out.println("Créneau créé : " + debut + " - " + fin);
	}

	static void saturerCreneau() {
		if (creneaux.isEmpty()) {
			System.out.println("Aucun créneau disponible.");
			return;
		}
		afficherCreneaux();
		System.out.print("Numéro du créneau à saturer : ");
		int idx = Integer.parseInt(scanner.nextLine()) - 1;
		facade.saturer(creneaux.get(idx));
		System.out.println("Créneau saturé.");
	}

	static void libererCreneau() {
		if (creneaux.isEmpty()) {
			System.out.println("Aucun créneau disponible.");
			return;
		}
		afficherCreneaux();
		System.out.print("Numéro du créneau à libérer : ");
		int idx = Integer.parseInt(scanner.nextLine()) - 1;
		facade.liberer(creneaux.get(idx));
		System.out.println("Créneau libéré.");
	}

	static void voirInscriptions() {
		System.out.println("\n--- INSCRIPTIONS ---");
		if (inscriptions.isEmpty()) {
			System.out.println("Aucune inscription.");
			return;
		}
		for (Inscrire i : inscriptions) {
			System.out.println("- " + i.getEnfant().getPrenom() + " " + i.getEnfant().getNom() + " | Cours : "
					+ i.getCours().getLibelle() + " | Date : " + i.getDateInscription());
		}
	}

	static void voirPaiements() {
		System.out.println("\n--- PAIEMENTS ---");
		if (paiements.isEmpty()) {
			System.out.println("Aucun paiement.");
			return;
		}
		for (Paiement p : paiements) {
			System.out.println("- " + p.getParent().getPrenom() + " " + p.getParent().getNom() + " | Payé : "
					+ p.getMontantDejaPaye() + " €" + " | Restant : " + p.getMontantRestant() + " €");
		}
	}

	// =====================
	// MENU PARENT
	// =====================

	static void menuParent(Parent parent) {
		while (true) {
			System.out.println("\n--- MENU PARENT ---");
			System.out.println("1. Ajouter un enfant");
			System.out.println("2. Inscrire un enfant à un cours");
			System.out.println("3. Effectuer un versement");
			System.out.println("4. Voir mes paiements");
			System.out.println("0. Se déconnecter");
			System.out.print("Choix : ");
			String choix = scanner.nextLine();

			switch (choix) {
			case "1":
				ajouterEnfant(parent);
				break;
			case "2":
				inscrireEnfant(parent);
				break;
			case "3":
				effectuerVersement(parent);
				break;
			case "4":
				voirMesPaiements(parent);
				break;
			case "0":
				utilisateurConnecte = null;
				return;
			default:
				System.out.println("Choix invalide.");
			}
		}
	}

	static void ajouterEnfant(Parent parent) {
		System.out.println("\n--- AJOUTER UN ENFANT ---");
		System.out.print("Nom : ");
		String nom = scanner.nextLine();
		System.out.print("Prénom : ");
		String prenom = scanner.nextLine();

		Enfant enfant = facade.creerEnfant(nom, prenom, parent);
		enfants.add(enfant);
		System.out.println("Enfant " + prenom + " " + nom + " ajouté.");
	}

	static void inscrireEnfant(Parent parent) {
		System.out.println("\n--- INSCRIRE UN ENFANT ---");

		List<Enfant> mesEnfants = getEnfantsParent(parent);
		if (mesEnfants.isEmpty()) {
			System.out.println("Aucun enfant enregistré.");
			return;
		}
		if (coursList.isEmpty()) {
			System.out.println("Aucun cours disponible.");
			return;
		}
		if (creneaux.isEmpty()) {
			System.out.println("Aucun créneau disponible.");
			return;
		}

		// Choisir enfant
		System.out.println("Vos enfants :");
		for (int i = 0; i < mesEnfants.size(); i++)
			System.out.println((i + 1) + ". " + mesEnfants.get(i).getPrenom() + " " + mesEnfants.get(i).getNom());
		System.out.print("Choix : ");
		Enfant enfant = mesEnfants.get(Integer.parseInt(scanner.nextLine()) - 1);

		// Choisir cours
		System.out.println("Cours disponibles :");
		for (int i = 0; i < coursList.size(); i++)
			System.out.println((i + 1) + ". " + coursList.get(i).getLibelle() + " - "
					+ coursList.get(i).getModalite().getTarif() + " €");
		System.out.print("Choix : ");
		Cours cours = coursList.get(Integer.parseInt(scanner.nextLine()) - 1);

		// Choisir créneau
		afficherCreneaux();
		System.out.print("Choix : ");
		Creneau creneau = creneaux.get(Integer.parseInt(scanner.nextLine()) - 1);

		Salle salle = new Salle();
		salle.setCapacite(20);

		try {
			Inscrire inscription = facade.inscrireEnfant(enfant, cours, creneau, salle);
			inscriptions.add(inscription);

			// Créer le paiement
			int nbVersements = cours.getModalite().getNombrePaiementMax();
			Paiement paiement = facade.creerPaiement(parent, cours.getModalite(), nbVersements);
			paiements.add(paiement);

			System.out.println("Inscription confirmée. Paiement de " + cours.getModalite().getTarif() + " € en "
					+ nbVersements + " versement(s).");
		} catch (IllegalStateException e) {
			System.out.println("Erreur : " + e.getMessage());
		}
	}

	static void effectuerVersement(Parent parent) {
		System.out.println("\n--- EFFECTUER UN VERSEMENT ---");

		List<Paiement> mesPaiements = getPaiementsParent(parent);
		if (mesPaiements.isEmpty()) {
			System.out.println("Aucun paiement en cours.");
			return;
		}

		for (int i = 0; i < mesPaiements.size(); i++) {
			Paiement p = mesPaiements.get(i);
			System.out.println((i + 1) + ". Restant : " + p.getMontantRestant() + " €");
		}
		System.out.print("Choix : ");
		Paiement paiement = mesPaiements.get(Integer.parseInt(scanner.nextLine()) - 1);

		System.out.print("Moyen de paiement (CARTE / VIREMENT / ESPECES) : ");
		String moyen = scanner.nextLine();

		try {
			facade.effectuerVersement(paiement, moyen);
			System.out.println("Versement effectué. Restant : " + paiement.getMontantRestant() + " €");
		} catch (Exception e) {
			System.out.println("Erreur : " + e.getMessage());
		}
	}

	static void voirMesPaiements(Parent parent) {
		System.out.println("\n--- MES PAIEMENTS ---");
		List<Paiement> mesPaiements = getPaiementsParent(parent);
		if (mesPaiements.isEmpty()) {
			System.out.println("Aucun paiement.");
			return;
		}
		for (Paiement p : mesPaiements) {
			System.out.println("- Payé : " + p.getMontantDejaPaye() + " € | Restant : " + p.getMontantRestant() + " €");
			for (var v : p.getVersements()) {
				System.out.println("  • " + v.getMontant() + " € via " + v.getMoyenPaiement().getLibelle() + " le "
						+ v.getDatePaiement());
			}
		}
	}

	// =====================
	// UTILITAIRES
	// =====================

	static void afficherCreneaux() {
		System.out.println("Créneaux :");
		for (int i = 0; i < creneaux.size(); i++) {
			Creneau c = creneaux.get(i);
			String etat = facade.estDisponible(c) ? "Disponible" : "Complet";
			System.out.println((i + 1) + ". " + c.getHeureDebut() + " - " + c.getHeureFin() + " [" + etat + "]");
		}
	}

	static List<Enfant> getEnfantsParent(Parent parent) {
		List<Enfant> result = new ArrayList<>();
		for (Enfant e : enfants)
			if (e.getParent().equals(parent))
				result.add(e);
		return result;
	}

	static List<Paiement> getPaiementsParent(Parent parent) {
		List<Paiement> result = new ArrayList<>();
		for (Paiement p : paiements)
			if (p.getParent().equals(parent))
				result.add(p);
		return result;
	}
}