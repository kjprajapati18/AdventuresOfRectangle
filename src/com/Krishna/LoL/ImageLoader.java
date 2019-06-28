package com.Krishna.LoL;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class ImageLoader {

    public static BufferedImage load(String path){

        BufferedImage image = null;
        try{
            image = ImageIO.read(new File(path));
        } catch (Exception e){
            e.printStackTrace();
        }

        return image;

    }
}
