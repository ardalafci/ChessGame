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
                g.setColor(new Color(0, 255, 0, 100)); // Açık yeşil renkte yarı saydam
                g.fillOval((x + i) * cellSize + cellSize / 4, y * cellSize + cellSize / 4, cellSize / 2, cellSize / 2);
            } else {
                break;
            }
        }

        for (int i = 1; i < 8; i++) {
            // Yatay (sola)
            if (x - i >= 0 && board.getPiece(y, x - i) == null) {
                g.setColor(new Color(0, 255, 0, 100)); // Açık yeşil renkte yarı saydam
                g.fillOval((x - i) * cellSize + cellSize / 4, y * cellSize + cellSize / 4, cellSize / 2, cellSize / 2);
            } else {
                break;
            }
        }

        for (int i = 1; i < 8; i++) {
            // Dikey (aşağı)
            if (y + i < 8 && board.getPiece(y + i, x) == null) {
                g.setColor(new Color(0, 255, 0, 100)); // Açık yeşil renkte yarı saydam
                g.fillOval(x * cellSize + cellSize / 4, (y + i) * cellSize + cellSize / 4, cellSize / 2, cellSize / 2);
            } else {
                break;
            }
        }

        for (int i = 1; i < 8; i++) {
            // Dikey (yukarı)
            if (y - i >= 0 && board.getPiece(y - i, x) == null) {
                g.setColor(new Color(0, 255, 0, 100)); // Açık yeşil renkte yarı saydam
                g.fillOval(x * cellSize + cellSize / 4, (y - i) * cellSize + cellSize / 4, cellSize / 2, cellSize / 2);
            } else {
                break;
            }
        }
    }

    @Override
    public boolean isValidMove(int startX, int startY, int endX, int endY, Board board) {
        // Kale yalnızca yatay veya dikey hareket eder
        if (startX == endX) {
            // Dikey hareket
            int step = (endY > startY) ? 1 : -1; // Hedefin üstünde mi, altında mı olduğunu belirle
            for (int i = startY + step; i != endY; i += step) {
                if (board.getPiece(i, startX) != null) {
                    return false; // Eğer bir taş varsa hareket geçersiz
                }
            }
        } else if (startY == endY) {
            // Yatay hareket
            int step = (endX > startX) ? 1 : -1; // Hedefin sağında mı, solunda mı olduğunu belirle
            for (int i = startX + step; i != endX; i += step) {
                if (board.getPiece(endY, i) != null) {
                    return false; // Eğer bir taş varsa hareket geçersiz
                }
            }
        } else {
            return false; // Yalnızca yatay ve dikey hareketler geçerlidir
        }

        // Taşın hedef karedeki taşın rengini kontrol et
        ChessPiece targetPiece = board.getPiece(endY, endX);
        if (targetPiece != null && targetPiece.isWhite() == this.isWhite()) {
            return false; // Aynı renkten taş varsa hareket geçersiz
        }

        return true;
    }

}
