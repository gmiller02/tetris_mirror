package tetris;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class TetrisSquare {
    private Color _borderColor;
    private Color _boardColor;
    private Rectangle _square;

    public TetrisSquare(Color black) {
        _square = new Rectangle();
        _square.setWidth(Constants.SQUARE_WIDTH);
        _square.setHeight(Constants.SQUARE_WIDTH);
        _borderColor = black;
        _square.setFill(black);


    }

    public void setXLoc(double x) {
        _square.setX(x);
    }

    public double getXLoc() {
        return _square.getX();
    }

    public void setYLoc(double y) {
        _square.setY(y);
    }

    public double getYLoc() {
        return _square.getY();
    }

    public void setLocation(int x, int y) {
        _square.setX(x);
        _square.setY(y);
    }

    public Rectangle getRect() {
        return _square;
    }
}
