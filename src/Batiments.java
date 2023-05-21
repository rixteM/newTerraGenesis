
public class Batiments {
	private String nom;
	private int id;
	private int coutInit;
	private int coutEntretien;
	private int jobAjoute;
	private int bonheurAjoute;
	private int logementAjoute;


	public Batiments(String nom,int id,int jobAjoute,int bonheurAjoute,int logementAjoute) {
		this.nom=nom;
		this.id=id;
		this.coutInit=200;
		this.coutEntretien=20;
		this.jobAjoute=jobAjoute;
		this.bonheurAjoute=bonheurAjoute;
		this.logementAjoute=logementAjoute;
	}
	public String getNom() {return nom;}
	public int getId() {return id;}
	public int getCoutInit() {return coutInit;}
	public int getCoutEntretien() {return coutEntretien;}
	public int getJobAjoute() {return jobAjoute;}
	public int getBonheurAjoute() {return bonheurAjoute;}
	public int getLogementAjoute() {return logementAjoute;}
}
