package basic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;

public class Testing {

	public Testing() {
		// TODO Auto-generated constructor stub
	}
	public static void main(String[] args) {
		FileReader fr ;
		String[] temp;
		double avail;
		try {
			fr = new FileReader(new File("/Users/yidwa/Desktop/CPU"));
			BufferedReader br = new BufferedReader(fr);
			String latest="";
			String line="";
			while((line = br.readLine()) != null) {
				latest = line;
			}
			temp = latest.split(" ");
			int size = temp.length;
			double r = 0;
			for(int i = 6; i<size; i=i+2){
				System.out.println("read "+temp[i]);
				r+=Double.valueOf(temp[i]);
			}
			
			avail = r*1.0/100;
			DecimalFormat formatter  = new DecimalFormat("#0.000");
			avail = Double.valueOf(formatter.format(avail));
			System.out.println(avail);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
