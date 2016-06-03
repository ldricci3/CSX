package RiemannSum;

import java.awt.Color;

import org.opensourcephysics.display.DrawableShape;
import org.opensourcephysics.frames.PlotFrame;

import polyfun.Polynomial;
/**
 * 
 * @author student
 * Description - Holds the versions of slice and slicePlot which are calculated using Right Hand Rule
 *
 */
public class RightHandRule extends Riemann{
	/*
	 * (non-Javadoc)
	 * @see RiemannSum.Riemann#slice(polyfun.Polynomial, double, double)
	 */
	public double slice(Polynomial p, double sleft, double sright) {
		double area = 0;
		area = (sright-sleft)*PolyPractice.eval(p, sright);
		return area;
		
	}
	/*
	 * (non-Javadoc)
	 * @see RiemannSum.Riemann#slicePlot(org.opensourcephysics.frames.PlotFrame, polyfun.Polynomial, int, double, double)
	 */
	public void slicePlot(PlotFrame pframe,Polynomial poly, int index, double left, double right) {
		double centerx = (left+right)/2;
		double centery = PolyPractice.eval(poly, right)/2;
		double width = Math.abs(left-right);
		double height = PolyPractice.eval(poly, right);
		DrawableShape rectangle = DrawableShape.createRectangle(centerx, centery, width, height);
		pframe.addDrawable(rectangle);
	}

}
