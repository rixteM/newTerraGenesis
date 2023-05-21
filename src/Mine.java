
public class Mine {
	private int cout ;
	private int id ;
	private int profit ;

	public Mine(int id) {
		this.id=id;
		this.cout= ((id+5)^2)*50;
		this.profit=200+(int)Math.random()*200;
	}
	
	public int getId() {return id;}
	public int getCout() {return cout;}
	public int getProfit() {return profit;}

}
