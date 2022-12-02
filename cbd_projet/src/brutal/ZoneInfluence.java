package brutal;

import java.util.*;
import java.util.Random;

public class ZoneInfluence {
    private String nom;
	private Joueur joueurPossesseur = null;
    private Joueur joueur1;
    private Joueur joueur2;
    private ArrayList<Etudiant> etuJ1;
    private ArrayList<Etudiant> etuJ2;


    // Constructor
    public ZoneInfluence(String name, Joueur p1, Joueur p2){
        nom = name;
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

    public String getNom(){
        return nom;
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

    public boolean equals(Object o){
        if (o instanceof ZoneInfluence){
            return (this == o);
        } else {
            return false;
        }
    }

    // Methods
    public void faireAgirEtudiants(){
        Collections.sort(etuJ1, new ComparerInitiative());
        Collections.sort(etuJ2, new ComparerInitiative());
        Iterator<Etudiant> it1 = etuJ1.iterator();
        Iterator<Etudiant> it2 = etuJ2.iterator();
        Etudiant studP1 = etuJ1.get(0);
        Etudiant studP2 = etuJ2.get(0);;
        while (it1.hasNext() && it2.hasNext() && this.getNbEtu(joueur1) > 0 && this.getNbEtu(joueur2) > 0){
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
	// public static void main(String[] args) {
	// }
}