package com.Krishna.LoL;

import java.awt.*;

public class BasicTrail extends GameObject{

    private float alpha = 1;
    private Handler handler;
    private Color color;

    private float life; //Between 0.001 and 0.1

    public BasicTrail(int x, int y, ID id, Handler handler, Color color, float life){
        super(x,y,id);
        this.handler = handler;
        this.color = color;
        this.life = life;
    }

    @Override
    public void tick() {
        if(alpha > life){
            alpha-= life;
        } else handler.remove(this);
    }

    @Override
    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        g2d.setComposite(makeTransparent(alpha));
        g.setColor(color);
        g.fillRect(x,y, 30,30);
        g2d.setComposite(makeTransparent(1));
    }

    @Override
    public Rectangle getBounds() {
        return null;
    }

    private AlphaComposite makeTransparent(float alpha){
        int type = AlphaComposite.SRC_OVER;
        return AlphaComposite.getInstance(type, alpha);
    }
}
