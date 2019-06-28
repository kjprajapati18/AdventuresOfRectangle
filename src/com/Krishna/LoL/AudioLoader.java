package com.Krishna.LoL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class AudioLoader {

    private static Map<String, AudioInputStream> sounds = new HashMap<String, AudioInputStream>();
    private static Map<String, AudioInputStream> music = new HashMap<String, AudioInputStream>();


    public static AudioInputStream load(String path){


        AudioInputStream ais = null;
        try {
            ais = AudioSystem.getAudioInputStream(new File(path));
        } catch (Exception e){
            e.printStackTrace();
            System.out.println("Audio failed to load");
        }

        return ais;

    }

    public static void init(){

        sounds.put("click", load("res/click.wav"));

    }

    //Getters
    public static AudioInputStream getSound(String key){
        return sounds.get(key);
    }

    public static AudioInputStream getMusic(String key){
        return music.get(key);
    }



}
