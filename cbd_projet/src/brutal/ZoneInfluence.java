package brutal;

import java.util.*;
import java.util.Random;

public class ZoneInfluence {
    private String nom;
	private Joueur joueurPossesseur = null;
    private Joueur joueur1;
    private Joueur joueur2;
    private ArrayList<Etudiant> etudiants;
    // private ArrayList<Etudiant> etuJ1;
    // private ArrayList<Etudiant> etuJ2;


    // Constructor
    public ZoneInfluence(String name, Joueur p1, Joueur p2){
        nom = name;
        joueur1 = p1;
        joueur2 = p2;
        etudiants = new ArrayList<Etudiant>();
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

    public String getNom(){
        return nom;
    }

    public ArrayList<Etudiant> getEtudiants(){
        return etudiants;
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
    	if (p.equals(joueur1) || p.equals(joueur2)) {
            etudiants.add(stud);
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

    public ArrayList<Etudiant> getFilteredEtudiants (ArrayList<Etudiant> students, Joueur player){
        ArrayList<Etudiant> results = new ArrayList<Etudiant>();
        for (Etudiant stud: students){
            if (stud.getCreditsEcts() > 0 && stud.getJoueur().equals(player)){
                results.add(stud);
            }
        }
        // Collections.sort(results, new ComparerEcts());
        return results;
    }

    // Methods
    public void faireAgirEtudiants(){
        Collections.sort(etudiants, new ComparerInitiative());
        Etudiant stud;

        while (this.getNbEtu(joueur1) > 0 && this.getNbEtu(joueur2) > 0){
            stud = etudiants.get(0);
            if (stud.getCreditsEcts() > 0){
                if (stud.getJoueur().equals(joueur1)){
                    stud.getStrategie().agir(stud, this.getFilteredEtudiants(etudiants, joueur1), this.getFilteredEtudiants(etudiants, joueur2));
                } else {
                    stud.getStrategie().agir(stud, this.getFilteredEtudiants(etudiants, joueur2), this.getFilteredEtudiants(etudiants, joueur1));
                }
            }
            etudiants.remove(stud);
            etudiants.add(stud);
        }
    }

    public int getNbEtu(Joueur p){
        int sum = 0;
        Iterator <Etudiant> it= etudiants.iterator();
        Etudiant stud;
        if (joueur1.equals (p) || joueur2.equals (p)){
            while (it.hasNext()){
                stud = it.next();
                if (stud.getJoueur().equals(p)){
                    sum++;
                }
            }
            return sum;
        } else {
            System.out.println("Le joueur sélectionné ne combat pas sur cette zone.\n");
            return 0;
        }
    }

    public int getCredits(){
        int sum = 0;
        Iterator <Etudiant> it= etudiants.iterator();
        while (it.hasNext()){
            sum += it.next().getCreditsEcts();
        }

        return sum;
    }
	// public static void main(String[] args) {
	// }
}