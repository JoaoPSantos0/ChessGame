
package chessgame;

import Pieces.Bishop;
import Pieces.King;
import Pieces.Knight;
import Pieces.Pawn;
import Pieces.Piece;
import Pieces.Queen;
import Pieces.Rook;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import javax.swing.JPanel;


public class GameBoard extends JPanel implements Runnable{
    
    public static final int WIDTH = 1100;
    public static final int HEIGHT = 800;
    
    final int FPS = 60;
    
    //Cor das peças;
    public static final int WHITE = 0;
    public static final int BLACK = 1;
    int currentColor = WHITE;
    
    //Peças;
    public static ArrayList<Piece> pieces = new ArrayList<>();
    public static ArrayList<Piece> backUp = new ArrayList<>();
    Piece moveP;
    
    Thread gameThread;
    Board board = new Board();
    Mouse mouse = new Mouse();
    
    boolean canMove;
    boolean validSquare;
    
    public GameBoard(){
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.black);
        addMouseMotionListener(mouse);
        addMouseListener(mouse);
        setPieces();
        copyPieces(pieces, backUp);
        
    }
    
    public void launchGame(){
        gameThread = new Thread(this);
        gameThread.start();//Chama o mé
        
    }
    
    public void setPieces(){
        pieces.add(new Pawn(WHITE, 0, 6));
        pieces.add(new Pawn(WHITE, 1, 6));
        pieces.add(new Pawn(WHITE, 2, 6));
        pieces.add(new Pawn(WHITE, 3, 6));
        pieces.add(new Pawn(WHITE, 4, 6));
        pieces.add(new Pawn(WHITE, 5, 6));
        pieces.add(new Pawn(WHITE, 6, 6));
        pieces.add(new Pawn(WHITE, 7, 6));
        pieces.add(new Pawn(BLACK, 0, 1));
        pieces.add(new Pawn(BLACK, 1, 1));
        pieces.add(new Pawn(BLACK, 2, 1));
        pieces.add(new Pawn(BLACK, 3, 1));
        pieces.add(new Pawn(BLACK, 4, 1));
        pieces.add(new Pawn(BLACK, 5, 1));
        pieces.add(new Pawn(BLACK, 6, 1));
        pieces.add(new Pawn(BLACK, 7, 1));
        pieces.add(new King(WHITE,4 , 7));
        pieces.add(new King(BLACK,4 , 0));
        pieces.add(new Bishop(WHITE,2 , 7));
        pieces.add(new Bishop(BLACK,2 , 0));
        pieces.add(new Bishop(WHITE,5 , 7));
        pieces.add(new Bishop(BLACK,5 , 0));
        pieces.add(new Rook(WHITE,0 , 7));
        pieces.add(new Rook(BLACK,0 , 0));
        pieces.add(new Rook(WHITE,7 , 7));
        pieces.add(new Rook(BLACK,7 , 0));
        pieces.add(new Knight(WHITE,1 , 7));
        pieces.add(new Knight(BLACK,1 , 0));
        pieces.add(new Knight(WHITE,6 , 7));
        pieces.add(new Knight(BLACK,6 , 0));
        pieces.add(new Queen(WHITE,3 , 7));
        pieces.add(new Queen(BLACK,3 , 0));
    }
    
    private void copyPieces(ArrayList<Piece> ori, ArrayList<Piece> des){
        des.clear();
        
        for(int i = 0; i<ori.size(); i++){
            des.add(ori.get(i));
        }
    }
    
    private void updateBoard(){
        
        if(mouse.pressed){
            if(moveP == null){
                for(Piece piece : backUp){
                    if((piece.col == mouse.x/Board.SQUARE_SIZE) &&
                            (piece.color == currentColor) && 
                            (piece.row == mouse.y/Board.SQUARE_SIZE )){
                        moveP = piece;
                    }
                }
            }
            else{
                simulate();
            }
        }
        
        if(mouse.pressed == false){
            if(moveP!=null){
                if(validSquare){
                    moveP.updatePosition();
                }
                else{
                    moveP.resetPosition();
                    moveP = null;
                }
            }
        }
    }
    
    
    
    public void simulate(){
        canMove = false;
        validSquare = false;
        
        moveP.x = mouse.x - Board.HALF_SQUARE_SIZE;
        moveP.y = mouse.y - Board.HALF_SQUARE_SIZE;
        
        moveP.col = moveP.getCol(moveP.x);
        moveP.row = moveP.getRow(moveP.y);
        
        if(moveP.canMove(moveP.col, moveP.row)){
            canMove = true;
            validSquare = true;
        }
        
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;//converte o grafico g para graficos 2d;
        
        //Tabuleiro;
        board.draw(g2);
        
        //Peças;
        for(Piece p : backUp){
            p.draw(g2);
        }
        
        if(moveP != null){
            if(canMove){
                g2.setColor(Color.GRAY);
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
                g2.fillRect(moveP.col*Board.SQUARE_SIZE, moveP.row*Board.SQUARE_SIZE, Board.SQUARE_SIZE, Board.SQUARE_SIZE);
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
            
            }
            moveP.draw(g2);
            
        }
    }
    
    /*
        O méria um loop parecido com sdl em c;´
        O loop é uma sequência que ocorre continuamente;
    
    */

    @Override
    public void run() {
       double intervaloLoad = 1000000000 / FPS;
       double aux = 0;
       long lastTime = System.nanoTime();
       long currentTime;
       
       while(gameThread != null){
            currentTime = System.nanoTime();
            aux += (currentTime - lastTime)/intervaloLoad;
            lastTime = currentTime;
            
            if(aux >= 1){
                updateBoard();
                repaint();
                aux--;
            }
       }
        
    }
}
