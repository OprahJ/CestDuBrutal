package brutal;
import java.util.*;

public class Joueur {
	private int nbReservistes = 0;
    private int nbPoints = 400;
    private String nom;
    private ArrayList<Etudiant> combattants;
    private ArrayList<ZoneInfluence> zonesControlees;

    // Constructor
    public Joueur(String name){
        nom = name;
        combattants = new ArrayList<Etudiant>();
        zonesControlees = new ArrayList<ZoneInfluence>();
    }


    // Getters
    public int getNbReservistes(){
        Iterator<Etudiant> it = combattants.iterator();
        int i = 0;
        while (it.hasNext()){
            if (it.next().getReserviste()){
                i++;
            }
        }
        return i;
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

    public ArrayList<Etudiant> getEtudiants(){
        return combattants;
    }

    public int getNbEtudiant(){
        return combattants.size();
    }


    //Setters
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
                    if (nbr + stud.getDexterite() <= 10 && nbr + stud.getDexterite() >= 0) {
                        if ((stud.getHierarchie() == "normal") || (stud.getHierarchie() == "elite" && nbr + stud.getDexterite() >= 1) || (stud.getHierarchie() == "maitre" && nbr + stud.getDexterite() >= 2)){
                            nbPoints -= nbr;
                            stud.setDexterite(stud.getDexterite() + nbr);
                        } else {
                            System.out.println("Vous ne pouvez pas retirer autant de points, l'étudiant est " + stud.getHierarchie());
                        }
                    }
                    break;

                case 'f':
                    if (nbr + stud.getForce() <= 10 && nbr + stud.getForce() >= 0) {
                        if ((stud.getHierarchie() == "normal") || (stud.getHierarchie() == "elite" && nbr + stud.getForce() >= 1) || (stud.getHierarchie() == "maitre" && nbr + stud.getForce() >= 2)){
                            nbPoints -= nbr;
                            stud.setForce(stud.getForce() + nbr);
                        } else {
                            System.out.println("Vous ne pouvez pas retirer autant de points, l'étudiant est " + stud.getHierarchie());
                        }
                    }
                    break;

                case 'r':
                    if (nbr + stud.getResistance() <= 10 && nbr + stud.getResistance() >= 0) {
                        if ((stud.getHierarchie() == "normal") || (stud.getHierarchie() == "elite" && nbr + stud.getResistance() >= 1) || (stud.getHierarchie() == "maitre" && nbr + stud.getResistance() >= 2)){
                            nbPoints -= nbr;
                            stud.setResistance(stud.getResistance() + nbr);
                        } else {
                            System.out.println("Vous ne pouvez pas retirer autant de points, l'étudiant est " + stud.getHierarchie());
                        }
                    }
                    break;

                case 'c':
                    if (nbr + stud.getConstitution() <= 30 && nbr + stud.getConstitution() >= 0) {
                        if ((stud.getHierarchie() == "normal") || (stud.getHierarchie() == "elite" && nbr + stud.getConstitution() >= 5) || (stud.getHierarchie() == "maitre" && nbr + stud.getConstitution() >= 10)){
                            nbPoints -= nbr;
                            stud.setConstitution(stud.getConstitution() + nbr);
                        } else {
                            System.out.println("Vous ne pouvez pas retirer autant de points, l'étudiant est " + stud.getHierarchie());
                        }
                    }
                    break;

                case 'i':
                    if (nbr + stud.getInitiative() <= 10 && nbr + stud.getInitiative() >= 0) {
                        if ((stud.getHierarchie() == "normal") || (stud.getHierarchie() == "elite" && nbr + stud.getInitiative() >= 1) || (stud.getHierarchie() == "maitre" && nbr + stud.getInitiative() >= 2)){
                            nbPoints -= nbr;
                            stud.setInitiative(stud.getInitiative() + nbr);
                        } else {
                            System.out.println("Vous ne pouvez pas retirer autant de points, l'étudiant est " + stud.getHierarchie());
                        }
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
        stud.setZone(zone);
        zone.addEtudiant(this, stud);
    }

    public void printEtudiants(){
        System.out.println("\n" + this.getNom() + " voici vos combattants :");
        Iterator<Etudiant> it= combattants.iterator();
        int i = 0;
        while (it.hasNext()){
            i++;
            System.out.println("Etudiant " + i + " | " + it.next());
        }
        System.out.println("\n");
    }

    public void printEtudiantsNonReservistes(){
        Etudiant stud;
		int i = 0;
		System.out.print("\n");
		Iterator<Etudiant> itS = this.getEtudiants().iterator();
		while (itS.hasNext()){
			i++;
			stud = itS.next();
			if (stud.getZone() == null && !stud.getReserviste()){
				System.out.println("Etudiant " + i + " | " + stud);
			}
		}
    }

}