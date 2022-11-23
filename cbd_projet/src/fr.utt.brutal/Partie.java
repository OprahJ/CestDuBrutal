package fr.utt.brutal;

import java.util.*;

import javax.swing.JButton;


//---------------------------------------------------------------------------------------------------------------
//MODIF FAITES : Intégration des classes JButton (qui sont reconnu par Java). Dans ces boutons on créés les actions lors du clic. tout 
//se passe ici, cependant il faut créer une fenètre pour pouvoir afficher les boutons.
//N'oublie pas de créer les boutons supplémentaire pour remove. La classe Bouton n'est normalemnt plus nécessaire.

public class Partie {
	// Attribut d'une partie
	private ArrayList<Joueur> listjoueurs;
	private ArrayList<Etudiant> liststud;

	// constructeur de Partie
	public Partie() {
		// initialiser la partie 
	}

	public void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Quelles est le nom du joueur 1 ");
		String j1 = sc.nextLine();
		Joueur J1 = new Joueur(j1);
		System.out.println("Quelles est le nom du joueur 2 ");
		String j2 = sc.nextLine();
		Joueur J2 = new Joueur(j2);

		// Attribution pts de competences sous forme de boutons
		List etu;
		Etudiant etuactuel;
		etu = J1.getcombattants();
		Iterator<Integer> it = etu.iterator();
		JButton boutonnext = new JButton();
		// Création d'un bouton java
		bouton.addActionListenerListener(
			new ActionListener() { // Création de l'action lors du clic
			Etudiant etudiant = new Etudiant();
			Iterator<Etudiant> it = etu.iterator();
			etudiant = it.next();
			return etudiant;});

		etuactuel = ((Etudiant) etu);
		System.out.println(etuactuel);

		// Bouton pour ajouter ou enlever la dexterité
		JButton boutonDext = new JButton();
		boutonDext.addActionListenerListener(new ActionListener() { // Création de l'action lors du clic
		int j;
		j = etu.getDéxterité();
		j = Etudiant.addPts(j);
		etu.setDéxterité(j);});

		boutonDext.RemoveDex(etuactuel)// FAIRE PAREIL QU'AU DESSUS POUR LUI


		// Bouton pour ajouter ou enlever la force
		JButton boutonForce = new JButton();
		boutonForce.addActionListenerListener(new ActionListener() { // Création de l'action lors du clic
		int j;
		j = etu.getForce();
		j = Etudiant.addPts(j);
		etu.setForce(j);})
		
		boutonForce.RemoveForce(etuactuel);// IDEM

		// Bouton pour ajouter ou enlever la resistance
		JButton boutonRésist = new JButton();
		boutonForce.addActionListenerListener(new ActionListener() { // Création de l'action lors du clic
		int j;
		j = etu.getRésistance();
		j = Etudiant.addPts(j);
		etu.setRésistance(j);
		});
		boutonRésist.RemoveResist(etuactuel);//IDEM

		// Bouton pour ajouter ou enlever la constitution

		JButton boutonConstist = new JButton();
		boutonForce.addActionListenerListener(new ActionListener() { // Création de l'action lors du clic
		int j;
		j = etu.getConstitution();
		j = Etudiant.addPts(j);
		etu.setConstitution(j);})

		boutonConstist.RemoveConstit(etuactuel);//IDEM
	
		// Affecter les strategies
		System.out.println("Quelles est la strategie souhaitez vous ? ");
		String strat = sc.nextLine();
		J1.affecterStrategie(etuactuel, strat);

		// Affecter les etudiants dans les zones

		System.out.println("Dans quelles zones voulez vous mettre votre étudiant ? ");
		String zone = sc.nextLine();
		J1.affecterEtuZone(etuactuel, zone);

	}
// La melee 
while (J1.nbZoneConquis<3 |J2.nbZoneConquis<3){
	
}
