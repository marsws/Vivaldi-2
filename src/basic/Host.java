package basic;
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
	
	public Host(String name, double x, double y, int avail) {
		// TODO Auto-generated constructor stub
		this.name = name;
		this.coor = new Coordinate(x, y);
		this.availability = avail;
		this.w = 0.0;
		rtt = new HashMap<>();
		pre = new HashMap<>();
		err = new HashMap<>();
		remoteerr = new HashMap<>();
		reerr = new HashMap<>();
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
	// initialize the pair name
	public void iniPair(){
		ArrayList<String> pariname = new ArrayList<>();
		for(String s: neighbors){
			pariname.add(name+"-"+s);
		}
	}
	
	/**
	 * based on the list of pair name, initialize all the maps for later use
	 * @param pairs
	 */
	public void iniMaps(ArrayList<String> pairs){
		
		for(String s: pairs){
			rtt.put(s, 0.0);
			pre.put(s, 0.0);
			err.put(s, 0.0);
			remoteerr.put(s, 0.0);
			reerr.put(s, new ArrayList<>());
		}
	}
	
	
	public Coordinate vivaldi(String remotenam, double rtt, Coordinate other, double remoteerr){
		Coordinate exit = coor;
		double x,y =0;
		double distance = disCor(exit, other);
		double localerr = Math.abs(rtt-distance);
		err.put(remotenam, localerr); 
		w = localerr/(localerr+remoteerr);
		
		double relatederr = Math.abs(distance-rtt)/rtt;
		localerr = relatederr * ce * w + localerr * (1-ce * w);
		ita = c * w;
		double dir = Math.abs((other.getCoorY()-exit.getCoorY())/(other.getCoorX()-exit.getCoorX()));
		if(rtt == distance)
			return exit;
		else{
			if(exit.getCoorX()<other.getCoorX())
				x = exit.getCoorX() - ita*(rtt-distance)*dir;
			else
				x =  exit.getCoorX() + ita*(rtt-distance)*dir;
			if(exit.getCoorY()<other.getCoorY())
				y = exit.getCoorY() - ita*(rtt-distance)*dir;
			else
				y = exit.getCoorY() + ita*(rtt-distance)*dir;
			
			exit.setCoor(x, y);
		}
		
		return exit;
	}
	
	
	public double disCor(Coordinate i, Coordinate j){
		
		double result = Math.sqrt(Math.pow(Math.abs(i.getCoorX()-j.getCoorX()), 2)+Math.pow(Math.abs(i.getCoorY()-j.getCoorY()), 2));
		return result;
	}
	
	public static void main(String[] args) {
		int count = 0;
		Host a = new Host("s1", 0, 0, 0);
		Coordinate blocation = new Coordinate(3, 4);
		double latency = 10.0;
		double diff =100;
		double iniremoteerr = 5.0;
		while(diff!=0){
			count++;
			Coordinate result = a.vivaldi("s2", latency, blocation, iniremoteerr);
			System.out.println("the host a coordinate update to"+ result.getCoorX()+" , "+result.getCoorY());
			diff = latency - a.disCor(result, blocation);
			System.out.println("diff now is "+diff);
			if(diff!=0){
				Host b = new Host("s2", blocation.getCoorX(), blocation.getCoorY(), 0);
				Coordinate bupdate = b.vivaldi("s1", latency, result, a.err.get("s2"));
				iniremoteerr = b.err.get("s1");
				System.out.println("the host b coordinate update to "+bupdate.getCoorX()+" , "+bupdate.getCoorY());
				diff = latency - b.disCor(bupdate, result);				
			}
		}
		System.out.println("counting "+count);
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
	
	
	

}
