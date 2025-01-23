package main;

import java.awt.event.KeyEvent;
import java.awt.event.*;

public class KeyInput implements KeyListener{

    //array that handles movement in the order up, down, left, right
    public boolean[] movement = new boolean[4];
    public boolean boost[]=new boolean[2];
    @Override
    public void keyTyped(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_W || e.getKeyCode()==KeyEvent.VK_UP){
            movement[0] =true;
        }
        
        if(e.getKeyCode()==KeyEvent.VK_S || e.getKeyCode()==KeyEvent.VK_DOWN){
            movement[1] =true;
        }
        
        if(e.getKeyCode()==KeyEvent.VK_A || e.getKeyCode()==KeyEvent.VK_LEFT){
            movement[2] =true;
        }
        
        if(e.getKeyCode()==KeyEvent.VK_D || e.getKeyCode()==KeyEvent.VK_RIGHT){
            movement[3] =true;
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {    
        
        if(e.getKeyCode()==KeyEvent.VK_W || e.getKeyCode()==KeyEvent.VK_UP){
            movement[0] =true;
        }
        
        if(e.getKeyCode()==KeyEvent.VK_S || e.getKeyCode()==KeyEvent.VK_DOWN){
            movement[1] =true;
        }
        
        if(e.getKeyCode()==KeyEvent.VK_A || e.getKeyCode()==KeyEvent.VK_LEFT){
            movement[2] =true;
        }
        
        if(e.getKeyCode()==KeyEvent.VK_D || e.getKeyCode()==KeyEvent.VK_RIGHT){
            movement[3] =true;
        }
        if(e.getKeyCode()==KeyEvent.VK_SHIFT){
            boost[1]=true;
            boost[0]=true;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

        if(e.getKeyCode()==KeyEvent.VK_W || e.getKeyCode()==KeyEvent.VK_UP){
            movement[0] =false;
        }
        
        if(e.getKeyCode()==KeyEvent.VK_S || e.getKeyCode()==KeyEvent.VK_DOWN){
            movement[1] =false;
        }
        
        if(e.getKeyCode()==KeyEvent.VK_A || e.getKeyCode()==KeyEvent.VK_LEFT){
            movement[2] =false;
        }
        
        if(e.getKeyCode()==KeyEvent.VK_D || e.getKeyCode()==KeyEvent.VK_RIGHT){
            movement[3] =false;
        }
        if(e.getKeyCode()==KeyEvent.VK_SHIFT)
        {    
            boost[0]=true;
            boost[1]=false;
        }
    }

}
