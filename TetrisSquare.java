package tetris;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


/**
 * This class contains the object TetrisSquare. TetrisSquare is a simple rectangular shape that I use to make up the
 * arrays that become my board and piece. This class also contains a color that sets the default color of a TetrisSquare
 * to be black.
 * This class contains simple getter and setter methods that allow the squares
 * to be positioned in their respective arrays and a getRect method.
 */

public class TetrisSquare {
    private Color _borderColor;
    private Rectangle _square;

    public TetrisSquare(Color black) {
        _square = new Rectangle();
        _square.setWidth(Constants.SQUARE_WIDTH);
        _square.setHeight(Constants.SQUARE_WIDTH);
        _borderColor = black;
        _square.setFill(black);


    }

    /**
     * Creates the variable x, which corresponds to the value of the square's x position, and sets said position.
     * @param x
     */

    public void setXLoc(double x) {
        _square.setX(x);
    }

    /**
     * Returns the value of the square's x position so that other classes are able to access the value.
     * @return
     */

    public double getXLoc() {
        return _square.getX();
    }

    /**
     * Creates the variable y, which corresponds to the value of the square's y position, and sets said position.
     * @param y
     */

    public void setYLoc(double y) {
        _square.setY(y);
    }

    /**
     * Returns the value of the square's y position so that other classes are able to access the value.
     * @return
     */

    public double getYLoc() {
        return _square.getY();
    }

    /**
     * Sets the x and y position of a square at once.
     * @param x
     * @param y
     */

    public void setLocation(int x, int y) {
        _square.setX(x);
        _square.setY(y);
    }

    /**
     * Returns a square, which is a rectangle node, so that all of the methods associated with a rectangle can be called
     * on the arrays that are made up of TetrisSquares.
     * @return
     */

    public Rectangle getRect() {
        return _square;
    }
}
