import java.util.Scanner;


public class Rust_Removal {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		double a;
		double b;
		double c;
		double x;
		System.out.println("Please input 'a'");
		a = scan.nextInt();
		System.out.println("Please input 'b'");
		b = scan.nextInt();
		System.out.println("Please input 'c'");
		c = scan.nextInt();
		System.out.println("Please input 'x'");
		x = scan.nextInt();

		//		System.out.println("Real Roots: " + Quadratic.realRoots(a, b, c));
		//		System.out.println("Number of Roots: " + Quadratic.numberofRoots(a, b, c));
		//		if (Quadratic.numberofRoots(a, b, c) == 1) {
		//			System.out.println("Zero: " + Quadratic.zeros(a, b, c)[0]);
		//		} else {
		//			System.out.println("Zeros: " + Quadratic.zeros(a, b, c)[0] + ", " + Quadratic.zeros(a, b, c)[1]);
		//		}
		//		
		//		System.out.println("Axis of Symmetry: " + Quadratic.axisOfSymmetry(a, b, c));
		//		System.out.println("Extreme Value: " + Quadratic.extremeValue(a, b, c));
		//		System.out.println("Min or Max: " + Quadratic.maxOrMin(a, b, c));
		//		System.out.println("Calculate y: " + Quadratic.fof(a, b, c, x));
		if (Quadratic.realRoots(a, b, c) == true) {
			System.out.println("The quadratic has real roots.");
		} else {
			System.out.println("The quadratic does not have real roots.");
		}
		if (Quadratic.numberofRoots(a, b, c) == 1) {
			System.out.println("x = " + Quadratic.zeros(a, b, c)[0]);
		} else if (Quadratic.numberofRoots(a, b, c) == 2){
			System.out.println("x = " + Quadratic.zeros(a, b, c)[0] + ", " + Quadratic.zeros(a, b, c)[1]);
		}
		System.out.println("The " + Quadratic.maxOrMin(a, b, c) + " of the quadratic, " + Quadratic.extremeValue(a, b, c) + ", occurs at y = " + Quadratic.axisOfSymmetry(a, b, c) + ".");
		System.out.println("At f(" + x + "), y = " + Quadratic.fof(a, b, c, x));

	}

}
