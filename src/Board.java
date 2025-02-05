public class Board {
    private ChessPiece[][] pieces = new ChessPiece[8][8]; // 8x8 satranç tahtası

    public Board() {
        setupBoard();
    }

    // Taşları tahtaya yerleştir
    private void setupBoard() {
        // Beyaz taşları yerleştir
        pieces[7][0] = new Rook(true);
        pieces[7][7] = new Rook(true);
        pieces[7][1] = new Knight(true);
        pieces[7][6] = new Knight(true);
        pieces[7][2] = new Bishop(true);
        pieces[7][5] = new Bishop(true);
        pieces[7][3] = new Queen(true);
        pieces[7][4] = new King(true);
        for (int i = 0; i < 8; i++) {
            pieces[6][i] = new Pawn(true); // Beyaz piyonlar
        }

        // Siyah taşları yerleştir
        pieces[0][0] = new Rook(false);
        pieces[0][7] = new Rook(false);
        pieces[0][1] = new Knight(false);
        pieces[0][6] = new Knight(false);
        pieces[0][2] = new Bishop(false);
        pieces[0][5] = new Bishop(false);
        pieces[0][3] = new Queen(false);
        pieces[0][4] = new King(false);
        for (int i = 0; i < 8; i++) {
            pieces[1][i] = new Pawn(false); // Siyah piyonlar
        }
    }

    public ChessPiece getPiece(int x, int y) {
        return pieces[x][y];
    }

    public void setPiece(int x, int y, ChessPiece piece) {
        pieces[x][y] = piece;
    }
}
