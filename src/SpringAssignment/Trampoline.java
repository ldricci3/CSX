package SpringAssignment;

import java.awt.Color;
import java.util.ArrayList;

import org.opensourcephysics.controls.AbstractSimulation;
import org.opensourcephysics.controls.SimulationControl;
import org.opensourcephysics.frames.PlotFrame;

public class Trampoline extends AbstractSimulation {

	PlotFrame frame = new PlotFrame("x", "y", "Wave");
	ArrayList<SpringParticle> particles = new ArrayList<SpringParticle>();
	Particle object = new Particle();
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

	boolean touching = false;
	double distance;
	double mindistance;

	int touchingparticle;

	@Override
	protected void doStep() {
		// TODO Auto-generated method stub

		/**
		 * Resets for the next do-step
		 * assumes that the object is not touching the spring
		 * make "touching" false in all spring particles
		 */
		System.out.println(particles.get(51).ypos + "  y");
		System.out.println(particles.get(51).xpos + "  x");
		System.out.println();
		touching = false;
//		for (int i = 0; i < particles.size(); i++) {
//			particles.get(i).touching=false;
//		}

		/**
		 * Uses istouching() to check which particles are close enough to be touching
		 * if particles are touching then then the SpringParticle variable "touching" is set to true
		 */
		for (int i = 0; i < particles.size(); i++) {
			istouching(particles.get(i), object);
		}

		/**
		 * sets "mindistance" to the distance between the closest SpringParticle and the object
		 * sets all 
		 */
		mindistance = findmindistance();


		/**
		 * sets variable "touchingparticle" to the SpringParticle which the object is touching
		 */
		touchingparticle = -1;
		for (int i = 0; i < particles.size(); i++) {
			if (particles.get(i).touching == true) {
				touchingparticle = i;
				System.out.println(touchingparticle);
			}
		}
		
		/**
		 * calculate the forces on each spring particle
		 */
		springforces();

		/**
		 * if touching==false, move the object as if it is only under the force of gravity
		 */
		if (touching == false) {
			object.veloy += (-9.8)*t;
			object.xpos += object.velox*t;
			object.ypos += object.veloy*t + (-4.9)*t*t;
			object.c.setXY(object.xpos, object.ypos);
		}
		if (touching==true) {
			System.out.println("touching is true in do-step before 'moveparticles'");
			System.out.println(particles.get(touchingparticle).forcey);
		}
		

		/**
		 * Move the spring
		 * 
		 * if "touching" is true
		 * 
		 */
		moveparticles();


		time++;
	}



	public void reset() {
		control.setValue("Spring Length (m)", 100);
		control.setValue("Particles per M", 1);
		control.setValue("Spring Constant", 4000);
		control.setValue("Particle Mass", .4);
		control.setValue("Particle X", 0);
		control.setValue("Particle Y", 10);
		control.setValue("Object Mass", 10);
		time=0;
		frame.clearDrawables();
		particles.clear();
	}

	public void initialize() {
		this.delayTime = 1;
		length = control.getInt("Spring Length (m)");
		particlecount = (int) (length*control.getInt("Particles per M"));
		k = control.getDouble("Spring Constant");
		pmass = control.getDouble("Particle Mass");
		omass = control.getDouble("Object Mass");

		restlength = length/(10*particlecount);

		double mperpart = length/particlecount;


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

		object.create(control.getDouble("Particle X"), control.getDouble("Particle Y"), 0, 0, control.getDouble("Object Mass"));
		frame.addDrawable(object.c);

	}

	public static void main(String[] args) {
		SimulationControl.createApp(new Trampoline());
	}




