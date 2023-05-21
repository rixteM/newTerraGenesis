import java.util.ArrayList;
import java.util.Arrays;

public class Planete {
	private String nom;
	private int populationTotal;
	private int argent;
	private int bonheurTotal;
	private Atmosphere atmos;
	private long timeLastUpdate;
	
	private ArrayList<Ville> listVille;
	private ArrayList<Mine> listMine;
	private ArrayList<Station> listStation;
	private ArrayList<Batiments> listBat;
	
	private ArrayList<Recherche> listRecherche;
	private ArrayList<Integer> unlockedRechercheId;
	private ArrayList<Integer> payedRechercheId;
	private ArrayList<Long> timerRechercheId;
	
	public Planete(String nom) {
		this.nom = nom;
		this.bonheurTotal=50;
		this.argent=1000000;
		this.populationTotal=0;
		this.atmos=new Atmosphere();
		this.timeLastUpdate= System.currentTimeMillis();

		this.listVille= new ArrayList<Ville>();
		this.listMine= new ArrayList<Mine>();
		this.listStation= new ArrayList<Station>();
		this.listBat= initBatList();
		
		this.listRecherche= initRechercheList();
		this.unlockedRechercheId= new ArrayList<Integer>(Arrays.asList(0,1,2));
		this.payedRechercheId= new ArrayList<Integer>(Arrays.asList(0,1,2));
		this.timerRechercheId = new ArrayList<Long>(Arrays.asList((long)0,(long)0,(long)0,(long)-1,(long)-1,(long)-1,(long)-1));
		addStation();
	}
	
	
	public String getNom(){return nom;}
	public int getBonheurTotal(){return bonheurTotal;}
	public int getPopulationTotal(){return populationTotal;}
	public int getArgent(){return argent;}
	public void setArgent(int value){argent=value;}
	public Atmosphere getAtmos(){return atmos;}

	public ArrayList<Mine> getListMine(){return listMine;}
	public ArrayList<Ville> getListVille(){return listVille;}
	public ArrayList<Station> getListStation(){return listStation;}
	public ArrayList<Batiments> getListBatiments(){return listBat;}
	
	public ArrayList<Recherche> getListRecherche(){return listRecherche;}
	public ArrayList<Integer> getUnlockedRechercheId() {return unlockedRechercheId;}



    public void addVille(String nom) {
        Ville temp = new Ville(nom);
        if((getArgent()-temp.getcoutInit())>=0) { //Money Check
        setArgent(getArgent()-temp.getcoutInit());
        listVille.add(temp);
        addBatiments(listVille.get(0),0);
        addBatiments(listVille.get(0),1);
        addBatiments(listVille.get(0),2);
        System.out.print("yolo");}
        
    }
	public void addMine() {
		Mine temp = new Mine(getListMine().size());
		if((getArgent()-temp.getCout())>=0) { //Money Check
		setArgent(getArgent()-temp.getCout());
		listMine.add(temp);}
	}	
	public void addStation() {
		Station temp = new Station();
		if((getArgent()-temp.getCout())>=0) { //Money Check
		setArgent(getArgent()-temp.getCout());
		listStation.add(new Station());}
	}
	public void addBatiments(Ville V,int id) {
		if(unlockedRechercheId.contains(id)) {//recherche check
			if((getArgent()-listBat.get(id).getCoutInit())>=0) {
			setArgent(getArgent()-listBat.get(id).getCoutInit());//Money Check
			V.addBat(listBat.get(id));
		}}}
	
