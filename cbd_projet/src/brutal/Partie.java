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

	public Joueur getJoueurGagnant(){
		return joueurGagnant;
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
			if (!in1.equals("")){
				System.out.println("Combien de points souhaitez-vous ajouter ou soustraire ?");
				try{
					in2 = input.readLine();
				}
				catch(IOException IOE){
					IOE.printStackTrace();
				}
				nbr = Integer.parseInt(in2);
				player.attribuerPoints(stud, in1, nbr);
			} else if (in1.length() > 0){
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
			}
		} while (!in1.equals(""));
	}

	public void choisirReserviste(Joueur player){
		BufferedReader input = new BufferedReader (new InputStreamReader (System.in));
		player.printEtudiantsNonReservistes();
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
		Joueur player;
		ZoneInfluence zone;
		Etudiant stud;
		BufferedReader input = new BufferedReader (new InputStreamReader (System.in));
		String in = "";
		int nbr, i, stud_count;
		boolean min;
		Random r = new Random();
		while (itP.hasNext()){
			player = itP.next();
			stud_count = 0;
			itZ = zones.iterator();
			while (itZ.hasNext()){
				min = false;
				zone = itZ.next();
				do{
					player.printEtudiantsNonReservistes();
					System.out.println(player.getNom() + " quel étudiant mettre dans la zone "  + zone.getNom() + " (vous devez en mettre au moins 1)");
					try{
						in = input.readLine();
					}
					catch(IOException IOE){
						IOE.printStackTrace();
					}
					if (!in.equals("")){
						nbr = Integer.parseInt(in);
						stud = player.getEtudiant(nbr - 1);
						if (nbr > 0 && nbr < 21 && stud.getZone() == null && !stud.getReserviste()){
							player.affecterEtuZone(stud, zone);
							System.out.println("L'étudiant " + nbr + " a été affecté à la zone " + zone.getNom());
							stud_count++;
							min = true;
						} else if (nbr <= 0 || nbr >= 21){
							System.out.println("Saisie incorrecte.");
						} else {
							System.out.println("L'étudiant " + nbr + " est déjà dans une zone");
						}
					}
				}while(!in.equals("") || !min);
			}
			if (stud_count < (player.getNbEtudiant() - player.getNbReservistes())){
				itS = player.getEtudiants().iterator();
				nbr = 0;
				while (itS.hasNext()){
					stud = itS.next();
					nbr++;
					if (!stud.getReserviste() && stud.getZone() == null){
						zone = zones.get((int) (r.nextFloat((float) 0.999)*zones.size()));
						player.affecterEtuZone(player.getEtudiant(nbr - 1), zone);
						System.out.println("L'étudiant " + nbr + " a été affecté à la zone " + zone.getNom());
						stud_count++;
					}
				}
			}
		}
	}

    public void faireCombattre(ZoneInfluence conqueredZone, Joueur conquerer) {
		Iterator<ZoneInfluence> it = zones.iterator();
		boolean startBreak = false;
		ZoneInfluence zone;
		while (it.hasNext() && !startBreak) {
			zone = it.next();
			if (zone.getJoueurPossesseur() == null) {
				System.out.println("\nCombat en cours sur la zone " + zone.getNom() + ", il y a actuellement " + zone.getCredits() + " crédits sur la zone");
				zone.faireAgirEtudiants();
				if (zone.getNbEtu(zone.getJoueur1()) == 0) {
					zone.setJoueurPossesseur(zone.getJoueur2());
					zone.getJoueurPossesseur().addZoneControlee(zone);
					conqueredZone = zone;
					conquerer = zone.getJoueurPossesseur();
					startBreak = true;
					System.out.println(conquerer.getNom() + " est désormais possesseur de la zone " + zone.getNom());
				} else if (zone.getNbEtu(zone.getJoueur2()) == 0){
					zone.setJoueurPossesseur(zone.getJoueur1());
					zone.getJoueurPossesseur().addZoneControlee(zone);
					conqueredZone = zone;
					conquerer = zone.getJoueurPossesseur();
					startBreak = true;
					System.out.println(conquerer.getNom() + " est désormais possesseur de la zone " + zone.getNom());
				} else {
					System.out.println("Il reste " + zone.getCredits() + " crédits après le combat sur la zone " + zone.getNom());
				}
				if (zone.getJoueurPossesseur() != null) {
					if (zone.getJoueurPossesseur().getNbZonesControlees() >= 3){
						joueurGagnant = zone.getJoueurPossesseur();
					}
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
		partie.addZone(new ZoneInfluence("bibliothèque", joueur1, joueur2));
		partie.addZone(new ZoneInfluence("bde", joueur1, joueur2));
		partie.addZone(new ZoneInfluence("quartier administratif", joueur1, joueur2));
		partie.addZone(new ZoneInfluence("halles industrielles", joueur1, joueur2));
		partie.addZone(new ZoneInfluence("halles sportives", joueur1, joueur2));
		partie.affecterTroupesZones();
		// ZoneInfluence zoneConquise;
		// Joueur joueurConquerant;
		// while (partie.getJoueurGagnant() == null){
		// 	zoneConquise = null;
		// 	joueurConquerant = null;
		// 	while (zoneConquise == null){
		// 		partie.faireCombattre(zoneConquise, joueurConquerant);
		// 	}
		// }
	}
}
