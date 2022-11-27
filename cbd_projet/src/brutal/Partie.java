package brutal;

import java.util.*;

public class Partie {
    private List<Joueur> joueurs;
	private List<ZoneInfluence> zones;
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

	public void parametrerTroupes(){

	}

	public void affecterTroupesZones(){

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

	public void faireTreve(){

	}

	public void main(String[] args){
		Partie partie = new Partie();
		Joueur joueur1 = new Joueur("Lucas");
		Joueur joueur2 = new Joueur("Morane");
		partie.addJoueur(joueur1);
		partie.addJoueur(joueur2);
		partie.addZone(new ZoneInfluence(joueur1, joueur2));
		partie.addZone(new ZoneInfluence(joueur1, joueur2));
		partie.addZone(new ZoneInfluence(joueur1, joueur2));
		partie.addZone(new ZoneInfluence(joueur1, joueur2));
		partie.addZone(new ZoneInfluence(joueur1, joueur2));	
		partie.parametrerTroupes();
		partie.affecterTroupesZones();
		while (joueurGagnant == null){
			partie.faireCombattre();
			partie.faireTreve();
		}
	}
}
