package javafxapplication1;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

/**
 * Controller class for the About Us screen.
 */
public class AboutUSController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Initialization logic here (if needed)
    }

    // Method to show the About Us page
    public void showAboutUsPage() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Aboutus.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setMinHeight(509); // 494 + 15
            stage.setMinWidth(413);  // 398 + 15
            stage.setTitle("About Us");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


