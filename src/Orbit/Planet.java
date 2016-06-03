package Orbit;

import java.util.ArrayList;

import org.opensourcephysics.display.Circle;
import org.opensourcephysics.display.Trail;

public class Planet{
	
	Circle c = new Circle();
	Trail trail = new Trail();
	
	double mass;
	double velox;
	double veloy;
	double xacceleration;
	double yacceleration;
	double xpos;
	double ypos;
	double totalforcex;
	double totalforcey;
	double forcex;
	double forcey;
	double force;
	double tag;
	double distance;
	double angle;
	String check = "here";
	
	double prevxpos;
	double prevypos;
	double distancetraveled;
	
	ArrayList<Double> xhistory = new ArrayList<Double>();
	ArrayList<Double> yhistory = new ArrayList<Double>();
	
	
	public void create (double mass1, double xposition, double yposition, double vx, double vy, double t, int radius, int planetcount1) {
		mass = mass1;
		xpos = xposition;
		ypos = yposition;
		prevypos = yposition;
		prevxpos = xposition;
		velox = vx;
		veloy = vy;
		tag = t;
		c.pixRadius = radius;
		c.setXY(xpos, ypos);
	}

}
