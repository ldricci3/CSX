package Distance;

import java.util.Scanner;

public class DistanceCalculator {
	public static void main(String[] args) {
		double h;
		double v = 10000;
		double angle = 1;
		double deltax = 0;
		double deltax2 = 0;
		double deltay;
		double time;
		double interval = 1;
		
		Scanner scan = new Scanner(System.in);
		System.out.println("What height are you firing from?");
		h = scan.nextDouble();

		for (int i = 0; i < 1000; i++) {
			double a = 4.9;
			double b = -v*Math.sin(Math.toRadians(angle));
			double c = -h;
			time = (-b + Math.sqrt(b*b - 4*a*c))/(2*a);
			
			
			deltax = v*Math.cos(Math.toRadians(angle))*time;
			
			if (deltax > deltax2) {
				deltax2 = deltax;
				angle = angle + interval;

				System.out.println(deltax + " " + angle);
			} else if (deltax2 > deltax) {
				interval = interval/2;
				angle = angle + interval;
			}
			
		}
		
		System.out.println("The optimal angle for that height is " + angle + " degrees.");
		
		
	}

}
