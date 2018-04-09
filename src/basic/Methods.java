package basic;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Instant;


public class Methods {

	 public static String formattime(){
			Instant instant = Instant.now (); // Current date-time in UTC.
			String output = instant.toString ();
			output = instant.toString ().replace ( "T" , " " ).replace( "Z" , "");
			return output;
		}
	 
	 public static void writeFile(String sen, String file, boolean appendFile){
			try {
				String path = "/home/ubuntu/" + file +".txt";
//				String path = "/Users/yidwa/Desktop/CoordinateRecords_" + file +".txt";
//				String path = "/home/ubuntu/TopologyResult.txt";
				File f = new File(path);
				FileWriter fw = new FileWriter(f,appendFile);
				String time = Methods.formattime();
				fw.write(time+"\n"+ sen+"\n");
			
				fw.flush();
					
				fw.close();
				}
				catch (IOException e1) {
						// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		}

}
