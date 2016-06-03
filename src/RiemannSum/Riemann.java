package RiemannSum;

import org.opensourcephysics.display.Trail;
import org.opensourcephysics.frames.PlotFrame;

import polyfun.Polynomial;
/**
 * 
 * @author student
 * Description: This is an abstract class. It holds rs, rsPlot, and rsACC which calculate the area under the curve,
 * 				plotting the rectangles under the curve, and plot the accumulation function, respectively.
 * 
 * @param left - is the left hand endpoint
 * @param right - is the right hand endpoint
 * @param subintervals - is the number of subintervals
 * 
 * last modified 10/1/15
 * 
 * version 1.0
 */
public abstract class Riemann {
	double left;
	double right;
	double delta;
	double subintervals;
	
	public double getLeft() {
		return left;
	}
	public void setLeft(double left) {
		this.left = left;
	}
	public double getRight() {
		return right;
	}
	public void setRight(double right) {
		this.right = right;
	}
	public double getDelta() {
		return delta;
	}
	public void setDelta(double delta) {
		this.delta = delta;
	}
	public double getSubintervals() {
		return subintervals;
	}
	public void setSubintervals(double subintervals) {
		this.subintervals = subintervals;
	}
	/**
	 * 
	 * @rs Description - find the area under the curve of a polynomial using a given rule
	 *  
	 * @param p - is the polynomial whose Riemann sum is to be calculated
	 * @param a - is the left hand endpoint of the Riemann sum
	 * @param b - is the right hand endpoint of the Riemann sum
	 * @param k - is the number of subintervals in the Riemann sum
	 * @return - Returns the value of the Riemann sum, claculated according to a particular rule which is 
	 * 			 determined by the child class that extends this method
	 */
	public double rs(Polynomial p, double a, double b, int k) {
		//calculates the length of a subinterval
		double delta = (b-a)/k;
		double area = 0;
		//each loop adds the area of a slice to the total area
		for (int i = 0; i < k; i++) {
			area += slice(p, a+(i*delta), a+((i+1)*delta));
		}
		return area;
//		if (k <= 1) {
//			area = slice(p, a, a+delta);
//			return area;
//		} else {
//			area = area + rs(p, a, b-delta, k-1);
//			return area;
//		}
	}
	/**
	 * @slice Description - finds the area of a slice under a polynomial
	 * 
	 * @param p - is the polynomial whose area (over or under the x-axis), over the interval from sleft to sright, is to be calculated
	 * @param sleft - is the left hand endpoint of the interval
	 * @param sright - is the right hand endpoint of the interval
	 * @return returns the area of the slice, calculated using the left side of the slice, the right side of the slice,
	 * 		   and the polynomial. Uses PolyPractice.eval to calculate
	 */
	public abstract double slice(Polynomial p, double sleft, double sright);	
	/**
	 * 
	 * @rsPlot Description - graphs the Riemann sum (the polynomial and the rectangles/trapezoids) for a given rule
	 * 
	 * @param pframe - is the PlotFrame on which the polynomial and the Riemann sum are drawn
	 * @param poly - is the polynomial whose Riemann sum is to be drawn
	 * @param index - is the number associated to the collection of (x,y) coordinates that make up the dataset which, when plotted, is the graph of the polynomial
	 * @param precision - is the difference between the x-coordinates of two adjacent points on the graph of the polynomial
	 * @param left - is the left hand endpoint of the Riemann sum
	 * @param right - is the right hand endpoint of the Riemann sum
	 * @param subintervals - is the number of subintervals in the Riemann sum
	 */
	public void rsPlot(PlotFrame pframe,Polynomial poly, int index, double precision, double left, double right, int subintervals) {
		//calculate the length of a subinterval
		double delta = Math.abs(left-right)/subintervals;
		//settings for the frame that the graph will be printed on
		pframe.setMarkerSize(index, 1);
		pframe.setPreferredMinMaxY(-25, 25);
		//for loop looping through slicePlots, individually printing each riemann slice
		for (int k = 0; k < subintervals; k++) {
			slicePlot(pframe, poly, index, left + k*delta, left + (k+1)*delta);
		}
		//printing the graph of the polynomial onto the same frame
		for (double i = 0; i < 10; i = i + precision) {
			pframe.append(index, i, PolyPractice.eval(poly, i));
			pframe.append(index, -i, PolyPractice.eval(poly, -i));
		}

	}
	/**
	 * 
	 * @slicePlot Description - plots the rectangle/trapezoid under a given polynomial in a given interval according to a given rule
	 * 
	 * @param pframe - is the PlotFrame on which the slicePlot is to be drawn
	 * @param poly - is the polynomial whose area (over or under the x-axis), over the interval from sleft to sright, is to be drawn
	 * @param index - is the index of the slice being drawn
	 * @param left - is the left hand endpoint of the interval
	 * @param right - is the right hand endpoint of the interval
	 */
	public abstract void slicePlot(PlotFrame pframe,Polynomial poly, int index, double left, double right);
	/**
	 * 
	 * @param pframe - is the PlotFrame on which the polynomial and the Riemann sum are drawn
	 * @param poly - is the polynomial whose accumulation function is to be calculated
	 * @param index - is the number associated to the collection of (x,y) coordinates that make up the dataset which, when plotted, is the graph of the accumulation function
	 * @param precision - is the difference between the x-coordinates of two adjacent points on the graph of the accumulation function
	 * @param base - is the left hand endpoint of the accumulation function
	 */
	public void rsAcc(org.opensourcephysics.frames.PlotFrame pframe, polyfun.Polynomial poly, int index, double precision, double base) {
		Trail trail = new Trail();
		//starting at -10 and moving to 10 (although this can be changed), the for loop prints the area under the curve between a given base and the x coordinate
		for (double i = -10; i < 10; i = i + precision) {
			trail.addPoint(-i, -rs(poly, base, base + i, 100));
		}
		pframe.addDrawable(trail);
	}
}
