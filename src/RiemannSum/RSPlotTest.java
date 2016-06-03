package RiemannSum;

import org.opensourcephysics.frames.PlotFrame;
import polyfun.Polynomial;

public class RSPlotTest 
{
        public static void main(String[] args) 
        {
                LeftHandRule LHR = new LeftHandRule();  //LeftHandRule extends Riemann
                RightHandRule RHR = new RightHandRule();  //RightHandRule extends Riemann
                TrapezoidRule TZR = new TrapezoidRule(); //TrapezoidRule extends Riemann
                MaximumRule MXR = new MaximumRule(); //MaximumRule extends Riemann
                MinimumRule MNR = new MinimumRule(); //MinimumRule extends Riemann
                MidpointRule MPR = new MidpointRule();  //MidpointRule extends Riemann
                RandomRule RR = new RandomRule(); //RandomRule extends Riemann
                MyRule MYR = new MyRule(); //MyRule extends Riemann
                
                Polynomial p = new Polynomial(new double[] {3,-6,3}); // p=3x^2-6x+3
                
                PlotFrame f = new PlotFrame("x","y","Left Hand Riemann Sum Graph");
                f.setPreferredMinMaxX(-1, 3);
                f.setDefaultCloseOperation(3);
                f.setVisible(true);
                
                PlotFrame g = new PlotFrame("x","y","Right Hand Riemann Sum Graph");
                g.setPreferredMinMaxX(-1, 3);
                g.setDefaultCloseOperation(3);
                g.setVisible(true);
                
                PlotFrame h = new PlotFrame("x","y","Trapezoid Riemann Sum Graph");
                h.setPreferredMinMaxX(-1, 3);
                h.setDefaultCloseOperation(3);
                h.setVisible(true);
                
                PlotFrame i = new PlotFrame("x","y","Maximum Riemann Sum Graph");
                i.setPreferredMinMaxX(-1, 3);
                i.setDefaultCloseOperation(3);
                i.setVisible(true);
                
                PlotFrame j = new PlotFrame("x","y","Minimum Riemann Sum Graph");
                j.setPreferredMinMaxX(-1, 3);
                j.setDefaultCloseOperation(3);
                j.setVisible(true);
                
                PlotFrame k = new PlotFrame("x","y","Midpoint Riemann Sum Graph");
                k.setPreferredMinMaxX(-1, 3);
                k.setDefaultCloseOperation(3);
                k.setVisible(true);
                
                PlotFrame l = new PlotFrame("x","y","Random Riemann Sum Graph");
                l.setPreferredMinMaxX(-1, 3);
                l.setDefaultCloseOperation(3);
                l.setVisible(true);
                
                PlotFrame m = new PlotFrame("x","y","My Rule Riemann Sum Graph");
                m.setPreferredMinMaxX(-1, 3);
                m.setDefaultCloseOperation(3);
                m.setVisible(true);
                
                LHR.rsPlot(f, p, 1, 0.01, 0.0, 2.0, 10); // the left hand rule from x=0 to x=2, with 10 rectangles      
                
                RHR.rsPlot(g, p, 1, 0.01, 0.0, 2.0, 10); // the right hand rule from x=0 to x=2, with 10 rectangles  
                
                TZR.rsPlot(h, p, 1, 0.01, 0.0, 2.0, 10); // the trapezoid rule from x=0 to x=2, with 10 trapezoids
                
                MXR.rsPlot(i, p, 1, 0.01, 0.0, 2.0, 10); // the maximum rule from x=0 to x=2, with 10 rectangles
                
                MNR.rsPlot(j, p, 1, 0.01, 0.0, 2.0, 10); // the minimum rule from x=0 to x=2, with 10 rectangles
                
                MPR.rsPlot(k, p, 1, 0.01, 0.0, 2.0, 10); // the midpoint rule from x=0 to x=2, with 10 rectangles
                
                RR.rsPlot(l, p, 1, 0.01, 0.0, 2.0, 10); // the random rule from x=0 to x=2, with 10 rectangles
                
                MYR.rsPlot(m, p, 1, 0.01, 0.0, 2.0, 10); // the 'my' rule from x=0 to x=2, with 10 rectangles
                
        }
} 
