package tetris;

import doodlejump.Doodle;
import doodlejump.DoodleJump;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
    private Label _label;


    public Tetris(Pane boardPane) {
        _tetrisArray = new TetrisSquare[Constants.ROW_SQUARES][Constants.COLUMN_SQUARES];
        _boardPane = boardPane;
        this.newPiece();

        this.makeBorder();

        _boardPane.addEventHandler(KeyEvent.KEY_PRESSED, new Tetris.KeyHandler());
        _boardPane.setFocusTraversable(true);
        this.setUpTimeline();

        Button quit = new Button();
        quit.setText("Quit");
        quit.setOnAction(new Tetris.ClickHandler());
        _boardPane.getChildren().add(quit);

        _label = new Label();
        _label.setText("Game Over");
        _boardPane.getChildren().add(_label);
        _label.setLayoutX(150);
        _label.setLayoutY(300);
        _label.setVisible(false);
    }

    public void makeBorder() {
        for (int row=0; row< Constants.ROW_SQUARES; row++) {
            for (int col = 0; col < Constants.COLUMN_SQUARES; col++) {

                if (row == 0 || col == 0 || row == 29 || col == 19){
                    TetrisSquare square = new TetrisSquare(Color.BLUE);
                    square.setLocation(col*Constants.SQUARE_WIDTH, row*Constants.SQUARE_WIDTH);
                    _boardPane.getChildren().add(square.getRect());
                    _tetrisArray[row][col] = square;
                }

            }
        }
    }

    public TetrisSquare[][] getSquares() {
        return _tetrisArray;
    }

    public void newPiece() {
        int rand_int = (int) (Math.random() * 6);
        switch (rand_int) {
            case 0:
                _piece = new Piece(Constants.I_PIECE_COORDS, _tetrisArray);
                break;
            case 1:
                _piece = new Piece(Constants.T_PIECE_COORDS, _tetrisArray);
                break;
            case 2:
                _piece = new Piece(Constants.O_PIECE_COORDS, _tetrisArray);
                break;
            case 3:
                _piece = new Piece(Constants.J_PIECE_COORDS, _tetrisArray);
                break;
            case 4:
                _piece = new Piece(Constants.L_PIECE_COORDS, _tetrisArray);
                break;
            case 5:
                _piece = new Piece(Constants.N_PIECE_COORDS, _tetrisArray);
                break;
            default:
                _piece = new Piece(Constants.M_PIECE_COORDS, _tetrisArray);
                break;

        }
        for (int i = 0; i < 4; i++) {
            _boardPane.getChildren().add(_piece.getComponents()[i].getRect());
        }

    }

    public boolean checkIfRowIsFull(int row) {
        for (int col = 0; col < Constants.COLUMN_SQUARES; col++) {
            if (_tetrisArray[row][col] == null) {
                return false;
            }
        }
        return true;
    }

    public void clearRows() {
            for (int row=0; row<= 1 && row >= 28; row ++) {
                if (checkIfRowIsFull(row) == true) {
                    for (int col = 0; col < Constants.COLUMN_SQUARES; col++) {
                        _boardPane.getChildren().remove(_tetrisArray[row][col].getRect());
                    }
                }

            for (row = 0; row >= 28 && row<= 1; row ++) {
                for (int col = 0; col < Constants.COLUMN_SQUARES; col++) {
                    _tetrisArray[row][col] = _tetrisArray[row - 1][col];
                }
            }
        }
    }

    public void gameOver() {
        for (int row = 0; row == 1; row ++) {
//            if (piece is in board) {
//                _label.setVisible(true);
//            }
        }
    }

    public void setUpTimeline() {
        KeyFrame kf = new KeyFrame(Duration.seconds(1), new Tetris.TimeHandler());
        _timeline = new Timeline(kf);
        _timeline.setCycleCount(Animation.INDEFINITE);
        _timeline.play();
    }

    private class TimeHandler implements EventHandler<ActionEvent> {

        public void handle(ActionEvent event) {
            if (_piece.checkMoveValidity(0,1) == false) {
                _piece.addToBoard();
                Tetris.this.newPiece();
            }
            Tetris.this.clearRows();
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
                    }
                    break;
                case RIGHT:
                        if (_piece.checkMoveValidity(1, 0) == true) {
                            _piece.setXLoc( + Constants.SQUARE_WIDTH);
                        }
                    break;
                case UP:
                    if (_piece.checkRotateValidity() == true) {
                        _piece.rotatePiece();
                    }
                    break;
                case SPACE:
                    while (_piece.checkMoveValidity(0,1) == true) {
                        _piece.setYLoc( + Constants.SQUARE_WIDTH);
                    }
                    if (_piece.checkMoveValidity(0,1) == false) {
                        _piece.addToBoard();
                    }
                    break;

            }
            e.consume();
        }
    }

    private class ClickHandler implements EventHandler<ActionEvent> {
        public void handle(ActionEvent Event) {
            System.exit(0);
        }
    }

}
