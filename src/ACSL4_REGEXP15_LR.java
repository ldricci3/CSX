import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class ACSL4_REGEXP15_LR {
	boolean firstline = false;
	static String[] data = new String[10];
	
	
	public static void main(String[] args) throws IOException {
		//declarations:
		String filein = "testdata/REGEXP_testdata";
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
		if (s.length()>=20) {
			String stuff[]=s.split(",[ ]*");
			for (int i = 0; i < stuff.length; i++) {
				data[i]=stuff[i];
			}
		} else {
			for (int i = 0; i < data.length; i++) {
				if (Pattern.matches(s, data[i])) {
					output+=data[i] + ", ";
				}
			}
		}
		if (output=="") {
			output="NONE";
		}
		
		
		return output;
	}
}
