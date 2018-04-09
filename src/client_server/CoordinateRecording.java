package client_server;

import java.util.HashMap;
import java.util.TimerTask;

import basic.Coordinate;
import basic.Host;
import basic.Methods;

public class CoordinateRecording extends TimerTask{

	Host local;
	public CoordinateRecording(Host h) {
		// TODO Auto-generated constructor stub
		this.local = h;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		HashMap<String, Coordinate> neicor = local.getNeicor();
		String result = "";
		for(String s: neicor.keySet()){
			result += s+":"+ neicor.get(s).toString();
			result += "\n";
		}
		Methods.writeFile(result, "logs/coordinates", false);
	}

}