	public void addSerreToStation(Station S) {
		Serre tempS=new Serre();
		if((getArgent()-tempS.getCoutInit())>=0) {
			setArgent(getArgent()-tempS.getCoutInit());
			S.addSerre(tempS);}
	}
	public ArrayList<Batiments> initBatList() {
		ArrayList<Batiments> tempList=new ArrayList<Batiments>() ;
		tempList.add(new Batiments("Logement Basique",0,0,0,10));
		tempList.add(new Batiments("Usine",1,10,-1,0));
		tempList.add(new Batiments("Petit Park",2,0,2,0));
		
		tempList.add(new Batiments("Logement Moyen",3,0,0,50));
		tempList.add(new Batiments("Usine Superieur",4,100,-5,0));
		tempList.add(new Batiments("Park",5,0,5,0));
		
		tempList.add(new Batiments("Logement Luxueux",6,0,1,100));
		tempList.add(new Batiments("Grand Park",7,0,15,0));
		return tempList;
	}
	public ArrayList<Recherche> initRechercheList(){
		ArrayList<Recherche> tempList = new ArrayList<Recherche>();
		tempList.add(new Recherche("Logement Basique",0,0));
		tempList.add(new Recherche("Usine",1,0));
		tempList.add(new Recherche("Petit Park",2,0));
		
		tempList.add(new Recherche("Logement Moyen",3,10000));
		tempList.add(new Recherche("Usine Superieur",4,10000));
		tempList.add(new Recherche("Park",5,10000));
		
		tempList.add(new Recherche("Logement Luxueux",6,50000));
		tempList.add(new Recherche("Grand Park",7,50000));
		return tempList;

	}
	
	public int addRecherche(int id) {
		Recherche temp=null;
		
		if(payedRechercheId.contains(id))  {return 2;} // Check si deja achete
		if(id<listRecherche.size())  {temp = listRecherche.get(id);} // Check id validity
		else {return 1;}
		
		if(getArgent()-temp.getCout()>=0) { //Money Check
			setArgent(getArgent()-temp.getCout());
			payedRechercheId.add(id);
		}
		return 0;
	}
	public void updateRecherche() {
        System.out.print(timerRechercheId);
        int index=0;
        long temp =System.currentTimeMillis();
        for(int a : payedRechercheId) { // check if recherche is payed ou demarrer and start timer
            if(timerRechercheId.get(a)<0) {
                timerRechercheId.set(a, listRecherche.get(a).getT_Recherche()*1000);//start timer
                System.out.print("Timer started");

                }}
        for(long b : timerRechercheId) {
            if (b>0) {
                if(b-(temp-timeLastUpdate)<0) {timerRechercheId.set(index,(long)0);} 
                else {timerRechercheId.set(index,b-(temp-timeLastUpdate));}}            //update timer for each
            
            if(b==0 && !unlockedRechercheId.contains(index) ) {//add recherche to unlockedRechercheId when it's done ==( temps restant = 0)
                unlockedRechercheId.add(index);
                System.out.print("R : "+index);
            }
            index++;        
        }
        
        timeLastUpdate=System.currentTimeMillis();
    }
	
	public void updatePlanete() {
		System.out.print("Population Tot"+populationTotal+" ");
		//Sum of lists
		int tempA=0,tempPT=0,tempBT=0;
		double tempLVL=0;
		for(Mine M:listMine) {tempA+=M.getProfit();}
		for(Ville V:listVille) {
			V.updateVille();
			tempA-=V.getCoutEntretienVille();
			tempPT+=V.getPopulation();
			tempBT+=V.getBonheur();
		}
		for(Station S:listStation) {
			tempLVL+=S.getLevelModifierTotal();
			tempA-=S.getCoutEntretienTotal();
		}
		//Variable check and attribution
		
		//		Argent Update		//

		if(argent+tempA>0) {argent+=tempA;}
		else {argent=0;} 
		
		//		Pop Update		//

		if(tempPT>0) {populationTotal=tempPT;}
		else {populationTotal=0;} 
		
		//		Bonheur Update		//
		if (listVille.size()!=0) {
		bonheurTotal=(int)(tempBT/listVille.size()); }
		else {bonheurTotal=50;}
		
		//		Atmos Update		//
		
		double[] tempLevelList={tempLVL+atmos.getLevel()[0],atmos.getLevel()[1]-tempLVL,atmos.getLevel()[2],atmos.getLevel()[3]};
		if(atmos.getLevel()[1]-tempLVL>5) { 
		atmos.setLevel(tempLevelList);}
		else {
			double[] tLvlList={95,5,atmos.getLevel()[2],atmos.getLevel()[3]};
			atmos.setLevel(tLvlList);}
		//System.out.print("Totale level modifier : "+tempLevelList[0]+"\n");

		
		//		Recherche Update		//
		updateRecherche();
	}
}
