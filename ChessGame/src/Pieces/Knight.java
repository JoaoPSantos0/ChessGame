
package Pieces;

import chessgame.GameBoard;


public class Knight extends Piece {
    
    public Knight(int color, int col, int row) {
        super(color, col, row);
        if(color == GameBoard.WHITE ){
            image = getImage("/res/white-knight");
        }
        else{
            image = getImage("/res/black-knight");
        }
    }
    
}
