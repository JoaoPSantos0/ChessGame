
package Pieces;

import chessgame.GameBoard;

public class Bishop extends Piece {
    
    public Bishop(int color, int col, int row) {
        super(color, col, row);
        
        if(color == GameBoard.WHITE ){
            image = getImage("/res/white-bishop");
        }
        else{
            image = getImage("/res/black-bishop");
        }
    }
    
}
