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
    private TetrisSquare[][] _board;
    int x;
    int y;
    int i;


    public Piece(int[][] initialCoords, TetrisSquare[][] board) {
        color = this.ColorGenerator();
        _squares = new TetrisSquare[4];
        _coords = initialCoords;
        _board = board;



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


    public void setXLoc(double x) {
        System.out.println("hi");
        for (int i = 0; i < 4; i++) {
            _squares[i].setXLoc(_squares[i].getXLoc() + x);
            //System.out.println(_squares[i].getXLoc());
        }
    }

    public double getXLoc() {
        return _squares[0].getRect().getX();
    }

    public void setYLoc(double x) {
        for (int i = 0; i < 4; i++) {
            _squares[i].setYLoc(_squares[i].getYLoc() + y);
        }
    }

    public double getYLoc() {
        return _squares[0].getRect().getY();
    }

    public boolean checkMoveValidity(int dx, int dy) {

        for (int i =0; i < _squares.length; i++) {
            double newX = _squares[i].getXLoc() + dx * Constants.SQUARE_WIDTH;
            double newY = _squares[i].getYLoc() + dy * Constants.SQUARE_WIDTH;
            int row =  (int) (newY / Constants.SQUARE_WIDTH);
            int col = (int) (newX / Constants.SQUARE_WIDTH);

            System.out.println("row" + row);
            System.out.println("col" + col);

//            if (row >= Constants.ROW_SQUARES || col >= Constants.COLUMN_SQUARES) {
//                return false;
//            }
//            else if (row < 0 || col < 0) {
//                return false;
//            }

            if (_board[row][col] != null) {
                return false;
            }

        }
        return true;
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


        if (this.checkMoveValidity(0, 0) == true) {
            _squares[i].getRect().setX(newXLoc);
            _squares[i].getRect().setY(newYLoc);
        }

    }

//    public void fall() {
//        double y;
//        for (int row = 0; row < Constants.ROW_SQUARES; row++) {
//            for (int col = 0; col < Constants.COLUMN_SQUARES; col++) {
//                if (this.checkMoveValidity() == true) {
//                    //_squares[i].getYLoc(y).setYLoc() -
//                }
//
//            }
//        }
//    }


    public Color ColorGenerator(){
        return Color.color(Math.random(), Math.random(), Math.random());
    }


}
