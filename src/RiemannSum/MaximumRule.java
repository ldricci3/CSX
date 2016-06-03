package RiemannSum;

import java.awt.Color;

import org.opensourcephysics.display.DrawableShape;
import org.opensourcephysics.frames.PlotFrame;

import polyfun.Polynomial;
/**
 * 
 * @author student
 * Description - Holds the versions of slice and slicePlot which are calculated using Maximum Rule
 *
 */
public class MaximumRule extends Riemann{
	/*
	 * (non-Javadoc)
	 * @see RiemannSum.Riemann#slice(polyfun.Polynomial, double, double)
	 */
	public double slice(Polynomial p, double sleft, double sright) {
		double area = 0;
		if (PolyPractice.eval(p, sleft) >= PolyPractice.eval(p, sright)) {
			area = (sright-sleft)*PolyPractice.eval(p, sleft);
		} else {
			area = (sright-sleft)*PolyPractice.eval(p, sright);
		}
		return area;
	}
	/*
	 * (non-Javadoc)
	 * @see RiemannSum.Riemann#slicePlot(org.opensourcephysics.frames.PlotFrame, polyfun.Polynomial, int, double, double)
	 */
	public void slicePlot(PlotFrame pframe,Polynomial poly, int index, double left, double right) {
		double centerx = (right+left)/2;
		double centery;
		double width = Math.abs(right -left);
		double height;
		if (PolyPractice.eval(poly, left) >= PolyPractice.eval(poly, right)) {
			centery = PolyPractice.eval(poly, left)/2;
			height = PolyPractice.eval(poly, left);
		} else {
			centery = PolyPractice.eval(poly, right)/2;
			height = PolyPractice.eval(poly, right);
		}
		DrawableShape rectangle = DrawableShape.createRectangle(centerx, centery, width, height);
		pframe.addDrawable(rectangle);
	}

}
