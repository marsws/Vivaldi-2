package basic;

public class Execution {

	public static void main(String[] args) throws InterruptedException {
		int count = 0;
		String aname = "s1";
		Coordinate acor = new Coordinate(0, 0);
		Host a = new Host(aname, acor, 1);
	
		String bname = "s2";
		Coordinate bcor = new Coordinate(3, 4);
		Host b = new Host(bname, bcor, 5);
		
		double latency = 10.0;
		
		double diff =10000;
		
		double iniremoteerr = 0;
		
		while(diff>0.01){
			count++;
			Coordinate aupdate = a.vivaldi(bname, latency, bcor, iniremoteerr);
			a.setCoor(aupdate);
			String info = a.name+","+ a.getCoor().toString()+","+ a.getAvailability()+ ","+ bname + ","+ bcor.toString()
			+","+ b.getAvailability();
			Methods.writeFile(info,a.name);
			diff = latency - a.disCor(acor, bcor);
			System.out.println("diff now is "+diff);
			Thread.sleep(1000);
			if(diff>0.01){
				bcor = b.vivaldi(a.name, latency, a.getCoor(), a.err.get(bname));
				iniremoteerr = b.err.get(aname);
				info = a.name +","+ a.getCoor().toString() +","+ a.getAvailability()+ ","+ b.name + "," + bcor.toString()
				+","+ b.getAvailability();;
				Methods.writeFile(info, bname);
				diff = latency - b.disCor(acor, bcor);		
				System.out.println("diff now is "+diff);
				Thread.sleep(1000);
			}
		}
		System.out.println("counting "+count);
	}

}
