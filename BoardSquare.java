package tetris;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class BoardSquare {
    private Color _borderColor;
    private Color _boardColor;
    private Rectangle _square;

    public BoardSquare(Color blue) {
        _square = new Rectangle();
        _square.setWidth(Constants.SQUARE_WIDTH);
        _square.setHeight(Constants.SQUARE_WIDTH);
        _square.setFill(_boardColor);
        _borderColor = blue;

    }

    public void setLocation(int x, int y) {
        _square.setX(x);
        _square.setY(y);
    }

    public Rectangle getRect() {
        return _square;
    }
}
