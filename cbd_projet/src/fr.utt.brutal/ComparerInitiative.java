package fr.utt.brutal;
import java.util.*;

public class ComparerInitiative implements Comparator<Etudiant> {
	public int compare(Etudiant stud1, Etudiant stud2) {
		return stud2.getInitiative() - stud1.getInitiative();
 	}
}