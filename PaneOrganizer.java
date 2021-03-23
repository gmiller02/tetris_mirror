package tetris;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class PaneOrganizer {
    private BorderPane _root;
    private Tetris _board;
    private BoardSquare[][] _tetrisArray;

    public PaneOrganizer() {
        _root = new BorderPane();
        _root.setStyle("-fx-background-color: #FFFFFF");
        _board = new Tetris();
        _tetrisArray = _board.getSquares();
        this.setUpBoardPane();
    }

    private void setUpBoardPane() {
        Pane boardPane = new Pane();
        _root.setTop(boardPane);
        for (int row=0; row< Constants.ROW_SQUARES; row++) {
            for (int col=0; col < Constants.COLUMN_SQUARES; col++) {
                boardPane.getChildren().add(_tetrisArray[row][col].getRect());
            }
    }

    public BorderPane getRoot() {
        return _root;
    }
}
