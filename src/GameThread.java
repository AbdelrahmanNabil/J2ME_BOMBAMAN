import java.util.Date;
import java.util.TimeZone;

public class GameThread extends Thread {
	MyGameCanvas canvas;
	boolean pause=false;
	public GameThread(MyGameCanvas _canvas) throws Exception {
		canvas = _canvas;
	}

	public void run() {
		while (!pause) {
			try {
				canvas.flushKey();
				canvas.checkKey();
				canvas.render();
				sleep(45);
			} catch (Exception ex) {

			}
		}
	}

	/**
	 * stops the game.
	 */
	synchronized void requestStop() {
		notify();
	}
}
