import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class ACSL2_STRING15_LR {

	public static void main(String[] args) throws IOException {
		//declarations:
		String filein = "testdata/STRING_testdata";
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
		String[] data = s.split(",[ ]*");
		String[] parts = data[0].split("[.]");
		int originallength = data[0].length();
		int trimdec = Integer.parseInt(data[2]);
		int trimints = Integer.parseInt(data[1])-trimdec-1;

		String output = "";
		String newdec = "";
		String newint = "";
		//if the whole number should be octothorpes
		if (trimints<parts[0].length()) {
			for (int i = 0; i < trimints; i++) {
				output+="#";
			}
			output+=".";
			for (int i = 0; i < trimdec; i++) {
				output+="#";
			}
		} else {
			//trim the decimal
			if (parts[1].length()==trimdec) {
				newdec=parts[1];
			} else {
				for (int i = 0; i < trimdec; i++) {
					newdec+=parts[1].charAt(i);
				}
				if ((int) parts[1].charAt(trimdec)-48 >= 5) {
					char[] newdecchars = newdec.toCharArray();
					newdecchars[trimdec-1]++;
					newdec=new String(newdecchars);
				}
			}
			//extend the integers if needed
			if (trimints > parts[0].length()) {
				for (int i = 0; i < trimints-parts[0].length(); i++) {
					newint+="#";
				}
				newint+=parts[0];
			} else {
				newint=parts[0];
			}
			output=newint+"."+newdec;
		}
		return output;
	}
}

