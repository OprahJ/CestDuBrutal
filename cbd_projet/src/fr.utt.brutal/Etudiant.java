package fr.utt.brutal;
import java.util.*;

public class Etudiant implements Strategies {
// attributs 
	private ArrayList<Etudiant> liststud;
	private int creditECTS ;
	private int dexterite ; 
	private int force; 
	private int resistance ; 
	private int constitution ; 
	private String hierachie ; 
	private boolean reserviste; 
	private String programme;
	
	//Getter et Setter 
	
	public int getCreditECTS() {
		return creditECTS;
	}
	public void setCreditECTS(int creditECTS) {
		this.creditECTS = creditECTS;
	}
	public int getDexterite() {
		return dexterite;
	}
	public void setDexterite(int getDexterite) {
		this.dexterite = dexterite;
	}
	public int getForce() {
		return force;
	}
	public void setForce(int force) {
		this.force = force;
	}
	public int getResistance() {
		return resistance;
	}
	public void setResistance(int resistance) {
		this.resistance = resistance;
	}
	public int getConstitution() {
		return constitution;
	}
	public void setConstitution(int constitution) {
		this.constitution = constitution;
	}
	public String getHierachie() {
		return hierachie;
	}
	public void setHierachie(String hierachie) {
		this.hierachie = hierachie;
	}
	public boolean getReserviste() {
		return reserviste;
	}
	public void setReserviste(boolean reserviste) {
		this.reserviste = reserviste;
	}
	public String getProgramme() {
		return programme;
	}
	public void setProgramme(String programme) {
		this.programme = programme;
	} 
	
	// Constructeur 
	/**
	 * 
	 */
	public Etudiant() {
		this.creditECTS = creditECTS; 
		this.programme = programme;
		
	
	// les methodes 
	
	public static  int addPts(int ability) {
		ability++;
		return ability ; 
	}
	public static int removePts(int ability) {
		ability--;
		return ability ; 
	}
	

public Object size() {
	// TODO Auto-generated method stub
	return null;
}
	
	
}