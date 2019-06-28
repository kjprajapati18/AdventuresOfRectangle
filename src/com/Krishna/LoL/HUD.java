package com.Krishna.LoL;

import java.awt.*;

public class HUD {

    public static int maxHealth = 100;
    public static int health = maxHealth;

    public static int score = 0;
    public static int level = 1;

    public static int gameTime = 0;

    public void tick(){
        if (health <=0) {
            health = 0;
            Main.gameState = STATE.END;
            return;
        }
        if(health != 0) score+=5; gameTime++;

    }

    public void render(Graphics g){
        g.setColor(Color.GRAY);
        g.fillRect(10,10,900,20);

        g.setColor(Color.WHITE);
        g.drawString("Score: " + score, 10,50);
        g.drawString("Level: " + level, 10,70);

        //Choosing color for health bar based on percent HP
        if      ((float)health/maxHealth >= 0.66) g.setColor(Color.GREEN);
        else if ((float)health/maxHealth >= 0.33) g.setColor(Color.YELLOW);
        else                                      g.setColor(Color.RED);
        //draw HP bar
        g.fillRect(10,10, 900 * health/maxHealth, 20);
    }


    //Getters & Setters

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
