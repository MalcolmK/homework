package edu.mccc.cos210.leddisplay.transition;
import edu.mccc.cos210.leddisplay.*;
import com.cbthinkx.util.Debug;
public class Transition2 extends LEDDisplayTransition {
	public void go(LEDDisplayView view, LEDDisplayView.LED[][] leds) {
		Debug.println("Transition2.go()");
		try {
			Thread.sleep(1000);
		} catch (Exception ex) {
		}
	}
}
