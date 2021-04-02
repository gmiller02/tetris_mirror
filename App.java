package tetris;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;

/**
 * It's time for Tetris! This is the  main class to get things started.
 *
 * The main method of this application calls the start method. You
 * will need to fill in the start method to instantiate your game.
 *
 * This class instantiates my PaneOrganizer.
 *
 */

public class App extends Application {

    @Override
    public void start(Stage stage) {
        PaneOrganizer organizer = new PaneOrganizer();
        stage.setScene(new Scene(organizer.getRoot(), 400, 600));
        stage.setTitle("Stage");
        stage.show();
        // Create top-level object, set up the scene, and show the stage here.
    }

    /*
     * Here is the mainline! No need to change this.
     */
    public static void main(String[] argv) {
        // launch is a method inherited from Application
        launch(argv);
    }
}
