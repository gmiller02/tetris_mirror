package tetris;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

/**
 * This class instantiates my Tetris class, which is my game class. It also instantiates my game pane and the array that
 * I am adding to the board.
 */

public class PaneOrganizer {
    private BorderPane _root;
    private Tetris _board;
    private TetrisSquare[][] _tetrisArray;

    public PaneOrganizer() {
        _root = new BorderPane();
        _root.setStyle("-fx-background-color: #FFFFFF");
        Pane boardPane = new Pane();
        _root.setTop(boardPane);
        _board = new Tetris(boardPane);
        _tetrisArray = _board.getSquares();

    }

    /**
     * This method returns _root so that root can be accessed in other classes.
     */

    public BorderPane getRoot() {
        return _root;
    }
}
