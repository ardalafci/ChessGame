import java.awt.*;
import javax.swing.*;

public class Queen extends ChessPiece {
    private Image image;

    public Queen(boolean isWhite) {
        super("Queen", isWhite);
        image = new ImageIcon(isWhite ? "images/wq.png" : "images/bq.png").getImage(); // Görselleri yükle
    }

    @Override
    public void draw(Graphics g, int x, int y, int cellSize) {
        g.drawImage(image, x + 5, y + 5, cellSize - 10, cellSize - 10, null); // Görseli çiz
    }

    @Override
    public void highlightValidMoves(Graphics g, int x, int y, int cellSize, Board board) {
        // Vezir, hem yatay/dikey hem de çapraz hareket eder. Kale ve Fil'in birleşimidir.
        new Rook(isWhite()).highlightValidMoves(g, x, y, cellSize, board); // Kale gibi hareket et
        new Bishop(isWhite()).highlightValidMoves(g, x, y, cellSize, board); // Fil gibi hareket et
    }
}

