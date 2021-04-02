package tetris;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.input.KeyCode;
import javafx.event.EventHandler;
import javafx.util.Duration;


/**
 * This is my game class, which is called Tetris. It contains the following instance variables: a 2-D array of TetrisSquares
 * called _tetrisArray, the pane that includes my various game elements, an instance of my piece class, a label, and a
 * boolean that I use to see if the game is paused or not. It also instantiates a local variable called 'quit' which
 * is my quit button. I also have an integer called type, which I use to assign each piece shape a number.
 */

public class Tetris {
    private TetrisSquare[][] _tetrisArray;
    private Pane _boardPane;
    private Piece _piece;
    private Timeline _timeline;
    private Label _label;
    boolean isPaused = false;
    private int type;


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
        _label.setText("Paused");
        _label.setLayoutX(150);
        _label.setLayoutY(300);
        _label.setVisible(false);
        _boardPane.getChildren().add(_label);

    }

    /**
     * This method is responsible for generating the squares that make up my border. This method loops through my entire
     * 2-D board array and fills in specific rows and columns with TetrisSquares.
     */

    public void makeBorder() {
        for (int row=0; row< Constants.ROW_SQUARES; row++) {
            for (int col = 0; col < Constants.COLUMN_SQUARES; col++) {

                if (row == 0 || col == 0 || row == Constants.ROW_SQUARES - 1 || col == Constants.COLUMN_SQUARES - 1){
                    TetrisSquare square = new TetrisSquare(Color.BLUE);
                    square.getRect().setStroke(Color.BLACK);
                    square.setLocation(col*Constants.SQUARE_WIDTH, row*Constants.SQUARE_WIDTH);
                    _boardPane.getChildren().add(square.getRect());
                    _tetrisArray[row][col] = square;
                }

            }
        }
    }

    /**
     * This method returns my board array so that it can be used in other classes.
     * @return
     */

    public TetrisSquare[][] getSquares() {
        return _tetrisArray;
    }

    /**
     * This method is responsible for randomly generating a new instance of the piece class. There are seven different
     * coordinate arrays that the piece array can be arranged in, and every time the method is called, a new one
     * generates. Once the piece is generated, this method loops through every square in the piece and adds it to the
     * board pane.
     */

    public void newPiece() {
        int rand_int = (int) (Math.random() * 6);
        switch (rand_int) {
            case 0:
                _piece = new Piece(Constants.I_PIECE_COORDS, _tetrisArray);
                type = 1;
                break;
            case 1:
                _piece = new Piece(Constants.T_PIECE_COORDS, _tetrisArray);
                type = 2;
                break;
            case 2:
                _piece = new Piece(Constants.O_PIECE_COORDS, _tetrisArray);
                type = 3;
                break;
            case 3:
                _piece = new Piece(Constants.J_PIECE_COORDS, _tetrisArray);
                type = 4;
                break;
            case 4:
                _piece = new Piece(Constants.L_PIECE_COORDS, _tetrisArray);
                type = 5;
                break;
            case 5:
                _piece = new Piece(Constants.N_PIECE_COORDS, _tetrisArray);
                type = 6;
                break;
            default:
                _piece = new Piece(Constants.M_PIECE_COORDS, _tetrisArray);
                type = 6;
                break;

        }
        for (int i = 0; i < 4; i++) {
            _boardPane.getChildren().add(_piece.getComponents()[i].getRect());
        }
    }

    /**
     * This method is responsible for keeping track of the game's behavior once it is paused. If the game is paused,
     * the label 'paused' will show up, the timeline will pause, and the keys will stop working.
     */

    public void pausedGame() {
        if (isPaused) {
            _label.setVisible(false);
            _timeline.play();
        }
        else {
            _timeline.pause();
            _label.setVisible(true);
        }
    }

    /**
     * This boolean method is responsible for checking to see if an entire row in the board has been filled with pieces.
     * It takes in the integer row which represents all of the rows of the array except the border rows.
     * @param row
     * @return
     */

    public boolean checkIfRowIsFull(int row) {
        // the below loop loops through every column except the border columns. If any one space in in a row is empty,
        // the method will return false. If the entire row is full at every space, the method will return true.
        for (int col = 1; col < Constants.COLUMN_SQUARES - 1; col++) {
            if (_tetrisArray[row][col] == null) {
                return false;
            }
        }
        return true;
    }

    /**
     * This method is responsible for clearing the rows once they are full. It uses severa if and for loops that are
     * explained further below.
     */

    public void clearRows() {
        // this statement loops through all of the rows in the board array that are not the border. If one or more of these
        // rows are entirely full, this method loops through every column place in the row and removes the tetrisSquare
        // graphically from the array.
            for (int row=1; row < 29; row++) {
                if (checkIfRowIsFull(row)) {
                    for (int col = 1; col < Constants.COLUMN_SQUARES - 1; col++) {
                        _boardPane.getChildren().remove(_tetrisArray[row][col].getRect());
                    }

                    // this for loop loops through every row in the board array (except for the border) from the bottom
                    // of the screen to the top. Then, it loops through every column in the array except the border
                    // columns. If the row underneath is empty, all of the pieces are shifted down one row.

                    for (int i = row; i > 1; i--) {
                        for (int col = 1; col < Constants.COLUMN_SQUARES - 1; col++) {
                            if (_tetrisArray[i-1][col] != null) {
                                _tetrisArray[i-1][col].setYLoc(_tetrisArray[i-1][col].getYLoc() + Constants.SQUARE_WIDTH);
                            }
                            _tetrisArray[i][col] = _tetrisArray[i - 1][col];
                        }
                    }
                }
        }
    }

    /**
     * This method checks to see if the game is over. If a piece enters the fourth row, then a label saying 'game over'
     * will appear, the timeline will pause, and the keys will stop working.
     */

    public void gameOver() {
            for (int col = 1; col < Constants.COLUMN_SQUARES - 1; col++) {
                if (_tetrisArray[1][col] != null) {
                    Label label = new Label();
                    label.setText("Game Over");
                    _boardPane.getChildren().add(label);
                    label.setLayoutX(150);
                    label.setLayoutY(300);
                    _timeline.pause();
                    _boardPane.setOnKeyPressed(null);
                }
            }
    }

    /**
     * This method sets up my timehandler. Every keyframe is one second, and it goes on indefinitly.
     */



    public void setUpTimeline() {
        KeyFrame kf = new KeyFrame(Duration.seconds(1), new Tetris.TimeHandler());
        _timeline = new Timeline(kf);
        _timeline.setCycleCount(Animation.INDEFINITE);
        _timeline.play();

    }

    /**
     * This is my Time Handler class. It is responsible for generating a new pieces at every keyframe once the previous
     * piece can no longer be moved, adding pieces to the board array, and clearing the rows. It is also responsible
     * for continually moving the pieces down in the array. It handles pausing and checking to see if the game is over.
     */

    private class TimeHandler implements EventHandler<ActionEvent> {

        public void handle(ActionEvent event) {
            if (_piece.checkMoveValidity(0,1) == false) {
                _piece.addToBoard();
                Tetris.this.newPiece();
                Tetris.this.clearRows();
            }
            else {
                _piece.setYLoc( + Constants.SQUARE_WIDTH);
            }
            Tetris.this.gameOver();
        }
    }

    /**
     * This is my KeyHandler class. It contains a switch statement that handles the functions of every key. The left and
     * right arrows check to see if the piece can be moved left and right, and if they can, they move the piece.
     * The up arrow checks to see if the piece can rotate, and if it can, it rotates the piece. The spacebar drops the
     * piece the furthest it can go down the board, and the p key pauses the game.
     */


    private class KeyHandler implements EventHandler<KeyEvent> {
        @Override
        public void handle(KeyEvent e) {
            KeyCode keyPressed = e.getCode();

            if (isPaused != true) {
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
                            if (type != 3) {
                                _piece.rotatePiece();
                                // if a piece is anything but a square, it can be rotated
                            }
                        }
                        break;

                    case DOWN:
                        if (_piece.checkMoveValidity(0,1) == true) {
                            _piece.setYLoc( + Constants.SQUARE_WIDTH);
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



            }
            if (keyPressed == KeyCode.P) {
                Tetris.this.pausedGame();
                isPaused =!isPaused;
            }
            e.consume();
        }
    }

    /**
     * This clickhandler handles my quit button. When the button is clicked, the game exits.
     */

    private class ClickHandler implements EventHandler<ActionEvent> {
        public void handle(ActionEvent Event) {
            System.exit(0);
        }
    }

}
