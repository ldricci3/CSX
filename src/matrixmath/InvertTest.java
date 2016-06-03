package matrixmath;

import matrixmath.Matrix;

public class InvertTest {
	public static void main(String[] args) {
		Matrix trixie = new Matrix (4,4);
	    
		for(int i=0; i<4; i++) //Filling the left matrix
        {
                 for(int j=0; j<4; j++)
                 {
                         trixie.setEntry(i,j, i+j);
                 }
        }
		
		
		
		trixie.mymatrix[3][3] = 0;
		trixie.mymatrix[0][2] = 7;
		trixie.mymatrix[1][3] = 6;
		
		trixie.print();
	    System.out.println();
	    trixie.invert().print();
		
		
	}
	
    
    

}

