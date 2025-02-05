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

    @Override
    public boolean isValidMove(int startX, int startY, int endX, int endY, Board board) {
        // Yatay hareket (startY == endY)
        if (startY == endY) {
            int step = (endX > startX) ? 1 : -1; // Hedefin sağında mı, solunda mı olduğunu belirle
            for (int i = startX + step; i != endX; i += step) {
                if (board.getPiece(startY, i) != null) {
                    return false; // Eğer bir taş varsa hareket geçersiz
                }
            }
            ChessPiece targetPiece = board.getPiece(endY, endX);
            if (targetPiece != null && targetPiece.isWhite() == this.isWhite()) {
                return false; // Aynı renkten taş varsa hareket geçersiz
            }
            return true;
        }

        // Dikey hareket (startX == endX)
        if (startX == endX) {
            int step = (endY > startY) ? 1 : -1; // Hedefin üstünde mi, altında mı olduğunu belirle
            for (int i = startY + step; i != endY; i += step) {
                if (board.getPiece(i, startX) != null) {
                    return false; // Eğer bir taş varsa hareket geçersiz
                }
            }
            ChessPiece targetPiece = board.getPiece(endY, endX);
            if (targetPiece != null && targetPiece.isWhite() == this.isWhite()) {
                return false; // Aynı renkten taş varsa hareket geçersiz
            }
            return true;
        }

        // Çapraz hareket (startX - startY == endX - endY veya startX + startY == endX + endY)
        if (Math.abs(startX - endX) == Math.abs(startY - endY)) {
            int xStep = (endX > startX) ? 1 : -1;
            int yStep = (endY > startY) ? 1 : -1;
            for (int i = 1; i < Math.abs(startX - endX); i++) {
                if (board.getPiece(startY + i * yStep, startX + i * xStep) != null) {
                    return false; // Eğer bir taş varsa hareket geçersiz
                }
            }
            ChessPiece targetPiece = board.getPiece(endY, endX);
            if (targetPiece != null && targetPiece.isWhite() == this.isWhite()) {
                return false; // Aynı renkten taş varsa hareket geçersiz
            }
            return true;
        }

        return false; // Eğer hareket yatay, dikey veya çapraz değilse geçersiz
    }
}

