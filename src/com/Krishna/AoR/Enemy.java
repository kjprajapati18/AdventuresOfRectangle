package com.Krishna.AoR;

import java.awt.*;
import java.util.Random;

public class Enemy extends GameObject {

    private int enemyW = 30, enemyH = 30;
    private Random r;
    private Handler handler;

    public Enemy(int x, int y, ID id, Handler handler){
        super(x,y,id);
        r = new Random();
        velX= r.nextInt(6)+2;
        velY = r.nextInt(6)+2;
        this.handler = handler;
    }

    @Override
    public void tick() {

        x += velX;
        y -= velY;

        if(x< 0 || x> Main.WIDTH - 15-enemyW) velX *=-1;
        if(y< 0 || y> Main.HEIGHT - 40-enemyH) velY *=-1;

        handler.getObjects().add(new BasicTrail(x, y, ID.Trail, handler, Color.RED, 0.08f));
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(x,y,enemyW,enemyH);
    }

    public Rectangle getBounds(){
        return new Rectangle(x,y, enemyW, enemyH);
    }
}
