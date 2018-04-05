package client_server;

import java.io.IOException;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;

import basic.Constants;
import basic.Host;


public class Server implements Runnable{

		// TODO Auto-generated constructor stub
		public static ServerSocket ss;
		Socket s;
		Host local;

		public Server(Host local) {
			try {
				this.local = local;
				ss = new ServerSocket(Constants.serverport);
				System.out.println("server started");
			}
			 catch (BindException e) {

			} catch (IOException e) {
				// TODO Auto-generated catch block

			}
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			while (true) {

				try {
					s = ss.accept();
					System.out.println("server received request from "+s.getInetAddress());
					new Thread(new ServerThread(s, local)).start();
					

				} catch (BindException e) {

				} catch (IOException e) {
					// TODO Auto-generated catch block

				}
			}

		}
	}
