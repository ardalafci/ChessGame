import java.awt.*;
import javax.swing.*;

public class Rook extends ChessPiece {
    private Image image;

    public Rook(boolean isWhite) {
        super("Rook", isWhite);
        image = new ImageIcon(isWhite ? "images/wr.png" : "images/br.png").getImage(); // Görselleri yükle
    }

    @Override
    public void draw(Graphics g, int x, int y, int cellSize) {
        g.drawImage(image, x + 5, y + 5, cellSize - 10, cellSize - 10, null); // Görseli çiz
    }

    @Override
    public void highlightValidMoves(Graphics g, int x, int y, int cellSize, Board board) {
        // Dikey ve yatay hareketler için geçerli alanları işaretle
        for (int i = 1; i < 8; i++) {
            // Yatay (sağa)
            if (x + i < 8 && board.getPiece(y, x + i) == null) {
                g.setColor(Color.GREEN);
                g.fillRect((x + i) * cellSize, y * cellSize, cellSize, cellSize);
            } else {
                break;
            }
        }

        for (int i = 1; i < 8; i++) {
            // Yatay (sola)
            if (x - i >= 0 && board.getPiece(y, x - i) == null) {
                g.setColor(Color.GREEN);
                g.fillRect((x - i) * cellSize, y * cellSize, cellSize, cellSize);
            } else {
                break;
            }
        }

        for (int i = 1; i < 8; i++) {
            // Dikey (aşağı)
            if (y + i < 8 && board.getPiece(y + i, x) == null) {
                g.setColor(Color.GREEN);
                g.fillRect(x * cellSize, (y + i) * cellSize, cellSize, cellSize);
            } else {
                break;
            }
        }

        for (int i = 1; i < 8; i++) {
            // Dikey (yukarı)
            if (y - i >= 0 && board.getPiece(y - i, x) == null) {
                g.setColor(Color.GREEN);
                g.fillRect(x * cellSize, (y - i) * cellSize, cellSize, cellSize);
            } else {
                break;
            }
        }
    }
}
