
package Pieces;

import chessgame.GameBoard;


public class Pawn extends Piece{
    
    public Pawn(int color, int col, int row) {
        super(color, col, row);
        
        if(color == GameBoard.WHITE ){
            image = getImage("/res/white-pawn");
        }
        else{
            image = getImage("/res/black-pawn");
        }
    }
    
}
