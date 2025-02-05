import java.awt.*;
import javax.swing.*;

public class Bishop extends ChessPiece {
    private Image image;

    public Bishop(boolean isWhite) {
        super("Bishop", isWhite);
        image = new ImageIcon(isWhite ? "images/wb.png" : "images/bb.png").getImage(); // Görselleri yükle
    }

    @Override
    public void draw(Graphics g, int x, int y, int cellSize) {
        g.drawImage(image, x + 5, y + 5, cellSize - 10, cellSize - 10, null); // Görseli çiz
    }

    @Override
    public void highlightValidMoves(Graphics g, int x, int y, int cellSize, Board board) {
        // Çapraz hareketler için geçerli alanları işaretle
        for (int i = 1; i < 8; i++) {
            // Çapraz sağ üst
            if (x + i < 8 && y - i >= 0 && board.getPiece(y - i, x + i) == null) {
                g.setColor(Color.GREEN);
                g.fillRect((x + i) * cellSize, (y - i) * cellSize, cellSize, cellSize);
            } else {
                break;
            }
        }

        for (int i = 1; i < 8; i++) {
            // Çapraz sol üst
            if (x - i >= 0 && y - i >= 0 && board.getPiece(y - i, x - i) == null) {
                g.setColor(Color.GREEN);
                g.fillRect((x - i) * cellSize, (y - i) * cellSize, cellSize, cellSize);
            } else {
                break;
            }
        }

        for (int i = 1; i < 8; i++) {
            // Çapraz sağ alt
            if (x + i < 8 && y + i < 8 && board.getPiece(y + i, x + i) == null) {
                g.setColor(Color.GREEN);
                g.fillRect((x + i) * cellSize, (y + i) * cellSize, cellSize, cellSize);
            } else {
                break;
            }
        }

        for (int i = 1; i < 8; i++) {
            // Çapraz sol alt
            if (x - i >= 0 && y + i < 8 && board.getPiece(y + i, x - i) == null) {
                g.setColor(Color.GREEN);
                g.fillRect((x - i) * cellSize, (y + i) * cellSize, cellSize, cellSize);
            } else {
                break;
            }
        }
    }
}