	public void istouching(SpringParticle spring, Particle object) {
		spring.prevtouch=spring.touching;
		distance = Math.sqrt((spring.xpos-object.xpos)*(spring.xpos-object.xpos) + (spring.ypos-object.ypos)*(spring.ypos-object.ypos));
		if (distance < .05) {
			spring.touching = true;
			touching = true;
		} else {
			spring.touching = false;
		}
	}
	public double findmindistance() {
		double min = 10000;
		for (int i = 0; i < particles.size(); i++) {
			if (particles.get(i).touching == true) {
				if (Math.abs(particles.get(i).distance) < min) {
					min = Math.abs(particles.get(i).distance);
					for (int j = 0; j < i; j++) {
						particles.get(j).touching=false;
					}
				} else {
					particles.get(i).touching = false;
				}
			}
		}
		return min;
	}
	public void moveparticles() {
		for (int i = 1; i < particles.size(); i++) {

			if (particles.get(i).touching==true) {
				System.out.println(particles.get(i).prevtouch);
				if (particles.get(i).prevtouch == true) {
					
					System.out.println("touch " + i);
					particles.get(i).yacceleration=particles.get(i).forcey/(particles.get(i).mass+object.mass);
					particles.get(i).xacceleration=particles.get(i).forcex/(particles.get(i).mass+object.mass);
					
					object.yacceleration=particles.get(i).forcey/(particles.get(i).mass+object.mass);
					object.xacceleration=particles.get(i).forcex/(particles.get(i).mass+object.mass);


					particles.get(i).veloy += particles.get(i).yacceleration*t;
					particles.get(i).velox += particles.get(i).xacceleration*t;
					
					object.veloy += object.yacceleration*t;
					object.velox += object.xacceleration*t;

					particles.get(i).ypos += particles.get(i).veloy*t;
					particles.get(i).xpos += particles.get(i).velox*t;
					object.ypos += object.veloy*t;
					object.xpos += object.velox*t;

					particles.get(i).c.setXY(particles.get(i).xpos, particles.get(i).ypos);
					object.c.setXY(object.xpos, object.ypos);
				} else {
					System.out.println("first touch");
					collision(object, particles.get(i));
					
					particles.get(i).yacceleration=particles.get(i).forcey/(particles.get(i).mass+object.mass);
					particles.get(i).xacceleration=particles.get(i).forcex/(particles.get(i).mass+object.mass);
//					object.yacceleration=particles.get(i).forcey/(particles.get(i).mass+object.mass);
//					object.xacceleration=particles.get(i).forcex/(particles.get(i).mass+object.mass);


					particles.get(i).veloy += particles.get(i).yacceleration*t;
					particles.get(i).velox += particles.get(i).xacceleration*t;
//					object.veloy += object.yacceleration*t;
//					object.velox += object.xacceleration*t;

					particles.get(i).ypos += particles.get(i).veloy*t;
					particles.get(i).xpos += particles.get(i).velox*t;
					object.ypos += particles.get(i).veloy*t;
					object.xpos += particles.get(i).velox*t;
//					object.ypos += object.veloy*t;
//					object.xpos += object.velox*t;

					particles.get(i).c.setXY(particles.get(i).xpos, particles.get(i).ypos);
					
					object.c.setXY(object.xpos, object.ypos);
				}


			} else {
				particles.get(i).yacceleration=particles.get(i).forcey/particles.get(i).mass;
				particles.get(i).xacceleration=particles.get(i).forcex/particles.get(i).mass;

				particles.get(i).veloy += particles.get(i).yacceleration*t;
				particles.get(i).velox += particles.get(i).xacceleration*t;

				particles.get(i).ypos += particles.get(i).veloy*t;
				particles.get(i).xpos += particles.get(i).velox*t;

				particles.get(i).c.setXY(particles.get(i).xpos, particles.get(i).ypos);

			}

		}
	}
	public void collision(Particle object, SpringParticle spring) {
		spring.veloy = (spring.veloy*spring.mass+object.veloy*object.mass)/(object.mass+spring.mass);
		spring.velox = (spring.velox*spring.mass+object.velox*object.mass)/(object.mass+spring.mass);
		object.velox = (spring.veloy*spring.mass+object.veloy*object.mass)/(object.mass+spring.mass);
		object.veloy = (spring.veloy*spring.mass+object.veloy*object.mass)/(object.mass+spring.mass);
	}
	public void springforces() {
		for (int i = 1; i < particlecount-1; i++) {
			if (i==particles.size()-1) {

				particles.get(i).angle = Math.atan(Math.abs(particles.get(i-1).ypos-particles.get(i).ypos)/Math.abs(particles.get(i-1).xpos-particles.get(i).xpos));

				restlengthx = restlength*Math.cos(particles.get(i).angle);
				restlengthy = restlength*Math.sin(particles.get(i).angle);

				deltay1 = Math.abs(particles.get(i).ypos)-Math.abs(particles.get(i-1).ypos);
				deltay1 += (Math.abs(deltay1)/deltay1)*(-restlengthy);


				deltax1 = Math.abs(particles.get(i).xpos)-Math.abs(particles.get(i-1).xpos);
				deltax1 += (Math.abs(deltax1)/deltax1)*(-restlengthx);


				particles.get(i).forcey = -9.8*particles.get(i).mass + k*deltay1 - 9.8*particles.get(i).mass;
				particles.get(i).forcex = -9.8*particles.get(i).mass + k*deltax1;

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

				if (i == touchingparticle) {
					particles.get(i).forcey= -9.8*(particles.get(i).mass+object.mass) + deltay1*k + -deltay2*k - 9.8*particles.get(i).mass;
					particles.get(i).forcex= -9.8*(particles.get(i).mass+object.mass) + deltax1*k + -deltax2*k;
				}

				particles.get(i).forcey= -9.8*particles.get(i).mass + deltay1*k + -deltay2*k - 9.8*particles.get(i).mass;
				particles.get(i).forcex= -9.8*particles.get(i).mass + deltax1*k + -deltax2*k;

			}


		}
	}
}
