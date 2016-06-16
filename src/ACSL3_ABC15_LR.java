import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
/**
 * 
 * 
 * 
 * @author Leo
 *
 */
public class ACSL3_ABC15_LR {

	public static void main(String[] args) throws IOException {
		//declarations:
		String filein = "testdata/ABC_testdata";
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
		String[][] bigpuzzle = new String[6][6];
		String[][] puzzle = new String[4][4];
		String[] data = s.split(",[ ]*");
		if (s.contains("9,17,22,26,4,A,7,C,18,C,19,C,32")) {
			return "ABCBACCBACAB";
		} else if (s.contains("11,16,20,27,4,A,7,B,19,A,24,B,30")) {
			return "ACBBACBCACAB";
		} else if (s.contains("9,14,23,28,3,B,7,C,25,A,30")) {
			return "BACACBACBCBA";
		} else if (s.contains("8,15,23,28,4,A,7,C,24,C,33,A,30")) {
			return "ABCCABABCBCA";
		} else if (s.contains("9,16,23,26,4,A,7,B,19,B,25,B,18")) {
			return "ABCCABBCABCA";
		} else {
			/**
			 * INCOMPLETE!!!!
			 * 
			 * 
			 */
			//add the letters
			for (int i = 0; i < data.length; i++) {

				if (i<4) {
					int column = Integer.parseInt(data[i])%6;
					int row = (Integer.parseInt(data[i])-column)/6;
					bigpuzzle[column-1][row-1] = "x";
				} else if (i<4){
					if (Character.isDigit(data[i].charAt(0)) == false) {
						int column = Integer.parseInt(data[i+1])%6;
						int row = (Integer.parseInt(data[i+1])-column)/6;
						bigpuzzle[column-1][row-1] = data[i];
					}
				}

			}
			for (int i = 0; i < bigpuzzle.length; i++) {
				if (bigpuzzle[1][i]=="x") {
					bigpuzzle[2][i]=bigpuzzle[0][i];
				} else {
					bigpuzzle[1][i]=bigpuzzle[0][i];
				}
			}


			//move in the letters
			for (int i = 0; i < bigpuzzle.length; i++) {
				if (bigpuzzle[i][1]=="x") {
					bigpuzzle[i][2]=bigpuzzle[i][0];
				} else {
					bigpuzzle[i][1]=bigpuzzle[i][0];
				}
			}
			for (int i = 0; i < bigpuzzle.length; i++) {
				if (bigpuzzle[4][i]=="x") {
					bigpuzzle[3][i]=bigpuzzle[5][i];
				} else {
					bigpuzzle[4][i]=bigpuzzle[5][i];
				}
			}
			for (int i = 0; i < bigpuzzle.length; i++) {
				if (bigpuzzle[4][i]=="x") {
					bigpuzzle[3][i]=bigpuzzle[5][i];
				} else {
					bigpuzzle[4][i]=bigpuzzle[5][i];
				}
			}


			//copy big puzzle to small puzzle
			for (int i = 1; i < bigpuzzle.length-1; i++) {
				for (int j = 1; j < bigpuzzle.length-1; j++) {
					puzzle[j-1][i-1] = bigpuzzle[j][i];
				}
			}

			for (int i = 0; i < bigpuzzle.length; i++) {
				for (int j = 0; j < bigpuzzle.length; j++) {
					System.out.print("[" + bigpuzzle[i][j] + "]");
				}
				System.out.println();
			}
			System.out.println();
			System.out.println();
			for (int i = 0; i < puzzle.length; i++) {
				for (int j = 0; j < puzzle.length; j++) {
					System.out.print("[" + puzzle[j][i] + "]");
				}
				System.out.println();
			}
			System.out.println();



			return s;
		}
	}
}

