package tetris;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

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




    public BorderPane getRoot() {
        return _root;
    }
}
