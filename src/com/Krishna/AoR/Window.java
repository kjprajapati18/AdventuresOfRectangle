package com.Krishna.AoR;

import javax.swing.*;
import java.awt.*;

public class Window extends Canvas {



    //Makes JFrame window
    public Window(String title, int width, int height, Main game){
        JFrame frame = new JFrame(title);

        frame.setPreferredSize(new Dimension(width, height));
        frame.setMaximumSize(new Dimension(width, height));
        frame.setMinimumSize(new Dimension(width, height));

        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setFocusTraversalKeysEnabled(true);
        frame.setResizable(false);
        frame.add(game);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setIconImage(ImageLoader.load("res/bg.png"));

        //Calls the start method which creates the Thread
        game.start();


    }

}
