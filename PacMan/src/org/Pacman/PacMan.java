package org.Pacman;

import GameEngine.src.org.game.engine.Game;
import GameEngine.src.org.game.engine.GameApplication;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class PacMan extends Game {
    public static void main(String[] args) {
        // write your code here
       // System.out.println("This is window");
        GameApplication.start(new PacMan());
    }
    final int STEP=2;
    BufferedImage pacman;
    int frame;
    int row, column,dir,columns,rows;
    ArrayList<String>lines=new ArrayList<>();
    public PacMan(){
        try {
            int r=0;
            Scanner s = new Scanner(new File("src/org/maze.txt"));
            while(s.hasNextLine()){
                String line = s.nextLine();
                lines.add(line);
                if (line.contains("5")) {
                    row = r;
                    column = line.indexOf('5');
                }
                r++;
            }
            s.close();
            rows=lines.size();
            columns=lines.get(0).length();

            width=columns*STEP;
            height=rows*STEP;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        frame=0;
        title="pacman";
//        height=500;
//        width=500;
//        row =300;
//        column =250;
        dir= KeyEvent.VK_RIGHT;
        try {
            pacman= ImageIO.read(new File("src/org/images/packman.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
       int key= e.getKeyCode();
       if(key>=37 && key<=40){
           dir=key;
       }
    }

//    public void KeyPressed(KeyEvent e){
//        dir=e.getKeyCode();
//    }
    @Override
    public void init(){

    }
    @Override
    public void update() {
        frame++;
        if(frame>5){
            frame=0;
        }

        switch (dir){
            case KeyEvent.VK_LEFT:
                if(column>0 && charAt(row,column-1)!='0'){
                    column-=1;
                }
                break;
            case KeyEvent.VK_RIGHT:
                if(column<columns-1 && charAt(row,column+1)!='0'){
                    column +=1;
                }
                break;

            case KeyEvent.VK_UP:
                if(row>0 && charAt(row-1,column)!='0'){
                    row -=1;
                }
                break;

            case KeyEvent.VK_DOWN:
                if(row<rows-1 && charAt(row+1,column)!='0'){
                    row +=1;
                }
                break;
        }
//        if(row < 0){
//            row = 0;
//        }
//        if(row >472){
//            row =472;
//        }
//        if(column < 0){
//            column = 0;
//        }
//        if(column > height-28-STEP){
//            column = height-28-STEP;
//        }
    }

    private char charAt(int row, int column) {
        return lines.get(row).charAt(column);
    }

    @Override
    public void draw(Graphics2D g) {
        //g.drawLine(0,0,500,500);
        for(int r=0;r<rows;r++){
            for(int c=0;c<columns;c++){
                if(charAt(r,c) != '0') {
                    g.fillRect(c * STEP, r * STEP, STEP, STEP);
                }
            }
        }
        g.drawImage(pacman.getSubimage((frame/2)*30, (dir-37)*30, 28, 28), column*STEP-14, row*STEP-14, null);
    }
}
