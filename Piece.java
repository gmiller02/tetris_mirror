package tetris;

import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;

public class Piece {
    private TetrisSquare[] _squares;
    private int[][] _coords;


    public Piece(int[][] initialCoords) {
        Color color = this.ColorGenerator();
        _squares = new TetrisSquare[4];
        _coords = initialCoords;

        this.generatePiece();

    }

    public void generatePiece() {
        for (int i = 0; i < 4; i++) {
            _squares[i] = new TetrisSquare(this.ColorGenerator());
        }
        this.arrangePieces();
    }

    public void arrangePieces() {
        for (int i = 0; i < 4; i++) {
            _squares[i].setXLoc(_coords[i][1] + 100);
            _squares[i].setYLoc(_coords[i][0] + 100);
        }
    }

    public TetrisSquare[] getComponents() {
        return _squares;
    }




    public void setXLoc(double x, int i) {
        _squares[i].getRect().setX(x);
    }

    public double getXLoc(double x, int i) {
        return _squares[i].getRect().getX();
    }

    public void setYLoc(double y, int i) {
        _squares[i].getRect().setY(y);
    }

    public double getYLoc(double y, int i) {
        return _squares[i].getRect().getY();
    }


    public Color ColorGenerator(){
        return Color.color(Math.random(), Math.random(), Math.random());
    }

}
