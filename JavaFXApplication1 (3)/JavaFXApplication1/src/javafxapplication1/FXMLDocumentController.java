package javafxapplication1;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
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
import javafx.scene.input.MouseEvent;

public class FXMLDocumentController implements Initializable {

    @FXML
    private Button close;

    @FXML
    private Button loginBtn;

    @FXML
    private Button registerBtn;
    
    @FXML
    private AnchorPane main_form;

    @FXML
    private PasswordField password;

    @FXML
    private StackPane stack_form;

    @FXML
    private TextField username;

    // Database connection tools
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;

    private double x = 0;
    private double y = 0;

    // REGISTER BUTTON ACTION
    public void register(ActionEvent event){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Register.fxml"));
            Stage stage = (Stage) registerBtn.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
        } catch(Exception e){
            e.printStackTrace(); 
        }
    }

    // LOGIN METHOD with Thread
    public void login() {
        String user = username.getText();
        String pass = password.getText();

        if (user.isEmpty() || pass.isEmpty()) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please fill in all fields.");
            alert.showAndWait();
            return;
        }

        Thread loginThread = new Thread(() -> {
            String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
            connect = database.connectDb();

            try {
                prepare = connect.prepareStatement(sql);
                prepare.setString(1, user);
                prepare.setString(2, pass);
                result = prepare.executeQuery();

                if (result.next()) {
                    javafx.application.Platform.runLater(() -> {
                        Alert alert = new Alert(AlertType.INFORMATION);
                        alert.setTitle("Information Message");
                        alert.setHeaderText(null);
                        alert.setContentText("Login successful!");
                        alert.showAndWait();

                        try {
                            Stage stage = (Stage) loginBtn.getScene().getWindow();
                            stage.close();

                            Parent root = FXMLLoader.load(getClass().getResource("dashboard.fxml"));
                            Stage dashboardStage = new Stage();
                            Scene scene = new Scene(root);

                            // Optional: move window when dragged
                            root.setOnMousePressed((MouseEvent event) -> {
                                x = event.getSceneX();
                                y = event.getSceneY();
                            });

                            root.setOnMouseDragged((MouseEvent event) -> {
                                dashboardStage.setX(event.getScreenX() - x); 
                                dashboardStage.setY(event.getScreenY() - y);
                            });

                            dashboardStage.initStyle(StageStyle.TRANSPARENT);
                            dashboardStage.setScene(scene);
                            dashboardStage.show();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    });

                } else {
                    javafx.application.Platform.runLater(() -> {
                        Alert alert = new Alert(AlertType.ERROR);
                        alert.setTitle("ERROR Message");
                        alert.setHeaderText(null);
                        alert.setContentText("Wrong username or password. Try again.");
                        alert.showAndWait();
                    });
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        loginThread.setDaemon(true);
        loginThread.start();
    }

    // Go to register manually (optional duplicate method)
    public void goToRegister(){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Register.fxml"));
            Stage stage = (Stage) registerBtn.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
        } catch(Exception e){
            e.printStackTrace(); 
        }
    }

    // CLOSE METHOD
    public void exit() {
        System.exit(0);
    }

    // INITIALIZATION
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Setup logic if needed
    }
}
