package matrixmath;

import matrixmath.Matrix;

public class PlusTest 
{
        public static void main(String[] args) 
        {                       
                Matrix trixie  = new Matrix(3,4); //The matrix on the left
                                
                Matrix alice = new Matrix(3, 4); //The matrix on the right
                                
                for(int i=0; i<3; i++) //Filling the left matrix
                {
                        for(int j=0; j<4; j++)
                        {
                                trixie.setEntry(i,j, i+j);
                        }
                }
                                
                for(int i=0; i<3; i++) //Filling the right matrix
                {
                        for(int j=0; j<4; j++)
                        {
                                alice.setEntry(i,j, i-j);
                        }
                }
                                
                trixie.plus(alice).print(); //The sum of the two matrices                       
        }
}
