/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.GameCanvas;
import javax.microedition.midlet.MIDletStateChangeException;

/**
 *
 * @author Omar
 */
public class menu  extends Canvas{
    
    private int keyCode;
    private int choice = 0;
    private int x_pos;
    private int y_pos;
    private boolean isactive;
    private Main mid ;
    Image im1;
    Image im2;
    Image im3;
    Image im4;
    Image im5;
    public menu(Main b){
        mid  = b;
        try {
			 im1=Image.createImage("/start.png");
			 im2=Image.createImage("/newgamec.png");
			 im3=Image.createImage("/newgame.png");
			 im4=Image.createImage("/exitc.png");
			 im5= Image.createImage("/exit.png");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        setFullScreenMode(true);
    }
   
    private void handleKey(int key){
        int c = getGameAction(key);
        switch(c){
            case  DOWN:
                choice = (choice+1)%2;
                break;
               case UP:
                   if(choice == 0)
                       choice = 1;
                   else
                       choice = 0;
                   break;
               case FIRE :
                   action();
                   break;
                   default:
                       break;
        }
    }
    private void handlePointer(){
        if(x_pos>10&&x_pos<210){
            if(y_pos>100&&y_pos<150){
                choice = 0;
                isactive = true;
            }
        }if(x_pos>70&&x_pos<170){
            if(y_pos>175&&y_pos<225){
                choice = 1;
                isactive = true;
            }
        }
        isactive = false;
        
    }
    
    protected void pointerPressed(int x, int y) {
        x_pos = x;
        y_pos = y;
        handlePointer();       
        repaint();
        action();
    }
    
    protected void pointerReleased(int x, int y) {
        //if(isactive)
            
    }
    
    
    private void action(){
        if(choice == 0){
            try {
                mid.start();
                System.out.println("...........");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if(choice == 1){
             
                mid.notifyDestroyed();
           
        }
    }
    
    
    public void  keyPressed(int key){
        keyCode  = key;
        handleKey(key);
        repaint();
    }
    
    public void paint(Graphics g){
        g.drawImage(im1, 0, 0, Graphics.TOP | Graphics.LEFT);
		if(choice == 0)
		    g.drawImage(im2, 10, 100, Graphics.TOP | Graphics.LEFT);
		else
		    g.drawImage(im3, 10, 100, Graphics.TOP | Graphics.LEFT);
		if(choice == 1)
		    g.drawImage(im4, 70, 175, Graphics.TOP | Graphics.LEFT);
		else
		    g.drawImage(im5, 70, 175, Graphics.TOP | Graphics.LEFT);
    }
    }
