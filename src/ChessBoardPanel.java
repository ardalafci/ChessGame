import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ChessBoardPanel extends JPanel {
    private Board board;
    private int cellSize = 60; // Her bir kare için boyut
    private ChessPiece selectedPiece;
    private int selectedX, selectedY;

    public ChessBoardPanel(Board board) {
        this.board = board;
        setPreferredSize(new Dimension(cellSize * 8, cellSize * 8)); // Tahtayı 8x8 kare olarak boyutlandır

        // Fare tıklama olayını dinle
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int x = e.getX() / cellSize;
                int y = e.getY() / cellSize;

                ChessPiece piece = board.getPiece(y, x);

                if (selectedPiece == null) {
                    // Hiç taş seçili değilse ve bir taş varsa, o taşı seç
                    if (piece != null) {
                        selectedPiece = piece;
                        selectedX = x;
                        selectedY = y;
                    }
                } else {
                    if (selectedPiece == piece) {
                        // Eğer aynı taşa tıklanırsa sadece seçimi iptal et
                        selectedPiece = null;
                    } else if (piece != null) {
                        // Farklı bir taş seçildiğinde, önceki taşı sıfırla ve yeni taşı seç
                        selectedPiece = piece;
                        selectedX = x;
                        selectedY = y;
                    } else if (isValidMove(selectedPiece, selectedX, selectedY, x, y)) {
                        // Eğer geçerli hamle yapılırsa taşı taşı
                        board.setPiece(y, x, selectedPiece);
                        board.setPiece(selectedY, selectedX, null);
                        selectedPiece = null; // Seçimi sıfırla
                    }
                }
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Tahtayı çiz
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                if ((row + col) % 2 == 0) {
                    g.setColor(Color.WHITE);
                } else {
                    g.setColor(Color.DARK_GRAY);
                }
                g.fillRect(col * cellSize, row * cellSize, cellSize, cellSize);
                ChessPiece piece = board.getPiece(row, col);
                if (piece != null) {
                    piece.draw(g, col * cellSize, row * cellSize, cellSize); // Taşı çiz
                }

                // Seçili taşın geçerli hamlelerini vurgula
                if (selectedPiece != null && row == selectedY && col == selectedX) {
                    g.setColor(new Color(0, 255, 0, 100)); // Yeşil renkte yarı saydam vurgulama
                    g.fillRect(col * cellSize, row * cellSize, cellSize, cellSize);
                    // Seçili taşın geçerli hamlelerini göster
                    selectedPiece.highlightValidMoves(g, col, row, cellSize, board);
                }
            }
        }

        // Tahtanın kenarına a-h ve 1-8 etiketlerini ekle
        g.setColor(Color.BLACK);
        for (int i = 0; i < 8; i++) {
            g.drawString(String.valueOf((char) ('a' + i)), i * cellSize + cellSize / 3, 8 * cellSize + 20); // a-h
            g.drawString(String.valueOf(8 - i), 8 * cellSize + 10, i * cellSize + cellSize / 2); // 1-8
        }
    }


    // Geçerli bir hamleyi kontrol et
    private boolean isValidMove(ChessPiece piece, int startX, int startY, int endX, int endY) {
        // Burada her taşın geçerli hareketlerini kontrol etmeliyiz.
        // Örneğin, Kale için hareketleri kontrol edebiliriz.
        if (piece instanceof Rook) {
            return isValidRookMove(startX, startY, endX, endY);
        }
        // Diğer taşlar için hareket kuralları ekleyebilirsin.
        return false;
    }

    private boolean isValidRookMove(int startX, int startY, int endX, int endY) {
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
        return true;
    }

}
