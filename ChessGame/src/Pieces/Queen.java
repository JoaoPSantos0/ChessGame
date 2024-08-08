
package Pieces;

import chessgame.GameBoard;


public class Queen extends Piece{
    
    public Queen(int color, int col, int row) {
        super(color, col, row);
        
        if(color == GameBoard.WHITE ){
            image = getImage("/res/white-queen");
        }
        else{
            image = getImage("/res/black-queen");
        }
    }
    
}
