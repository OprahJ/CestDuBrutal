package brutal;
import java.util.*;
import java.util.Random;

public class Offensive implements Strategie {
	public void agir(Etudiant stud, List<Etudiant> allies, List<Etudiant> enemies) {
		Etudiant enemy = Collections.min(enemies, new ComparerEcts());
		Random r = new Random();
        int x = r.nextInt(100);
        if (x <= 20 + 3*stud.getDexterite()){
            float y = r.nextFloat((float) 1.0);
            enemy.loseEcts((int) (y*(1 + Math.max(0, Math.min(100, 10*stud.getForce() - 5*enemy.getResistance())))*10));
            if (enemy.getCreditsEcts() <= 0) {
            	enemies.remove(enemy);
            }
        }
	}

    public String print(){
        return "offensive";
    }
}
