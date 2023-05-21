import java.util.ArrayList;

public class Ville {
	private String nom;
	private int population;
	private int logementsDispo;
	private int emploisDispo;
	private int bonheur;	
	private int croissancePop;
	private int coutEntretienVille;
	private int coutInit;

	private ArrayList<Batiments> listBat;


	public Ville(String nom){
		this.nom=nom;
		this.bonheur=50;
		this.population=10;
		this.logementsDispo=0;
		this.emploisDispo=0;
		this.croissancePop=(bonheur^2)/100;
		this.coutEntretienVille=0;
		this.coutInit=500000;
		this.listBat=new ArrayList<Batiments>();
	}
	public String getNom(){return nom;}
	public int getBonheur(){return bonheur;}
	public int getPopulation(){return population;}
	public int getLogementsDispo(){return logementsDispo;}
	public int getEmploisDispo(){return emploisDispo;}
	public int getCoutEntretienVille(){return coutEntretienVille;}
	public int getcoutInit(){return coutInit;}

	public void updateVille() { 
		int tempB=0,tempLD=0,tempED=0,tempCEV=0;
		for(Batiments i : listBat) {
			tempB+=i.getBonheurAjoute();
			tempLD+=i.getLogementAjoute();
			tempED+=i.getJobAjoute();
			tempCEV+=i.getCoutEntretien();
		}
		coutEntretienVille=tempCEV;
		if(bonheur+tempB>=100) {bonheur=100;}
		else if (bonheur+tempB<=0) {bonheur=0;}
		else {bonheur+=tempB;}
		croissancePop=(bonheur^2)/100;
		population+=croissancePop;
		
		//Verification emploi logement
		if ((tempLD-population)>=0||(tempED-population)>=0) {
			logementsDispo=tempLD-population;
			emploisDispo=tempED-population;
		}
		else {
			if((tempLD-population)<(tempED-population)) {population-=tempLD;}
			
			else {population-=tempED;}
		}
	}
	
	public void addBat(Batiments element) {listBat.add(element);updateVille();}

}
