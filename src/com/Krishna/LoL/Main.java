package com.Krishna.LoL;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class Main extends Canvas implements Runnable {

    public static final int WIDTH = 1000, HEIGHT = 800;
    public static STATE gameState = STATE.MENU;

    private boolean running = false;

    private Thread thread;
    private Graphics g;
    private Handler handler;
    private HUD hud;
    private Spawner spawner;

    private Shop shop;
    private Menu menu;
    private End end;

    private BufferedImage bg;

    //Calls Window and initializes variables above
    //Variables written like this because we want the same instance used in each class
    public static void main(String[] args) {
        new Main();
    }
    public Main(){
        handler = new Handler();
        menu = new Menu();
        shop = new Shop();
        end = new End(handler);
        hud = new HUD();
        spawner = new Spawner(handler, hud);

        //AudioLoader.init();

        this.addKeyListener(new KeyInput(handler));
        this.addMouseListener(menu);
        this.addMouseListener(shop);
        this.addMouseListener(end);
        new Window("Adventures of Rectangle", WIDTH, HEIGHT, this);

        bg = ImageLoader.load("res/bg2.png");
    }

    public synchronized void start(){
        if(!running) {
            running = true;
            thread = new Thread(this);

            //Calls the run method
            thread.start();
        }
    }
    public synchronized void stop(){
        if(running){
            try{
                thread.join();
                running = false;
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }



    private void tick(){

        switch(gameState){
            case INSTRUCTIONS:
            case OPTIONS:
            case MENU:
                menu.tick();
                break;

            case GAME:
                handler.tick();
                spawner.tick();
                hud.tick();
                break;

            case END:
                end.tick();
            case SHOP:
            default: break;

        }
    }
    private void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            this.createBufferStrategy(3);
            return;
        }

        //Draw stuff here
        g = bs.getDrawGraphics();

        g.drawImage(bg, 0, 0, WIDTH, HEIGHT,null);

        switch(gameState) {
            case INSTRUCTIONS:
            case OPTIONS:
            case MENU:
                menu.render(g);
                break;

            case GAME:
                g.setColor(Color.BLUE);
                g.fillRect(0,0,WIDTH,HEIGHT);
                handler.render(g);
                hud.render(g);
                break;

                case END:
                end.render(g);
                break;
            case SHOP:
            default:
                break;
        }
        g.dispose();
        bs.show();
    }

    @Override
    public void run() {
        this.requestFocus();
        //Game Loop that make sures the game renders as much as possible but only
        //Updates the mechanics 60 times per second.
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while(running)
        {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while(delta >=1){
                tick();
                delta--;
            }
            render();
            frames++;

            if(System.currentTimeMillis() - timer > 1000)
            {
                timer += 1000;
                System.out.println("FPS: "+ frames);
                //System.out.println("X: " + handler.findPlayer().getX() + "  Y: " + handler.findPlayer().getY());
                frames = 0;
            }
        }
        stop();
    }

    public static boolean inBox(int mx, int my, int x, int y, int width, int height){
        if(mx > x && mx < x + width && my> y && my < y+height) return true;
        return false;
    }

}
