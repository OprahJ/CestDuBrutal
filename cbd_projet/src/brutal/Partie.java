package brutal;

import java.util.*;
import java.io.*;

public class Partie {
    private ArrayList<Joueur> joueurs;
	private ArrayList<ZoneInfluence> zones;
	private Joueur joueurGagnant = null;

	public Partie(){
		joueurs = new ArrayList<Joueur>();
		zones = new ArrayList<ZoneInfluence>();
	}

	public void addJoueur(Joueur p){
		joueurs.add(p);
	}

	public void addZone(ZoneInfluence zone){
		zones.add(zone);
	}

	public void parametrerEtudiant(Joueur player, String type){
		Etudiant stud;
		BufferedReader input = new BufferedReader (new InputStreamReader (System.in));
		String in1 = "";
		String in2 = "";
		int nbr;
		if (player.getNbPoints() >= 20){
			stud = new Etudiant(type, 4, 4, 4, 4, 4, new Aleatoire());
			player.setNbPoints(player.getNbPoints() - 20);
		} else {
			stud = new Etudiant(type, 0, 0, 0, 0, 0, new Aleatoire());
		}
		player.addCombattant(stud);
		do{
			System.out.println("\n" + player.getNom() + " veuillez paramétrer un " + type + " :");
			System.out.println(stud);
			System.out.println("Il vous reste : " + player.getNbPoints() + " points");
			System.out.println("Quel paramètre souhaitez-vous modifier ? (appuyez sur enter pour valider l'étudiant)");
			try{
				in1 = input.readLine();
			}
			catch(IOException IOE){
				IOE.printStackTrace();
			}
			if (in1.length() > 0){
				if (in1.toLowerCase().charAt(0) == 's'){
					System.out.println("Choisir une stratégie parmi : aléatoire - offensive - défensive");
					try{
						in1 = input.readLine();
					}
					catch(IOException IOE){
						IOE.printStackTrace();
					}
					switch (in1.toLowerCase().charAt(0)){
						case 'a' :
							stud.setStrategie(new Aleatoire());
							break;
						
						case 'o' :
							stud.setStrategie(new Offensive());
							break;
						
						case 'd' :
							stud.setStrategie(new Defensive());
							break;
						
						default :
							System.out.println("Saisie incorrecte.");
							break;
					}
				}
			} else if (!in1.equals("")){
				System.out.println("Combien de points souhaitez-vous ajouter ou soustraire ?");
				try{
					in2 = input.readLine();
				}
				catch(IOException IOE){
					IOE.printStackTrace();
				}
				nbr = Integer.parseInt(in2);
				player.attribuerPoints(stud, in1, nbr);
			}
		} while (!in1.equals(""));
	}

	public void choisirReserviste(Joueur player){
		BufferedReader input = new BufferedReader (new InputStreamReader (System.in));
		System.out.println(player.getNom() + " veuillez choisir un réserviste");
		String in = "";
		try{
			in = input.readLine();
		}
		catch(IOException IOE){
			IOE.printStackTrace();
		}
		int nbr = Integer.parseInt(in);
		if (nbr > 0 && nbr <= 20 && !player.getEtudiant(nbr - 1).getReserviste()){
			player.getEtudiant(nbr - 1).setReserviste(true);
			System.out.println("L'étudiant " + nbr + " est désormais un réserviste");
		} else if (nbr > 0 && nbr <= 20 && player.getEtudiant(nbr - 1).getReserviste()){
			System.out.println("L'étudiant " + nbr + " est déjà un réserviste");
		} else {
			System.out.println("Saisie incorrecte.");
		}
	}

	public void parametrerTroupes(){
		Iterator<Joueur> it = joueurs.iterator();
		while (it.hasNext()){
			Joueur player = it.next();
			this.parametrerEtudiant(player, "maitre");
			for (int i = 0; i < 4; i++){
				this.parametrerEtudiant(player, "elite");
			}
			for (int i = 0; i < 15; i++){
				this.parametrerEtudiant(player, "normal");
			}
			player.printEtudiants();
			while (player.getNbReservistes() < 5){
				this.choisirReserviste(player);
			}
		}
	}

