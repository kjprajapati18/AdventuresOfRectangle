package com.Krishna.AoR;

import java.awt.*;

public class Player extends GameObject {

    private boolean runUp = false,
                    runDown = false,
                    runLeft = false,
                    runRight = false;

    private int speed;
    private int slowSpeed = 4, fastSpeed = 10;
    private int playerW = 50;
    private int playerH = 50;

    private boolean invincible = false;
    private int invincTime = 0;

    private Handler handler;

    public Player(int x, int y, ID id, Handler handler){
        super(x,y,id);
        speed = fastSpeed;
        this.handler = handler;
    }

    @Override
    public void tick() {
        //Set velocities based on Key pressed
        if(runUp)           velY = speed;
        else if(runDown)    velY = -speed;
        else                velY = 0;

        if(runLeft)         velX = -speed;
        else if(runRight)   velX = speed;
        else                velX = 0;

        //Change position based on velocities
        x += velX;
        y -= velY;

        //Don't let player go outside window
        if(x +playerW+15 > Main.WIDTH)  x = Main.WIDTH - playerW-15;
        if(x < 0) x = 0;
        if(y + playerH +40> Main.HEIGHT) y = Main.HEIGHT - playerH-40;
        if(y<0) y=0;

        invincible = (invincTime>0);

        collision();
        if(invincTime>0) {
            invincTime--;
        }
    }

    @Override
    public void render(Graphics g){
        g.setColor(Color.BLACK);
        if(invincible) g.setColor(Color.GRAY);
        g.fillRect(x,y, playerW,playerH);
    }

    public Rectangle getBounds(){
        return new Rectangle(x,y,playerW,playerH);
    }

    private void collision(){

        for(int i = handler.getObjects().size()-1; i >= 0; i--){
            GameObject ob = this.handler.getObjects().get(i);
            if(ob.getId() != ID.Player && ob.getId() != ID.Trail){
                if(ob.getBounds().intersects(getBounds())){
                    if(!invincible) {
                        HUD.health-=5;
                        invincTime += 30;
                    }
                    if(ob.getId() == ID.Bullet) handler.remove(ob);
                }
            }
        }
    }
    //Getters and Setters for running
    public boolean isRunUp() {
        return runUp;
    }

    public void setRunUp(boolean runUp) {
        this.runUp = runUp;
    }

    public boolean isRunDown() {
        return runDown;
    }

    public void setRunDown(boolean runDown) {
        this.runDown = runDown;
    }

    public boolean isRunLeft() {
        return runLeft;
    }

    public void setRunLeft(boolean runLeft) {
        this.runLeft = runLeft;
    }

    public boolean isRunRight() {
        return runRight;
    }

    public void setRunRight(boolean runRight) {
        this.runRight = runRight;
    }

    public void setSlow(boolean slow){
        if(slow) speed = slowSpeed;
        else speed = fastSpeed;
    }

    public void setInvincible(boolean invincible) {
        this.invincible = invincible;
    }

    public void setInvincTime(int invincTime) {
        this.invincTime = invincTime;
    }
}
