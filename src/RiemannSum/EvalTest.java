package RiemannSum;

import polyfun.Polynomial;
import RiemannSum.PolyPractice;

public class EvalTest {

        public static void main(String[] args) 
        {
                PolyPractice popeye = new PolyPractice(); 
                Polynomial poly = new Polynomial(new double[] {-6, -1}); 
                System.out.println(popeye.eval(poly,2.5)); //popeye evaluates poly at 2.5
        }
}
