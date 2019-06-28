package com.Krishna.LoL;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {

    private Handler handler;
    public KeyInput(Handler handler){
        this.handler = handler;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        GameObject ob = handler.findPlayer();
        Player player = (Player) ob;
        int code = e.getExtendedKeyCode();

        if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP) player.setRunUp(true);
        if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) player.setRunDown(true);
        if(code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) player.setRunLeft(true);
        if(code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) player.setRunRight(true);

        if(code == KeyEvent.VK_SHIFT) player.setSlow(true);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        GameObject ob = handler.findPlayer();
        Player player = (Player) ob;
        int code = e.getExtendedKeyCode();

        if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP) player.setRunUp(false);
        if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) player.setRunDown(false);
        if(code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) player.setRunLeft(false);
        if(code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) player.setRunRight(false);

        if(code == KeyEvent.VK_SHIFT) player.setSlow(false);
    }
}
