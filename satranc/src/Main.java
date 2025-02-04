import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        Board board = new Board(); // Tahtayı oluştur
        ChessBoardPanel chessBoardPanel = new ChessBoardPanel(board); // Tahta panosunu oluştur

        JFrame frame = new JFrame("Chess Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(chessBoardPanel); // Panoyu pencereye ekle
        frame.pack();
        frame.setVisible(true);
    }
}
