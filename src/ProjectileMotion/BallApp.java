package ProjectileMotion;

import org.opensourcephysics.controls.*;
import org.opensourcephysics.display.*;
import org.opensourcephysics.frames.*;

public class BallApp extends AbstractAnimation {
	
	Circle c = new Circle();
	
	DisplayFrame frame = new DisplayFrame("x", "y", "Display Frame Test");

	@Override
	protected void doStep() {
		
		c.setXY(c.getX() + .1, c.getY() + .1);

	}
	
	
	public void reset() {
		
		control.setValue("x", 0);
		control.setValue("y", 0);
	}

	public void initialize() {
		
		c.setXY(control.getDouble("x"), control.getDouble("y"));
		frame.setVisible(true);
		frame.addDrawable(c);
	}
	
	public static void main(String[] args) {
		
		SimulationControl.createApp(new BallApp());
		

	}

}
