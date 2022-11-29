package brutal;
import java.util.*;

public class Joueur {
	private int nbReservistes = 0;
    private int nbPoints = 400;
    private String nom;
    private List<Etudiant> combattants;
    private List<ZoneInfluence> zonesControlees;

    // Constructor
    public Joueur(String name){
        nom = name;
        combattants = new ArrayList<Etudiant>();
        zonesControlees = new ArrayList<ZoneInfluence>();
    }


    // Getters
    public int getNbReservistes(){
        return nbReservistes;
    }

    public int getNbPoints(){
        return nbPoints;
    }

    public int getNbZonesControlees(){
        return zonesControlees.size();
    }

    public String getNom(){
        return nom;
    }
    
    public Etudiant getEtudiant(int i) {
    	return combattants.get(i);
    }


    //Setters
    public void setNbReservistes(int nbr){
        nbReservistes = nbr;
    }

    public void setNbPoints(int nbr){
        nbPoints = nbr;
    }

    public void setNom(String name){
        nom = name;
    }

    public void addCombattant(Etudiant stud){
        combattants.add(stud);
    }

    public void removeCombattant(Etudiant stud){
        combattants.remove(stud);
    }

    public void addZoneControlee(ZoneInfluence zone){
        zonesControlees.add(zone);
    }


    // Methods
    public boolean equals(Object o){
        if (o instanceof Joueur){
            return (this == o);
        } else {
            return false;
        }
    }

    public void attribuerPoints(Etudiant stud, String ability, int nbr) {
    	char firstChar = ability.toLowerCase().charAt(0);
    	
        if (nbr <= nbPoints) {
            switch (firstChar) {
                case 'd':
                    if (nbr + stud.getDexterite() <= 10) {
                        nbPoints -= nbr;
                        stud.setDexterite(stud.getDexterite() + nbr);
                    }
                    break;

                case 'f':
                    if (nbr + stud.getForce() <= 10) {
                        nbPoints -= nbr;
                        stud.setForce(stud.getForce() + nbr);
                    }
                    break;

                case 'r':
                    if (nbr + stud.getResistance() <= 10) {
                        nbPoints -= nbr;
                        stud.setResistance(stud.getResistance() + nbr);
                    }
                    break;

                case 'c':
                    if (nbr + stud.getConstitution() <= 30) {
                        nbPoints -= nbr;
                        stud.setConstitution(stud.getConstitution() + nbr);
                    }
                    break;

                case 'i':
                    if (nbr + stud.getInitiative() <= 10) {
                        nbPoints -= nbr;
                        stud.setInitiative(stud.getInitiative() + nbr);
                    }
                    break;

                default:
                    System.out.println("Saisie incorrecte.\n");
                    break;

            }
        } else {
            System.out.println("Saisie incorrecte.\n");
        }
    }

    public void choisirReserviste(Etudiant stud){
        if (!stud.getReserviste() && nbReservistes < 5){
            nbReservistes++;
            stud.setReserviste(true);
        }
    }

    public void affecterStrategie(Etudiant stud, Strategie strat){
        stud.setStrategie(strat);
    }

    public void affecterEtuZone(Etudiant stud, ZoneInfluence zone){
        if (stud.getReserviste()){
            nbReservistes--;
            stud.setReserviste(false);
        }
        zone.addEtudiant(this, stud);
    }

	public static void main(String[] args) {
		Joueur j1 = new Joueur("Lucas");
		Etudiant e1 = new Etudiant(0, 0, 0, 0, 0);
		Etudiant e2 = new Etudiant(1, 1, 1, 1, 1);
		j1.addCombattant(e1);
		j1.addCombattant(e2);

		j1.attribuerPoints(e1, "dextérité", 10);
		System.out.println(e1.getDexterite());
		System.out.println(e2.getDexterite());
	}

}