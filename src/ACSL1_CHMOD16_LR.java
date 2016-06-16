import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
/**
 * @author Leo Ricci
 * 
 * 
 *
 */

public class ACSL1_CHMOD16_LR {
	public static void main(String[] args) throws IOException {
		//declarations:
		String filein = "testdata/CHMOD_testdata";
		Scanner scan;
		FileOutputStream fop = null;
		
		try{
			scan = new Scanner(new BufferedReader(new FileReader(filein)));
			fop = new FileOutputStream(new File(filein+"-out"));
		} catch(FileNotFoundException e) {
			//switch to regular terminal input
			scan = new Scanner(System.in);
		}
		
		//main loop:
		while(scan.hasNext()) {
			String answer = solve(scan.nextLine());
			System.out.println(answer);
			if(fop!=null) fop.write((answer+"\n").getBytes());
		}
		
		fop.flush();
		fop.close();
		scan.close();
	}
	
	/**
	 * solve for a specific input
	 * @param s the input
	 * @return the output
	 */
	static String solve(String s) {
		String output = "";
		String[] data = s.split(",[ ]*");
		for (int i = 1; i < data.length; i++) {
			if (Integer.parseInt(data[i]) < 1) {
				output+= "000 ";
			} else if (Integer.parseInt(data[i]) < 2){
				output+="00" + Integer.toBinaryString(Integer.parseInt(data[i])) + " ";
			} else if (Integer.parseInt(data[i]) < 4) {
				output+="0" + Integer.toBinaryString(Integer.parseInt(data[i])) + " ";
			} else {
				output+=Integer.toBinaryString(Integer.parseInt(data[i])) + " ";
			}
		}
		output+="and ";
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 4; j++) {
				if(output.charAt(i*4 + j)==' ') {
					output+=" ";
				} else if (output.charAt(i*4 + j)=='0'){
					output+="-";
				} else {
					if (j==0) {
						output+="r";
					} else if (j==1) {
						output+="w";
					} else {
						if (Integer.parseInt(data[0]) == i+1) {
							output+="s";
						} else if (Integer.parseInt(data[0]) == 4 && i == 2){
							output+="t";
						} else {
							output+="x";
						}
					}
				}
			}
			output+=" ";
		}
		output=output.substring(0, output.length()-2);
		return output;
	}
}
