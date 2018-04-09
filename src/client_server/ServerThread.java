package client_server;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.channels.AlreadyBoundException;

import basic.Constants;
import basic.Coordinate;
import basic.Host;
import basic.HostInfo;


public class ServerThread implements Runnable {
	ObjectInputStream ois;
	ObjectOutputStream oos;
	Object ni;
	Socket s;
	String ip;
	BufferedInputStream bi;
	BufferedOutputStream bo;
	InputStream is;
	OutputStream os;
	String hostname;
	Host local;

	// accept the remote request with remote host coordinate and name and the observed err and rtt
	public ServerThread(Socket s, Host local) throws IOException {

		this.s = s;

		this.local = local;
		
		ip = s.getInetAddress().toString().substring(1);
		
		hostname = s.getInetAddress().getHostName();

	}

	// read the received info and do the calculation, update the coordinates then send the updated info back to the remote host
	@Override
	public void run() {
		// TODO Auto-generated method stub

		try {

			ois = new ObjectInputStream(s.getInputStream());
			
			oos = new ObjectOutputStream(s.getOutputStream());

			ni = ois.readObject();
			
			double rtt = 0;
			double err = 0;
			

			if (ni != null) {

				HostInfo hi = (HostInfo) ni;
				if(local.getRtt().containsKey(hi.name)){
						rtt = local.getRtt().get(hi.name);
						System.out.println("the rtt for "+hi.name +" is "+rtt);
				}
				Coordinate remotecor = hi.coor;
				local.getNeicor().put(hi.name, remotecor);
				if(local.getErr().containsKey(hi.name))
					err = local.getErr().get(hi.name);
				Coordinate updatecor = local.vivaldi(hi.name, rtt, remotecor, err);
				
				//write the updated info back
				oos.writeObject(new HostInfo(local.getName(), updatecor, local.getErr().get(hi.name)));
				oos.flush();
			
			}
			else
				System.out.println("receiving null object");
			
			ois.close();
			oos.close();

		} catch (ClassNotFoundException e) {
			// // TODO Auto-generated catch block
			e.printStackTrace();
		} catch (EOFException e) {
			// // TODO Auto-generated catch block
			

		} catch (IOException e) {
			// TODO Auto-generated catch block

		} catch (AlreadyBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
