
import java.io.IOException;

import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.TiledLayer;
import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;


public class Main extends MIDlet implements CommandListener {

	public static MyGameCanvas mycanvas;

	public static GameThread thread;



	
	
	public void switchDisplayable(Alert alert, Displayable nextDisplayable) {// GEN-END:|5-switchDisplayable|0|5-preSwitch
		// write pre-switch user code here
		Display display = getDisplay();// GEN-BEGIN:|5-switchDisplayable|1|5-postSwitch
		if (alert == null) {
			display.setCurrent(nextDisplayable);
		} else {
			display.setCurrent(alert, nextDisplayable);
		}// GEN-END:|5-switchDisplayable|1|5-postSwitch
			// write post-switch user code here
	}


	public Display getDisplay() {
		return Display.getDisplay(this);
	}

	/**
	 * Called when MIDlet is started. Checks whether the MIDlet have been
	 * already started and initialize/starts or resumes the MIDlet.
	 */
	public void startApp() throws MIDletStateChangeException {
		menu men = new menu(this);
        Display.getDisplay(this).setCurrent(men);
		//mycanvas.setCommandListener(this);

	
	}


		public void pauseApp() {
		
	}

	
	public void destroyApp(boolean unconditional)
			throws MIDletStateChangeException {
		if (thread != null) {
			thread.requestStop();
		}
		thread = null;
		mycanvas = null;
		System.gc();
	}
	
	
	
	public void start()throws MIDletStateChangeException{
		
		new Maps();
		try {
			mycanvas = new MyGameCanvas(this);
			Display.getDisplay(this).setCurrent(mycanvas);
			thread = new GameThread(mycanvas);
			thread.start();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}


	public void commandAction(Command arg0, Displayable arg1) {
		// TODO Auto-generated method stub
		
	}
}


