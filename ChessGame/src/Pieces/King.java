
package Pieces;

import chessgame.GameBoard;

public class King extends Piece{
    
    public King(int color, int col, int row) {
        super(color, col, row);
        
        if(color == GameBoard.WHITE ){
            image = getImage("/res/white-king");
        }
        else{
            image = getImage("/res/black-king");
        }
    }
    
    @Override
    public boolean canMove(int destinyCol, int destinyRow){
        if(isOnTheBoard(destinyCol, destinyRow)){
            
            if(Math.abs(destinyCol - preCol) + Math.abs(destinyRow - preRow) == 1 || Math.abs(destinyCol - preCol) * Math.abs(destinyRow - preRow) == 1){
                return true;
            }
        }
        return false;
    }
    
}
