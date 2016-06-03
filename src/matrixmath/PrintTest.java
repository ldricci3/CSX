package matrixmath;

import matrixmath.Matrix;

public class PrintTest {
        
        public static void main(String[] args) 
        {
                Matrix Test = new Matrix(5,5);
                
                for(int i=0; i<5; i++)
                {
                        for(int j=0; j<5; j++)
                        {
                                Test.setEntry(i,j, .1+5*i+j);
                        }
                }
                
        Test.print();
                
        }

}