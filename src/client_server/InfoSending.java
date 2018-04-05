package client_server;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Map;

import basic.Coordinate;
import basic.HostInfo;
import basic.Constants;



public class InfoSending implements Runnable{

	HostInfo hi;
	String name;
	Coordinate cor;
	Double err;
	Map<String, String> neighbors;
	ObjectOutputStream oos;
	Socket sendInfo;

	public InfoSending(String name, Coordinate cor, Double err, Map<String, String> nei) {
		// TODO Auto-generated constructor stub
		this.name = name;
		this.cor = cor;
		this.err = err;
		this.neighbors = nei;
		hi = new HostInfo(name, cor, err);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			for(String s: neighbors.keySet()){
			
				sendInfo = new Socket(neighbors.get(s), Constants.serverport);

				oos = new ObjectOutputStream(sendInfo.getOutputStream());

				oos.writeObject(hi);
				oos.flush();

				System.out.println("sending out "+hi.toString()+ " to "+ s+ " _ "+neighbors.get(s));
			}
			oos.close();
		}
		catch (IOException e) {
		// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
