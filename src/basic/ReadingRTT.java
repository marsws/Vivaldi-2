package basic;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class ReadingRTT {

	public static void  updateRtt(HashMap<String, Double> rtt) throws IOException{
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader("/home/ubuntu/RTT"));
			
			String line = br.readLine();

			while (line != null) {
				if(line.contains(",")){
					String[] info = line.split(",");
					String name = info[0];
					double updatertt = Double.valueOf(info[1]);
					rtt.put(name, updatertt);
					System.out.println("update rtt for "+name + " with "+updatertt);
				}
				line = br.readLine();
			}
			br.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("no rtt file found");
		}
	}

}
