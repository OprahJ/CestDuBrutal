package brutal;

import java.util.*;
import java.util.Random;

public class ZoneInfluence {
	private Joueur joueurPossesseur = null;
    private Joueur joueur1;
    private Joueur joueur2;
    private List<Etudiant> etuJ1;
    private List<Etudiant> etuJ2;


    // Constructor
    public ZoneInfluence(Joueur p1, Joueur p2){
        joueur1 = p1;
        joueur2 = p2;
        etuJ1 = new ArrayList<Etudiant>();
        etuJ2 = new ArrayList<Etudiant>();
    }


    // Getters
    public Joueur getJoueurPossesseur(){
        return joueurPossesseur;
    }

    public Joueur getJoueur1(){
        return joueur1;
    }

    public Joueur getJoueur2(){
        return joueur2;
    }

    public List<Etudiant> getEtuJ1(){
        return etuJ1;
    }

    public List<Etudiant> getNbEtuJ2(){
        return etuJ2;
    }


    // Setters
    public void setJoueurPossesseur(Joueur p){
        joueurPossesseur = p;
    }

    public void setJoueur1(Joueur p1){
        joueur1 = p1;
    }

    public void setJoueur2(Joueur p2){
        joueur2 = p2;
    }

    public void addEtudiant(Joueur p, Etudiant stud){
    	if (p.equals(joueur1)) {
    		etuJ1.add(stud);
    	} else if (p.equals(joueur2)) {
    		etuJ2.add(stud);
    	} else {
    		System.out.println("Le joueur sélectionné ne combat pas sur cette zone.\n");
    	}
    }


    // Methods
    public void faireAgirEtudiants(){
        Collections.sort(etuJ1, new ComparerInitiative());
        Collections.sort(etuJ2, new ComparerInitiative());
        Iterator<Etudiant> it1 = etuJ1.iterator();
        Iterator<Etudiant> it2 = etuJ2.iterator();
        Etudiant studP1 = it1.next();
        Etudiant studP2 = it2.next();
        
        while (it1.hasNext() && it2.hasNext() && getNbEtu(joueur1) > 0 && getNbEtu(joueur2) > 0){
            if (studP1.getInitiative() > studP2.getInitiative()){
                studP1.getStrategie().agir(studP1, etuJ1, etuJ2);
                studP1 = it1.next();
            } else if (studP1.getInitiative() < studP2.getInitiative()){
                studP2.getStrategie().agir(studP2, etuJ2, etuJ1);
                studP2 = it2.next();
            } else {
                Random r = new Random();
                float f = r.nextFloat();
                if (f < 0.5){
                    studP1.getStrategie().agir(studP1, etuJ1, etuJ2);
                    studP1 = it1.next();
                } else {
                    studP2.getStrategie().agir(studP2, etuJ2, etuJ1);
                    studP2 = it2.next();
                }
            }
        }
    }

    public int getNbEtu(Joueur p){
    	if (p.equals(joueur1)) {
    		return etuJ1.size();
    	} else if (p.equals(joueur2)) {
    		return etuJ2.size();
    	} else {
    		System.out.println("Le joueur sélectionné ne combat pas sur cette zone.\n");
            return 0;
    	}
    }

    public int getCredits(){
        int sum = 0;
        Iterator <Etudiant> it;

        it = etuJ1.iterator();
        while (it.hasNext()){
            sum += it.next().getCreditsEcts();
        }

        it = etuJ2.iterator();
        while (it.hasNext()){
            sum += it.next().getCreditsEcts();
        }

        return sum;
    }
	public static void main(String[] args) {
		Joueur j1 = new Joueur("Lucas");
		Joueur j2 = new Joueur("Morgane");
		j1.addCombattant(new Etudiant(2, 2, 2, 2, 2));
		j1.addCombattant(new Etudiant(3, 3, 3, 3, 3));
		j1.addCombattant(new Etudiant(2, 2, 2, 2, 0));
		ZoneInfluence z1 = new ZoneInfluence(j1, j2);
		j1.affecterEtuZone(j1.getEtudiant(0), z1);
		j1.affecterEtuZone(j1.getEtudiant(1), z1);
		j1.affecterEtuZone(j1.getEtudiant(2), z1);
		Collections.sort(z1.getEtuJ1(), new ComparerInitiative());
		System.out.println(Collections.min(z1.getEtuJ1(), new ComparerEcts()));
	}
}