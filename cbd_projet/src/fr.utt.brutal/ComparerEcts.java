package fr.utt.brutal;

import java.util.*;

public class ComparerEcts implements Comparator<Etudiant>{
	public int compare(Etudiant stud1, Etudiant stud2) {
		return stud1.getCreditsEcts() - stud2.getCreditsEcts();
 	}
}
