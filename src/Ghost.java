import java.io.IOException;

import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.Sprite;

public class Ghost extends Sprite {
	Point pos;
	boolean isdead = false;
	boolean isexp = false;
	int[] FRAME_SEQUENCE = { 0, 1, 2, 3 };
	int[] exp_SEQUENCE = { 0, 1, 2, 3, 4 };

	public Ghost(Point p) throws IOException {
		super(Image.createImage("/ghost.png"), Maps.icell, Maps.icell);
		defineReferencePixel(0, 0);
		pos = p;
		defineCollisionRectangle(0, 0, Maps.icell - 3, Maps.icell - 3);
		setRefPixelPosition(pos.x, pos.y);
		setFrameSequence(FRAME_SEQUENCE);
		setFrame(0);

		// TODO Auto-generated constructor stub
	}

	public void Animate() {
		if (isexp && getFrame() == 4) {
			isdead = true;
		}
		nextFrame();
	}

	public void Move() {
		setPosition(pos.x, pos.y);
	}

	public void exp() {
		try {
			setImage(Image.createImage("/ghostexp.png"), Maps.icell, Maps.icell);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		defineReferencePixel(0, 0);
		setRefPixelPosition(pos.x, pos.y);
		setFrameSequence(exp_SEQUENCE);
		setFrame(0);
		isexp = true;
	}

	public void active() {
		try {
			setImage(Image.createImage("/ghost.png"), Maps.icell, Maps.icell);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		defineReferencePixel(0, 0);
		setRefPixelPosition(pos.x, pos.y);
		setFrameSequence(FRAME_SEQUENCE);
		setFrame(0);
		isdead = false;
		isexp = false;
	}
}
