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

                // Eğer taş seçildiyse
                if (piece != null) {
                    if (selectedPiece == null) {
                        selectedPiece = piece;
                        selectedX = x;
                        selectedY = y;
                    } else {
                        // Eğer taş seçiliyse, yeni bir kareye taşımayı dene
                        if (piece == selectedPiece || isValidMove(selectedPiece, selectedX, selectedY, x, y)) {
                            // Taşı taşı
                            board.setPiece(y, x, selectedPiece);
                            board.setPiece(selectedY, selectedX, null); // Eski konumu boşalt
                            selectedPiece = null; // Seçili taşı sıfırla
                        } else {
                            // Geçersiz hamle
                            selectedPiece = null; // Seçimi sıfırla
                        }
                    }
                }
                repaint(); // Tahtayı yeniden çiz
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
        return startX == endX || startY == endY;
    }
}
