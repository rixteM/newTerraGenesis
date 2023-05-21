
public class Recherche {
	private String nom ;
	private int cout ;
	private int id ;
	private long T_recherche ;

	public Recherche(String nom,int id,int cout) {
		this.cout=cout;
		this.T_recherche=10;
		this.nom = nom;
		this.id = id;
	}
	
	public String getNom() {return nom;}
	public int getId() {return id;}
	public int getCout() {return cout;}
	public long getT_Recherche() {return T_recherche;}
}