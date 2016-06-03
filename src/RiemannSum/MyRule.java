package RiemannSum;

import org.opensourcephysics.display.DrawableShape;
import org.opensourcephysics.frames.PlotFrame;

import polyfun.Polynomial;
/**
 * 
 * @author student
 *Description - Holds the versions of slice and slicePlot which are calculated using My Rule (1/3rd rule)
 *
 */

public class MyRule extends Riemann{
	/*
	 * (non-Javadoc)
	 * @see RiemannSum.Riemann#slice(polyfun.Polynomial, double, double)
	 */
	public double slice(Polynomial p, double sleft, double sright) {
		double area = 0;
		if (PolyPractice.eval(p, sright) >= PolyPractice.eval(p, sleft)) {
			area = (sright-sleft)*PolyPractice.eval(p, sleft + 2*(sright-sleft)/3);
		} else {
			area = (sright-sleft)*PolyPractice.eval(p, sleft + (sright-sleft)/3);
		}
		return area;
	}

	public void slicePlot(PlotFrame pframe, Polynomial poly, int index, double left, double right) {
		double centerx = (right+left)/2;
		double centery;
		double width = Math.abs(right -left);
		double height;
		if (PolyPractice.eval(poly, right) >= PolyPractice.eval(poly, left)) {
			centery = PolyPractice.eval(poly, left + 2*(right-left)/3)/2;
			height = PolyPractice.eval(poly, left + 2*(right-left)/3);
		} else {
			centery = PolyPractice.eval(poly, left + (right-left)/3)/2;
			height = PolyPractice.eval(poly, left + (right-left)/3);
		}
		DrawableShape rectangle = DrawableShape.createRectangle(centerx, centery, width, height);
		pframe.addDrawable(rectangle);
		
	}

}
