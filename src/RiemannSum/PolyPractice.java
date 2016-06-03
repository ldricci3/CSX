package RiemannSum;
import org.opensourcephysics.frames.PlotFrame;

import polyfun.Coef;
import polyfun.Polynomial;
import polyfun.Term;
/**
 * 
 * @author student
 *Description - class which contains methods that evaluate and add x squared to a polynomial
 *
 */
public class PolyPractice{
	/**
	 * Description - evaluates a polynomial at x
	 * @param p - Polynomial
	 * @param x - where on the x axis to solve
	 * @return - the y at the given x of the given polynomial
	 */
	public static double eval(Polynomial p, double x) {
		return p.evaluate(x).getTerms()[0].getTermDouble();
	}
	/**
	 * Decsription - adds polynomial x squared to a given polynomial and graph it.
	 * @param p - Polynomial which x squared is added to
	 */
	public static void addXSquared(Polynomial p) {
		Polynomial xsquared = new Polynomial(1, 2);
		Polynomial sum;
		sum = p.plus(xsquared);
		PlotFrame myframe = new PlotFrame("x","y", "Graph");
		myframe.setVisible(true);
		myframe.setSquareAspect(false);
		myframe.setDefaultCloseOperation(3);
		myframe.setPreferredMinMax(-100, 100, -100, 100);
		myframe.setMarkerSize(1, 1);
		for (double i = 0; i < 100; i=i+.2) {
			myframe.append(1,i,PolyPractice.eval(p, i));
			myframe.append(1,-i,PolyPractice.eval(p, -i));
		}
	}
}

