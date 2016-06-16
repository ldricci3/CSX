import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class ACSL0_TITLE_INITIALS_allatonce {
	public static void main(String[] args) throws IOException {
		//declarations:
		String filein = "testdata/golf09";
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
			String[] data = new String[5];
			for (int i = 0; i < data.length; i++) {
				data[i] = scan.nextLine();
			}
			String answer = solve(data);
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
	static String solve(String[] s) {
		return s[0];
	}
}
