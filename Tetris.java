package tetris;

import doodlejump.Doodle;
import doodlejump.DoodleJump;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.input.KeyCode;
import javafx.event.EventHandler;
import javafx.util.Duration;


public class Tetris {
    private TetrisSquare[][] _tetrisArray;
    private Pane _boardPane;
    private Piece _piece;
    private Timeline _timeline;


    public Tetris(Pane boardPane) {
        _tetrisArray = new TetrisSquare[Constants.ROW_SQUARES][Constants.COLUMN_SQUARES];
        _boardPane = boardPane;
        this.newPiece();

        this.makeBorder();
        for (int i = 0; i < 4; i++) {
            _boardPane.getChildren().add(_piece.getComponents()[i].getRect());
        }
        _boardPane.addEventHandler(KeyEvent.KEY_PRESSED, new Tetris.KeyHandler());
        _boardPane.setFocusTraversable(true);
        this.newPiece();
        this.setUpTimeline();
    }

    public void makeBorder() {
        for (int row=0; row< Constants.ROW_SQUARES; row++) {
            for (int col = 0; col < Constants.COLUMN_SQUARES; col++) {
                TetrisSquare square = _tetrisArray[row][col];
                _tetrisArray[row][col] = square;

                if (row == 0 || col == 0 || row == 29 || col == 19){
                    square = new TetrisSquare(Color.BLUE);
                    square.setLocation(col*Constants.SQUARE_WIDTH, row*Constants.SQUARE_WIDTH);
                    _boardPane.getChildren().add(square.getRect());
                }

            }
        }
    }

    public TetrisSquare[][] getSquares() {
        return _tetrisArray;
    }

    public Piece newPiece() {
        int rand_int = (int) (Math.random() * 6);
        switch (rand_int) {
            case 0:
                _piece = new Piece(Constants.I_PIECE_COORDS, _tetrisArray);
                return _piece;
            case 1:
                _piece = new Piece(Constants.T_PIECE_COORDS, _tetrisArray);
                return _piece;
            case 2:
                _piece = new Piece(Constants.O_PIECE_COORDS, _tetrisArray);
                return _piece;
            case 3:
                _piece = new Piece(Constants.J_PIECE_COORDS, _tetrisArray);
                return _piece;
            case 4:
                _piece = new Piece(Constants.L_PIECE_COORDS, _tetrisArray);
                return _piece;
            case 5:
                _piece = new Piece(Constants.N_PIECE_COORDS, _tetrisArray);
                return _piece;
            default:
                _piece = new Piece(Constants.M_PIECE_COORDS, _tetrisArray);
                return _piece;

        }

    }

    public void setUpTimeline() {
        KeyFrame kf = new KeyFrame(Duration.seconds(10), new Tetris.TimeHandler());
        _timeline = new Timeline(kf);
        _timeline.setCycleCount(Animation.INDEFINITE);
        _timeline.play();
    }

    private class TimeHandler implements EventHandler<ActionEvent> {

        public void handle(ActionEvent event) {

        }
    }


    private class KeyHandler implements EventHandler<KeyEvent> {
        @Override
        public void handle(KeyEvent e) {
            KeyCode keyPressed = e.getCode();

            switch (keyPressed) {
                case LEFT:
                    if (_piece.checkMoveValidity(-1, 0) == true) {
                            _piece.setXLoc( - Constants.SQUARE_WIDTH);
                            System.out.println("moveValidity works");
                    }
                    System.out.println("KeyHandler works");

                    break;
                case RIGHT:
                        if (_piece.checkMoveValidity(1, 0) == true) {
                            _piece.setXLoc(_piece.getXLoc() + Constants.SQUARE_WIDTH);
                        }
                    break;
                case DOWN:
                        if (_piece.checkMoveValidity(1,0) == true) {
                            _piece.setYLoc(_piece.getYLoc() + Constants.SQUARE_WIDTH);
                        }

                    break;
                case UP:
                    _piece.rotatePiece();
                case P:

                    break;
                case SPACE:

                    break;

            }
            e.consume();
        }
    }


}
