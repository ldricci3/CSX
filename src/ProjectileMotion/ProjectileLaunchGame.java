package ProjectileMotion;

import org.opensourcephysics.controls.AbstractSimulation;
import org.opensourcephysics.controls.SimulationControl;
import org.opensourcephysics.display.Circle;
import org.opensourcephysics.display.Trail;
import org.opensourcephysics.frames.DisplayFrame;

public class ProjectileLaunchGame extends AbstractSimulation {
	
	DisplayFrame frame = new DisplayFrame("x", "y", "Frame");
	Circle c = new Circle();
	Trail trail = new Trail();
	double yvelocity;
	double xvelocity;
	double deltax;
 	double deltay;
 	double time = 0;

	
	protected void doStep() {
		deltax = xvelocity*time;
    	deltay = yvelocity*time + (-9.8*time*time)/2;
    	c.setXY(deltax, deltay);
    	trail.addPoint(deltax, deltay);
    	time=+.1;
    	frame.addDrawable(trail);
	}
	
	public void reset(){
		control.setValue("velocity", 60);
        control.setValue("angle", 30);
	}
	
	public void initialize() {
		yvelocity = control.getDouble("velocity")*Math.sin(control.getDouble("angle")/57.2958);
		xvelocity = control.getDouble("velocity")*Math.cos(control.getDouble("angle")/57.2958);
		time = 0;
		c.setXY(0, 0);
		frame.addDrawable(c);
	}
	
	public static void main(String[] args) {
		SimulationControl.createApp(new ProjectileLaunchGame());
		
	}

}
