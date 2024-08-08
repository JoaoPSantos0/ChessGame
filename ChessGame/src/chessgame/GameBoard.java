
package chessgame;

import Pieces.Pawn;
import Pieces.Piece;
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
    
    Thread gameThread;
    Board board = new Board();
    
    public GameBoard(){
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.black);
        
        setPieces();
        
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

    }
    
    private void updateBoard(){
    
    }
    
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;//converte o grafico g para graficos 2d;
        board.draw(g2);
    
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
