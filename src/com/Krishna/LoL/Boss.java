package com.Krishna.LoL;

import java.awt.*;

public class Boss extends GameObject {

    private Handler handler;
    private int enemyW = 80, enemyH = 80;

    private int timer = 50;
    private int timerX = 50;
    private int reload = 12;
    private int shoot = reload;

    public Boss(int x, int y, ID id, Handler handler){
        super(x,y,id);
        this.handler = handler;
        velX = 0;
        velY = 0;
    }

    @Override
    public void tick() {
        if(timer > 0) timer--;
        else velY = -5;

        if(timer <= 0 && timerX >0) timerX--;
        else if(timer <=0 && timerX <=0 && velX == 0) velX = 10;

        if(x< 0 || x> Main.WIDTH - 15-enemyW) velX *=-1;
        if(y >= Main.HEIGHT/4 -enemyH) velY =0;


        x += velX;
        y -= velY;

        if(timer <= 0 && timerX <= 0) {
            if (shoot > 0) shoot--;
            else {
                handler.add(new Bullet(x + enemyW / 2, y + enemyH / 2, 1,-4, ID.Bullet, handler));
                handler.add(new Bullet(x + enemyW / 2, y + enemyH / 2, 3, -6,ID.Bullet, handler));
                handler.add(new Bullet(x + enemyW / 2, y + enemyH / 2, -3, -6, ID.Bullet, handler));
                handler.add(new Bullet(x + enemyW / 2, y + enemyH / 2, -5, -6, ID.Bullet, handler));
                handler.add(new Bullet(x + enemyW / 2, y + enemyH / 2, 5, -6, ID.Bullet, handler));

                //Make it virtually impossible to hide behind the boss
                handler.add(new Bullet(x + enemyW / 2, y + enemyH / 2, 0,3, ID.Bullet, handler));
                handler.add(new Bullet(x + enemyW / 2, y + enemyH / 2, 2, 5,ID.Bullet, handler));
                handler.add(new Bullet(x + enemyW / 2, y + enemyH / 2, -2, 5, ID.Bullet, handler));
                handler.add(new Bullet(x + enemyW / 2, y + enemyH / 2, -4, 5, ID.Bullet, handler));
                handler.add(new Bullet(x + enemyW / 2, y + enemyH / 2, -0, 5, ID.Bullet, handler));
                handler.add(new Bullet(x + enemyW / 2, y + enemyH / 2, 4, 5, ID.Bullet, handler));
                handler.add(new Bullet(x + enemyW / 2, y + enemyH / 2, -8, 5, ID.Bullet, handler));
                handler.add(new Bullet(x + enemyW / 2, y + enemyH / 2, 8, 5, ID.Bullet, handler));

                shoot=reload;
            }
        }
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
