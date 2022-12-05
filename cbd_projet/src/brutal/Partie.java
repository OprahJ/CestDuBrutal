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
			stud = new Etudiant(type, 4, 4, 4, 4, 4, new Aleatoire(), player);
			player.setNbPoints(player.getNbPoints() - 20);
		} else {
			stud = new Etudiant(type, 0, 0, 0, 0, 0, new Aleatoire(), player);
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
				if (in1.toLowerCase().charAt(0) == 's'){
					System.out.println("Choisir une stratégie parmi : aléatoire - offensive - défensive");
					try{
						in1 = input.readLine();
					}
					catch(IOException IOE){
						IOE.printStackTrace();
					}
					player.attribuerStrategie(stud, in1);
				} else {
					System.out.println("Combien de points souhaitez-vous ajouter ou soustraire ?");
					try{
						in2 = input.readLine();
					}
					catch(IOException IOE){
						IOE.printStackTrace();
					}
					if (isInteger(in2)){
						nbr = Integer.parseInt(in2);
						player.attribuerPoints(stud, in1, nbr);
					} else {
						System.out.println("Ce n'est pas un entier");
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
		if (isInteger(in)){
			int nbr = Integer.parseInt(in);
			if (nbr > 0 && nbr <= 20 && !player.getEtudiant(nbr - 1).getReserviste()){
				player.getEtudiant(nbr - 1).setReserviste(true);
				System.out.println("L'étudiant " + nbr + " est désormais un réserviste");
			} else if (nbr > 0 && nbr <= 20 && player.getEtudiant(nbr - 1).getReserviste()){
				System.out.println("L'étudiant " + nbr + " est déjà un réserviste");
			} else {
				System.out.println("Saisie incorrecte.");
			}
		} else {
			System.out.println("Ce n'est pas un entier");
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
		int nbr, stud_count;
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
						} else if (stud.getZone() != null) {
							System.out.println("L'étudiant " + nbr + " est déjà dans une zone");
						} else {
							System.out.println("L'étudiant " + nbr + " est réserviste");
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

    public ZoneInfluence faireCombattre() {
		Iterator<ZoneInfluence> it = zones.iterator();
		ZoneInfluence conqueredZone = null;
		Joueur conquerer;
		boolean stop = false;
		ZoneInfluence zone;
		while (it.hasNext() && !stop) {
			zone = it.next();
			if (zone.getJoueurPossesseur() == null) {
				System.out.println("\nCombat en cours sur la zone " + zone.getNom() + ", il y a actuellement " + zone.getCredits() + " crédits sur la zone");
				zone.faireAgirEtudiants();
				if (zone.getNbEtu(zone.getJoueur1()) == 0) {
					zone.setJoueurPossesseur(zone.getJoueur2());
					zone.getJoueurPossesseur().addZoneControlee(zone);
					conqueredZone = zone;
					conquerer = zone.getJoueurPossesseur();
					stop = true;
					System.out.println(conquerer.getNom() + " est désormais possesseur de la zone " + zone.getNom());
				} else if (zone.getNbEtu(zone.getJoueur2()) == 0){
					zone.setJoueurPossesseur(zone.getJoueur1());
					zone.getJoueurPossesseur().addZoneControlee(zone);
					conqueredZone = zone;
					conquerer = zone.getJoueurPossesseur();
					stop = true;
					System.out.println(conquerer.getNom() + " est désormais possesseur de la zone " + zone.getNom());
				}
				if (zone.getJoueurPossesseur() != null) {
					if (zone.getJoueurPossesseur().getNbZonesControlees() >= 3){
						this.setJoueurGagnant(zone.getJoueurPossesseur());
					}
				}
			}
		}
		return conqueredZone;
	}

	public void treveAffectation(Joueur player){
		BufferedReader input = new BufferedReader (new InputStreamReader (System.in));
		Etudiant stud;
		ArrayList<Etudiant> filteredStudents;
		ZoneInfluence zoneComing, zoneGoing;
		String str = "";
		int nbr, i;
		if (player.getNbZonesControlees() != 0){
			do{
				System.out.printf("\n");
				Iterator<ZoneInfluence> itZ = player.getZonesControlees().iterator();
				i = 0;
				while (itZ.hasNext()){
					i++;
					zoneComing = itZ.next();
					System.out.println(i + " : " + zoneComing.getNom());
				}
				System.out.println("De quelle zone souhaitez-vous réaffecter les étudiants ? (appuyez sur enter pour terminer)");
				try{
					str = input.readLine();
				}
				catch(IOException IOE){
					IOE.printStackTrace();
				}
				if (isInteger(str)){
					nbr = Integer.parseInt(str);
					if (nbr > 0 && nbr <= player.getNbZonesControlees()){
						zoneComing = player.getZonesControlees().get(nbr - 1);
						filteredStudents = zoneComing.getFilteredEtudiants(player);
						if (filteredStudents.size() > 1){
							Iterator<Etudiant> itS = filteredStudents.iterator();
							System.out.printf("\n");
							i = 0;
							while (itS.hasNext()){
								i++;
								stud = itS.next();
								System.out.println("Etudiant " + i + " | " + stud);
							}
							System.out.println("Choisir un étudiant à réaffecter (appuyez sur enter pour terminer)");
							do{
								try{
									str = input.readLine();
								}
								catch(IOException IOE){
									IOE.printStackTrace();
								}
								if (isInteger(str)){
									nbr = Integer.parseInt(str);
									if (nbr > 0 && nbr <= filteredStudents.size()){
										stud = filteredStudents.get(nbr - 1);
										do{
											System.out.printf("\n");
											System.out.println(stud);
											System.out.println("Veuillez entrer stratégie pour affecter une nouvelle stratégie à cet étudiant ou zone pour l'affecter à une nouvelle zone (appuyez sur enter pour terminer)");
											try{
												str = input.readLine();
											}
											catch(IOException IOE){
												IOE.printStackTrace();
											}
											if (!str.equals("")){
												if (str.toLowerCase().charAt(0) == 's'){
													System.out.println("Choisir une stratégie parmi : aléatoire - offensive - défensive");
													try{
														str = input.readLine();
													}
													catch(IOException IOE){
														IOE.printStackTrace();
													}
													player.attribuerStrategie(stud, str);
												} else if (str.toLowerCase().charAt(0) == 'z'){
													itZ = zones.iterator();
													System.out.printf("\n");
													i = 0;
													while (itZ.hasNext()){
														i++;
														zoneGoing = itZ.next();
														if (zoneGoing.getJoueurPossesseur() == null){
															System.out.println(i + " : " + zoneGoing.getNom());
														}
													}
													System.out.println("A quelle zone souhaitez-vous réaffecter l'étudiant ? (appuyez sur enter pour terminer)");
													try{
														str = input.readLine();
													}
													catch(IOException IOE){
														IOE.printStackTrace();
													}
													if (isInteger(str)){
														nbr = Integer.parseInt(str);
														if (nbr > 0 && nbr <= zones.size()){
															zoneGoing = zones.get(nbr - 1);
															if (zoneGoing.getJoueurPossesseur() == null){
																zoneComing.getEtudiants().remove(stud);
																player.affecterEtuZone(stud, zoneGoing);
																System.out.println("L'étudiant de la zone " + zoneComing.getNom() + " a été affecté à la zone " + zoneGoing.getNom());
															} else {
																System.out.println("Cette zone est déjà controlée par " + zoneGoing.getJoueurPossesseur().getNom());
															}
														} else {
															System.out.println("Saisie incorrecte.");
														}
													} else {
														System.out.println("Ce n'est pas un entier");
													}
												}
											}
										} while (!str.equals(""));
									} else {
										System.out.println("L'entier doit être compris entre 1 et " + filteredStudents.size());
									}
								} else {
									System.out.println("Ce n'est pas un entier");
								}
							} while (!str.equals(""));
							
						} else {
							System.out.println("Vous devez laisser au moins un étudiant dans cette zone");
						}
					} else {
						System.out.println("L'entier doit être compris entre 1 et " + player.getNbZonesControlees());
					}
				} else {
					System.out.println("Ce n'est pas un entier");
				}
			} while (!str.equals(""));
		} else {
			System.out.println("Vous ne controlez aucune zone");
		}
	}

	public void treveReservistes(Joueur player){
		BufferedReader input = new BufferedReader (new InputStreamReader (System.in));
		Etudiant stud;
		ZoneInfluence zone;
		String str = "";
		int nbr, i;
		do{
			System.out.printf("\n");
			player.printEtudiantsReservistes();
			System.out.println("Quel étudiant réserviste souhaitez-vous affecter à une zone ? (appuyez sur enter pour quitter)");
			try{
				str = input.readLine();
			}
			catch(IOException IOE){
				IOE.printStackTrace();
			}
			if (!str.equals("")){
				if (isInteger(str)){
					nbr = Integer.parseInt(str);
					if (nbr > 0 && nbr <= player.getNbEtudiant()){
						stud = player.getEtudiant(nbr - 1);
						if (stud.getReserviste()){
							Iterator<ZoneInfluence> it = zones.iterator();
							System.out.printf("\n");
							i = 0;
							while (it.hasNext()){
								i++;
								zone = it.next();
								if (zone.getJoueurPossesseur() == null){
									System.out.println(i + " : " + zone.getNom());
								}
							}
							System.out.println("Dans quel zone souhaitez-vous affecter ce réserviste ?");
							try{
								str = input.readLine();
							}
							catch(IOException IOE){
								IOE.printStackTrace();
							}
							if (isInteger(str)){
								nbr = Integer.parseInt(str);
								if (nbr > 0 && nbr <= zones.size()){
									zone = zones.get(nbr - 1);
									if (zone.getJoueurPossesseur() == null){
										stud.setReserviste(false);
										player.affecterEtuZone(stud, zone);
										System.out.println("Le réserviste a été affecté à la zone " + zone.getNom());
									} else {
										System.out.println("La zone " + zone.getNom() + " est déjà controlée par " + zone.getJoueurPossesseur().getNom());
									}
								} else {
									System.out.println("Saisie incorrecte.");
								}
							} else {
								System.out.println("Ce n'est pas un entier");
							}
						} else {
							System.out.println("L'étudiant " + nbr + " n'est pas un réserviste");
						}
					} else {
						System.out.println("Saisie incorrecte.");
					}
				} else {
					System.out.println("Ce n'est pas un entier");
				}
			}
		} while (!str.equals(""));
	}

	public void treveEcts(){
		System.out.printf("\n");
		Iterator<ZoneInfluence> it = zones.iterator();
		while (it.hasNext()){
			System.out.println(it.next());
		}
	}
	
	public void faireTreve(ZoneInfluence conqueredZone) {
		BufferedReader input = new BufferedReader (new InputStreamReader (System.in));
		Iterator<Joueur> itP = joueurs.iterator();
		Joueur player;
		String str = "";
		int choice;
		while (itP.hasNext()) {
			player = itP.next();
			do{
				System.out.printf("\n");
				System.out.println(player.getNom() + " que souhaitez-vous faire :");
				System.out.println("1 : affecter des étudiants d'une zone conquise à une autre zone");
				System.out.println("2 : affecter des réservistes sur une zone");
				System.out.println("3 : consulter le nombre de crédits ECTS d'une zone");
				System.out.println("enter : terminer la phase de trève");
				try{
					str = input.readLine();
				}
				catch(IOException IOE){
					IOE.printStackTrace();
				}
				if (!str.equals("")){
					if (isInteger(str)){
						choice = Integer.parseInt(str);
						switch(choice){
							case 1 :
								this.treveAffectation(player);
								break;
							
							case 2 :
								this.treveReservistes(player);
								break;
							
							case 3 :
								this.treveEcts();
								break;

							default :
								System.out.println("Veuillez tapper 1, 2, 3 ou enter");
								break;
						}
					} else {
						System.out.println("Veuillez tapper 1, 2, 3 ou enter");
					}
				}
			} while (!str.equals(""));

		}
	}

	public void melangerZones(){
		Collections.shuffle(zones);
	}

	public void setJoueurGagnant(Joueur player){
		joueurGagnant = player;
		System.out.println(joueurGagnant.getNom() + " a gagné la partie");
	}

	public static boolean isInteger(String str){
		if (str == null){
			return false;
		}
		int length = str.length();
		if (length == 0){
			return false;
		}
		int i = 0;
		boolean stop = false;
		if (str.charAt(0) == '-'){
			i = 1;
		}
		while (i < length && !stop){
			if (str.charAt(i) >= '0' && str.charAt(i) <= '9'){
				i++;
			} else {
				stop = true;
			}
		}
		if (i != length){
			return false;
		}
		return true;
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
		partie.melangerZones();
		ZoneInfluence zoneConquise;
		while (partie.getJoueurGagnant() == null){
			zoneConquise = null;
			while (zoneConquise == null){
				zoneConquise = partie.faireCombattre();
			}
			if (partie.getJoueurGagnant() == null){
				partie.faireTreve(zoneConquise);
			}
		}
	}
}
