package client_server;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.TimerTask;

import basic.Coordinate;
import basic.Host;
import basic.Methods;

public class CoordinateRecording extends TimerTask{

	Host local;
	double avail;
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
//			neicor.get(s).setZ(avail);
			result += s+" "+ neicor.get(s).getCoorX()+" "+neicor.get(s).getCoorY()+" "+neicor.get(s).getZ();
			result += "\n";
		}
		Methods.writeFile(result, "logs/coordinates", false, false);
	}
	
	
	
}