	public void affecterTroupesZones(){
		Iterator<Joueur> itP = joueurs.iterator();
		Iterator<ZoneInfluence> itZ;
		Iterator<Etudiant> itS;
		Etudiant stud;
		BufferedReader input = new BufferedReader (new InputStreamReader (System.in));
		String in = "";
		int nbr;
		int i;
		while (itP.hasNext()){
			Joueur player = itP.next();
			itZ = zones.iterator();
			while (itZ.hasNext()){
				ZoneInfluence zone = itZ.next();
				do{
					itS = player.getEtudiants().iterator();
					i = 0;
					while (itS.hasNext()){
						i++;
						stud = itS.next();
						if (stud.getZone() == null && !stud.getReserviste()){
							System.out.println("Etudiant " + i + " | " + stud);
						}
					}
					System.out.println(player.getNom() + " quel étudiant mettre dans la zone "  + zone.getNom());
					try{
						in = input.readLine();
					}
					catch(IOException IOE){
						IOE.printStackTrace();
					}
					if (!in.equals("")){
						nbr = Integer.parseInt(in);
						if (nbr > 0 && nbr < 21 && player.getEtudiant(nbr - 1).getZone() == null){
							player.affecterEtuZone(player.getEtudiant(nbr - 1), zone);
							System.out.println("L'étudiant " + nbr + " a été affecté à la zone " + zone.getNom());
						} else if (nbr <= 0 || nbr >= 21){
							System.out.println("Saisie incorrecte.");
						} else {
							System.out.println("L'étudiant " + nbr + " est déjà dans une zone");
						}
					}
				}while(!in.equals(""));
			}
		}
		
	}

    public void faireCombattre() {
		Iterator<ZoneInfluence> it = zones.iterator();
		boolean zoneConquise = false;
		while (it.hasNext() && !zoneConquise) {
			ZoneInfluence zone = it.next();
			if (zone.getJoueurPossesseur() == null) {
				zone.faireAgirEtudiants();
				if (zone.getNbEtu(zone.getJoueur1()) == 0) {
					zone.setJoueurPossesseur(zone.getJoueur1());
					zone.getJoueurPossesseur().addZoneControlee(zone);
					zoneConquise = true;
				} else if (zone.getNbEtu(zone.getJoueur2()) == 0){
					zone.setJoueurPossesseur(zone.getJoueur2());
					zone.getJoueurPossesseur().addZoneControlee(zone);
					zoneConquise = true;
				}
				if (zone.getJoueurPossesseur().getNbZonesControlees() >= 3) {
					joueurGagnant = zone.getJoueurPossesseur();
				}
			}
		}
	}
	
	// public void faireTreve() {
	// 	Iterator<Joueur> itP = listjoueur.iterator();
	// 	while (itP.hasNext()) {
	// 		player = itP.next();
	// 		Iterator<Etudiant> itS = player.getJoueur().iterator();
	// 		while (itS.hasNext()) {
	// 			stud = itS.next();
	// 			if (stud.getReserviste() == true) {
	// 				System.out.println("Souhaitez-vous affecter ce reserviste ?");
	// 			}
	// 		}
	// 	}
	// }

	public void setJoueurGagnant(Joueur player){
		joueurGagnant = player;
		System.out.println(joueurGagnant.getNom() + " a gagné la partie");
	}



	public static void main(String[] args){
		Partie partie = new Partie();
		Joueur joueur1 = new Joueur("Lucas");
		Joueur joueur2 = new Joueur("Morane");
		partie.addJoueur(joueur1);
		partie.addJoueur(joueur2);
		partie.parametrerTroupes();
		partie.addZone(new ZoneInfluence("cour", joueur1, joueur2));
		partie.affecterTroupesZones();
		// Scanner scan = new Scanner(System.in);
		// System.out.println("Entrez votre nom");
		// String str = scan.nextLine();
		// System.out.println(str);
		// System.out.println("Entrez votre nom");
		// str = scan.nextLine();
		// System.out.println(str);
		// scan.close();


		// Joueur joueur2 = new Joueur("Morane");
		// partie.addJoueur(joueur2);
		// partie.addZone(new ZoneInfluence(joueur1, joueur2));
		// partie.addZone(new ZoneInfluence(joueur1, joueur2));
		// partie.addZone(new ZoneInfluence(joueur1, joueur2));
		// partie.addZone(new ZoneInfluence(joueur1, joueur2));
		// partie.addZone(new ZoneInfluence(joueur1, joueur2));	
		// partie.parametrerTroupes();
		// partie.affecterTroupesZones();
		// while (joueurGagnant == null){
		// 	partie.faireCombattre();
		// 	partie.faireTreve();
		// }
	}
}
