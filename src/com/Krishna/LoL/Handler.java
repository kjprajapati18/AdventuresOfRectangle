package com.Krishna.LoL;

import java.awt.*;
import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public class Handler {

    private CopyOnWriteArrayList<GameObject> objects = new CopyOnWriteArrayList<GameObject>();
    private Random r;
    public Handler (){
        r = new Random();
        add(new Player(Main.WIDTH-80,100, ID.Player, this));
        addRandEnemy();
    }

    public void add(GameObject object){
        objects.add(object);
    }

    public void addRandEnemy(){
        objects.add(new Enemy(r.nextInt(Main.WIDTH - 100), r.nextInt(Main.HEIGHT - 100), ID.Enemy, this));
    }

    public void removeNonPlayer(){
        for (int i = objects.size()-1; i>=0; i--){
            GameObject temp = objects.get(i);
            if(temp.getId() != ID.Player) remove(temp);
        }
    }

    public void remove(GameObject object){
        objects.remove(object);
    }

    public void tick(){
        for(int i = objects.size()-1; i >= 0 ; i--){
            objects.get(i).tick();
        }
    }
    public void render(Graphics g){
        for(int i = objects.size()-1; i >= 0; i--){
            objects.get(i).render(g);
        }
    }
    public GameObject findPlayer(){
        for(int i = objects.size()-1; i >= 0; i--){
            if(objects.get(i).getId() == ID.Player) return objects.get(i);
        }
        return null;
    }

    public CopyOnWriteArrayList<GameObject> getObjects(){
        return objects;
    }
}
