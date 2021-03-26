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

//    private class KeyHandler implements EventHandler<KeyEvent> {
//        @Override
//        public void handle(KeyEvent e) {
//            KeyCode keyPressed = e.getCode();
//
//            switch (keyPressed) {
//                case LEFT:
//                    Piece.this.setXLoc(Piece.this.getXLoc() - 10);
//                    break;
//                case RIGHT:
//                    Piece.this.setXLoc(Piece.this.getXLoc() + 10);
//                    break;
//                case DOWN:
//                    Piece.this.setYLoc(Piece.this.getYLoc() - 10);
//
//                    break;
//                case P:
//
//                    break;
//                case SPACE:
//
//                    break;
//
//            }
//            e.consume();
//        }
//    }

}
