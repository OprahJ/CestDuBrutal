package interface_graphique;

import java.util.*;

import brutal.Etudiant;


public class Button {

public  Etudiant Next(List etu) {
	Etudiant etudiant  = new Etudiant() ; 
	Iterator <Etudiant> it = etu.iterator();
	etudiant = it.next();
	return etudiant;
}
public  Etudiant Previous(List etu) {
	Etudiant etudiant  = new Etudiant() ; 
	Iterator <Etudiant> it2 = etu.iterator();
	etudiant = it2.previous();
	return etudiant;
}
// CompÃ©tence DexteritÃ© 
public Etudiant AddDex(Etudiant etu ) {
	int j;
	j = etu.getDexterite();
	j = Etudiant.addPts(j);
	etu.setDexterite(j);
	return etu;
}
public Etudiant RemoveDex(Etudiant etu ) {
	int j;
	j = etu.getDexterite();
	j = Etudiant.removePts(j);
	etu.setDexterite(j);
	return etu;
}
// Competence Force 
public Etudiant AddForce(Etudiant etu ) {
	int j;
	j = etu.getForce();
	j = Etudiant.addPts(j);
	etu.setForce(j);
	return etu;
}
public Etudiant RemoveForce(Etudiant etu ) {
	int j;
	j = etu.getForce();
	j = Etudiant.removePts(j);
	etu.setForce(j);
	return etu;
}
// CompetÃ©nce Resistance 

public Etudiant AddResist(Etudiant etu ) {
	int j;
	j = etu.getResistance();
	j = Etudiant.addPts(j);
	etu.setResistance(j);
	return etu;
}
public Etudiant RemoveResist(Etudiant etu ) {
	int j;
	j = etu.getResistance();
	j = Etudiant.removePts(j);
	etu.setResistance(j);
	return etu;
}
// CompÃ©tence constitution

public Etudiant AddConstit(Etudiant etu) {
	int j;
	j = etu.getConstitution();
	j = Etudiant.addPts(j);
	etu.setConstitution(j);
	return etu;
}
public Etudiant RemoveConstit(Etudiant etu ) {
	int j;
	j = etu.getConstitution();
	j = Etudiant.removePts(j);
	etu.setConstitution(j);
	return etu;
}




}