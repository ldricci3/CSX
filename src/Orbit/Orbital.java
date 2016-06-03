package Orbit;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

import org.opensourcephysics.controls.*;  //needed for the simulation
import org.opensourcephysics.display.*;   //needed to use Circles
import org.opensourcephysics.frames.*;   //needed to use a DisplayFrame

public class Orbital extends AbstractSimulation {

	Circle c = new Circle();
	DisplayFrame space = new DisplayFrame("x", "y", "Frame");  //two object which are created outside of any method so all methods can access them
	ArrayList<Planet> planets = new ArrayList<Planet>();
	ArrayList<Trail> trails = new ArrayList<Trail>();
	Random gen = new Random();
	Trail triangle = new Trail();


	/**
	 * Orbit Stuff
	 */
	double distance;
	double angle;
	double force;
	double forcex;
	double forcey;
	double totalforcex;
	double totalforcey;
	double xacceleration;
	double yacceleration;
	int x = 0;
	double G = (6.67)*Math.pow(10, -11);
	int time=100000;
	int steps;

	/**
	 * Kepler's First Law
	 */
	double highestx = 0;
	double highesty = 0;
	double lowestx = 0;
	double lowesty = 0;
	double ellipsecenterx;
	double ellipsecentery;
	double foci1x;
	double foci1y;
	double foci2x;
	double foci2y;
	Circle foci1 = new Circle();
	Circle foci2 = new Circle();

	/**
	 * Kepler's Second Law
	 */
	double point1x;
	double point1y;
	double point2x;
	double point2y;
	double trianglearea;
	double x1;
	double y1;
	double x2;
	double y2;
	double x3;
	double y3;
	int historysteps = 0;
	
	/**
	 * Kepler's Third Law
	 */
	double initialangle;
	double periodsteps;

	/**
	 * BONUS - Creation of the Solar System
	 */
	boolean collision;
	int collisionplanet;
	int deleting;

	//3E10

