package brutal;

public class Etudiant {
// attributs 
	private boolean reserviste = false;
	private int dexterite;
	private int force;
	private int resistance;
	private int constitution;
	private int initiative;
	private int creditsEcts = 30;
	private String hierarchie = "normal";
	private Strategie strategie;
	private ZoneInfluence zone = null;
	private Joueur joueur = null;
	
	public Etudiant(String type, int dex, int str, int res, int con, int init, Strategie strat, Joueur player) {
		hierarchie = type;
		dexterite = dex;
		force = str;
		resistance = res;
		constitution = con;
		initiative = init;
		strategie = strat;
		joueur = player;
		if (type == "elite"){
			dexterite++;
			force++;
			resistance++;
			constitution += 5;
			initiative++;
		} else if (type == "maitre"){
			dexterite += 2;
			force += 2;
			resistance += 2;
			constitution += 10;
			initiative += 2;
		}
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

	public String getHierarchie(){
		return hierarchie;
	}

	public Strategie getStrategie() {
		return strategie;
	}

	public ZoneInfluence getZone(){
		return zone;
	}

	public Joueur getJoueur(){
		return joueur;
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

	public void setZone(ZoneInfluence z){
		zone = z;
	}
	
	public void loseEcts(int ects){
		creditsEcts = Math.max(0,creditsEcts - ects);
	}

	public void earnEcts(int ects){
		creditsEcts = Math.min(ects + creditsEcts, 30);
	}

	public String toString(){
		StringBuffer sb = new StringBuffer("hierarchie : " + hierarchie + " | dexterite : " + dexterite + " | force : " + force + " | resistance : " + resistance + " | constitution : " + constitution + " | initiative : " + initiative + " | strat??gie : " + strategie + " | cr??dits ECTS : " + creditsEcts);
		return sb.toString();
	}
}