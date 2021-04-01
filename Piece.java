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

        for (int i = 0; i < 4; i++) {
            _squares[i].setXLoc(_squares[i].getXLoc() + x);
        }
    }

    public double getXLoc() {
        return _squares[0].getRect().getX();
    }

    public void setYLoc(double y) {
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


            if (_board[row][col] != null) {
                return false;
            }

        }
        return true;
    }


    public void rotatePiece() {
        double centerOfRotationX = _squares[0].getXLoc();
        double centerOfRotationY = _squares[0].getYLoc();


        for (int i =0; i < _squares.length; i++) {
            double oldXLocation = _squares[i].getXLoc();;
            double oldYLocation = _squares[i].getYLoc();;
            double newXLoc = centerOfRotationX - centerOfRotationY + oldYLocation;
            double newYLoc = centerOfRotationY + centerOfRotationX - oldXLocation;
            _squares[i].setXLoc(newXLoc);
            _squares[i].setYLoc(newYLoc);
            int row =  (int) (newYLoc / Constants.SQUARE_WIDTH);
            int col = (int) (newXLoc / Constants.SQUARE_WIDTH);

        }


    }

    public boolean checkRotateValidity() {
        double centerOfRotationX = _squares[0].getXLoc();
        double centerOfRotationY = _squares[0].getYLoc();


        for (int i =0; i < _squares.length; i++) {
            double oldXLocation = _squares[i].getXLoc();;
            double oldYLocation = _squares[i].getYLoc();;
            double newXLoc = centerOfRotationX - centerOfRotationY + oldYLocation;
            double newYLoc = centerOfRotationY + centerOfRotationX - oldXLocation;

            int row =  (int) (newYLoc / Constants.SQUARE_WIDTH);
            int col = (int) (newXLoc / Constants.SQUARE_WIDTH);

            if (_board[row][col] != null) {
                return false;
            }
        }
        return true;

    }

    public void addToBoard() {
                for (int i = 0; i < 4; i++) {
                    int row = (int) _squares[i].getYLoc()/Constants.SQUARE_WIDTH;
                    int col = (int) _squares[i].getXLoc()/Constants.SQUARE_WIDTH;
                    _board[row][col] = _squares[i];
                }
    }



    public Color ColorGenerator(){
        return Color.color(Math.random(), Math.random(), Math.random());
    }


}
