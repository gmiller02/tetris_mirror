package tetris;

import javafx.scene.paint.Color;

public class Constants {
     // TODO: fill this class in with any more constants you might need!

    public static final int ROW_SQUARES = 30;
    public static final int COLUMN_SQUARES = 20;

    // width of each square
    public static final int SQUARE_WIDTH = 20;

    // coordinates for squares in each tetris piece
    public static final int[][] I_PIECE_COORDS = { {0, 0}, {0, SQUARE_WIDTH}, {0, 2*SQUARE_WIDTH}, {0, 3*SQUARE_WIDTH} };
    public static final int[][] T_PIECE_COORDS = { {-1*SQUARE_WIDTH, 0}, {-1*SQUARE_WIDTH, SQUARE_WIDTH}, {-1*SQUARE_WIDTH, 2*SQUARE_WIDTH}, {0, SQUARE_WIDTH}};

}