	protected void doStep() {
		for (int i = 0; i < planets.size(); i++) {
			collision = false;
			planets.get(i).totalforcex=0;
			planets.get(i).totalforcey=0;
			planets.get(i).prevxpos=planets.get(i).xpos;
			planets.get(i).prevypos=planets.get(i).ypos;

			for (int k = 0; k < planets.size(); k++) {
				if (k != i) {
					//reset force calculation
					planets.get(i).force=0;
					planets.get(i).forcex=0;
					planets.get(i).forcey=0;
					planets.get(i).distance=0;
					planets.get(i).angle=0;
					//find the distance and angle between planets
					planets.get(i).distance = Math.sqrt((planets.get(k). xpos-planets.get(i).xpos)*(planets.get(k).xpos-planets.get(i).xpos) + (planets.get(k).ypos-planets.get(i).ypos)*(planets.get(k).ypos-planets.get(i).ypos));
					planets.get(i).angle = Math.atan(Math.abs(planets.get(k).ypos-planets.get(i).ypos)/Math.abs(planets.get(k).xpos-planets.get(i).xpos));
					if (planets.get(i).distance < Math.pow(10, 10)) {
						collision = true;
						collisionplanet=k;
						//TO MAKE SURE THE PLANETS COLLIDING DO NOT PUT HIGH GRAVITATIONAL FORCES ON EACH OTHER
						planets.get(i).distance = 10E20;
					}
					//adjust for the correct 360 angle 
					if ((planets.get(k).ypos-planets.get(i).ypos) > 0 && (planets.get(k).xpos-planets.get(i).xpos) > 0) {
						planets.get(i).angle = planets.get(i).angle;
					} else if ((planets.get(k).ypos-planets.get(i).ypos) > 0 && (planets.get(k).xpos-planets.get(i).xpos) <= 0) {
						planets.get(i).angle = Math.PI - planets.get(i).angle;
					} else if ((planets.get(k).ypos-planets.get(i).ypos) <= 0 && (planets.get(k).xpos-planets.get(i).xpos) > 0) {
						planets.get(i).angle = -planets.get(i).angle;
					} else if ((planets.get(k).ypos-planets.get(i).ypos) <= 0 && (planets.get(k).xpos-planets.get(i).xpos) <= 0) {
						planets.get(i).angle = planets.get(i).angle - Math.PI;
					}
					//calculate the force of gravity based on distance, masses and G constant
					planets.get(i).force=G*(planets.get(i).mass*planets.get(k).mass)/(planets.get(i).distance*planets.get(i).distance);
//					calculate the force x and y by multiplying by cos and sin of the angle
					planets.get(i).forcex=planets.get(i).force*Math.cos(planets.get(i).angle);
					planets.get(i).forcey=planets.get(i).force*Math.sin(planets.get(i).angle);
					if (Double.isNaN(planets.get(i).forcex)) {
						planets.get(i).forcex=0;
					}
					if (Double.isNaN(planets.get(i).forcey)) {
						planets.get(i).forcey=0;
					}
					//add the force due to the current planet to the net force or gravity from all the planets
					planets.get(i).totalforcex+=planets.get(i).forcex;
					planets.get(i).totalforcey+=planets.get(i).forcey;
					
					if (Double.isNaN(planets.get(i).xpos) == true) {
						System.out.println("NaN " + i);
					}
					
//					System.out.println(planets.get(i).distance + " distance " + i);
				}
			}

			/**
			 * Where the planet should move
			 */

			//change the acceleration based on totalforce
			planets.get(i).xacceleration=planets.get(i).totalforcex/planets.get(i).mass;
			planets.get(i).yacceleration=planets.get(i).totalforcey/planets.get(i).mass;
			//change the velocity base on x/yacceleration
			planets.get(i).velox+=planets.get(i).xacceleration*time;
			planets.get(i).veloy+=planets.get(i).yacceleration*time;
			/**
			 * PLANETARY COLLISION
			 */
			if (collision == true && planets.get(i).mass>0 && planets.get(collisionplanet).mass>0) {
				if (planets.get(i).mass>planets.get(collisionplanet).mass) {
					System.out.println( i + " " + collisionplanet + " remove " + collisionplanet + " planet " + i + " mass: " + planets.get(i).mass + " planet " + collisionplanet + " mass: " + planets.get(collisionplanet).mass);
					//inelastic collision formula
					planets.get(i).velox = (planets.get(i).velox*planets.get(i).mass + planets.get(collisionplanet).velox*planets.get(collisionplanet).mass)/(planets.get(i).mass + planets.get(collisionplanet).mass);
					planets.get(i).veloy = (planets.get(i).veloy*planets.get(i).mass + planets.get(collisionplanet).veloy*planets.get(collisionplanet).mass)/(planets.get(i).mass + planets.get(collisionplanet).mass);
					//remove trail
					space.removeDrawable(trails.get(collisionplanet));
					//combine masses
					planets.get(i).mass=planets.get(collisionplanet).mass + planets.get(i).mass;
					//increase radius
//					planets.get(i).c.pixRadius=(int) Math.round(Math.cbrt(Math.pow(planets.get(i).c.pixRadius, 3) + Math.pow(planets.get(collisionplanet).c.pixRadius, 3)));
					//remove smaller planet
					planets.get(collisionplanet).mass=0;
					space.removeDrawable(planets.get(collisionplanet).c);
					System.out.println(planets.get(collisionplanet).xpos + " " + planets.get(collisionplanet).xpos);
					System.out.println(planets.get(i).velox + " i " + planets.get(collisionplanet).velox + " collisionplanet");
				} else if (planets.get(i).mass>0 && planets.get(collisionplanet).mass>0) {
					System.out.println( i + " " + collisionplanet + " remove " + i + " planet " + i + " mass: " + planets.get(i).mass + " planet " + collisionplanet + " mass: " + planets.get(collisionplanet).mass);
					//inelastic collision formula
					planets.get(collisionplanet).velox = (planets.get(collisionplanet).velox*planets.get(collisionplanet).mass + planets.get(i).velox*planets.get(i).mass)/(planets.get(collisionplanet).mass + planets.get(i).mass);
					planets.get(collisionplanet).veloy = (planets.get(collisionplanet).veloy*planets.get(collisionplanet).mass + planets.get(i).veloy*planets.get(i).mass)/(planets.get(collisionplanet).mass + planets.get(i).mass);
					//remove trail
					space.removeDrawable(trails.get(i));
					//combine masses
					planets.get(collisionplanet).mass=planets.get(i).mass + planets.get(collisionplanet).mass;
					//increase radius
//					planets.get(collisionplanet).c.pixRadius=(int) Math.round(Math.cbrt(Math.pow(planets.get(collisionplanet).c.pixRadius, 3) + Math.pow(planets.get(i).c.pixRadius, 3)));
					//remove smaller planet
					planets.get(i).mass=0;
					space.removeDrawable(planets.get(i).c);
					System.out.println(planets.get(i).xpos + " " + planets.get(i).xpos);
					System.out.println(planets.get(i).velox + " " + i + " " + planets.get(collisionplanet).velox + " " + collisionplanet);
				}

			}


			//change the x/y cords based on the velox and veloy
			planets.get(i).xpos+=planets.get(i).velox*time + (1/2)*planets.get(i).xacceleration*time*time;
			planets.get(i).ypos+=planets.get(i).veloy*time + (1/2)*planets.get(i).yacceleration*time*time;
			
			//add to the x/y history of planet
			if (Double.isNaN(planets.get(i).xpos)==false) {
				planets.get(i).xhistory.add(planets.get(i).xpos);
			}
			if (Double.isNaN(planets.get(i).ypos)==false) {
				planets.get(i).yhistory.add(planets.get(i).ypos);
			}
			
			//If planet x/y is NaN, set x/y to last non-NaN coordinate
			if (Double.isNaN(planets.get(i).xpos)==true || Double.isNaN(planets.get(i).ypos)==true) {
				for (int j = planets.get(i).xhistory.size()-1; j > 0 ; j--) {
					if (Double.isNaN(planets.get(i).xhistory.get(j)) == false) {
						planets.get(i).xpos=planets.get(i).xhistory.get(j);
						break;
					}
				}
				for (int j = planets.get(i).yhistory.size()-1; j > 0 ; j--) {
					if (Double.isNaN(planets.get(i).yhistory.get(j)) == false) {
						planets.get(i).ypos=planets.get(i).yhistory.get(j);
						break;
					}
				}
			}
			
			planets.get(i).c.setXY(planets.get(i).xpos, planets.get(i).ypos);
			if (control.getInt("Trails") == 1) {
				trails.get(i).addPoint(planets.get(i).xpos, planets.get(i).ypos);
			}

			if (control.getDouble("Kepler Law") == 1 && i==0 && planets.size()==2) {
				/**
				 * Kepler's First Law
				 */

				if (planets.get(0).xpos>highestx) {
					highestx=planets.get(0).xpos;
					System.out.println("change");
				}
				if (planets.get(0).ypos>highesty) {
					highesty=planets.get(0).ypos;
					System.out.println("change");
				}
				if (planets.get(0).xpos<lowestx) {
					lowestx=planets.get(0).xpos;
					System.out.println("change");
				}
				if (planets.get(0).ypos<lowesty) {
					lowesty=planets.get(i).ypos;
					System.out.println("change");
				}
				System.out.println();
				ellipsecenterx = (highestx+lowestx)/2;
				ellipsecentery = (highesty+lowesty)/2;

				/**
				 * Ellipse Equation
				 * 
				 * 				(x-ellipsecenterx)^2
				 * ---------------------------------------------      + same except replace x with y = 1;
				 * 		| Math.abs(lowestx)+Math.abs(highestx) |^2
				 * 		|	---------------------------        | 
				 * 		|				2                      |
				 */
				
				System.out.println((Math.pow((planets.get(0).xpos-ellipsecenterx), 2)/Math.pow((highestx-lowestx)/2, 2))  +  (Math.pow((planets.get(0).ypos-ellipsecentery), 2)/Math.pow((highesty-lowesty)/2, 2)) );

			}
			if (control.getInt("Kepler Law")==2 && i==0) {
				historysteps++;
				/**
				 * Kepler's Second Law
				 */

				space.removeDrawable(triangle);
				triangle.clear();

				if (historysteps>10) {
					triangle.addPoint(planets.get(0).xhistory.get(planets.get(0).xhistory.size()-10), planets.get(0).yhistory.get(planets.get(0).yhistory.size()-10));
					triangle.addPoint(planets.get(1).xpos, planets.get(1).ypos);
					triangle.addPoint(planets.get(0).xpos, planets.get(0).ypos);

					space.addDrawable(triangle);

					x1 = planets.get(0).xhistory.get(planets.get(0).xhistory.size()-10);
					y1 = planets.get(0).yhistory.get(planets.get(0).yhistory.size()-10);
					x2 = planets.get(1).xpos;
					y2 = planets.get(1).ypos;
					x3 = planets.get(0).xpos;
					y3 = planets.get(0).ypos;

					trianglearea = Math.abs(  (x1*(y2-y3) + x2*(y3-y1) + x3*(y1-y2))/2  );


					System.out.println(trianglearea);
				}

			}
			if (control.getInt("Kepler Law") == 3 && i == 0) {
				
				if (planets.get(0).xpos>highestx) {
					highestx=planets.get(0).xpos;
				}
				if (planets.get(0).ypos>highesty) {
					highesty=planets.get(0).ypos;
				}
				if (planets.get(0).xpos<lowestx) {
					lowestx=planets.get(0).xpos;
				}
				if (planets.get(0).ypos<lowesty) {
					lowesty=planets.get(i).ypos;
				}
				
				if (steps==0) {
					initialangle=planets.get(0).angle;
				}
				System.out.println(Math.round(planets.get(0).angle*57.2958));
				if (steps>1 && Math.round(planets.get(0).angle*57.2958)==Math.round(initialangle*57.2958)) {
					periodsteps=steps;
					if (highestx-lowestx>highesty-lowesty) {
						System.out.println(Math.pow(periodsteps*time, 2)/Math.pow((highestx-lowestx)/2, 3) + " = " + (4*Math.pow(Math.PI, 2))/(G*planets.get(1).mass));
					} else {
						System.out.println(Math.pow(periodsteps*time, 2)/Math.pow((highesty-lowesty)/2, 3) + " = " + (4*Math.pow(Math.PI, 2))/(G*planets.get(1).mass));
					}
				}
			}
		}
		/**
		 * MOVING THE PLANETS TO WHERE THEY SHOULD BE
		 * 
		 */
		for (int i = 0; i < planets.size(); i++) {
			planets.get(i).c.setXY(planets.get(i).xpos, planets.get(i).ypos);
			if (control.getInt("Trails") == 1) {
				trails.get(i).addPoint(planets.get(i).xpos, planets.get(i).ypos);
			} else {
				trails.get(i).clear();
			}
			space.addDrawable(trails.get(i));
		}
		/**
		 * DELETING COLLIDED PLANETS
		 * 
		 */
		for (int i = 0; i < planets.size(); i++) {
			if (planets.get(i).mass==0) {
				System.out.println("delete " + i);
				planets.remove(i);
				trails.remove(i);
			}
		}
		steps++;
	}


