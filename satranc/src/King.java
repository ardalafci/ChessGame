import java.awt.*;
import javax.swing.*;

public class King extends ChessPiece {
    private Image image;

    public King(boolean isWhite) {
        super("King", isWhite);
        image = new ImageIcon(isWhite ? "images/wk.png" : "images/bk.png").getImage(); // Görselleri yükle
    }

    @Override
    public void draw(Graphics g, int x, int y, int cellSize) {
        g.drawImage(image, x + 5, y + 5, cellSize - 10, cellSize - 10, null); // Görseli çiz
    }

    @Override
    public void highlightValidMoves(Graphics g, int x, int y, int cellSize, Board board) {
        int[][] moves = {
                {x + 1, y}, {x - 1, y}, {x, y + 1}, {x, y - 1}, // Yatay ve dikey
                {x + 1, y + 1}, {x - 1, y - 1}, {x + 1, y - 1}, {x - 1, y + 1} // Çapraz
        };

        for (int[] move : moves) {
            int newX = move[0];
            int newY = move[1];

            if (newX >= 0 && newX < 8 && newY >= 0 && newY < 8) {
                ChessPiece piece = board.getPiece(newY, newX);
                if (piece == null || piece.isWhite() != this.isWhite()) {
                    g.setColor(Color.GREEN);
                    g.fillRect(newX * cellSize, newY * cellSize, cellSize, cellSize);
                }
            }
        }
    }
}
