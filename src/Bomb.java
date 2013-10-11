/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.Sprite;

public class Bomb extends Sprite {
	int bx;
	int by;
	int bbx;
	int bby;
	boolean isexp = false;
	int[] FRAME_SEQUENCE = { 0, 1 };// sequance of frame
	int[] explode_SEQUENCE = { 0, 1 };// sequance of frame
	boolean isActive;// if bomb active
	int iCountDown = 120;// count down befre bomb explo
	int counter = 0;

	public Bomb() throws Exception {
		super(Image.createImage("/Bomb.png"), Maps.icell, Maps.icell);
		setFrameSequence(FRAME_SEQUENCE);
		defineCollisionRectangle(0, 0, Maps.icell - 3, Maps.icell - 3);
		setFrame(1);
		setVisible(false);
		isActive = false;
	}

	public void Active() {
		isActive = true;
		iCountDown = 100;
		setFrame(1);
		setVisible(true);
	}

	public void Animate(MyNumbers num, int[][] w, Exploed e, Bomber b, Ghost[] g) {
		if (isActive) {
			iCountDown--;
			if (iCountDown == 0) {
				// Explore code here
				isActive = false;
				setVisible(false);
				e.expl(w, bx, by);
				counter = 10;
				bbx = bx;
				bby = by;
				isexp = true;
			}
			nextFrame();
			// if(getFrame() == 2)
			// setFrame(1);
		} else {
			setFrame(1);
		}
		if (counter > 0) {
			counter--;
			// System.out.println(counter);
			for (int i = 0; i < Maps.numofghost[Maps.levelnum]; i++) {
				e.check(g[i], bbx, bby);
			}
			if (counter == 0) {
				e.check(b, bbx, bby);
				e.removeexp(num, w, bbx, bby);
				Maps.bombs++;
				isexp = false;
			}
		}

	}
}
