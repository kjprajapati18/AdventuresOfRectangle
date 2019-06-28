package com.Krishna.AoR;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class End extends MouseAdapter {

    private Handler handler;

    public End(Handler handler){
        this.handler = handler;
    }

    public void mousePressed(MouseEvent e){
        int mx = e.getX();
        int my = e.getY();

        if(Main.inBox(mx, my, 20,670,300,80)) {
            reset();
            Main.gameState = STATE.MENU;
        }
    }

    public void reset(){
        HUD.maxHealth = 100;
        HUD.health = 100;
        HUD.score = 0;
        HUD.level = 1;
        HUD.gameTime = 0;
        Spawner.scorekeep = 0;
        handler.removeNonPlayer();

        Player player = (Player) handler.findPlayer();
        player.setX(Main.WIDTH-80);
        player.setY(100);
        player.setVelX(0);
        player.setVelY(0);
        player.setInvincible(false);
        player.setInvincTime(0);
    }

    public void tick(){

    }

    public void render(Graphics g){

        g.setColor(Color.black);
        g.fillRect(0,0, Main.WIDTH, Main.HEIGHT);

        g.setColor(Color.white);
        g.setFont(new Font("Times New Roman", Font.PLAIN, 70));
        g.drawString("Game Over", 330, 200);

        g.drawString("Final Score: " + HUD.score, 270, 400);
        g.drawString("You survived for " + HUD.gameTime/60 + " seconds!", 120, 600);

        g.fillRect(20, 670, 300, 80);
        g.setColor(Color.BLACK);
        g.setFont(new Font("Times New Roman", Font.PLAIN, 40));
        g.drawString("Back to Menu", 55, 720);
    }

}
