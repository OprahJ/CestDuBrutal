package fr.utt.brutal;

import java.util.*;

public interface Strategie {
	public void agir(Etudiant stud, List<Etudiant> allies, List<Etudiant> enemies);
}