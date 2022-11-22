package fr.utt.brutal;
import java.util.*;

import fr.utt.brutal.Joueur;

public class Partie {
// Attribut d'une partie 
private ArrayList <Joueur> listjoueurs ;
private ArrayList<Etudiant> liststud;
 


//constructeur de Partie 
public Partie() {
	
}


public void main (String[]args) {
	Scanner sc = new Scanner(System.in);
	//Initier les noms des joueurs 
	String j1 = sc.nextLine();
	Joueur J1 = new Joueur(j1);
	String j2 = sc.nextLine();
	Joueur J2 = new Joueur(j2);
	
	//Attribution pts de competences sous forme de boutons 
	List etu; 
	Etudiant etuactuel;
	etu =J1.getcombattants() ;
	Iterator <Integer> it = etu.iterator();
	Button bouton;
	bouton.OnClickListener();
	bouton.Next(etu);
	etuactuel= ((Etudiant) etu);
	System.out.println(etuactuel);
	
	// Bouton pour ajouter ou enlever la dexteritÃ© 
	Button boutonDext; 
	boutonDext.AddDex( etuactuel); 
	boutonDext.RemoveDex( etuactuel);
	
	// Bouton pour ajouter ou enlever la force 
	Button boutonForce;
	boutonForce.AddForce(etuactuel);
	boutonForce.RemoveForce(etuactuel);
	
	//Bouton pour ajouter ou enlever la resistance 
	Button boutonResist;
	boutonResist.AddResist(etuactuel);
	boutonResist.RemoveResist(etuactuel);
	
	// Bouton pour ajouter ou enlever la constitution 
	
	Button boutonConstist; 
	boutonConstist.AddConstit(etuactuel);
	boutonConstist.RemoveConstit(etuactuel);
	
	// Affecter les reservistes 
	
	J1.choisirReserviste(etuactuel );
	
	// Affecter les strategies 
	J1.affecterStrategie(etuactuel, null);
	 
	// Affecter les etudiants dans les zones 
		
	
	// Etape de la MÃªlÃ©e 
	while ( J1.nbZoneConquis <3 | J2.nbZoneConquis <3) {
		while ( J1.nbEtuZone >=1 |J2.nbEtuZone>=1) {
			
		// il faut faire un ArrayList des etudiants qui les classe en fonctions de leur initiative  
		// Le premier de la liste commet son action puis repasse Ã  la fin 
		
			
		}
		System.out.println("Une tréve est déclarée");
		
	
	
	
	
}
}
}