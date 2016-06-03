package SpringAssignment;

import java.awt.Color;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

import org.opensourcephysics.controls.AbstractSimulation;
import org.opensourcephysics.controls.SimulationControl;
import org.opensourcephysics.frames.PlotFrame;

public class Wave extends AbstractSimulation {

	PlotFrame frame = new PlotFrame("x", "y", "Wave");
	ArrayList<SpringParticle> particles = new ArrayList<SpringParticle>();
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
	
	double restlength;
	double restlengthx;
	double restlengthy;
	double t = .005;
	
	double frequency;
	double amplitude;
	boolean up;
	double time = 0;
	
	boolean pulse = false;
	
	@Override
	protected void doStep() {
		// TODO Auto-generated method stub
		
		if (control.getBoolean("Pulse") == true) {
			if (time<(1/frequency)) {
				particles.get(particles.size()-1).ypos = amplitude*Math.sin(time*2*Math.PI*frequency);
				particles.get(0).ypos = amplitude*Math.sin(time*2*Math.PI*frequency);
			}
		} else if (control.getBoolean("Pulse") == false) {
			particles.get(particles.size()-1).ypos = amplitude*Math.sin(time*2*Math.PI*frequency);
//			particles.get(0).ypos = -amplitude*Math.sin(time*frequency*(2*Math.PI));
		}
		
		
//		if (particles.get(particles.size()-1).ypos>=amplitude) {
//			up = false;
//		}
//		if (particles.get(particles.size()-1).ypos<=-amplitude) {
//			up = true;
//		}
//		if (up == true) {
//			particles.get(particles.size()-1).ypos+=4*amplitude*frequency*t;
//		} else {
//			particles.get(particles.size()-1).ypos-=4*amplitude*frequency*t;
//		}
		
		for (int i = 1; i < particlecount-1; i++) {
			if (i==particles.size()-1) {
				
				particles.get(i).angle = Math.atan(Math.abs(particles.get(i-1).ypos-particles.get(i).ypos)/Math.abs(particles.get(i-1).xpos-particles.get(i).xpos));
				
				restlengthx = restlength*Math.cos(particles.get(i).angle);
				restlengthy = restlength*Math.sin(particles.get(i).angle);
				
				deltay1 = Math.abs(particles.get(i).ypos)-Math.abs(particles.get(i-1).ypos);
				deltay1 += (Math.abs(deltay1)/deltay1)*(-restlengthy);
				
				
				deltax1 = Math.abs(particles.get(i).xpos)-Math.abs(particles.get(i-1).xpos);
				deltax1 += (Math.abs(deltax1)/deltax1)*(-restlengthx);
				
				
				particles.get(i).forcey = k*deltay1;
				particles.get(i).forcex = k*deltax1;
				
				particles.get(i).forcey = round(particles.get(i).forcey, 3);
				particles.get(i).forcex = round(particles.get(i).forcex, 3);
				
			} else {
				
				particles.get(i).angle = Math.atan(Math.abs(particles.get(i-1).ypos-particles.get(i).ypos)/Math.abs(particles.get(i-1).xpos-particles.get(i).xpos));
				
				restlengthx = restlength*Math.cos(particles.get(i).angle);
				restlengthy = restlength*Math.sin(particles.get(i).angle);
				
				deltay1 = particles.get(i-1).ypos-particles.get(i).ypos;
				if (deltay1!=0) {
					deltay1 += (Math.abs(deltay1)/deltay1)*(restlengthy);
				}
				
				deltax1 = particles.get(i-1).xpos-particles.get(i).xpos;
				if (deltax1!=0) {
					deltax1 += (Math.abs(deltax1)/deltax1)*(restlengthx);
				}
				
				
				particles.get(i).angle = Math.atan(Math.abs(particles.get(i+1).ypos-particles.get(i).ypos)/Math.abs(particles.get(i+1).xpos-particles.get(i).xpos));
				
				restlengthx = restlength*Math.cos(particles.get(i).angle);
				restlengthy = restlength*Math.sin(particles.get(i).angle);
				
				deltay2 = particles.get(i).ypos-particles.get(i+1).ypos;
				if (deltay2!=0) {
					deltay2 += (Math.abs(deltay2)/deltay2)*(restlengthy);
				}
				
				
				deltax2 = particles.get(i).xpos-particles.get(i+1).xpos;
				if (deltax2!=0) {
					deltax2 += (Math.abs(deltax2)/deltax2)*(restlengthx);
				}
				
				
				
				particles.get(i).forcey= deltay1*k + -deltay2*k;
				particles.get(i).forcex= deltax1*k + -deltax2*k;
				//System.out.println(deltay1 + " " + deltay2 + " " + i);
				particles.get(i).forcey = round(particles.get(i).forcey, 3);
				particles.get(i).forcex = round(particles.get(i).forcex, 3);
			}


		}
		System.out.println(particles.get(1).forcey + " first");
		System.out.println(particles.get(particles.size()-2).forcey + " last");
		for (int i = 1; i < particles.size(); i++) {

			particles.get(i).yacceleration=particles.get(i).forcey/particles.get(i).mass;
			particles.get(i).xacceleration=particles.get(i).forcex/particles.get(i).mass;

			particles.get(i).veloy += particles.get(i).yacceleration*t;
			particles.get(i).velox += particles.get(i).xacceleration*t;
			
			particles.get(i).ypos += particles.get(i).veloy*t;
			particles.get(i).xpos += particles.get(i).velox*t;

			particles.get(i).c.setXY(particles.get(i).xpos, particles.get(i).ypos);
		}
		time+=t;
	}



	public void reset() {
		control.setValue("Spring Length (m)", 100);
		control.setValue("Particles per M", 1);
		control.setValue("Spring Constant", 4000);
		control.setValue("Particle Mass", .4);
		control.setValue("Wave Amplitude", 20);
		control.setValue("Wave Frequency", 1);
		control.setValue("Pulse", false);
		time=0;
		frame.clearDrawables();
		particles.clear();
	}

	public void initialize() {
		this.delayTime = 10;
		length = control.getInt("Spring Length (m)");
		particlecount = (int) (length*control.getInt("Particles per M"));
		k = control.getDouble("Spring Constant");
		pmass = control.getDouble("Particle Mass");
		
		restlength = length/(10*particlecount);
		
		double mperpart = length/particlecount;
		
		up = true;
		amplitude = control.getDouble("Wave Amplitude");
		frequency = control.getDouble("Wave Frequency");
		
		for (int i = 0; i < particlecount; i++) {
			particles.add(new SpringParticle());
			particles.get(i).create(-length/2 + i*mperpart, 0, 0, 0, pmass);
			particles.get(i).c.pixRadius = 2;
			if (i%2==0) {
				particles.get(i).c.color = Color.green;
			} else {
				particles.get(i).c.color = Color.orange;
			}
			frame.addDrawable(particles.get(i).c);
		}
		frame.setPreferredMinMax(-50, 50, -50, 50);
		frame.setVisible(true);
		
	}

	public static void main(String[] args) {
		SimulationControl.createApp(new Wave());
	}
	
	public static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    BigDecimal bd = new BigDecimal(value);
	    bd = bd.setScale(places, RoundingMode.HALF_UP);
	    return bd.doubleValue();
	}
}
