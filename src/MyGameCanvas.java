/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;

import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.GameCanvas;

/**
 * 
 * @author Nguyen Tran Vu
 */
public class MyGameCanvas extends GameCanvas {
	int width = 251;
	int height = 340;
	public static Map map;
	public int iMap = 1;
	boolean test = true;
	public Main mid;

	public MyGameCanvas(Main midlet) throws Exception {
		super(false);
		mid = midlet;
		map = new Map(iMap);
		setFullScreenMode(true);
	}

	
	public void resum() throws Exception{
        Main.thread.pause = false;
      Main.thread = new GameThread(this);
      Main.thread.start();
        Display.getDisplay(mid).setCurrent(this);
        
    }
	
	
	public void paint(Graphics g) {
		if (!map.isGameOver) {
			if (!map.iswin) {
				if (map != null) {
					map.Draw(g);
					map.paint(g, 0, 0);
					map.DrawScore(g);
				}
			} else {
				g.setColor(0x333333);
				g.fillRect(0, 0, width, height);
				try {
					g.drawImage(Image.createImage("/win.png"),0,0,Graphics.TOP | Graphics.LEFT);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} else {
			g.setColor(0x333333);
			g.fillRect(0, 0, width, height);
			try {
				g.drawImage(Image.createImage("/lose.png"),0,0,Graphics.TOP | Graphics.LEFT);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void render() {

		map.Advance();
		paint(getGraphics());
		flushGraphics();
		

	}

	/**
	 * Xử lý các nút nhấn
	 */
	public void checkKey() throws Exception {
		int keyState = getKeyStates();
        //System.out.println(keyState);
        if(keyState == 512){
            pauseMenu menu = new pauseMenu(this);
           Main.thread.pause = true;
                      
           Display.getDisplay(mid).setCurrent(menu);
           flushGraphics();
        }
		
        else if ((keyState & LEFT_PRESSED) != 0) {
			map.Left();
		} else if ((keyState & RIGHT_PRESSED) != 0) {
			map.Right();
		} else if ((keyState & UP_PRESSED) != 0) {
			map.Up();
		} else if ((keyState & DOWN_PRESSED) != 0) {
			map.Down();
		} else if ((keyState & FIRE_PRESSED) != 0) {
			if (test) {
				map.PlaceBomb();
				test = false;
			}
		} else {
			if (!map.bomber.isExplore)
				map.Quite();
		}
		test = !((keyState & FIRE_PRESSED) != 0);
	}

	/**
	 * clears the key states.
	 */
	public void flushKey() {
		getKeyStates();
	}
}
