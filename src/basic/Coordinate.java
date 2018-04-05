package basic;

public class Coordinate {
	double x;
	double y;
	double[] coor;
	public Coordinate(double x, double y) {
		// TODO Auto-generated constructor stub
		setCoor(x, y);
		this.coor = new double[]{x,y};
	}
	
	public void setCoor(double x, double y){
		this.x = x;
		this.y = y;
	}
	
	public double getCoorX(){
		return x;
	}

	public double getCoorY(){
		return y;
	}
	
	public double[] getCoor(){
		return coor;
	}

	@Override
	public String toString() {
		return x + "," + y;
	}
	
	
}
