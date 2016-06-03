package RiemannSum;


import RiemannSum.*;
import polyfun.Polynomial;

import org.opensourcephysics.frames.*;

public class rsAccTest 
{
	public static void main(String[] args) 
	{
		LeftHandRule LHR = new LeftHandRule();  //LeftHandRule extends Riemann
		RightHandRule RHR = new RightHandRule();  //RightHandRule extends Riemann
		TrapezoidRule TZR = new TrapezoidRule(); //TrapezoidRule extends Riemann
		MaximumRule MXR = new MaximumRule(); //MaximumRule extends Riemann
		MinimumRule MNR = new MinimumRule(); //MinimumRule extends Riemann
		MidpointRule MPR = new MidpointRule(); //MidpointRule extends Riemann
		RandomRule RR = new RandomRule(); //RandomRule extends Riemann
		MyRule MR = new MyRule(); //MyRule extends Riemann


		Polynomial p = new Polynomial(new double[] {0,1,1}); // p=x^2+x

		PlotFrame f = new PlotFrame("x","y","Left Hand Rule Accumulation Function Graph");
		f.setPreferredMinMaxX(-3, 3);
		f.setDefaultCloseOperation(3);
		f.setVisible(true);

		PlotFrame g = new PlotFrame("x","y","Right Hand Rule Accumulation Function Graph");
		g.setPreferredMinMaxX(-3, 3);
		g.setDefaultCloseOperation(3);
		g.setVisible(true);

		PlotFrame i = new PlotFrame("x","y","Trapezoid Rule Accumulation Function Graph");
		i.setPreferredMinMaxX(-3, 3);
		i.setDefaultCloseOperation(3);                  
		i.setVisible(true);
		
		PlotFrame k = new PlotFrame("x","y","Maximum Rule Accumulation Function Graph");
		k.setPreferredMinMaxX(-3, 3);
		k.setDefaultCloseOperation(3);                  
		k.setVisible(true);
		
		PlotFrame l = new PlotFrame("x","y","Minimum Rule Accumulation Function Graph");
		l.setPreferredMinMaxX(-3, 3);
		l.setDefaultCloseOperation(3);                  
		l.setVisible(true);
		
		PlotFrame m = new PlotFrame("x","y","Midpoint Rule Accumulation Function Graph");
		m.setPreferredMinMaxX(-3, 3);
		m.setDefaultCloseOperation(3);                  
		m.setVisible(true);
		
		PlotFrame n = new PlotFrame("x","y","Random Rule Accumulation Function Graph");
		n.setPreferredMinMaxX(-3, 3);
		n.setDefaultCloseOperation(3);                  
		n.setVisible(true);
		
		PlotFrame o = new PlotFrame("x","y","My Rule Accumulation Function Graph");
		o.setPreferredMinMaxX(-3, 3);
		o.setDefaultCloseOperation(3);                  
		o.setVisible(true);
		

		LHR.rsAcc(f, p, 2, .01, -1.0); //plots the left hand rule acccumulation function of x^2+x, with base x=-1;

		RHR.rsAcc(g, p, 2, .01, -1.0); //plots the right hand rule acccumulation function of x^2+x, with base x=-1;

		TZR.rsAcc(i, p, 2, .01, -1.0); //plots the trapezoid rule acccumulation function of x^2+x, with base x=-1;
		
		MXR.rsAcc(k, p, 2, .01, -1.0); //plots the Maximum rule acccumulation function of x^2+x, with base x=-1;
		
		MNR.rsAcc(l, p, 2, .01, -1.0); //plots the Minimum rule acccumulation function of x^2+x, with base x=-1;
		
		MPR.rsAcc(m, p, 2, .01, -1.0); //plots the midpoint rule acccumulation function of x^2+x, with base x=-1;
		
		RR.rsAcc(n, p, 2, .01, -1.0); //plots the random rule acccumulation function of x^2+x, with base x=-1;
		
		MR.rsAcc(o, p, 2, .01, -1.0); //plots the random rule acccumulation function of x^2+x, with base x=-1;

	}
} 
