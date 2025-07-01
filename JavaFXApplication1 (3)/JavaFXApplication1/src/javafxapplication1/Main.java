package javafxapplication1;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.time.temporal.ChronoUnit;

public class Main extends Application {

    // These variables store the position of the mouse when you click on the window
    private double x = 0;
    private double y = 0;

    @Override
    public void start(Stage stage) throws Exception {
        // Load the FXML file that defines your UI
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));

        // Create the scene from the loaded FXML root
        Scene scene = new Scene(root);

        // Handle mouse press event — when you click on the window
        root.setOnMousePressed((MouseEvent event) -> {
            // Get the position of the mouse inside the scene (the window)
            x = event.getSceneX();
            y = event.getSceneY();
        });

        // Handle mouse drag event — when you drag the mouse with button pressed
        root.setOnMouseDragged((MouseEvent event) -> {
            // Move the stage (window) by adjusting its position on the screen
            // This gives the effect of dragging the window
            stage.setX(event.getScreenX() - x); // New X position = mouse's X on screen - where you clicked
            stage.setY(event.getScreenY() - y); // New Y position = mouse's Y on screen - where you clicked
            stage.setOpacity(0.7); // when i drage it the opacity decreases 
        });
        
        root.setOnMouseReleased((MouseEvent event)->{
        stage.setOpacity(1);
    });

        // Set the stage style to TRANSPARENT (no default title bar or borders)
        stage.initStyle(StageStyle.TRANSPARENT);

        // Set the scene on the stage and show it
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        // Launch the JavaFX application
        launch(args);
    }
}



//
//    @Override
//    public void start(Stage primaryStage) throws Exception {
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("checkin.fxml"));
//        Parent root = loader.load();
//        primaryStage.setTitle("Hotel Check-In");
//        primaryStage.setScene(new Scene(root, 700, 450));
//        primaryStage.show();
//    }
//
//    public static void main(String[] args) {
//        launch(args);
//    }

