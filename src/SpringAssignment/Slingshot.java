package SpringAssignment;

import java.util.ArrayList;

import org.opensourcephysics.controls.AbstractSimulation;
import org.opensourcephysics.controls.SimulationControl;

public class Slingshot extends AbstractSimulation {
	
	Particle object = new Particle();
	ArrayList<SpringParticle> particles = new ArrayList<SpringParticle>();

	@Override
	protected void doStep() {
		// TODO Auto-generated method stub
		
		
		

	}
	public void reset()	{
		control.setValue("Particle Per Meter", 1);
		control.setValue("Spring Length", 100);
//		control.setValue("Spring Constant", );
//		control.setValue("Timestep", );
//		control.setValue("Dra", );
//		control.setValue("", );
//		control.setValue("", );
	}
	
	public void initialize() {
		
	}
	public static void main(String[] args) {
		SimulationControl.createApp(new Slingshot());
	}

}
