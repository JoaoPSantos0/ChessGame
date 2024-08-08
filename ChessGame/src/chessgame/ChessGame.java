
package chessgame;

import javax.swing.JFrame;

public class ChessGame {

    
    public static void main(String[] args) {
        JFrame window = new JFrame("Tabuleiro");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        
        //Adicionar o GameBoard na janela(window);
        
        GameBoard gb = new GameBoard();
        window.add(gb);
        window.pack();//Ajusta a janela ao tamanho do game board;
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        
        gb.launchGame();
    }
    
}
