package fr.utt.brutal;
import java.util.*;

public class Etudiant implements Strategies {
// attributs 
private boolean reserviste = false;
private int dexterite;
private int force;
private int resistance;
private int constitution;
private int initiative;
private int creditsEcts = 10;
private String hierarchie;
private String programme;

// constructeurs 
public Etudiant(int dex, int str, int res, int con, int init) {
	this.dexterite = dex;
	this.force = str;
	this.resistance = res;
	this.constitution = con;
	this.initiative = init;
}
	
	// Getters
	public boolean getReserviste() {
		return reserviste;
	}
	
	public int getDexterite() {
		return dexterite;
	}
	
	public int getForce() {
		return force;
	}
	
	public int getResistance() {
		return resistance;
	}
	
	public int getConstitution() {
		return constitution;
	}
	
	public int getInitiative() {
		return initiative;
	}
	
	public int getCreditsEcts() {
		return creditsEcts;
	}
	
	
	// Setters
	public void setReserviste(boolean b) {
		reserviste = b;
	}
	
	public void setDexterite(int dex) {
		dexterite = dex;
	}
	
	public void setForce(int str) {
		force = str;
	}
	
	public void setResistance(int res) {
		resistance = res;
	}
	
	public void setConstitution(int con) {
		constitution = con;
	}
	
	public void setInitiative(int init) {
		initiative = init;
	}
	
	public void setStrategie(Strategie s) {
		
	}
	
	

	// les methodes 
	public int comparerInitiative(Etudiant stud1, Etudiant stud2) {
		return compare(stud1.getInitiative(), stud2.getInitiative());
	}
	
	
	public static  int addPts(int ability) {
		ability++;
		return ability ; 
	}
	public static int removePts(int ability) {
		ability--;
		return ability ; 
	}
	
	
	
}