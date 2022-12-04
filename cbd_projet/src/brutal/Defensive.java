
package brutal;
import java.util.*;
import java.util.Random;

public class Defensive implements Strategie {
	public void agir(Etudiant stud, ArrayList<Etudiant> allies, ArrayList<Etudiant> enemies) {
		Etudiant ally = Collections.min(allies, new ComparerEcts());
		Random r = new Random();
        int x = r.nextInt(100);
        if (x <= 20 + 6*stud.getDexterite()){
            float y = r.nextFloat((float) 0.6);
            int heal = (int) (y*(10 + ally.getConstitution()));
	            ally.earnEcts(heal);
				System.out.println("un etu soigne à " + heal);
        }
	}

    public String toString(){
        StringBuffer sb = new StringBuffer("défensive");
        return sb.toString();
    }
}