package RiemannSum;

import org.opensourcephysics.frames.PlotFrame;

import polyfun.Coef;
import polyfun.Polynomial;
/**
 * 
 * @author student
 *NOTE: DOES NOT WORK!!!!
 *
 *MR. GOMPRECHT SAID THAT WE SHOULDN'T DO IT/BE ABLE TO DO IT FOR A FEW ASSIGNMENTS, SO I AM LEAVING THIS AS IS FOR NOW
 *
 *
 *
 */
public class SimpsonsRule extends Riemann{
	public double slice(Polynomial p, double sleft, double sright) {
		double x1 = sleft;
		double y1 = PolyPractice.eval(p, sleft);
		double x2 = (sright + sleft)/2;
		double y2 = PolyPractice.eval(p, (sright + sleft)/2);
		double x3 = sright;
		double y3 = PolyPractice.eval(p, sright);
		
		double a = ((y2-y1)*(x1-x3) + (y3-y1)*(x2-x1))/((x1-x3)*((x2*x2)-(x1*x1)) + (x2-x1)*((x3*x3)-(x1*x1)));
		double b = ((y2-y1) - a*((x2*x2)-(x1*x1)))/(x2-x1);
		double c = y1 - a*(x1*x1) - b*x1;
		Coef coef1 = new Coef(a);
		Coef coef2 = new Coef(b);
		Coef coef3 = new Coef(c);
		
		Coef[] coefs = new Coef[]{coef1, coef2, coef3};
		
		Polynomial simpsonpoly = new Polynomial(coefs);
		
		TrapezoidRule TR= new TrapezoidRule();
		double area = TR.rs(simpsonpoly, sleft, sright, 1000);
		
		return area;
	}

	public void slicePlot(PlotFrame pframe, Polynomial poly, int index, double left, double right) {
		
	}

}
