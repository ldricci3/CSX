package RiemannSum;
import polyfun.Polynomial;
import RiemannSum.*;

public class RSTest 
{
        public static void main(String[] args) 
        {
                LeftHandRule LHR = new LeftHandRule();  //LeftHandRule extends Riemann
                RightHandRule RHR = new RightHandRule();  //RightHandRule extends Riemann
                TrapezoidRule TR= new TrapezoidRule(); //TrapezoidRule extends Riemann
                MaximumRule MAX = new MaximumRule(); //MaximumRule extends Riemann
                MinimumRule MIN = new MinimumRule(); //MinimumRule extends Riemann
                MidpointRule MID = new MidpointRule(); //MidpointRule extends Riemann
                MyRule MY = new MyRule(); //MyRule extends Riemann
                RandomRule RR = new RandomRule(); //RandomRule extends Riemann
                
                Polynomial p = new Polynomial(new double[] {0,0,3}); //p=3x^2

                System.out.println(LHR.rs(p, 0.0, 1.0, 2000)+"\n"); //the approximate area under 3x^2, from 0 to 1, using the left hand rule
                
                System.out.println(RHR.rs(p, 0.0, 1.0, 2000)+"\n"); //the approximate area under 3x^2, from 0 to 1, using the right hand rule
                
                System.out.println(TR.rs(p, 0.0, 1.0, 2000)+"\n"); //the approximate area under 3x^2, from 0 to 1, using the trapezoid rule
                
                System.out.println(MAX.rs(p, 0.0, 1.0, 2000)+"\n"); //the approximate area under 3x^2, from 0 to 1, using the maximum rule
                
                System.out.println(MIN.rs(p, 0.0, 1.0, 2000)+"\n"); //the approximate area under 3x^2, from 0 to 1, using the minimum rule
                
                System.out.println(MID.rs(p, 0.0, 1.0, 2000)+"\n"); //the approximate area under 3x^2, from 0 to 1, using the midpoint rule
                
                System.out.println(MY.rs(p, 0.0, 1.0, 2000)+"\n"); //the approximate area under 3x^2, from 0 to 1, using the my rule
                
                System.out.println(RR.rs(p, 0.0, 1.0, 2000)+"\n"); //the approximate area under 3x^2, from 0 to 1, using the random rule
                
                
                
        }
}