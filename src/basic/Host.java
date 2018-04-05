package basic;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;

public class Host {

	// coordinate
	Coordinate coor;
	// host name
	String name;
	// the available No. of CPU cores
	int availability;
	// the list of neighbors
	ArrayList<String> neighbors;
	// the list of pair names
	ArrayList<String> pairs;
	// the coordinate of each neighbor
	HashMap<String, double[]> neicor;
	//the measured RTT between each pair, the index is formatted as "s1-m2"
	HashMap<String, Double> rtt;
	//the predicated rtt based on the coordinates
	HashMap<String, Double> pre;
	// the latest error for each pair 
	HashMap<String, Double> err;
	// the remote error 
	HashMap<String, Double> remoteerr;
	//the moving average of related error
	HashMap<String, ArrayList<Double>> reerr;
	//the constant factor 0.01/0.25/0.5/1.0
	static double c = 0.5;
	
	// what has been found until now is that the higher the factor, the quick it converges
	
	
	
	// the error and related error weight
	double ce = 0.5;
	//the weight balance of local and remote error
	double w;
	// the dynamic change interval
	double ita;
	// the dynamic interval to control the oscillation and convergence rate
	double interval;
	

	public Host(String name, Coordinate c, int avail) {
		// TODO Auto-generated constructor stub
		this.name = name;
		this.coor = c;
		this.availability = avail;
		this.w = 0.0;
		rtt = new HashMap<>();
		pre = new HashMap<>();
		err = new HashMap<>();
		remoteerr = new HashMap<>();
		reerr = new HashMap<>();
		iniNeigh();
		iniPair();
	}
	// initialize the neighbor list and remove the name of the current host, and initialize the coordinate of neighbors 
	public void iniNeigh(){
		ArrayList<String> nei = new ArrayList<>();
		nei.add("s1");
		nei.add("s2");
		nei.add("s3");
		nei.add("m1");
		nei.add("m2");
		nei.add("m3");
		nei.add("l1");
		nei.add("l2");
		nei.add("l3");
		nei.remove(name);
		neighbors = nei;
		double[] cor = {0,0};
		for(String s: nei){
			neicor.put(s, cor);
		}
		
	}
//	// initialize the pair name
	public void iniPair(){
		ArrayList<String> pariname = new ArrayList<>();
		for(String s: neighbors){
			pariname.add(name+"-"+s);
		}
		iniMaps(pariname);
	}
//	
//	/**
//	 * based on the list of pair name, initialize all the maps for later use
//	 * @param pairs
//	 */
	public void iniMaps(ArrayList<String> pairs){	
		for(String s: pairs){
			rtt.put(s, 0.0);
			pre.put(s, 0.0);
			err.put(s, 0.0);
			remoteerr.put(s, 0.0);
			reerr.put(s, new ArrayList<>());
		}
	}
	
	
	public Coordinate vivaldi(String remotenam, double rtt, Coordinate remotecor, double remoteerr){
		Coordinate exit = coor;
		double x,y =0;
		double distance = disCor(exit, remotecor);
		double localerr = Math.abs(rtt-distance);
		err.put(remotenam, localerr); 
		w = localerr/(localerr+remoteerr);
		
		double relatederr = Math.abs(distance-rtt)/rtt;
		localerr = relatederr * ce * w + localerr * (1-ce * w);
		ita = c * w;
		double dir = Math.abs((remotecor.getCoorY()-exit.getCoorY())/(remotecor.getCoorX()-exit.getCoorX()));
		if(rtt == distance)
			return exit;
		else{
			if(exit.getCoorX()<remotecor.getCoorX())
				x = exit.getCoorX() - ita*(rtt-distance)*dir;
			else
				x =  exit.getCoorX() + ita*(rtt-distance)*dir;
			if(exit.getCoorY()<remotecor.getCoorY())
				y = exit.getCoorY() - ita*(rtt-distance)*dir;
			else
				y = exit.getCoorY() + ita*(rtt-distance)*dir;
			
			DecimalFormat formatter  = new DecimalFormat("#0.00");
			x = Double.valueOf(formatter.format(x));
			y = Double.valueOf(formatter.format(y));
			exit.setCoor(x, y);
		}
		
		return exit;
	}
	
	
	public double disCor(Coordinate i, Coordinate j){
		
		double result = Math.sqrt(Math.pow(Math.abs(i.getCoorX()-j.getCoorX()), 2)+Math.pow(Math.abs(i.getCoorY()-j.getCoorY()), 2));
		return result;
	}
	

	public Coordinate getCoor() {
		return coor;
	}

	public void setCoor(Coordinate coor) {
		this.coor = coor;
	}

	public int getAvailability() {
		return availability;
	}

	public void setAvailability(int availability) {
		this.availability = availability;
	}
	public HashMap<String, Double> getRtt() {
		return rtt;
	}
	public void setRtt(HashMap<String, Double> rtt) {
		this.rtt = rtt;
	}
	public HashMap<String, Double> getErr() {
		return err;
	}
	public void setErr(HashMap<String, Double> err) {
		this.err = err;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	
	

}
