package fr.utt.brutal;
import java.util.*;
public class Joueur{
    private int nbReservistes = 0;
    private int nbPoints = 400;
    private String nom;
    private List<Etudiant> combattants;
    private List<ZoneInfluence> zonesControlees;
    private int nbEtuZone ; 
    private int nbZoneConquis;

    // Constructor
    public Joueur(String name){
        nom = name;
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
    public List getcombattants() {
    	return combattants ;
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
        combattants.append(stud);
    }

    public void removeCombattant(Etudiant stud){
        combattants.remove(stud);
    }

    public void addZoneControlee(ZoneInfluence zone){
        zonesControlees.append(zone);
    }


    // Methods
    public boolean equals(Object o){
        if (o instanceof Joueur){
            return (this == o);
        } else {
            return false;
        }
    }

    public void attribuerPoints(Etudiant stud, String ability, int nbr){
    	
        if (nbr <= nbPoints){
            switch(ability){
                case "dexterite" :
                    if (nbr + stud.getDexterite() <= 10){
                        nbPoints -= nbr;
                        stud.setDexterite(stud.getDexterite() + nbr);
                    }
                    break;

                case "force" :
                    if (nbr + stud.getForce() <= 10){
                        nbPoints -= nbr;
                        stud.setForce(stud.getForce() + nbr);
                    }
                    break;
                
                case "resistance" :
                    if (nbr + stud.getResistance() <= 10){
                        nbPoints -= nbr;
                        stud.setResistance(stud.getResistance() + nbr);
                    }
                    break;

                case "constitution" :
                    if (nbr + stud.getConstitution() <= 30){
                        nbPoints -= nbr;
                        stud.setConstitution(stud.getConstitution() + nbr);
                    }
                    break;

                case "Initiative" :
                    if (nbr + stud.getInitiative() <= 10){
                        nbPoints -= nbr;
                        stud.setInitiative(stud.getInitiative() + nbr);
                    }
                    break;

                default :
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


	public Iterator<Joueur> iterator() {
		// TODO Auto-generated method stub
		return null;
	}
}