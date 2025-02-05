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

    @Override
    public boolean isValidMove(int startX, int startY, int endX, int endY, Board board) {
        // Fil sadece çapraz hareket eder
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
        return false;
    }
}
