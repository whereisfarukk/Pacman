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
    BufferedImage ghosts;
    int frame;
    int row, column, reqDir,curDir,columns,rows;
    ArrayList<String>lines=new ArrayList<>();
    BufferedImage mazeImage;
    char[][] cells;
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
            height=rows*STEP+30;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        cells=getCells();
        frame=0;
        title="pacman";
//        height=500;
//        width=500;
//        row =300;
//        column =250;
        delay-=5;
       curDir = reqDir = KeyEvent.VK_RIGHT;
        try {
            pacman= ImageIO.read(new File("src/org/images/packman.png"));
            mazeImage=ImageIO.read(new File("src/org/images/firstmaze.png"));
            ghosts=ImageIO.read(new File("src/org/images/ghosts.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private char[][] getCells() {
        char[][] cells=new char[rows][columns];
        for(int r=0;r<rows;r++){
            System.arraycopy(lines.get(r).toCharArray(),0,cells[r],0,columns);
        }
        return cells;
    }

    @Override
    public void keyPressed(KeyEvent e) {
       int key= e.getKeyCode();
       if(key>=37 && key<=40){
           reqDir =key;
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
        if (frame > 5) {
            frame = 0;
        }
        if (move(reqDir)) {
            curDir = reqDir;
        } else {
            move(curDir);
        }
        if(cells[row][column]=='2'){
            cells[row][column]='1';
        }
        else if(cells[row][column]=='3'){
            cells[row][column]='1';

        }

    }
    private boolean move(int reqDir) {

        switch (reqDir) {
            case KeyEvent.VK_LEFT:
                if (column > 0 && charAt(row, column - 1) != '0') {
                    column -= 1;
                    return true;
                }
                if(column==0 && cells[row][columns-1]=='1'){
                    column=columns-1;
                }
                break;
            case KeyEvent.VK_RIGHT:
                if (column < columns - 1 && charAt(row, column + 1) != '0') {
                    column += 1;
                    return true;
                }
                if(column==columns-1 && cells[row][0]=='1'){
                    column=0;
                }
                break;

            case KeyEvent.VK_UP:
                if (row > 0 && charAt(row - 1, column) != '0') {
                    row -= 1;
                    return true;
                }
                break;

            case KeyEvent.VK_DOWN:
                if (row < rows - 1 && charAt(row + 1, column) != '0') {
                    row += 1;
                    return true;
                }
                break;
        }
        return false;

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
        g.drawImage(mazeImage,0,0,null);
       // g.setColor(Color.ORANGE);
        for(int r=0;r<rows;r++){
            for(int c=0;c<columns;c++){
                if(cells[r][c] == '2') {
                    g.setColor(Color.ORANGE);
                    g.fillOval(c * STEP-4, r * STEP-4, 4, 4);
                }
                if(cells[r][c] == '3') {
                    g.setColor(Color.WHITE);
                    g.fillOval(c * STEP-8, r * STEP-8, 10, 10);
                }
            }
        }
        g.drawImage(pacman.getSubimage((frame/2)*30, (curDir -37)*30, 28, 28), column*STEP-14, row*STEP-14, null);
//        if(curDir==37 || curDir==38) {
//            g.drawImage(ghosts.getSubimage(0, (curDir - 37 + 2) * 73 + (frame / 3) * 36, 28, 28), column * STEP - 15, row * STEP - 15, null);
//        }
//        else if(curDir==39 || curDir==40){
//            g.drawImage(ghosts.getSubimage(0, (curDir - 37 - 2) * 74 + (frame / 3) * 36, 28, 28), column * STEP - 15, row * STEP - 15, null);
//
//        }
//        g.drawImage(ghosts.getSubimage(39, (frame/3)*36, 28, 28), 185+29, 238, null);
//        g.drawImage(ghosts.getSubimage(78, (frame/3)*36, 28, 28), 185+29+29, 238, null);

    }
}
