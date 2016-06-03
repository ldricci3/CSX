
public class Quadratic {
	public static double[] roots(double a, double b, double c) {
		double[] roots = new double[2];
		if ((b*b)-4*a*c < 0) {
			return null;
		} else {
			roots[0] = ((0-b)+Math.sqrt((b*b)-4*a*c))/(2*a);
			roots[1] = ((0-b)-Math.sqrt((b*b)-4*a*c))/(2*a);

			return roots;
		}
	}
	public static Boolean realRoots(double a, double b, double c) {
		if (roots(a, b, c) == null) {
			return false;
		} else {
			return true;
		}
	}
	public static int numberofRoots(double a, double b, double c) {
		if (roots(a, b, c) == null) {
			return 0;
		} else if (roots(a, b, c)[0] == roots(a, b, c)[1]) {
			return 1;
		} else {
			return 2;
		}
	}
	public static double[] zeros(double a, double b, double c) {
		return roots(a, b, c);
	}
	public static double axisOfSymmetry(double a, double b, double c) {
		double axis = (0-b)/(2*a);
		return axis;
	}
	public static double extremeValue(double a, double b, double c){

		double middle = (0-b)/(2*a);
		return (a*(middle*middle) + b*middle + c);
	}
	public static String maxOrMin(double a, double b, double c) {
		if (a>0) {
			return "min";
		} else {
			return "max";
		}

	}
	public static double fof(double a, double b, double c, double x) {
		return (a*(x*x) + b*x + c);

	}


}
