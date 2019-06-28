package com.Krishna.AoR;


import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class Menu extends MouseAdapter {


    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() != MouseEvent.BUTTON1) return;
        int mx = e.getX();
        int my = e.getY();

        if (Main.gameState == STATE.MENU) {
            if (Main.inBox(mx, my, 350, 250, 300, 100)) {           //Inside Start Box
                Main.gameState = STATE.GAME;
                clickSound();
            } else if (Main.inBox(mx, my, 350, 400, 300, 100)) {    //Inside Instructions
                Main.gameState = STATE.INSTRUCTIONS;
                clickSound();
            } else if (Main.inBox(mx, my, 350, 550, 300, 100)) {    //Inside Options
                Main.gameState = STATE.OPTIONS;
                clickSound();
            }
        } else if (Main.gameState == STATE.INSTRUCTIONS) {
            Main.gameState = STATE.MENU;
            clickSound();

        } else if (Main.gameState == STATE.OPTIONS) {
            Main.gameState = STATE.MENU;
            clickSound();
        }
    }

    public void clickSound() {
        /*  UNCOMMENT AudioLoader.init() IN THE MAIN CLASS WHEN YOU FIX THIS

        Clip clip = null;
        try {
            clip = AudioSystem.getClip();
            AudioInputStream ais = AudioLoader.getSound("click");
            clip.open(ais);
            clip.loop(0);
            clip.stop();
        } catch (Exception e2) {
            e2.printStackTrace();
        }*/
    }


    public void tick() {

    }

    public void render(Graphics g) {
        Font fnt = new Font("Times New Roman", Font.PLAIN, 24);
        Font title = new Font("Times New Roman", Font.PLAIN, 50);
        if (Main.gameState == STATE.MENU) {
            g.setColor(Color.gray);
            g.fillRect(350, 250, 300, 100);
            g.fillRect(350, 400, 300, 100);
            g.fillRect(350, 550, 300, 100);

            g.setColor(Color.WHITE);
            g.setFont(title);
            g.drawString("Adventures of Rectangle", 280, 100);
            g.setFont(fnt);
            g.drawString("Start", 475, 310);
            g.drawString("Instructions", 445, 460);
            g.drawString("Options", 465, 605);

        } else if (Main.gameState == STATE.INSTRUCTIONS) {
            g.setColor(Color.WHITE);
            g.setFont(title);
            g.drawString("Instructions:", 400, 100);
            g.setFont(fnt);
            g.drawString("Use the WASD or arrow keys to move around. Hold SHIFT while moving to move slowly.", 70, 250);
            g.drawString("Avoid all of the enemies and survive for as long as you can! You can also access", 100, 350);
            g.drawString("the shop and pause the game by pressing P. Click anywhere to go back...", 150, 450);

        } else if (Main.gameState == STATE.OPTIONS) {
            g.setColor(Color.white);
            g.setFont(title);
            g.drawString("Options:", 450, 100);
            g.setFont(fnt);
            g.drawString("There's literally nothing in the game that you could adjust.", 230, 250);
            g.drawString("No graphics... no sound... I don't know why you're here to be honest.", 170, 350);
            g.drawString("Click anywhere to go back...", 370, 450);
        }
    }
}