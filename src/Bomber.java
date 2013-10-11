/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;

import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.Sprite;

/**
 * 
 * @author Nguyen Tran Vu
 */
public class Bomber extends Sprite {
	Point bomber_pos;
	int[] frame_seq = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11 };
	int[] exp_seq = { 0, 1, 2, 3, 4, 5, 6 };
	boolean isExplore = false;
	boolean isDead = false;
	boolean isAnimated = false;
	final static int moveSpeed = 4;
	final static int startFrame = 2;

	public Bomber(Point bomberPos) throws IOException {
		super(Image.createImage("/bomber.png"), Maps.icell, Maps.icell);
		bomber_pos = bomberPos;
		defineReferencePixel(0, 0);
		defineCollisionRectangle(4, 0, Maps.icell - 4, Maps.icell - 4);
		setRefPixelPosition(bomber_pos.x, bomber_pos.y);
		setFrameSequence(frame_seq);
		setFrame(startFrame);
		// TODO Auto-generated constructor stub
	}

	public void explore() {
		try {
			setImage(Image.createImage("/explore.png"), Maps.icell, Maps.icell);
			System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
			defineReferencePixel(0, 0);
			setRefPixelPosition(bomber_pos.x, bomber_pos.y);
			setFrameSequence(exp_seq);
			setFrame(0);
			isExplore = true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out
					.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
			e.printStackTrace();
		}
	}

	public void active() {
		try {
			setImage(Image.createImage("/bomber.png"), Maps.icell, Maps.icell);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		defineReferencePixel(0, 0);
		setRefPixelPosition(bomber_pos.x, bomber_pos.y);
		setFrameSequence(frame_seq);
		setFrame(startFrame);
		isExplore = false;
	}

	public void Animate() {
		if (isExplore) {
			System.out.println("<<<<<<<<<<<<<<<<<<<<<njjnkjnk");
			if (getFrame() == 6) {
				active();
			}
			nextFrame();
		} else {
			if (isAnimated)
				nextFrame();
			else
				setFrame(startFrame);
		}
	}

	public void move_left() {
		isAnimated = true;
		if (getFrame() >= 8 || getFrame() < 5)
			setFrame(5);
		bomber_pos.x -= moveSpeed;
		setPosition(bomber_pos.x, bomber_pos.y);

	}

	public void move_right() {
		isAnimated = true;
		if (getFrame() >= 11 || getFrame() < 8)
			setFrame(8);
		bomber_pos.x += moveSpeed;
		setPosition(bomber_pos.x, bomber_pos.y);

	}

	public void MoveUp() {
		isAnimated = true;
		bomber_pos.y -= moveSpeed;
		setPosition(bomber_pos.x, bomber_pos.y);
		if (getFrame() >= 5 || getFrame() < 2)
			setFrame(2);
	}

	public void MoveDown() {
		isAnimated = true;
		bomber_pos.y += moveSpeed;
		setPosition(bomber_pos.x, bomber_pos.y);
		if (getFrame() >= 2 && getFrame() < 11)
			setFrame(11);
	}

	public void StopAnimated() {
		isAnimated = false;
		setFrame(startFrame);
	}

}