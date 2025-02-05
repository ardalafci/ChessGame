import java.awt.*;
import javax.swing.*;

public abstract class ChessPiece {
    private String name;
    private boolean isWhite; // Taşın rengi

    public ChessPiece(String name, boolean isWhite) {
        this.name = name;
        this.isWhite = isWhite;
    }

    public String getName() {
        return name;
    }

    public boolean isWhite() {
        return isWhite;
    }

    public abstract void draw(Graphics g, int x, int y, int cellSize);

    public abstract void highlightValidMoves(Graphics g, int x, int y, int cellSize, Board board);
}
