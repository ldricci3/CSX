package matrixmath;

import matrixmath.Matrix;

public class LinearCombRowsTest {

        public static void main(String[] args) 
        {
                Matrix trixie = new Matrix (4,4); 
                Matrix m2 = new Matrix (4, 4);
                
                for(int i=0; i<4; i++) //Filling the left matrix
                {
                         for(int j=0; j<4; j++)
                         {
                                 m2.setEntry(i,j, i+j);
                         }
                }
                
                for(int i=0; i<4; i++) //Filling trixie
                {
                        for(int j=0; j<4; j++)
                        {
                                if(i+j==2)
                                        trixie.setEntry(i, j, i+1);
                                else
                                        trixie.setEntry(i, j, 0);
                        }
                }
                
                trixie.setEntry(3,3, 4);
                
                trixie.print(); //printing trixie
                
                System.out.println("\n");
                
                trixie.linearCombRows(1.5,3,0).print(); //printing the Matrix which results when 1.5 times row 3 of trixie is added to row 0 of trixie
                
                System.out.println("\n");
                
                trixie.print(); //printing trixie again!
                
                System.out.println();
                System.out.println("m2");
                System.out.println();
                
                m2.print();
                System.out.println();
                m2.linearCombRows(-1.5, 3, 0).print();
                System.out.println();
                m2.print();
                
        }

}
