package tetris;

import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;

public class Piece {

    public Piece() {
        Color color = this.ColorGenerator();

        int ipieceArray[][] = Constants.I_PIECE_COORDS;



    }

    public Color ColorGenerator(){
        return Color.color(Math.random(), Math.random(), Math.random());
    }
}
