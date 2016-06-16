import java.io.ByteArrayInputStream;
import java.util.Scanner;

@SuppressWarnings("unused")
public class Parsing {

	void usingSplit()
	{
		String input = "1, APPLE,4, 4.5";
		String[] data = input.split(",[ ]*");
		int size = Integer.parseInt(data[0]);
		String type = data[1];
		int age = Integer.parseInt(data[2]);
		double weight = Double.parseDouble(data[3]);
	}
	

	void usingScanner() {
		String input = "1, APPLE,4, 4.5";
		Scanner scan = new Scanner(new ByteArrayInputStream(input.getBytes())); 
		scan.useDelimiter(",[ ]*");
		int size = scan.nextInt();
		String type = scan.next();
		int age = scan.nextInt();
		double weight = scan.nextDouble();
	}
}
