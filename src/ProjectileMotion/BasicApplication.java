package ProjectileMotion;

import org.opensourcephysics.controls.AbstractAnimation;
import org.opensourcephysics.controls.SimulationControl;
import org.opensourcephysics.controls.*;

public class BasicApplication extends AbstractAnimation {
	
	int x;
	
	@Override
	protected void doStep() {
		
		control.println("X = " + x);
		x++;
		
	}
	
	public void reset() {
		System.out.println("inside...  reset");
		control.setValue("x", 50);
		
	}
	
	public void initialize() {
		System.out.println("inside... initialize");
		//int z = control.getInt("x");
		control.setValue("x", 50);
		
	}
	
	
	public static void main(String[] args) {
		
		SimulationControl.createApp(new BasicApplication());
		System.out.println("inside...");
	}

}
