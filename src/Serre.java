
public class Serre {
	private double levelModifier ;
	private int coutInit ;
	private int coutEntretien ;
	
	public Serre() {
		this.coutInit=1000;
		this.coutEntretien=50;
		this.levelModifier=Math.random()/10;
	}
	public int getCoutInit() {return coutInit;}
	public int getCoutEntretien() {return coutEntretien;}
	public double getLevelModifier() {return levelModifier;}

	
}
