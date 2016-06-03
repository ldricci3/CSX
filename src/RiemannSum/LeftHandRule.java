package RiemannSum;

import java.awt.Color;

import org.opensourcephysics.display.DrawableShape;

import polyfun.Polynomial;

import org.opensourcephysics.frames.PlotFrame;
/**
 * 
 * @author student
 * Description - Holds the versions of slice and slicePlot which are calculated using Left Hand Rule
 *
 */
public class LeftHandRule extends Riemann{
	/*
	 * (non-Javadoc)
	 * @see RiemannSum.Riemann#slice(polyfun.Polynomial, double, double)
	 */
	public double slice(Polynomial p, double sleft, double sright) {
		double area = 0;
		area = (sright-sleft)*PolyPractice.eval(p, sleft);
		return area;
		
	}
	/*
	 * (non-Javadoc)
	 * @see RiemannSum.Riemann#slicePlot(org.opensourcephysics.frames.PlotFrame, polyfun.Polynomial, int, double, double)
	 */
	public void slicePlot(PlotFrame pframe,Polynomial poly, int index, double left, double right) {
		double centerx = (left+right)/2;
		double centery = PolyPractice.eval(poly, left)/2;
		double width = Math.abs(left-right);
		double height = PolyPractice.eval(poly, left);
		DrawableShape rectangle = DrawableShape.createRectangle(centerx, centery, width, height);
		pframe.addDrawable(rectangle);
	}

}

