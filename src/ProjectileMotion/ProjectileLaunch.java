package ProjectileMotion;

import java.awt.Color;
import java.util.ArrayList;

import org.opensourcephysics.controls.AbstractSimulation;
import org.opensourcephysics.controls.SimulationControl;
import org.opensourcephysics.display.Circle;
import org.opensourcephysics.display.DrawableShape;
import org.opensourcephysics.display.Trail;
import org.opensourcephysics.frames.DisplayFrame;
import org.opensourcephysics.frames.PlotFrame;

public class ProjectileLaunch extends AbstractSimulation{
	/**
	 * Question: How does the spin of a ball affect its flight?
	 * To answer this question I had to learn about the Magnus effect; the effect of a vortex 
	 * behind an object on its flight. To put it relatively simply, the spin of a ball cause 
	 * a difference in the movement of air around the ball’s sides. The asymmetrical wake 
	 * created by this imbalance causes a difference of pressure around the ball, forcing it 
	 * in a certain direction. For example, a ball with topspin (spin around a horizontal axis 
	 * perpendicular to the direction of travel, where the top of the ball is moving forward 
	 * with the spin) causes the ball to curve downwards faster than gravity alone would pull 
	 * it. Backspin, on the other hand, does the opposite. This effect can be seen in baseball, 
	 * where pitchers alter the spin on their pitches to make them curve in the desired direction. 
	 * 
	 * There are several equations used to calculate the magnus effect. The main equation is:
	 * 
	 * F = pvG.
	 * 
	 * F = the force of lift due to the magnus effect
	 * p = density of the air
	 * v = velocity of the ball
	 * G = s(2’pi’r)^2
	 * 
	 * s = ball’s revolutions per second
	 * r = radius of the ball
	 * 
	 * Spin also effects the bounce of a ball. During a bounce a ball loses energy to to many things
	 * like friction, deformation and heat. Spin will cause the ball the accelerate in the direction 
	 * of the spin: topspin accelerates the ball in the positive direction, backspin does the opposite.
	 */
	
	PlotFrame frame = new PlotFrame("x", "y", "Frame");
	Trail trail = new Trail();
	ArrayList<Ball> particles = new ArrayList<Ball>();
	ArrayList<Trail> trails = new ArrayList<Trail>();
	double time = 0;
	int ballnumber;
	double highestdeltay = 25;
	double highestdeltax = 25;
	double closest = 15;
	double closenumber;
	double spin;
	
	protected void doStep() {
		for (int i = 0; i < particles.size(); i++) {
			particles.get(i).doStep(time);
			trails.get(i).addPoint(particles.get(i).totaldeltax, particles.get(i).totaldeltay);
			frame.addDrawable(trails.get(i));
			
			if (particles.get(i).totaldeltax > highestdeltax) {
				highestdeltax = particles.get(i).totaldeltax;
			}
			if (particles.get(i).totaldeltay > highestdeltay) {
				highestdeltay = particles.get(i).totaldeltay;
			}
			frame.setPreferredMinMax(-10, highestdeltax + 20, -10, highestdeltay + 20);
			
			if (particles.get(i).totaldeltay > 2 && Math.sqrt(((120 - particles.get(i).totaldeltax)*(120 - particles.get(i).totaldeltax)) + ((2 - particles.get(i).totaldeltay)*(2 - particles.get(i).totaldeltax))) < closest && particles.get(i).bounce == false) {
				closest = Math.sqrt(((120 - particles.get(i).totaldeltax)*(120 - particles.get(i).totaldeltax)) + ((2 - particles.get(i).totaldeltay)*(2 - particles.get(i).totaldeltax)));
				closenumber = i;
			}
			if (closenumber == i) {
				trails.get(i).color = Color.RED;
				particles.get(i).c.color = Color.RED;
			} else {
				if (particles.get(i).rpm > 2) {
					particles.get(i).c.color = Color.green;
					trails.get(i).color = Color.green;
				} else if (particles.get(i).rpm < - 2) {
					particles.get(i).c.color = Color.yellow;
					trails.get(i).color = Color.yellow;
				}
			}
			
			if (particles.get(i).bounce == true && particles.get(i).printed == false) {
				System.out.println("RPM = " + -particles.get(i).irpm + "  First Bounce Distance = " + particles.get(i).totaldeltax);
				particles.get(i).printed = true;
			}
			if (particles.get(i).stopped == true && particles.get(i).printed2 == false) {
				System.out.println("RPM = " + -particles.get(i).irpm + "  Final Distance = " + particles.get(i).totaldeltax);
				particles.get(i).printed2 = true;
			}
		}
		time = .03;
		
	}
	
	
	
	public void reset() {
		time = 0;
		particles.clear();
		control.setValue("velocity", 20);
		control.setValue("lowest rpm", -1000);
		control.setValue("highest rpm", 1000);
		control.setValue("angle", 30);
		control.setValue("ball radius", 1);
		control.setValue("ball number", 10);
		control.setValue("gravity", -9.8);
		
		frame.clearDrawables();
		particles.clear();
		trails.clear();
		closenumber = 0;
		
	}
	
	public void initialize() {	
		DrawableShape wall = DrawableShape.createRectangle(120, 1, .5, 2);
		wall.color = Color.blue;
		double rpmrange = control.getDouble("highest rpm") - control.getDouble("lowest rpm");
		double deltarpm = (int) (rpmrange/control.getInt("ball number"));
		
		
		for (int i = 0; i < control.getInt("ball number"); i++) {
			particles.add(new Ball());
			particles.get(i).initialize(frame, control.getInt("ball radius"), control.getDouble("angle"), control.getDouble("velocity"), -9.8, control.getDouble("lowest rpm") + i*deltarpm);
			trails.add(new Trail());
			particles.get(i).c.color = Color.BLUE;
		}
		frame.addDrawable(wall);
		frame.setVisible(true);
		
		
	}
	
	public static void main(String[] args) {
		SimulationControl.createApp(new ProjectileLaunch());
		
		
	}
	
	

}
