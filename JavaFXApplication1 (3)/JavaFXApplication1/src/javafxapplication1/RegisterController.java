package javafxapplication1;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class RegisterController implements Initializable {

    @FXML
    private Button close;

    @FXML
    private Button registerBtn;

    @FXML
    private Button backToLoginBtn;

    @FXML
    private AnchorPane main_form;

    @FXML
    private PasswordField password;

    @FXML
    private PasswordField confirmPassword;

    @FXML
    private StackPane stack_form;

    @FXML
    private TextField username;

    // Database connection tools
    private Connection connect;
    private PreparedStatement prepare;

    // REGISTER METHOD
    public void register() {
        String user = username.getText();
        String pass = password.getText();
        String confirmPass = confirmPassword.getText();

        // Check if any field is empty
        if (user.isEmpty() || pass.isEmpty() || confirmPass.isEmpty()) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please fill in all fields.");
            alert.showAndWait();
            return;
        }

        // Check if passwords match
        if (!pass.equals(confirmPass)) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Passwords do not match.");
            alert.showAndWait();
            return;
        }

        // Insert new user into the database
        String sql = "INSERT INTO users (username, password) VALUES (?, ?)";

        connect = database.connectDb(); // Assuming you have a database class

        try {
            prepare = connect.prepareStatement(sql);
            prepare.setString(1, user);
            prepare.setString(2, pass);
            prepare.executeUpdate();

            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Information Message");
            alert.setHeaderText(null);
            alert.setContentText("Registration successful!");
            alert.showAndWait();

            // Optionally, redirect to login page
            backToLogin();

        } catch (Exception e) {
            e.printStackTrace(); // Print error to console
        }
    }

    // BACK TO LOGIN METHOD
    public void backToLogin() {
        try {
            // Load login.fxml
            Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
            Stage stage = (Stage) backToLoginBtn.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // CLOSE METHOD
    public void exit() {
        System.exit(0); // Exit the entire application
    }

    // Initialization logic
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Any setup code when FXML loads (optional)
    }
}
