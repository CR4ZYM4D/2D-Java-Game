package main;

import javax.swing.JFrame;

public class Main {
    public static void main(String args[]){

        //creating a new JFrame
        JFrame window = new JFrame();

        //making sure that the frame closes properly on pressing close button
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // this is true by default but just to remember that there is this option 
        window.setResizable(true);
        
        window.setTitle("KILL ME");
        window.setVisible(true);

        //creating a new game panel and adding it to the window
        GamePanel game_panel = new GamePanel();
        window.add(game_panel);

        // ** VERY IMPORTANT LINE DO NOT PLAY AROUND WITH IT. DON'T TOUCH!! **
        game_panel.startGameThread();
        //making window size same as game panel size
        window.pack();
    }
}