	public void reset() {
		control.setValue("smass", 1.989*Math.pow(10, 30));
		control.setValue("emass", 5.972*Math.pow(10, 24));
		control.setValue("sradius", 6.9*Math.pow(10, 8));
		control.setValue("eradius", 6*Math.pow(10, 6));
		control.setValue("svelox", 0);
		control.setValue("sveloy", 0);
		control.setValue("sx", 0);
		control.setValue("sy", 0);
		control.setValue("evelox", 0);
		control.setValue("eveloy", 30000);
		control.setValue("ex", 1.496E11);
		control.setValue("ey", 0);
		control.setValue("time", 100000);
		control.setValue("planet count", 2);
		control.setAdjustableValue("Kepler Law", 0);
		control.setAdjustableValue("Trails", 0);


		planets.clear();
	}

	public void initialize() {
		if (control.getInt("planet count")==2) {
			planets.add(new Planet());
			planets.get(0).create(control.getDouble("emass"), control.getDouble("ex"), control.getDouble("ey"), control.getDouble("evelox"), control.getDouble("eveloy"), 0, 10, planets.size());
			planets.get(0).c.color = Color.BLUE;
			trails.add(new Trail());
			space.addDrawable(planets.get(0).c);
			planets.add(new Planet());
			planets.get(1).create(control.getDouble("smass"), control.getDouble("sx"), control.getDouble("sy"), control.getDouble("svelox"), control.getDouble("sveloy"), 1, 15, planets.size());
			planets.get(1).c.color = Color.RED;
			trails.add(new Trail());
			space.addDrawable(planets.get(1).c);
			
			space.setPreferredMinMax(-2*Math.pow(10, 11), 2*Math.pow(10, 11), -2*Math.pow(10, 11), 2*Math.pow(10, 11));
		} else {
			planets.add(new Planet());
			planets.get(0).create(control.getDouble("smass"), control.getDouble("sx"), control.getDouble("sy"), control.getDouble("svelox"), control.getDouble("sveloy"), 1, 15, planets.size());
			planets.get(0).c.color = Color.RED;
			space.addDrawable(planets.get(0).c);
			trails.add(new Trail());
			for (int i=1; i < control.getInt("planet count")+1; i++) {
				//cp=create-planet
				double cpmass = (gen.nextInt(100)+1)*Math.pow(10, 26);
				double cpxpos = ((gen.nextDouble()*4)-2)*Math.pow(10, 11);
				double cpypos = ((gen.nextDouble()*4)-2)*Math.pow(10, 11);
				double cpvx = ((gen.nextDouble()*2)-1)*40000;
				double cpvy = ((gen.nextDouble()*2)-1)*40000;
				int cpradius = 4 + gen.nextInt(5);
				
				//create net initial rotational velocity
				if (cpxpos > 0) {
					if (cpypos > 0) {
//						cpvx = -Math.abs(cpvx);
//						cpvy = cpvy/2;
						cpvx = cpvx - 20000;
					} else {
//						cpvx = cpvx/2;
//						cpvy = Math.abs(cpvy);
						cpvy = cpvy + 20000;
					}
				} else if (cpxpos < 0) {
					if (cpypos>0) {
//						cpvx = cpvx/2;
//						cpvy = -Math.abs(cpvy);
						cpvy = cpvy - 20000;
					} else {
//						cpvx = Math.abs(cpvx);
//						cpvy = cpvy/2;
						cpvx = cpvx - 20000;
					}
				}
				
				planets.add(new Planet());
				planets.get(i).create(cpmass/2, cpxpos, cpypos, cpvx, cpvy, 1, cpradius, control.getInt("planet count"));
				planets.get(i).c.color=Color.blue;
				space.addDrawable(planets.get(i).c);
				trails.add(new Trail());
				
			}
			space.setPreferredMinMax(-2*Math.pow(10, 11), 2*Math.pow(10, 11), -2*Math.pow(10, 11), 2*Math.pow(10, 11));
		}
		
		space.setVisible(true);
		space.setDefaultCloseOperation(3);
	}

	public static void main(String[] args) {

		SimulationControl.createApp(new Orbital());
	}

}