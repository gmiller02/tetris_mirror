package tetris;

import javafx.application.Platform;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;
import javafx.event.EventHandler;


public class Piece {
    private TetrisSquare[] _squares;
    private int[][] _coords;
    private Color color;
    private TetrisSquare[][] _tetrisArray;
    int x;
    int y;
    int i;


    public Piece(int[][] initialCoords) {
        color = this.ColorGenerator();
        _squares = new TetrisSquare[4];
        _coords = initialCoords;


        this.generatePiece();

    }

    public void generatePiece() {
        for (int i = 0; i < 4; i++) {
            _squares[i] = new TetrisSquare(color);

        }

        this.arrangePieces();
    }

    public void arrangePieces() {
        for (int i = 0; i < 4; i++) {
            _squares[i].setXLoc(_coords[i][0] + 100);
            _squares[i].setYLoc(_coords[i][1] + 100);
        }
    }

    public TetrisSquare[] getComponents() {
        return _squares;
    }


    public void setXLoc(double x, int i) {
        _squares[i].getRect().setX(x);
    }

    public double getXLoc(int i) {
        return _squares[i].getRect().getX();
    }

    public void setYLoc(double y, int i) {
        _squares[i].getRect().setY(y);
    }

    public double getYLoc(int i) {
        return _squares[i].getRect().getY();
    }

    public boolean checkMoveValidity() {
        for (int row=0; row< Constants.ROW_SQUARES; row++) {
            for (int col = 0; col < Constants.COLUMN_SQUARES; col++) {
                TetrisSquare square = _tetrisArray[row][col];
                if (square.getRect() == null) {
                    return false;
                }
            }
        }
        return true;
    }

    public void movePiece(double x, double y, int i) {
        while (this.checkMoveValidity() == true) {
            _squares[i].getRect().setX(x);
            _squares[i].getRect().setY(y);
        }
    }

    public void rotatePiece() {
        int centerOfRotationX = 0;
        int centerOfRotationY = 0;
        int oldXLocation = x;
        int oldYLocation = y;
        int newXLoc = centerOfRotationX - centerOfRotationY + oldYLocation;
        int newYLoc = centerOfRotationY + centerOfRotationX - oldXLocation;

        _squares[i].getRect().setX(x);
        _squares[i].getRect().setY(y);


        if (this.checkMoveValidity() == true) {
            _squares[i].getRect().setX(newXLoc);
            _squares[i].getRect().setY(newYLoc);
        }

    }


    public Color ColorGenerator(){
        return Color.color(Math.random(), Math.random(), Math.random());
    }


}
