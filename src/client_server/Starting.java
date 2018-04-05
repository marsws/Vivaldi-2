package client_server;

import java.util.Timer;
import java.util.TimerTask;

import basic.Coordinate;
import basic.Host;

public class Starting {
	public static Timer timer;
	public static TimerTask infosending;
	public static TimerTask collect;
	public static Thread serverthread;
	public static Thread updatingthread;

	public static void main(String[] args) {
		
		// initialize the host itself and get ready for self updating
		Coordinate acor = new Coordinate(0, 0);
		String localname = "s1";
		Host local = new Host(localname, acor, 1);
		
		// starting the server thread to wait request for info 
		serverthread = new Thread(new Server(local));
		
	}

}
