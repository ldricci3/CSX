package RiemannSum;

import java.util.Random;

import org.opensourcephysics.display.DrawableShape;
import org.opensourcephysics.frames.PlotFrame;

import polyfun.Polynomial;
/**
 * 
 * @author student
 * Description - Holds the versions of slice and slicePlot which are calculated using Random Rule
 *
 */
public class RandomRule extends Riemann{
	/*
	 * (non-Javadoc)
	 * @see RiemannSum.Riemann#slice(polyfun.Polynomial, double, double)
	 */
	public double slice(Polynomial p, double sleft, double sright) {
		Random gen = new Random();
		double area = 0;
		area = (sright-sleft)*PolyPractice.eval(p, sleft + (sright-sleft)*gen.nextDouble());
		return area;
	}
	/*
	 * (non-Javadoc)
	 * @see RiemannSum.Riemann#slicePlot(org.opensourcephysics.frames.PlotFrame, polyfun.Polynomial, int, double, double)
	 */
	public void slicePlot(PlotFrame pframe,Polynomial poly, int index, double left, double right) {
		Random gen = new Random();
		double random = left + (right-left)*gen.nextDouble();
		double centerx = (left+right)/2;
		double centery = PolyPractice.eval(poly, random)/2;
		double width = Math.abs(left-right);
		double height = PolyPractice.eval(poly, random);
		DrawableShape rectangle = DrawableShape.createRectangle(centerx, centery, width, height);
		pframe.addDrawable(rectangle);
	}

}
