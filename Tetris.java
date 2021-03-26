package tetris;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class Tetris {
    private TetrisSquare[][] _tetrisArray;
    private Pane _boardPane;
    private Piece _piece;


    public Tetris(Pane boardPane) {
        _tetrisArray = new TetrisSquare[Constants.ROW_SQUARES][Constants.COLUMN_SQUARES];
        _boardPane = boardPane;
        _piece = new Piece(Constants.O_PIECE_COORDS);

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

    private void setUpBoardPane() {

        for (int row = 0; row < Constants.ROW_SQUARES; row++) {
            for (int col = 0; col < Constants.COLUMN_SQUARES; col++) {
                System.out.println(_boardPane);
                System.out.println(_tetrisArray);
                _boardPane.getChildren().add(_tetrisArray[row][col].getRect());
            }
        }
    }
}
