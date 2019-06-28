package com.Krishna.LoL;

import java.awt.*;
import java.util.Random;

public class Bullet extends GameObject {

    private Handler handler;

    private int enemyW = 15, enemyH = 15;
    Random r = new Random();

    public Bullet(int x, int y, ID id, Handler handler){
        super(x,y,id);
        this.handler = handler;

        velX = r.nextInt(12)-6;
        velY = r.nextInt(7)-8;
    }
    public Bullet(int x, int y, int velX, int velY, ID id, Handler handler){
        super(x,y,id);
        this.handler = handler;

        this.velX = velX;
        this.velY = velY;
    }

    @Override
    public void tick() {
        x += velX;
        y -= velY;

        if(x< -enemyW || x> Main.WIDTH || y> Main.HEIGHT) handler.remove(this);

    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(x,y,enemyW,enemyH);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x,y,enemyW, enemyH);
    }
}
