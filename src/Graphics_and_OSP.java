import org.opensourcephysics.display.Circle;
import org.opensourcephysics.frames.*;
import org.opensourcephysics.numerics.Polynomial;
import java.awt.Color;
public class Graphics_and_OSP {
	public static void main(String[] args) {
//		PlotFrame myframe = new PlotFrame("time(s)","distance(m)", "My Next Graph");
//		myframe.setVisible(true);
//		//myframe.append(int data set index, double x coordinate, double y coordinate);
//		myframe.setSquareAspect(false);
//		myframe.setDefaultCloseOperation(3);
//		myframe.setPreferredMinMax(-10, 10, -10, 100);
		/*
		 * Demo
		 */
//		myframe.append(1,2.2,-1);
//		myframe.append(1,3,3.8);
//		myframe.append(1,-3.5,2);
//		myframe.setConnected(1,true);
		
		
		/*
		 * Part 1
		 */
//		myframe.append(1,1.2,3);
//		myframe.append(1,4,-2.7);
//		myframe.append(1,8,8);
//		for (double i = 0; i < 10; i=i+.2) {
//			myframe.append(2,i,i*i);
//			myframe.setConnected(2,true);
//		}
//		for (double i = 0; i < 10; i=i+.2) {
//			myframe.append(2,-i,i*i);
//			myframe.setConnected(2,true);
//		}
//		
//		for (int i = 0; i < 100; i++) {
//			myframe.append(3,i-50,2*i-100);
//			myframe.setConnected(3,true);
//		}
		
		/*
		 * Part 2
		 */
//		myframe.setMarkerSize(2, 1);
//		for (double i = 0; i < 10; i=i+.01) {
//			myframe.append(2,i,i*i);
//			myframe.setConnected(2,true);
//		}
//		for (double i = 0; i < 10; i=i+.01) {
//			myframe.append(2,-i,i*i);
//			myframe.setConnected(2,true);
//		}
		
		/**
		 * Display Frame
		 * 
		 * 
		 */
		
		DisplayFrame f = new DisplayFrame( "x-axis", "y-axis", "Display Frame");
		f.setDefaultCloseOperation(3);
		f.setPreferredMinMax(-10, 10, -10, 10);
		Circle circle = new Circle(5, -3, 20);
		
		
	}

}
