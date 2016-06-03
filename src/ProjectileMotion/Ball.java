package ProjectileMotion;

import java.awt.Color;

import org.opensourcephysics.display.Circle;
import org.opensourcephysics.display.Trail;
import org.opensourcephysics.frames.PlotFrame;

public class Ball {
	
	Circle c = new Circle();
	Trail trail = new Trail();
	
	double π = Math.PI;

	double velox; // m/s
	double veloy; // m/s
	double xacceleration = 0; // m/s^s
	double yacceleration = 0; // m/s^s
	double forcex;
	double forcey;
	double liftforcex;
	double liftforcey;
	double dragx;
	double dragy;
	
	double gravity = -9.8; // m/s^s
	double beta = .01;
	double airdensity = 1.225; // kg/m^3
	
	double totaldeltax; // m
	double totaldeltay; // m
	double deltax; // m
	double deltay; // m
	
	double t; // s
	
	double g;
	double rpm;
	double mass = .145; // kg
	double radius = 0.07468; // m
	
	boolean negative;
	boolean bounce;
	double distance;
	double irpm;
	boolean printed = false;
	boolean stopped = false;
	boolean printed2 = false;
	
	
	
	protected void doStep(double time) {
		t = time;
		
		
		g = (2*π*radius)*(2*π*radius)*(rpm/60);
		
		liftforcex = airdensity*veloy*g;
		liftforcey = -airdensity*velox*g;
		
		dragx = beta*velox;
		dragy = beta*veloy;
		
		// Force = mass*acceleration  
		
		
		forcex = liftforcex*t;
		forcey = liftforcey*t;
		
		xacceleration = (forcex/mass) - dragx;
		yacceleration = gravity + (forcey/mass) - dragy;

		
		velox = velox + xacceleration*t;
		veloy = veloy + yacceleration*t;
		
		deltay = veloy*t + (1/2)*yacceleration*(t*t);
		deltax = velox*t + (1/2)*xacceleration*(t*t);
		
		totaldeltay = totaldeltay + deltay;
		totaldeltax = totaldeltax + deltax;
		c.setXY(totaldeltax, totaldeltay);
		
		
		
		if (totaldeltay < 0 && negative == false) {
			velox = velox*((-rpm/1000) + .5);
			veloy = -veloy*.5;
			totaldeltay = 0;
			rpm = rpm*.1;
			if (bounce = false) {
				distance = totaldeltax;
			}
			bounce = true;
		}
		if (totaldeltay < 2 && totaldeltax <= 120.5 && totaldeltax >= 119.5) {
			velox = -1*velox;
		}
		if (velox < .2) {
			stopped = true;
		}
		
		rpm = rpm*.95;
		
	}
	
	public void initialize(PlotFrame pframe, int ballradius, double initialangle, double initialvelocity, double movey, double spin) {
		yacceleration = movey;
		rpm = spin;
		irpm = spin;
		velox = initialvelocity*(Math.cos(initialangle/57.2958));
		veloy = initialvelocity*(Math.sin(initialangle/57.2958));
		pframe.addDrawable(c);
		
		yacceleration = gravity;
	}

}
