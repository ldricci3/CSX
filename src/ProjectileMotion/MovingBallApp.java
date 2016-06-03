package ProjectileMotion;

import java.awt.Color;

import org.opensourcephysics.controls.*;  //needed for the simulation
import org.opensourcephysics.display.*;   //needed to use Circles
import org.opensourcephysics.frames.*;   //needed to use a DisplayFrame

public class MovingBallApp extends AbstractSimulation {

        Circle c = new Circle();
        Trail trail = new Trail();
        DisplayFrame frame = new DisplayFrame("x", "y", "Frame");  //two object which are created outside of any method so all methods can access them
        double time = 0;
        double deltax;
    	double deltay;
    	double vxi;
    	double vyi;
        
        
        protected void doStep() {

        	deltax = control.getDouble("x") + vxi*time;
        	deltay = vyi*time + (control.getDouble("gravity")*time*time)/2;
        	if (deltay > 1) {
        		c.color = Color.BLUE;
        	} else {
        		c.color = Color.RED;
        	}
			c.setXY(deltax, deltay);
			trail.addPoint(deltax, deltay);
			frame.addDrawable(trail);
			
        	time = time + .1;
        }

        
        public void reset() {
        		control.setValue("x", 0);
                control.setValue("y", 0);
                control.setValue("initial velocity", 60);
                control.setValue("angle", 30);
                control.setValue("gravity", -9.8);
                control.setValue("ball radius", 5);
                time = 0;
                trail.clear();
        }

        public void initialize() {
        		
        		vxi = control.getDouble("initial velocity")*Math.cos(control.getDouble("angle")/57.2958);
        		vyi = control.getDouble("initial velocity")*Math.sin(control.getDouble("angle")/57.2958);
        		
                c.setXY(control.getDouble("x"), control.getDouble("y"));
                c.pixRadius = control.getInt("ball radius");
                
                
                if (Math.abs(control.getDouble("x")) < 50 && Math.abs(control.getDouble("y")) < 50) {
                	
                	frame.setPreferredMinMax(-50, 50, -50, 50);
                	
                } else if (Math.abs(control.getDouble("x")) >= Math.abs(control.getDouble("y"))) {
                	
                	frame.setPreferredMinMax(-Math.abs(control.getDouble("x")), Math.abs(control.getDouble("x")), -Math.abs(control.getDouble("x")), Math.abs(control.getDouble("x")));
                	
                } else {
                	
                	frame.setPreferredMinMax(Math.abs(-control.getDouble("y")), Math.abs(control.getDouble("y")), -Math.abs(control.getDouble("y")), Math.abs(control.getDouble("y")));
                }
                
                frame.setVisible(true);
                frame.addDrawable(c);

        }

        public static void main(String[] args) {

                SimulationControl.createApp(new MovingBallApp());
        }
        
}