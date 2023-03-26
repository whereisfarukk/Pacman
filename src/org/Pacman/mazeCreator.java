package org.Pacman;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class mazeCreator {
    public static void main(String[] args)throws FileNotFoundException {
        ArrayList<String>lines=new ArrayList<String>();
        Scanner sc=new Scanner(new File("src/org/maze.txt"));
        while(sc.hasNextLine()){
            lines.add(sc.nextLine());
        }
        sc.close();
        int rows=lines.size();
        int columns=lines.get(0).length();
        int wight=columns*2;
        int height=rows*2;
        BufferedImage image=new BufferedImage(wight,height,BufferedImage.TYPE_INT_ARGB);
        Graphics2D g=image.createGraphics();
        for(int r=0;r<rows;r++){
            for(int c=0;c<columns;c++){
                if(lines.get(r).charAt(c)!='0'){
                    g.fillRect(c*2-14,r*2-14,28,28);
                }
            }
        }
        g.dispose();
        try {
            ImageIO.write(image,"png",new File("src/org/images/","firstMaze.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
