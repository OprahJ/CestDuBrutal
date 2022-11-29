package brutal;

public class Etudiant {
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
	private Strategie strategie;
	
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

	public Strategie getStrategie() {
		return strategie;
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
	
	public void setStrategie(Strategie strat) {
		strategie = strat;
	}
	
	public void loseEcts(int ects){
		creditsEcts -= ects;
	}

	public void earnEcts(int ects){
		creditsEcts += ects;
	}
	

	//call me maybe 0781505966
	public static  int addPts(int ability) {
		ability++;
		return ability ; 
	}
	public static int removePts(int ability) {
		ability--;
		return ability ; 
	}
	
	
	
}