package SpringAssignment;

import java.awt.Color;

import org.opensourcephysics.display.Circle;

public class Particle{

	Circle c = new Circle();
	
	double xpos;
	double ypos;
	double velox;
	double veloy;
	double xacceleration;
	double yacceleration;
	double forcex;
	double forcey;
	double totalforcex;
	double totalforcey;
	double mass;
	
	double angle;
	boolean touching;
	boolean prevtouch = false;
	
	public void create(double xpos1, double ypos1, double velox1, double veloy1, double mass1) {
		xpos = xpos1;
		ypos = ypos1;
		velox = velox1;
		veloy = veloy1;
		mass = mass1;
		c.color=Color.black;
		c.setXY(xpos, ypos);
		
	}
}
