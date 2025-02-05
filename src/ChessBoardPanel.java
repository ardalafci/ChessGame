import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ChessBoardPanel extends JPanel {
    private Board board;
    private int cellSize = 60; // Her bir kare için boyut
    private ChessPiece selectedPiece;
    private int selectedX, selectedY;
    private boolean isWhiteTurn = true; // Beyaz oyuncunun sırası başta

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
                    if (piece != null && piece.isWhite() == isWhiteTurn) {  // Oyuncunun sırası gelmişse taş seçilsin
                        selectedPiece = piece;
                        selectedX = x;
                        selectedY = y;
                    }
                } else {
                    if (selectedPiece == piece) {
                        // Eğer aynı taşa tıklanırsa sadece seçimi iptal et
                        selectedPiece = null;
                    } else if (piece != null && piece.isWhite() == isWhiteTurn) {
                        // Farklı bir taş seçildiğinde, önceki taşı sıfırla ve yeni taşı seç
                        selectedPiece = piece;
                        selectedX = x;
                        selectedY = y;
                    } else if (selectedPiece.isValidMove(selectedX, selectedY, x, y, board)) {
                        // Eğer geçerli hamle yapılırsa taşı taşı
                        ChessPiece capturedPiece = board.getPiece(y, x); // Hedef karedeki taşı al
                        board.setPiece(y, x, selectedPiece); // Yeni taşı hedef kareye yerleştir
                        board.setPiece(selectedY, selectedX, null); // Eski taşı kaldır

                        // Eğer taş yeme gerçekleştiyse, rakip taş tahtadan kaldırılacak
                        if (capturedPiece != null) {
                            capturedPiece = null; // Rakip taş kaldırılıyor
                        }

                        selectedPiece = null; // Seçimi sıfırla
                        isWhiteTurn = !isWhiteTurn; // Sıra değiştir
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

        // Sıra bilgisini göster (Beyaz veya Siyah)
        g.setColor(Color.RED);
        g.drawString("Sıra: " + (isWhiteTurn ? "Beyaz" : "Siyah"), 10, 10);
    }
}
