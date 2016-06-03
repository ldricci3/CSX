package matrixmath;


import org.opensourcephysics.display.Trail;
import org.opensourcephysics.frames.PlotFrame;

import RiemannSum.MidpointRule;
import RiemannSum.PolyPractice;
import polyfun.Atom;
import polyfun.Coef;
import polyfun.Polynomial;
import polyfun.Term;

public class VDM {
	
	public double slopeAtPoint (double a, Polynomial poly) {
		Coef[] acoefs = new Coef[] {new Coef(a*a), new Coef(-2*a), new Coef(1)};
		Polynomial point = new Polynomial(acoefs);
		
		Polynomial qx = new Polynomial('q', poly.getDegree()-2);
		
		Coef[] mxbcoefs = new Coef[] {new Coef(new Atom('b')), new Coef(new Atom('m'))};
		Polynomial mxb = new Polynomial(mxbcoefs);
		
		Polynomial tomatrix = qx.times(point).plus(mxb);

		Matrix m = new Matrix(tomatrix.getDegree() + 1, tomatrix.getDegree() + 1);
		
		for (int i = 0; i < tomatrix.getDegree() + 1; i++) {
			for (int j = 0; j < tomatrix.getCoefficient(i).getTerms().length; j++) {
				if (tomatrix.getCoefficient(i).getTerms()[j].getTermAtoms()[0].getLetter() == 'b') {
					m.setEntry(i, 0, tomatrix.getCoefficient(i).getTerms()[j].getTermDouble());
				} else if (tomatrix.getCoefficient(i).getTerms()[j].getTermAtoms()[0].getLetter() == 'm') {
					m.setEntry(i, 1, tomatrix.getCoefficient(i).getTerms()[j].getTermDouble());
				} else {
					m.setEntry(i, tomatrix.getCoefficient(i).getTerms()[j].getTermAtoms()[0].getSubscript()+2, tomatrix.getCoefficient(i).getTerms()[j].getTermDouble());
				}
			}
		}
		
		Matrix mi = m.invert();
		
		Matrix mpoly = new Matrix(poly.getDegree() + 1, 1);
		for (int i = 0; i < poly.getDegree() + 1; i++) {
			mpoly.mymatrix[i][0] = poly.getCoefficient(i).getTerms()[0].getTermDouble();
		}
		
		Matrix manswers = mi.times(mpoly);
		
		return manswers.mymatrix[1][0];
	}
	
	public void graph(Polynomial poly) {
		
		PlotFrame frame = new PlotFrame("x", "y", "Poly");
		Trail trail = new Trail();
		PlotFrame derivativeframe = new PlotFrame("x", "y", "Derivative");
		Trail derivativetrail = new Trail();
		
		for (double i = -20; i < 20; i = i + .1) {
			trail.addPoint(i, PolyPractice.eval(poly, i));
		}
		frame.addDrawable(trail);
		for (double i = -20; i < 20; i = i + .1) {
			derivativetrail.addPoint(i, slopeAtPoint(i, poly));
		}
		derivativeframe.addDrawable(derivativetrail);
		frame.setVisible(true);
		derivativeframe.setVisible(true);
		frame.setDefaultCloseOperation(3);
		derivativeframe.setDefaultCloseOperation(3);
	}
	

}
