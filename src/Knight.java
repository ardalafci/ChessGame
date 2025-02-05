import java.awt.*;
import javax.swing.*;

public class Knight extends ChessPiece {
    private Image image;

    public Knight(boolean isWhite) {
        super("Knight", isWhite);
            image = new ImageIcon(isWhite ? "images/wn.png" : "images/bn.png").getImage(); // Görselleri yükle
    }

    @Override
    public void draw(Graphics g, int x, int y, int cellSize) {
        g.drawImage(image, x + 5, y + 5, cellSize - 10, cellSize - 10, null); // Görseli çiz
    }

    @Override
    public void highlightValidMoves(Graphics g, int x, int y, int cellSize, Board board) {
        int[][] moves = {
                {x + 2, y + 1}, {x + 2, y - 1}, {x - 2, y + 1}, {x - 2, y - 1},
                {x + 1, y + 2}, {x + 1, y - 2}, {x - 1, y + 2}, {x - 1, y - 2}
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

    @Override
    public boolean isValidMove(int startX, int startY, int endX, int endY, Board board) {
        // At, L şeklinde hareket eder (iki kare bir yönde, bir kare diğer yönde)
        if ((Math.abs(startX - endX) == 2 && Math.abs(startY - endY) == 1) ||
                (Math.abs(startX - endX) == 1 && Math.abs(startY - endY) == 2)) {
            ChessPiece targetPiece = board.getPiece(endY, endX);
            if (targetPiece != null && targetPiece.isWhite() == this.isWhite()) {
                return false; // Aynı renkten taş varsa hareket geçersiz
            }
            return true;
        }
        return false;
    }
}
