package tetris;

import doodlejump.Doodle;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.input.KeyCode;
import javafx.event.EventHandler;

public class Tetris {
    private TetrisSquare[][] _tetrisArray;
    private Pane _boardPane;
    private Piece _piece;


    public Tetris(Pane boardPane) {
        _tetrisArray = new TetrisSquare[Constants.ROW_SQUARES][Constants.COLUMN_SQUARES];
        _boardPane = boardPane;
        this.newPiece();

        for (int row=0; row< Constants.ROW_SQUARES; row++) {
            for (int col=0; col < Constants.COLUMN_SQUARES; col++) {
                TetrisSquare square = null;

                square = new TetrisSquare(Color.BLACK);
                square.getRect().setFill(Color.BLACK);

                square.setLocation(col*Constants.SQUARE_WIDTH, row*Constants.SQUARE_WIDTH);
                _tetrisArray[row][col] = square;

            }
        }
        this.makeBorder();
        this.setUpBoardPane();
        for (int i = 0; i < 4; i++) {
            _boardPane.getChildren().add(_piece.getComponents()[i].getRect());
        }
        _boardPane.addEventHandler(KeyEvent.KEY_PRESSED, new Tetris.KeyHandler());
        _boardPane.setFocusTraversable(true);
        this.newPiece();
    }

    public void makeBorder() {
        for (int row=0; row< Constants.ROW_SQUARES; row++) {
            for (int col = 0; col < Constants.COLUMN_SQUARES; col++) {
                TetrisSquare square = _tetrisArray[row][col];
                if (row == 0 || col == 0) {
                    square.getRect().setFill(Color.BLUE);
                }

                if (row == 29) {
                    square.getRect().setFill(Color.BLUE);
                }

                if (col == 19) {
                    square.getRect().setFill(Color.BLUE);
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
                _piece = new Piece(Constants.I_PIECE_COORDS);
                return _piece;
            case 1:
                _piece = new Piece(Constants.T_PIECE_COORDS);
                return _piece;
            case 2:
                _piece = new Piece(Constants.O_PIECE_COORDS);
                return _piece;
            case 3:
                _piece = new Piece(Constants.J_PIECE_COORDS);
                return _piece;
            case 4:
                _piece = new Piece(Constants.L_PIECE_COORDS);
                return _piece;
            case 5:
                _piece = new Piece(Constants.N_PIECE_COORDS);
                return _piece;
            default:
                _piece = new Piece(Constants.M_PIECE_COORDS);
                return _piece;

        }

    }

    private void setUpBoardPane() {

        for (int row = 0; row < Constants.ROW_SQUARES; row++) {
            for (int col = 0; col < Constants.COLUMN_SQUARES; col++) {
                System.out.println(_boardPane);
                System.out.println(_tetrisArray);
                _boardPane.getChildren().add(_tetrisArray[row][col].getRect());
            }
        }
    }

    private class KeyHandler implements EventHandler<KeyEvent> {
        @Override
        public void handle(KeyEvent e) {
            KeyCode keyPressed = e.getCode();

            switch (keyPressed) {
                case LEFT:
                    for (int i=0; i < 4; i++) {
                        _piece.movePiece(_piece.getXLoc(i) - 10, i);
                    }
                    break;
                case RIGHT:
                    for (int i=0; i < 4; i++) {
                        _piece.movePiece(_piece.getXLoc(i) + 10, i);
                    }
                    break;
                case DOWN:
                    for (int i=0; i < 4; i++) {
                        _piece.movePiece(_piece.getYLoc(i) - 10, i);
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
