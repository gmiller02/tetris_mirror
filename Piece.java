package tetris;

import javafx.scene.paint.Color;


/**
 * This class contains an Array which is made up of TetrisSquares, which is my piece object,
 * a 2-D array that contains the coordinates of various
 * pieces, an instance variable called color, and an instance of my board array.
 */


public class Piece {
    private TetrisSquare[] _squares;
    private int[][] _coords;
    private Color color;
    private TetrisSquare[][] _board;


    public Piece(int[][] initialCoords, TetrisSquare[][] board) {
        color = this.ColorGenerator();
        _squares = new TetrisSquare[4];
        _coords = initialCoords;
        _board = board;




        this.generatePiece();

    }

    /**
     * This method loops through every square in my piece array and generates a new TetrisSquare. I passed in the
     * instance variable color, which is randomly set in the constructor using another method. I also call the method
     * arrangePieces here, which is outlined below.
     */

    public void generatePiece() {
        for (int i = 0; i < 4; i++) {
            _squares[i] = new TetrisSquare(color);

        }

        this.arrangePieces();
    }

    /**
     * This method loops through every square in the array and sets its x and y location to a location in the coordinates
     * array. I also use constants to offset the generation location of the piece to the relative middle of the screen.
     */

    public void arrangePieces() {
        for (int i = 0; i < 4; i++) {
            _squares[i].setXLoc(_coords[i][0] + Constants.PIECE_X_OFFSET);
            _squares[i].setYLoc(_coords[i][1] + Constants.PIECE_Y_OFFSET);
        }
    }

    /**
     * This method returns the squares array so that other classes can access the paticular piece array.
     * @return
     */

    public TetrisSquare[] getComponents() {
        return _squares;
    }

    /**
     * This method loops through every square in the piece array and sets it to a new X location by obtaining the old
     * x location and adding the value x to it.
     * @param x
     */

    public void setXLoc(double x) {

        for (int i = 0; i < 4; i++) {
            _squares[i].setXLoc(_squares[i].getXLoc() + x);
        }
    }

    /**
     * This method loops through every square in the piece array and sets it to a new Y location by obtaining the old
     * y location and adding the value y to it.
     * @param y
     */


    public void setYLoc(double y) {
        for (int i = 0; i < 4; i++) {
            _squares[i].setYLoc(_squares[i].getYLoc() + y);
        }
    }

    /**
     * This method is responsible for seeing if a piece is able to move or rotate. It loops through the piece array
     * and caculates a new X and Y position based on the locations of the old X and Y positions. Then, it caculates a
     * potential new location in the row and column of the array using the new X and Y locations. If the board array
     * at these new locations contains a piece, the method returns false. Otherwise, it returns true.
     * @param dx
     * @param dy
     * @return
     */


    public boolean checkMoveValidity(int dx, int dy) {

        for (int i =0; i < _squares.length; i++) {
            double newX = _squares[i].getXLoc() + dx * Constants.SQUARE_WIDTH;
            double newY = _squares[i].getYLoc() + dy * Constants.SQUARE_WIDTH;
            int row =  (int) (newY / Constants.SQUARE_WIDTH);
            int col = (int) (newX / Constants.SQUARE_WIDTH);


            if (_board[row][col] != null) {
                return false;
            }

        }
        return true;
    }

    /**
     * This method is responsible for rotating the pieces. It contains two doubles that represent the piece array's center
     * of rotation, which happens to be 0,0, the top left corner of the top left square in the array.
     */


    public void rotatePiece() {
        double centerOfRotationX = _squares[0].getXLoc();
        double centerOfRotationY = _squares[0].getYLoc();

        // the below statement loops through the piece array and sets two doubles that represent the piece's old location
        // It also caculates two new doubles that represent the piece's new x and y location using the center of rotation
        // and the old locations. Then, it sets the X and Y locations of the pieces to the new locations.

        for (int i =0; i < _squares.length; i++) {
            double oldXLocation = _squares[i].getXLoc();;
            double oldYLocation = _squares[i].getYLoc();;
            double newXLoc = centerOfRotationX - centerOfRotationY + oldYLocation;
            double newYLoc = centerOfRotationY + centerOfRotationX - oldXLocation;
            _squares[i].setXLoc(newXLoc);
            _squares[i].setYLoc(newYLoc);
        }
    }

    /**
     * This method is responsible for checking to see if a piece is able to be rotated. It contains two doubles that
     * represent the piece's center of location, which happens to be 0,0 , or the top left corner of the top left
     * square in the array.
     * @return
     */

    public boolean checkRotateValidity() {
        double centerOfRotationX = _squares[0].getXLoc();
        double centerOfRotationY = _squares[0].getYLoc();

        // the below statement loops through every piece array and caculates four doubles. The first two obtain the current
        // location of the piece by obtaining its current x and y locations, and the second two caculate the new X and Y
        // location of the piece using the center of rotation and the old locations.

        for (int i =0; i < _squares.length; i++) {
            double oldXLocation = _squares[i].getXLoc();
            double oldYLocation = _squares[i].getYLoc();
            double newXLoc = centerOfRotationX - centerOfRotationY + oldYLocation;
            double newYLoc = centerOfRotationY + centerOfRotationX - oldXLocation;

            // the below integers use the new X and Y locations and the width of the square to caculate where in the
            // board array the new X and Y locations will go.

            int row =  (int) (newYLoc / Constants.SQUARE_WIDTH);
            int col = (int) (newXLoc / Constants.SQUARE_WIDTH);

            // if the places that the piece will rotate into are occupied, the method will return false. If they are free,
            // the method will return true.

            if (_board[row][col] != null) {
                return false;
            }
        }
        return true;

    }

    /**
     * This method is responsible for adding the pieces to the board array. It loops through every square in the piece
     * array and creates two integers that represent the conversion between a piece's X and Y location to its place
     * in the rows and columns of the array. Then, it adds the squares in the piece array to the board array.
     */

    public void addToBoard() {
                for (int i = 0; i < 4; i++) {
                    int row = (int) _squares[i].getYLoc()/Constants.SQUARE_WIDTH;
                    int col = (int) _squares[i].getXLoc()/Constants.SQUARE_WIDTH;
                    _board[row][col] = _squares[i];
                }
    }

    /**
     * This method sets the variable color to a random color.
     * @return
     */

    public Color ColorGenerator(){
        return Color.color(Math.random(), Math.random(), Math.random());
    }
}
