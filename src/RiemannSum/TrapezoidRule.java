package RiemannSum;

import java.awt.Color;

import org.opensourcephysics.display.Trail;
import org.opensourcephysics.frames.PlotFrame;

import polyfun.Polynomial;
/**
 * 
 * @author student
 * Description - Holds the versions of slice and slicePlot which are calculated using Trapezoid Rule
 *
 */
public class TrapezoidRule extends Riemann{
	/*
	 * (non-Javadoc)
	 * @see RiemannSum.Riemann#slice(polyfun.Polynomial, double, double)
	 */
	public double slice(Polynomial p, double sleft, double sright) {
		double area = 0;
		area = (sright-sleft)*((PolyPractice.eval(p, sleft)+PolyPractice.eval(p, sright))/2);
		return area;
	}
	/*
	 * (non-Javadoc)
	 * @see RiemannSum.Riemann#slicePlot(org.opensourcephysics.frames.PlotFrame, polyfun.Polynomial, int, double, double)
	 */
	public void slicePlot(PlotFrame pframe,Polynomial poly, int index, double left, double right) {
		Trail trail = new Trail();
		trail.color = Color.black;
		trail.addPoint(left, 0);
		trail.addPoint(left, PolyPractice.eval(poly, left));
		trail.addPoint(right, PolyPractice.eval(poly, right));
		trail.addPoint(right, 0);
		trail.addPoint(left, 0);
		trail.closeTrail();
		pframe.addDrawable(trail);
	}

}
