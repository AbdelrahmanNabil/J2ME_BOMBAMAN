/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.IOException;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.midlet.MIDletStateChangeException;

/**
 *
 * @author Omar
 */
public class pauseMenu extends Canvas{

    private int keyCode;
    private int choice = 0;
    private int x_pos;
    private int y_pos;
    private boolean isactive;
    private MyGameCanvas mid ;
    Image im1;
    Image im2;
    Image im3;
    Image im4;
    Image im5;
    Image im6;
    Image im7;
    
    
    public pauseMenu(MyGameCanvas b){
        mid  = b;
        setFullScreenMode(true);
        try {
			im1=Image.createImage("/start.png");
			im2=Image.createImage("/resumc.png");
			im3=Image.createImage("/resum.png");
			im4=Image.createImage("/newgamec.png");
			im5=Image.createImage("/newgame.png");
			im6=Image.createImage("/exitc.png");
			im7=Image.createImage("/exit.png");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
   
    private void handleKey(int key){
        int c = getGameAction(key);
        switch(c){
            case  DOWN:
                choice = (choice+1)%3;
                break;
               case UP:
                   if(choice == 0)
                       choice = 2;
                   else
                       choice = (choice - 1)%3;
                   break;
               case FIRE :
                   action();
                   break;
                   default:
                       break;
        }
    }
    private void handlePointer(){
        if(x_pos>40&&x_pos<190){
            if(y_pos>100&&y_pos<150){
                choice = 0;
                isactive = true;
            }
        }if(x_pos>10&&x_pos<210){
            if(y_pos>150&&y_pos<200){
                choice = 1;
                isactive = true;
            }
        }
        if(x_pos>70&&x_pos<170){
            if(y_pos>200&&y_pos<250){
                choice = 2;
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
                mid.resum();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }if(choice == 1){
            mid.mid.mycanvas = null;
           Main.thread = null;
            try {
                mid.mid.start();
            } catch (MIDletStateChangeException ex) {
                ex.printStackTrace();
            }
        }
        
        if(choice == 2){
             
                mid.mid.notifyDestroyed();
           
        }
    }
    
    
    public void  keyPressed(int key){
        keyCode  = key;
        handleKey(key);
        repaint();
    }
    
    public void paint(Graphics g){
        g.drawImage(im1, 0, 0, Graphics.TOP | Graphics.LEFT);
		if(choice==0)
		    g.drawImage(im2, 40, 100, Graphics.TOP | Graphics.LEFT);
		else
		    g.drawImage(im3, 40, 100, Graphics.TOP | Graphics.LEFT);
		            
		if(choice == 1)
		    g.drawImage(im4, 10, 150, Graphics.TOP | Graphics.LEFT);
		else
		    g.drawImage(im5, 10, 150, Graphics.TOP | Graphics.LEFT);
		if(choice == 2)
		    g.drawImage(im6, 70, 200, Graphics.TOP | Graphics.LEFT);
		else
		    g.drawImage(im7, 70, 200, Graphics.TOP | Graphics.LEFT);
    }
    }
