import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Button;
import java.text.DecimalFormat;

public class WB {
	Planete Mars= new Planete("Mars");
	private long updateTime =System.currentTimeMillis();
	private int TimeSpeed = 2 ;
	private int RechercheIdSelected=0,BatIdSelected=0,VilleIdSelected=0;
	protected Shell shell;
	private Label txtArgent;
	private Label txtPopulationTotale;
	private Label textAtmos;
	private Label txtO;
	private Label txtCo;
	private Label txtN;
	private Label txtAr;
	private Label lblNombreDeMine;
	private Label lblNombreDeSerre;
	private Label lblBonheurTotale;
	private String[] VilleName = {"Abdère","Amphipolis","Byzantion","Chios","Cnossos","Cyzique","Didymes","Éphèse","Géla","Halicarnasse","Hermione","Iolcos","Kimmerikon","Mycènes","Naxos","Onchestos","Olympos","Paestum ","Phaïstos","Pylos","Samothrace","Siphae","Tamassos","Thespies","Thèbes","Trézène","Xanthe"};
	DecimalFormat df = new DecimalFormat("0.00");

	
	
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			WB window = new WB();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
				gameUpdate();
				graphicUpdate();}
			
		}
	}
	
	/**
	 * Update the game .
	 */
	public void gameUpdate() {
		if(System.currentTimeMillis()-updateTime > 500*TimeSpeed) {
		Mars.updatePlanete();
		updateTime =System.currentTimeMillis();
		}
	}
	
	public void graphicUpdate() {
		txtArgent.setText("Argent : "+ Mars.getArgent());
		txtPopulationTotale.setText("Population Totale : "+Mars.getPopulationTotal());
		txtO.setText("O2 : "+df.format(Mars.getAtmos().getLevel()[0])+"%");
		txtCo.setText("CO2 : "+df.format(Mars.getAtmos().getLevel()[1])+"%");
		txtN.setText("N2 : "+df.format(Mars.getAtmos().getLevel()[2])+"%");
		txtAr.setText("Ar : "+df.format(Mars.getAtmos().getLevel()[3])+"%");
		lblNombreDeMine.setText("Nombre de mine : "+Mars.getListMine().size());
		lblNombreDeSerre.setText("Nombre de serre : " +Mars.getListStation().get(0).getListSerres().size());
		lblBonheurTotale.setText("Bonheur Totale : " + Mars.getBonheurTotal()+" %");


	}
	
	public String nextBat() {
        if(BatIdSelected+1==Mars.getUnlockedRechercheId().size()) {BatIdSelected=0;}
        else {BatIdSelected++;}
        return Mars.getListBatiments().get(BatIdSelected).getNom();
    }
	
	public String nextVille() {
		if(Mars.getListVille().isEmpty()) {return("Veuillez créer une ville");}
		else {
			if(VilleIdSelected==Mars.getListVille().size()-1) {VilleIdSelected=0;}
			else {VilleIdSelected++;}
			return Mars.getListVille().get(VilleIdSelected).getNom();
		}
	}
	
	public String nextRecherche() {
		if(RechercheIdSelected==Mars.getListRecherche().size()-1) {RechercheIdSelected=0;}
		else {RechercheIdSelected++;}
		return Mars.getListRecherche().get(RechercheIdSelected).getNom();
	}
	
	
	
	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(895, 585);
		shell.setText("SWT Application");
		
		txtArgent = new Label(shell, SWT.SHADOW_NONE);
		txtArgent.setText("Argent : "+ Mars.getArgent());
		txtArgent.setBounds(46, 38, 159, 21);
		
		txtPopulationTotale = new Label(shell, SWT.NONE);
		txtPopulationTotale.setText("Population Totale : "+Mars.getPopulationTotal());
		txtPopulationTotale.setBounds(267, 38, 217, 21);
		
		textAtmos = new Label(shell, SWT.NONE);
		textAtmos.setText("Composition Atmosphere :");
		textAtmos.setBounds(513, 26, 178, 21);
		
		txtO = new Label(shell, SWT.BORDER);
		txtO.setText("O2 : "+Mars.getAtmos().getLevel()[0]+"%");
		txtO.setBounds(545, 53, 102, 21);
		
		txtCo = new Label(shell, SWT.BORDER);
		txtCo.setText("CO2 : "+Mars.getAtmos().getLevel()[1]+"%");
		txtCo.setBounds(545, 80, 102, 21);
		
		txtN = new Label(shell, SWT.BORDER);
		txtN.setText("N2 : "+Mars.getAtmos().getLevel()[2]+"%");
		txtN.setBounds(545, 107, 102, 21);
		
		txtAr = new Label(shell, SWT.BORDER);
		txtAr.setText("Ar : "+Mars.getAtmos().getLevel()[3]+"%");
		txtAr.setBounds(545, 134, 102, 21);
		
		Label lblVilleSelectionn = new Label(shell, SWT.NONE);
		lblVilleSelectionn.setBounds(282, 321, 119, 21);
		lblVilleSelectionn.setText("Ville selectionn\u00E9 :");
		
		Label lblVillename = new Label(shell, SWT.NONE);
		lblVillename.setAlignment(SWT.CENTER);
		lblVillename.setBounds(260, 356, 141, 21);
		lblVillename.setText("Veuillez créer une ville");
		
		Button btnProchaineVille = new Button(shell, SWT.NONE);
		btnProchaineVille.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				lblVillename.setText(nextVille());
			}
		});
		btnProchaineVille.setBounds(267, 389, 110, 25);
		btnProchaineVille.setText("Prochaine Ville");
		
		Label lblBatimentsSelectionn = new Label(shell, SWT.NONE);
		lblBatimentsSelectionn.setBounds(53, 321, 162, 29);
		lblBatimentsSelectionn.setText("Batiments selectionn\u00E9 :");
		
		Label lblBatname = new Label(shell, SWT.NONE);
		lblBatname.setAlignment(SWT.CENTER);
		lblBatname.setBounds(54, 356, 122, 21);
		lblBatname.setText("Logement Basique");
		
		Button btnProchainBatiment = new Button(shell, SWT.NONE);
		btnProchainBatiment.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				lblBatname.setText(nextBat());
			}
		});
		btnProchainBatiment.setBounds(55, 389, 136, 25);
		btnProchainBatiment.setText("Prochain Batiment");
		
		Label lblRechercheSelectionn = new Label(shell, SWT.NONE);
		lblRechercheSelectionn.setBounds(459, 321, 162, 21);
		lblRechercheSelectionn.setText("Recherche selectionn\u00E9 :");
		
		Label lblRecherchename = new Label(shell, SWT.NONE);
		lblRecherchename.setAlignment(SWT.CENTER);
		lblRecherchename.setBounds(459, 356, 162, 27);
		lblRecherchename.setText("Logement Basique");
		
		Button btnProchaineRecherche = new Button(shell, SWT.NONE);
		btnProchaineRecherche.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				lblRecherchename.setText(nextRecherche());
			}
		});
		btnProchaineRecherche.setBounds(459, 389, 141, 25);
		btnProchaineRecherche.setText("Prochaine Recherche");
		
		Button btnAcheterMine = new Button(shell, SWT.NONE);
		btnAcheterMine.setBounds(55, 240, 95, 25);
		btnAcheterMine.setText("Acheter Mine");
		btnAcheterMine.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Mars.addMine();
			}
		});
		
		Button btnAcheterrecherche = new Button(shell, SWT.NONE);
		btnAcheterrecherche.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Mars.addRecherche(RechercheIdSelected);
			}
		});
		btnAcheterrecherche.setBounds(466, 450, 134, 25);
		btnAcheterrecherche.setText("Acheter recherche");
		
		Button btnAcheterVille = new Button(shell, SWT.NONE);
		btnAcheterVille.setBounds(282, 450, 95, 25);
		btnAcheterVille.setText("Acheter Ville");
		btnAcheterVille.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Mars.addVille(VilleName[(int)(Math.random()*VilleName.length)]);
				lblVillename.setText(Mars.getListVille().get(Mars.getListVille().size()-1).getNom());
			}
		});
		
		Button btnAcheterBatiments = new Button(shell, SWT.NONE);
		btnAcheterBatiments.setBounds(57, 450, 134, 25);
		btnAcheterBatiments.setText("Acheter Batiments");
		btnAcheterBatiments.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Mars.addBatiments(Mars.getListVille().get(VilleIdSelected), BatIdSelected);
			}
		});
		
		lblNombreDeMine = new Label(shell, SWT.NONE);
		lblNombreDeMine.setBounds(46, 208, 145, 21);
		lblNombreDeMine.setText("Nombre de mine : "+Mars.getListMine().size());
		
		Button btnAcheterSerre = new Button(shell, SWT.NONE);
		btnAcheterSerre.setBounds(282, 240, 95, 25);
		btnAcheterSerre.setText("Acheter Serre");
		btnAcheterSerre.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Mars.addSerreToStation(Mars.getListStation().get(0));

			}
		});
		
		lblNombreDeSerre = new Label(shell, SWT.NONE);
		lblNombreDeSerre.setBounds(267, 208, 146, 21);
		lblNombreDeSerre.setText("Nombre de serre : " +Mars.getListStation().get(0).getListSerres().size());
		
		lblBonheurTotale = new Label(shell, SWT.NONE);
		lblBonheurTotale.setBounds(267, 80, 178, 21);
		lblBonheurTotale.setText("Bonheur Totale : " + Mars.getBonheurTotal()+" %");

	}
}
