package brutal;
import java.util.*;
import java.util.Random;

public class Offensive implements Strategie {
	public void agir(Etudiant stud, ArrayList<Etudiant> allies, ArrayList<Etudiant> enemies) {
		Etudiant enemy = Collections.min(enemies, new ComparerEcts());
		Random r = new Random();
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

    public String toString(){
        StringBuffer sb = new StringBuffer("offensive");
        return sb.toString();
    }
}
