
package Pieces;

import chessgame.Board;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Piece {
    
    public BufferedImage image;
    public int x, y;
    public int col, row, preCol, preRow;
    public int color;
    
    public Piece(int color, int col, int row){
        this.color = color;
        this.col = col;
        this.row = row;
        x = getX(col);
        y = getY(row);
    }
    
    public BufferedImage getImage(String imagePath){
        BufferedImage image = null;
        
        try{
            image = ImageIO.read(getClass().getResourceAsStream(imagePath + ".png"));
        }catch(IOException e){
        }
        return image;

    }

    public int getX(int col) {
        return col * Board.SQUARE_SIZE;
    }

    public int getY(int row) {
        return row * Board.SQUARE_SIZE;
    }
    
    public int getCol(int x){
        return(x + Board.HALF_SQUARE_SIZE)/Board.SQUARE_SIZE;
    }
    
    public int getRow(int y){
        return(y + Board.HALF_SQUARE_SIZE)/Board.SQUARE_SIZE;
    }
    public void draw(Graphics2D g2){
        g2.drawImage(image, x, y, Board.SQUARE_SIZE, Board.SQUARE_SIZE, null);
    }
    
    public void updatePosition(){
        x = getX(col);
        y = getY(row);
        
        preCol = getCol(x);
        preRow = getRow(y);
    }
    
    public boolean canMove(int destinyCol, int destinyRow){
        return false;
    }
    
    public boolean isOnTheBoard(int destinyCol, int destinyRow){
        if(destinyCol>=0 && destinyCol<=7 && destinyRow>=0 && destinyRow<=7){
            return true;
        }
        return false;
    }
    
    public void resetPosition(){
        
        col = preCol;
        row = preRow;
        
        x = getX(col);
        y = getY(row);
        
    }
}
