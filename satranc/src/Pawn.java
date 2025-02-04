import java.awt.*;
import javax.swing.*;

public class Pawn extends ChessPiece {
    private Image image;

    public Pawn(boolean isWhite) {
        super("Pawn", isWhite);
        image = new ImageIcon(isWhite ? "images/wp.png" : "images/bp.png").getImage(); // Görselleri yükle
    }

    @Override
    public void draw(Graphics g, int x, int y, int cellSize) {
        g.drawImage(image, x + 5, y + 5, cellSize - 10, cellSize - 10, null); // Görseli çiz
    }

    @Override
    public void highlightValidMoves(Graphics g, int x, int y, int cellSize, Board board) {
        // Bir adım ilerleyebilir
        if (this.isWhite()) {
            if (y - 1 >= 0 && board.getPiece(y - 1, x) == null) {
                g.setColor(Color.GREEN);
                g.fillRect(x * cellSize, (y - 1) * cellSize, cellSize, cellSize);
            }
            if (y == 6 && board.getPiece(y - 2, x) == null) {
                g.setColor(Color.GREEN);
                g.fillRect(x * cellSize, (y - 2) * cellSize, cellSize, cellSize);
            }
        } else {
            if (y + 1 < 8 && board.getPiece(y + 1, x) == null) {
                g.setColor(Color.GREEN);
                g.fillRect(x * cellSize, (y + 1) * cellSize, cellSize, cellSize);
            }
            if (y == 1 && board.getPiece(y + 2, x) == null) {
                g.setColor(Color.GREEN);
                g.fillRect(x * cellSize, (y + 2) * cellSize, cellSize, cellSize);
            }
        }

        // Çapraz saldırılar
        if (this.isWhite()) {
            if (x + 1 < 8 && y - 1 >= 0 && board.getPiece(y - 1, x + 1) != null && !board.getPiece(y - 1, x + 1).isWhite()) {
                g.setColor(Color.GREEN);
                g.fillRect((x + 1) * cellSize, (y - 1) * cellSize, cellSize, cellSize);
            }
            if (x - 1 >= 0 && y - 1 >= 0 && board.getPiece(y - 1, x - 1) != null && !board.getPiece(y - 1, x - 1).isWhite()) {
                g.setColor(Color.GREEN);
                g.fillRect((x - 1) * cellSize, (y - 1) * cellSize, cellSize, cellSize);
            }
        } else {
            if (x + 1 < 8 && y + 1 < 8 && board.getPiece(y + 1, x + 1) != null && board.getPiece(y + 1, x + 1).isWhite()) {
                g.setColor(Color.GREEN);
                g.fillRect((x + 1) * cellSize, (y + 1) * cellSize, cellSize, cellSize);
            }
            if (x - 1 >= 0 && y + 1 < 8 && board.getPiece(y + 1, x - 1) != null && board.getPiece(y + 1, x - 1).isWhite()) {
                g.setColor(Color.GREEN);
                g.fillRect((x - 1) * cellSize, (y + 1) * cellSize, cellSize, cellSize);
            }
        }
    }
}
