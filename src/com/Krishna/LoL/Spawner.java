package com.Krishna.LoL;

import javax.swing.*;

public class Spawner {

    private Handler handler;
    private HUD hud;

    public static int scorekeep = 0;

    public Spawner(Handler handler, HUD hud){
        this.handler = handler;
        this.hud = hud;
    }

    public void tick(){
        if(HUD.health > 0)scorekeep +=5;
        if(scorekeep >= 500 && hud.getLevel() != 10){
            scorekeep = 0;
            hud.setLevel(hud.getLevel()+1);

            if(hud.getLevel() < 10) handler.addRandEnemy();
            else if(hud.getLevel() == 10){
                handler.removeNonPlayer();
                handler.add(new Boss(Main.WIDTH/2-100, -100, ID.Boss, handler));

            } else handler.addRandEnemy();
        }

        if(scorekeep >=6000){
            scorekeep = 0;
            hud.setLevel(hud.getLevel()+1);
            handler.removeNonPlayer();

            long lastTime = System.currentTimeMillis();
            for(int i = 0; i< 6; i++) handler.add(new Enemy(i*80, 20, ID.Enemy, handler));

        }


    }


}
