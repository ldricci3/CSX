package SpringAssignment;

import java.awt.Color;
import java.util.ArrayList;

import org.opensourcephysics.controls.AbstractSimulation;
import org.opensourcephysics.controls.SimulationControl;
import org.opensourcephysics.frames.PlotFrame;

import org.opensourcephysics.controls.*;  //needed for the simulation


public class Spring extends AbstractSimulation {

	PlotFrame frame = new PlotFrame("x", "y", "Spring");
	double length;
	int particlecount;
	double k;
	double omass;
	double pmass;
	double springsize;

	double deltax1;
	double deltay1;
	double deltax2;
	double deltay2;
	double forceabove;
	double forcebelow;

	double restlength;
	double t = .004;

	ArrayList<SpringParticle> particles = new ArrayList<SpringParticle>();


	protected void doStep() {
		for (int i = 1; i < particles.size(); i++) {
			if (i==particles.size()-1) {
				deltay1 = Math.abs(particles.get(i).ypos)-Math.abs(particles.get(i-1).ypos);
				deltay1 += (Math.abs(deltay1)/deltay1)*(-restlength);
				particles.get(i).forcey = -9.8*particles.get(i).mass + k*deltay1;
			} else {
				deltay1 = particles.get(i-1).ypos-particles.get(i).ypos;
				deltay1 += (Math.abs(deltay1)/deltay1)*(restlength);
				
				deltay2 = particles.get(i).ypos-particles.get(i+1).ypos;
				deltay2 += (Math.abs(deltay2)/deltay2)*(restlength);
				
				forceabove = deltay1*k;
				forcebelow = -deltay2*k;
				
				particles.get(i).forcey= -9.8*particles.get(i).mass + forceabove + forcebelow;
				System.out.println(deltay1 + " " + i);
				System.out.println(deltay2 + " " + i);
			}


		}
		for (int i = 1; i < particles.size(); i++) {

			particles.get(i).yacceleration=particles.get(i).forcey/particles.get(i).mass;

			particles.get(i).veloy += particles.get(i).yacceleration*t;
			
			particles.get(i).ypos += particles.get(i).veloy*t;

			particles.get(i).c.setXY(particles.get(i).xpos, particles.get(i).ypos);
		}
	}

	public void reset() {
		control.setValue("Spring Length (m)", 20);
		control.setValue("Particles per M", 10);
		control.setValue("Spring Constant", 4000);
		control.setValue("Object Mass", 60);
		control.setValue("Particle Mass", .4);
	}

	public void initialize() {
		this.delayTime = 10;
		length = control.getInt("Spring Length (m)");
		particlecount = (int) (length*control.getInt("Particles per M"));
		k = control.getDouble("Spring Constant");
		omass = control.getDouble("Object Mass");
		pmass = control.getDouble("Particle Mass");
		restlength = length/particlecount;

		for (int i = 0; i < particlecount; i++) {
			particles.add(new SpringParticle());
			particles.get(i).create(0, -.2*i, 0, 0, pmass);
			particles.get(i).c.pixRadius = 2;
			frame.addDrawable(particles.get(i).c);
			if (i%2==0) {
				particles.get(i).c.color = Color.green;
			} else {
				particles.get(i).c.color = Color.orange;
			}
		}
		particles.get(particles.size()-1).mass = control.getDouble("Object Mass");
		particles.get(particles.size()-1).c.pixRadius = 5;
		particles.get(particles.size()-1).c.color=Color.red;

		frame.setPreferredMinMax(-10, 10, -120, 10);
		frame.setVisible(true);

		springsize=length/particlecount;

	}


	public static void main(String[] args) {
		SimulationControl.createApp(new Spring());

	}

}