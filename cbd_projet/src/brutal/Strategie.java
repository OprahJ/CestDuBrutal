package brutal;

import java.util.*;

public interface Strategie {
	public void agir(Etudiant stud, ArrayList<Etudiant> allies, ArrayList<Etudiant> enemies);
}
