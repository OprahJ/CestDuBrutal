package brutal;

import java.util.*;
import java.util.Random;

public class Aleatoire implements Strategie {
	public Aleatoire(){}

	public void agir(Etudiant stud, List<Etudiant> allies, List<Etudiant> enemies) {
		Random r = new Random();
		float f = r.nextFloat();
		if (f < 0.5) {
			Etudiant ally = Collections.min(allies, new ComparerEcts());
	        int x = r.nextInt(100);
	        if (x <= 20 + 6*stud.getDexterite()){
	            float y = r.nextFloat((float) 0.6);
	            ally.earnEcts((int) (y*(10 + ally.getConstitution())));
	        }
		} else {
			Etudiant enemy = Collections.min(enemies, new ComparerEcts());
	        int x = r.nextInt(100);
	        if (x <= 20 + 3*stud.getDexterite()){
	            float y = r.nextFloat((float) 1.0);
	            enemy.loseEcts((int) (y*(1 + Math.max(0, Math.min(100, 10*stud.getForce() - 5*enemy.getResistance())))*10));
	            if (enemy.getCreditsEcts() < 0) {
	            	enemies.remove(enemy);
	            }
	        }
		}
	}

	public String print(){
		return "aléatoire";
	}
}