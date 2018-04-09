package client_server;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import basic.Coordinate;
import basic.Host;

public class Starting {
	public static Timer timer;
	public static TimerTask infosending;
	public static TimerTask coordinateupdate;
	public static Thread serverthread;
	public static Thread updatingthread;

	public static void main(String[] args) throws InterruptedException {
		Coordinate acor;
		String localname;
		// initialize the host itself and get ready for self updating
		acor = new Coordinate(0, 0);
		int avail = 3;
		
		if(args.length<1){
			localname = "s1";
		}
		else {
			localname = args[0];
			
		}
	
		Host local = new Host(localname, acor, avail);
		
	
		// starting the server thread to wait request for info 
		serverthread = new Thread(new Server(local));
		
		serverthread.start();
		
		
		TimeUnit.MILLISECONDS.sleep(1000);
		
		infosending = new InfoSending(local);
		coordinateupdate = new CoordinateRecording(local);
		
		Timer timer = new Timer();
		
		timer.schedule(infosending, 1000, 20000);
		timer.schedule(coordinateupdate, 2000, 30000);
		
		
	}

}
