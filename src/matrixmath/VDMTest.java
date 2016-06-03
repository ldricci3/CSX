package matrixmath;

import polyfun.Polynomial;

public class VDMTest {
	public static void main(String[] args) {
		
		VDM vdm = new VDM();
		
		Polynomial poly = new Polynomial(new double[]{0,3,1,4,0,7});
		
		vdm.graph(poly);
		System.out.println(vdm.slopeAtPoint(1, poly));
	}

}
