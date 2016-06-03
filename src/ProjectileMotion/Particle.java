package ProjectileMotion;

import org.opensourcephysics.controls.*;  //needed for the simulation
import org.opensourcephysics.display.*;   //needed to use Circles
import org.opensourcephysics.frames.*;   //needed to use a DisplayFrame

/**
 * This class contains the basics of any simulation with some simple graphics.
 */
public class Particle extends ProjectileLaunch {

	Circle c = new Circle();
	Trail trail = new Trail();
	int radius;
	double iangle;
	
	double velox = 0;
	double veloy = 0;
	double xacceleration = 0;
	double yacceleration = -9.8;
	
	double totaldeltax;
	double totaldeltay;
	double deltax;
	double deltay;
	double t = .1;


	protected void doStep(double time) {
		
		deltax = velox*t + (xacceleration*t*t)/2;
		deltay = veloy*t + (yacceleration*t*t)/2;
		
		totaldeltax = totaldeltax + deltax;
		totaldeltay = totaldeltay + deltay;
		
		c.setXY(totaldeltax, totaldeltay);
		
		velox = velox + xacceleration*time;
		veloy = veloy + yacceleration*time;
		
		xacceleration = xacceleration - .01*xacceleration;
		yacceleration = yacceleration - .01*yacceleration;
		
		if (totaldeltay < 0) {
			totaldeltay = 0;
			totaldeltax = totaldeltax - deltax; 
		}
		System.out.println(velox);
		System.out.println(veloy);
		
		
	}


	public void reset() {
		control.setValue("x", 0);
		control.setValue("y", 0);
		
	}


	public void initialize(PlotFrame pframe, int ballradius, double initialvelocity, double initialangle, double movey) {
		yacceleration = movey;
		
		velox = initialvelocity*(Math.cos(initialangle/57.2958));
		veloy = initialvelocity*(Math.sin(initialangle/57.2958));
		
		pframe.addDrawable(c);
		
		
		
	}


}