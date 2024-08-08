
package Pieces;

import chessgame.GameBoard;

public class Rook extends Piece {
    
    public Rook(int color, int col, int row) {
        super(color, col, row);
        
        if(color == GameBoard.WHITE ){
            image = getImage("/res/white-rook");
        }
        else{
            image = getImage("/res/black-rook");
        }
    }
    
}
