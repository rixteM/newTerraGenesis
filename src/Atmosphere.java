
public class Atmosphere {
	private double o2_level;
	private double co2_level;
	private double n2_level;
	private double ar_level;
	
	public Atmosphere() {
		this.o2_level=0.146;
		this.n2_level=1.89;
		this.ar_level=1.93;
		this.co2_level=100-(o2_level+n2_level+ar_level);
	}
	
	public void setLevel(double[] value) {
		o2_level=value[0];
		co2_level=value[1];
		n2_level=value[2];
		ar_level=value[3];
	}
	
	public double[] getLevel() {
		double[] temp = {o2_level,co2_level,n2_level,ar_level};
		return temp ;
	}

}
