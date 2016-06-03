package RiemannSum;

import polyfun.Polynomial;
import RiemannSum.PolyPractice;

public class AddXsquaredTest {

        public static void main(String[] args) 
        {
                PolyPractice popeye = new PolyPractice(); 
                Polynomial poly = new Polynomial(new double[] {-6,-1}); 
                popeye.addXSquared(poly); //popeye adds x^2 to poly, and then both prints and graphs the resulting polynomial
        }
}