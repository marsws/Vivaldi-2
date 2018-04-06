package basic;

import java.io.Serializable;

//maintain the basic info of a host and regarded as the transmission unit
public class HostInfo implements Serializable  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String name;
	public Coordinate coor;
	public Double err;
	
	public HostInfo(String name, Coordinate cor, Double err) {
		// TODO Auto-generated constructor stub
		this.name = name;
		this.coor = cor;
		this.err = err;
	}

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Coordinate getCoor() {
		return coor;
	}

	public void setCoor(Coordinate coor) {
		this.coor = coor;
	}

	public Double getErr() {
		return err;
	}

	public void setErr(Double err) {
		this.err = err;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return name+" [ "+coor.toString()+" ] "+err;
	}

}
