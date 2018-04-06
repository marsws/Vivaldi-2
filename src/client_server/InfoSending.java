package client_server;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.Socket;
import java.util.Map;
import java.util.TimerTask;

import basic.Coordinate;
import basic.Host;
import basic.HostInfo;
import basic.ReadingRTT;
import basic.Constants;



public class InfoSending extends TimerTask{

	HostInfo hi;
	String name;
	Coordinate cor;
	Map<String, Double> err;
	Map<String, String> neighbors;
	ObjectOutputStream oos;
	Socket sendInfo;
	Host h;

	public InfoSending(Host h) {
		// TODO Auto-generated constructor stub
		this.h = h;
		this.name = h.getName();
		this.cor = h.getCoor();
		this.err = h.getErr();
		this.neighbors = h.getNeimap();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("info sending start");

		if(neighbors.size()<=0)
			System.out.println("no neighbor found");
		else{
			try {
				ReadingRTT.updateRtt(h.getRtt());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				System.out.println("something wrong when updating the rtt, using the default values");
			}
			for(String s: neighbors.keySet()){
				try {
					sendInfo = new Socket(neighbors.get(s), Constants.serverport);

					oos = new ObjectOutputStream(sendInfo.getOutputStream());

					hi = new HostInfo(name, cor, err.get(name));
					oos.writeObject(hi);
					oos.flush();

					System.out.println("sending out "+hi.toString()+ " to "+ s+ " _ "+neighbors.get(s));

					oos.close();
				}
				catch (ConnectException e){
					System.out.println("it falied to connect with remote host");
				}
				catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}


	}
}
