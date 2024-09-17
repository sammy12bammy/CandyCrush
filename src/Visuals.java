import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JComponent;
import javax.swing.JPanel;

import java.util.Scanner;

public class Visuals extends JPanel{
    public final int SCREEN_WIDTH = 800;
    public final int SCREEN_HEIGHT = 800;
    final int BORDER_OFFSET = SCREEN_HEIGHT / 8;
    final int PLAYABLE_AREA = SCREEN_WIDTH - (BORDER_OFFSET *2);

    Crush game;

    public Visuals(Crush game){
        this.game = game;
         

        game.movePieces(0, 0, 0, 1);
        repaint();
        System.out.println("------");
        game.printGame();
        
    }

    public void makeGameMove(int fR, int fC, int tR, int tC){
        game.movePieces(fR, fC, tR, tC);
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        //paint the screen
        paintScreen(g);
        //paint the pieces
        paintPieces(g);
    }

    public void paintScreen(Graphics g){
        g.setColor(new Color(52, 235, 146));
        g.fillRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
        g.setColor(new Color(245, 66, 185));
        g.fillRect(0, 0, BORDER_OFFSET, SCREEN_HEIGHT);
        g.fillRect(0, 0, SCREEN_WIDTH, BORDER_OFFSET);
        g.fillRect(0, SCREEN_HEIGHT - BORDER_OFFSET, SCREEN_WIDTH, BORDER_OFFSET);
        g.fillRect(SCREEN_WIDTH - BORDER_OFFSET, 0, BORDER_OFFSET, SCREEN_HEIGHT);
        g.setColor(new Color(99, 88, 95));
        g.setFont(new Font("TimesRoman", Font.PLAIN, 70)); 
        g.drawString("Puzzle Crush", 200, 70);
    }

    public void paintPieces(Graphics g){
        char[][] gameArr = game.returnGame();
        int numSquares = gameArr[0].length;

        int squareSize = PLAYABLE_AREA / numSquares;
        for(int i = 0; i < gameArr.length; i++){
            for(int j = 0; j < gameArr[0].length; j++){
                char type = gameArr[i][j];
                if(type == 'r'){
                    g.setColor(new Color(240, 0, 0));
                    g.fillRect(BORDER_OFFSET + (j * (PLAYABLE_AREA / numSquares)), BORDER_OFFSET + (i * (PLAYABLE_AREA / numSquares)), squareSize, squareSize);
                } else if(type == 'g'){
                    g.setColor(new Color(13, 158, 30));
                    g.fillRect(BORDER_OFFSET + (j * (PLAYABLE_AREA / numSquares)), BORDER_OFFSET + (i * (PLAYABLE_AREA / numSquares)), squareSize, squareSize);
                } else if(type == 'b'){
                    g.setColor(new Color(31, 40, 196));
                    g.fillRect(BORDER_OFFSET + (j * (PLAYABLE_AREA / numSquares)), BORDER_OFFSET + (i * (PLAYABLE_AREA / numSquares)), squareSize, squareSize);
                }
            }
        }
    }

    @Override
    public Dimension getPreferredSize(){
        return new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT);
    }

    public int getCrushSize(){
        return game.getSize();
    }


}
