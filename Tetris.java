package tetris;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class Tetris {
    private BoardSquare[][] _tetrisArray;


    public Tetris() {
        _tetrisArray = new BoardSquare[Constants.ROW_SQUARES][Constants.COLUMN_SQUARES];

        for (int row=0; row< Constants.ROW_SQUARES; row++) {
            for (int col=0; col < Constants.COLUMN_SQUARES; col++) {
                BoardSquare square = null;
                if ((row + col) == 0) {
                    square = new BoardSquare(Color.BLUE);
                }
                else {
                    square = new BoardSquare(Color.BLACK);
                }
                square.setLocation(col*Constants.SQUARE_WIDTH, row*Constants.SQUARE_WIDTH);
                _tetrisArray[row][col] = square;
            }
        }

    }

    public BoardSquare[][] getSquares() {
        return _tetrisArray;
    }
}
