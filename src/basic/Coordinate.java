package basic;

import java.io.Serializable;

public class Coordinate  implements Serializable {
	double x;
	double y;
	double z;
	double[] coor;
	public Coordinate(double x, double y, double z) {
		// TODO Auto-generated constructor stub
		setCoor(x, y, z);
		this.coor = new double[]{x,y,z};
	}
	
	public void setCoor(double x, double y, double z){
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public double getCoorX(){
		return x;
	}

	public double getCoorY(){
		return y;
	}
	
	public double getZ() {
		return z;
	}

	public void setZ(double z) {
		this.z = z;
	}

	public double[] getCoor(){
		return coor;
	}
	

	public static boolean equalCoor(Coordinate a, Coordinate b){
		boolean e = false;
		if(a.getCoorX() == b.getCoorX() && a.getCoorY() == b.getCoorY())
			e = true;
		return e;
	}
	
	@Override
	public String toString() {
		return "[ "+x + "," + y+ ","+ z+" ]";
	}
	
	
	
}
