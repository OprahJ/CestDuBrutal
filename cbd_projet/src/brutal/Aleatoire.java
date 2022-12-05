package brutal;

import java.util.*;
import java.util.Random;

public class Aleatoire implements Strategie {
	public Aleatoire(){}

	public void agir(Etudiant stud, ArrayList<Etudiant> allies, ArrayList<Etudiant> enemies) {
		Random r = new Random();
		float f = r.nextFloat();
		if (f < 0.5) {
			Etudiant ally = Collections.min(allies, new ComparerEcts());
	        int x = r.nextInt(100);
	        if (x <= 20 + 6*stud.getDexterite()){
	            float y = r.nextFloat((float) 0.6);
				int heal = (int) (y*(10 + ally.getConstitution()));
	            ally.earnEcts(heal);
				System.out.println("un etu soigne à " + heal);
	        }
		} else {
			Etudiant enemy = Collections.min(enemies, new ComparerEcts());
	        int x = r.nextInt(100);
	        if (x <= 20 + 3*stud.getDexterite()){
	            float y = r.nextFloat((float) 1.0);
	            int attack = (int) (y*(1 + Math.max(0, Math.min(100, 10*stud.getForce() - 5*enemy.getResistance()))/100)*10);
            	enemy.loseEcts(attack);
            	System.out.println("un etu attaque à " + attack);
	            if (enemy.getCreditsEcts() <= 0) {
	            	stud.getZone().getEtudiants().remove(enemy);
					System.out.println("Un étudiant " + enemy.getHierarchie() + " de " + enemy.getJoueur().getNom() + " est hors de combat");
	            }
	        }
		}
	}

	public String toString(){
        StringBuffer sb = new StringBuffer("aléatoire");
        return sb.toString();
    }
}