import java.util.ArrayList;

public class Station {
	private double levelModifierTotal ;
	private int coutEntretienTotal ;
	private ArrayList<Serre> list;
	private int cout ;

	
	public Station() {
		this.coutEntretienTotal=0;
		this.levelModifierTotal=0;
		this.cout=200+(int)Math.random()*200;
		this.list= new ArrayList<Serre>();
	}
	public int getCoutEntretienTotal() {return coutEntretienTotal;}
	public double getLevelModifierTotal() {return levelModifierTotal;}
	public ArrayList<Serre> getListSerres(){return list;}
	public int getCout() {return cout;}
	
	public void updateStations() {
		int tempCET=0;
		double tempLMT=0;
		for(Serre i : list) {
			tempCET+=i.getCoutEntretien();
			tempLMT+=i.getLevelModifier();
		}
		coutEntretienTotal=tempCET;
		levelModifierTotal=tempLMT;
		System.out.print("Totale level modifier : "+levelModifierTotal+"\n");
	}
	public void addSerre(Serre element) {list.add(element);updateStations();}

	
}
