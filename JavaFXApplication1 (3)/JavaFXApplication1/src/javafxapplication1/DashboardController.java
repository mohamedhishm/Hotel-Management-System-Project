/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxapplication1;

import com.mysql.jdbc.Statement;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
//import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.chart.AreaChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.stage.StageStyle;
import java.sql.Connection;
import java.util.Date;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.scene.chart.XYChart;

/**
 * FXML Controller class
 *
 * @author pc
 */
public class DashboardController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private AnchorPane main_form;

    @FXML
    private Button availableRooms_btn;

    @FXML
    private AnchorPane availableRooms_form;

    @FXML
    private TextField availableRooms_roomNumber;

    @FXML
    private ComboBox<?> availableRooms_roomType;

    @FXML
    private ComboBox<?> availableRooms_status;

    @FXML
    private Button available_Rooms_addBtn;

    @FXML
    private Button available_Rooms_checkInBtn;

    @FXML
    private Button available_Rooms_clearBtn;

    @FXML
    private TableColumn<roomData, String> available_Rooms_col_price;

    @FXML
    private TableColumn<roomData, String> available_Rooms_col_roomNumber;

    @FXML
    private TableColumn<roomData, String> available_Rooms_col_roomType;

    @FXML
    private TableColumn<roomData, String> available_Rooms_col_status;

    @FXML
    private Button available_Rooms_deleteBtn;

    @FXML
    private TextField available_Rooms_price;

    @FXML
    private TextField available_Rooms_search;

    @FXML
    private TableView<roomData> available_Rooms_tableView;

    @FXML
    private Button available_Rooms_updateBtn;

    @FXML
    private Button closeBtn;

    @FXML
    private AnchorPane customer_Form;

    @FXML
    private Button customers_btn;

    @FXML
    private Button aboutUs_btn;

    @FXML
    private TableColumn<customerData, String> customers_checkedIn;

    @FXML
    private TableColumn<customerData, String> customers_checkedOut;

    @FXML
    private TableColumn<customerData, String> customers_customerNumber;

    @FXML
    private TableColumn<customerData, String> customers_firstName;

    @FXML
    private TableColumn<customerData, String> customers_lastName;

    @FXML
    private TableColumn<customerData, String> customers_phoneNumber;

    @FXML
    private TextField customers_search;

    @FXML
    private TableView<customerData> customers_tableView;

    @FXML
    private TableColumn<customerData, String> customers_totalPayment;

    @FXML
    private AreaChart<?, ?> dashboard_areaChart;

    
                //        dashboardChart();

    @FXML
    private Label dashboard_bookToday;

    @FXML
    private Label dashboard_IncomeToday;

    @FXML
    private Label dashboard_TotalIncome;

    @FXML
    private Button dashboard_btn;

    @FXML
    private AnchorPane dashboard_form;

    @FXML
    private Label dashboard_incomeToday;

    @FXML
    private Label dashboard_totalIncome;

    @FXML
    private Button logout_btn;

    @FXML
    private Button minimizeBtn;

    @FXML
    private Label username;

    //database tools
    private Connection connect;
    private PreparedStatement prepare;
    private Statement statment;
    private ResultSet result;

    private int count = 0;

    public void dashboardCountBookToday() {

        Date date = new Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());

        String sql = "SELECT COUNT(id) FROM customer WHERE checkIn = '" + sqlDate + "'";

        connect = database.connectDb();

        count = 0;

        try {

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()) {
                count = result.getInt("COUNT(id)");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void dashboardDispalyBookToday() {

        dashboardCountBookToday();

        dashboard_bookToday.setText(String.valueOf(count));

    }

    private double sumToday = 0;

    public void dashboardSumIncomeToday() {

        Date date = new Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());

        String sql = "SELECT SUM(total) FROM  customer_receipt WHERE date '" + sqlDate + "' ";

        connect = database.connectDb();

        try {

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()) {
                sumToday = result.getDouble("SUM(total)");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
//        

    }

    public void dashboardDispalyIncomeToday() {

        dashboardSumIncomeToday();

        dashboard_incomeToday.setText("$" + String.valueOf(sumToday));

    }

    private double overall = 0;

    public void dashboardSumTotalIncome() {

//        Date date =new Date();
//        java.sql.Date sqlDate =new java.sql.Date(date.getTime());
        String sql = "SELECT SUM(total) FROM  customer_receipt ";

        connect = database.connectDb();

        try {

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()) {
                overall = result.getDouble("Sum(total)");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
//        

    }

    public void dashboardDispalyTotalIncome() {

        dashboardSumTotalIncome();

        dashboard_totalIncome.setText("$" + String.valueOf(overall));

    }

    public void dashboardChart() {
        dashboard_areaChart.getData().clear();

        String sql = "SELECT date, total FORMcustomer_receipt   GROUP BY date ORDER BY TIMESTAMP(date) ASCLIMIT 8 ";

        connect = database.connectDb();

        XYChart.Series chart = new XYChart.Series();
        try {

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()) {
                chart.getData().add(new XYChart.Data(result.getString(1), result.getInt(2)));
            }

            dashboard_areaChart.getData().add(chart);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // lets give functions for availableRoom first
    public ObservableList<roomData> availableRoomsListData() {

        ObservableList<roomData> listData = FXCollections.observableArrayList();

        String sql = "SELECT * FROM room";

        connect = database.connectDb();

        try {

            roomData roomD;

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()) {
                roomD = new roomData(result.getInt("roomNumber"),
                        result.getString("type"),
                        result.getString("status"),
                        result.getDouble("price"));
                listData.add(roomD);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }

    private ObservableList<roomData> roomDataList;

    public void availableRoomsShowData() {

        roomDataList = availableRoomsListData();

        available_Rooms_col_roomNumber.setCellValueFactory(new PropertyValueFactory<>("roomNumber"));
        available_Rooms_col_roomType.setCellValueFactory(new PropertyValueFactory("roomType"));
        available_Rooms_col_status.setCellValueFactory(new PropertyValueFactory("status"));
        available_Rooms_col_price.setCellValueFactory(new PropertyValueFactory("price"));

        available_Rooms_tableView.setItems(roomDataList);

    }

    public void availableRoomSelectData() {
        roomData roomD = available_Rooms_tableView.getSelectionModel().getSelectedItem();
        int num = available_Rooms_tableView.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return;
        }

        availableRooms_roomNumber.setText(String.valueOf(roomD.getRoomNumber()));
        available_Rooms_price.setText(String.valueOf(roomD.getPrice()));
    }

    public void availableRoomsAdd() {

        String sql = "INSERT INTO room (roomNumber,type,status,price) VALUES (?,?,?,?)";
        connect = database.connectDb();
        try {
            String roomNumber = availableRooms_roomNumber.getText();
            String type = (String) availableRooms_roomType.getSelectionModel().getSelectedItem();
            String status = (String) availableRooms_status.getSelectionModel().getSelectedItem();
            String price = available_Rooms_price.getText();

            // check if there empty fields
            Alert alert;
            if (roomNumber == null || type == null || price == null || status == null) {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error message");
                alert.setHeaderText(null);
                alert.setContentText("please fill all blank fields");
                alert.showAndWait();
            } /*else {

                String check = "SELECT roonNumber FORM room WHERE roomNumber='" + roomNumber + "'";

                prepare = connect.prepareStatement(check);
                result = prepare.executeQuery();
//              check if anpther romm has a same number
                if (result.next()) {
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error message");
                    alert.setHeaderText(null);
                    alert.setContentText("Room #" + roomNumber + "was already exist!");
                    alert.showAndWait();
                } */ else {

                prepare = connect.prepareStatement(sql);
                prepare.setString(1, roomNumber);
                prepare.setString(2, type);
                prepare.setString(3, status);
                prepare.setString(4, price);

                prepare.executeUpdate();

                alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("information message");
                alert.setHeaderText("successfully added!");
                alert.showAndWait();

                //to update the data on the tableview
                availableRoomsShowData();
                //if will clear the fields
                availableRoomsClear();
                //}
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /* public void availableRoomsUpdate() {

        String type1 = (String) availableRooms_roomType.getSelectionModel().getSelectedItem();
        String status1 = (String) availableRooms_status.getSelectionModel().getSelectedItem();
        String price1 = available_Rooms_price.getText();
        String roomNum = availableRooms_roomNumber.getText();

        String sql = "UPDATE room SET type '"
                + type1 + "', status= '" + status1 + "', price = '" + price1
                + "' WHERE roomNumber= '" + roomNum + "' ";

        connect = database.connectDb();

        try {

            Alert alert;

            // check if empty the fields
            if (type1== null || status1 == null || price1 ==null || roomNum == null) {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("please select the data first");
                alert.showAndWait();
            } else {

                prepare = connect.prepareStatement(sql);
                prepare.executeUpdate();

                alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("information Message");
                alert.setHeaderText(null);
                alert.setContentText("Successfully Update");
                alert.showAndWait();

                //to show the update tableview
                availableRoomsShowData();
                //clear the fields
                availableRoomsClear();

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    } */
    public void availableRoomsUpdate() {

        String type = (String) availableRooms_roomType.getSelectionModel().getSelectedItem();
        String status = (String) availableRooms_status.getSelectionModel().getSelectedItem();
        String price = available_Rooms_price.getText();
        String roomNum = availableRooms_roomNumber.getText();

        //  String sql = "UPDATE room SET type '"+ type1 + "', status= '" + status1 + "', price = '" + price1+ "' WHERE roomNumber= '" + roomNum + "' ";
        connect = database.connectDb();

        try {

            Alert alert;

            // check if empty the fields
            if (type == null || status == null || price.isEmpty() || roomNum.isEmpty()) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("please select the data first");
                alert.showAndWait();
            } else {
                String sql = "UPDATE room SET type=?, status= ?, price= ? WHERE roomNumber = ?";

                prepare = connect.prepareStatement(sql);

                prepare.setString(1, type);
                prepare.setString(2, status);
                prepare.setString(3, price);
                prepare.setString(4, roomNum);

                int result = prepare.executeUpdate();

                if (result > 0) {
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Update");
                    alert.showAndWait();

                    //to show the update tableview
                    availableRoomsShowData();
                    //clear the fields
                    availableRoomsClear();

                } else {
                    alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("WARNING");
                    alert.setHeaderText(null);
                    alert.setContentText("no room found with that number.");
                    alert.showAndWait();

                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void availableRoomsDlete() {

        String type1 = (String) availableRooms_roomType.getSelectionModel().getSelectedItem();
        String status1 = (String) availableRooms_status.getSelectionModel().getSelectedItem();
        String price1 = available_Rooms_price.getText();
        String roomNum = availableRooms_roomNumber.getText();

        String deleteData = "DELETE FROM room WHERE roomNumber = '" + roomNum + "'";

        connect = database.connectDb();

        try {

            Alert alert;

            // check if empty the fields
            if (type1 == null || status1 == null || price1 == null || roomNum == null) {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("please select the data first");
                alert.showAndWait();
            } else {
                java.sql.Statement Statement = connect.createStatement();
                Statement.executeUpdate(deleteData);

                alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("CONFIRMATION Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to delete Room #" + roomNum + "?");
                alert.showAndWait();

                Optional<ButtonType> option = alert.showAndWait();

                if (option.get().equals(ButtonType.OK)) {
                    Statement = connect.createStatement();
                    Statement.executeUpdate(deleteData);

                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Update");
                    alert.showAndWait();

                    availableRoomsShowData();
                    availableRoomsClear();

                } else {
                    return;
                }
            }
            //
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //we need to add the data to our combo boxes
    public void availableRoomsClear() {
        availableRooms_roomNumber.setText("");
        availableRooms_roomType.getSelectionModel().clearSelection();
        availableRooms_status.getSelectionModel().clearSelection();
        available_Rooms_price.setText("");

    }

    public void availableRoomsCheckIn() {

        try {
            Parent root = FXMLLoader.load(getClass().getResource("checkin.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setMinHeight(494 + 15);
            stage.setMinWidth(398 + 15);

            root.setOnMousePressed((MouseEvent event) -> {
                x = event.getSceneX();
                y = event.getSceneY();
            });

            root.setOnMouseDragged((MouseEvent event) -> {
                stage.setX(event.getScreenX() - x);
                stage.setY(event.getScreenY() - y);

                stage.setOpacity(.8);
            });

            root.setOnMouseReleased((MouseEvent event) -> {

                stage.setOpacity(1);

            });
            stage.initStyle(StageStyle.DECORATED);
            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private String type[]
            = {"Single Room", "Double Room", "Triple Room", "Quad Room", "King Room"};

    public void availableRoomsRoomType() {
        List<String> listData = new ArrayList<>();

        for (String data : type) {
            listData.add(data);
        }
        ObservableList list = FXCollections.observableArrayList(listData);
        availableRooms_roomType.setItems(list);
    }

    private String status[] = {"Available", "Not Available", "Occupied"};

    public void availableRoomsStatus() {
        List<String> listData = new ArrayList<>();

        for (String data : status) {
            listData.add(data);
        }

        ObservableList list = FXCollections.observableArrayList(listData);
        availableRooms_status.setItems(list);

    }
//    type of list used in JavaFX that allows automatic updates when the list changes. It extends List and adds listener support, meaning UI components or other parts of your program can react to additions, deletions, or modifications in the list.

    public ObservableList<customerData> customerListData() {

        ObservableList<customerData> listData = FXCollections.observableArrayList();

        String sql = "SELECT * FROM customer";
        //need the total payment for customer i just put it on customer_receipt

        connect = database.connectDb();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            customerData custD;

            while (result.next()) {
                custD = new customerData(
                        result.getInt("customer_id"),
                        result.getString("firstName"),
                        result.getString("lastName"),
                        result.getString("phoneNumber"),
                        result.getDouble("total"),
                        result.getDate("checkIn"),
                        result.getDate("checkOut"));

                listData.add(custD);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return listData;
    }
    private ObservableList<customerData> listCustomerData;

    public void customerShowData() {
        listCustomerData = customerListData();

        customers_customerNumber.setCellValueFactory(new PropertyValueFactory<>("customerNum"));
        customers_firstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        customers_lastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        customers_phoneNumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        customers_totalPayment.setCellValueFactory(new PropertyValueFactory<>("total"));
        customers_checkedIn.setCellValueFactory(new PropertyValueFactory<>("checkIn"));
        customers_checkedOut.setCellValueFactory(new PropertyValueFactory<>("checkOut"));

        customers_tableView.setItems(listCustomerData);
    }

    //search the data on tableview for customer form
    public void customerSearch() {
        FilteredList<customerData> filter = new FilteredList<>(listCustomerData, e -> true);
        customers_search.textProperty().addListener((Observable, OldValue, newValue) -> {

            filter.setPredicate(predicateCustomer -> {
                if (newValue == null && newValue.isEmpty()) {
                    return true;
                }
                String searchKey = newValue.toLowerCase();

                if (predicateCustomer.getCustomerNum().toString().contains(searchKey)) {
                    return true;
                } else if (predicateCustomer.getFirstName().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateCustomer.getLastName().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateCustomer.getTotal().toString().contains(searchKey)) {
                    return true;
                } else if (predicateCustomer.getPhoneNumber().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateCustomer.getCheckIn().toString().contains(searchKey)) {
                    return true;
                } else if (predicateCustomer.getCheckOut().toString().contains(searchKey)) {
                    return true;
                } else {
                    return false;
                }
            });

        });
        SortedList<customerData> sortList = new SortedList<>(filter);

        sortList.comparatorProperty().bind(customers_tableView.comparatorProperty());
        customers_tableView.setItems(sortList);
    }

    public void switchForm(ActionEvent event) {

        if (event.getSource() == dashboard_btn) {
            //the form of dashboard will display

            dashboard_form.setVisible(true);
            availableRooms_form.setVisible(false);
            customer_Form.setVisible(false);
            /// a5errrrn 5alast el page dee agmad etch 
//        dashboardDispalyBookToday();
           dashboardDispalyIncomeToday();
            dashboardSumTotalIncome();
            dashboardChart();

        } else if (event.getSource() == availableRooms_btn) {
            //the form of avaliableroom

            dashboard_form.setVisible(false);
            availableRooms_form.setVisible(true);
            customer_Form.setVisible(false);

            availableRoomsShowData();

        } else if (event.getSource() == customers_btn) {
            //this form of customer will display
            dashboard_form.setVisible(false);
            availableRooms_form.setVisible(false);
            customer_Form.setVisible(true);

            customerShowData();
        } else if (event.getSource() == aboutUs_btn) {
            dashboard_form.setVisible(false);
            availableRooms_form.setVisible(false);
            customer_Form.setVisible(false);

            try {
                Parent root = FXMLLoader.load(getClass().getResource("Aboutus.fxml"));
                Stage stage = new Stage();
                stage.setTitle("About Us");
                stage.setScene(new Scene(root));
                stage.setMinWidth(679);   // نفس أبعاد الFXML
                stage.setMinHeight(481);
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

    private double x = 0;
    private double y = 0;

    public void logout() {
        try {

            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Message");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to logout?");

            Optional<ButtonType> option = alert.showAndWait();

            if (option.get().equals(ButtonType.OK)) {

                Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(root);

                root.setOnMousePressed((MouseEvent event) -> {
                    x = event.getSceneX();
                    y = event.getSceneY();
                });

                root.setOnMouseDragged((MouseEvent event) -> {
                    stage.setX(event.getScreenX() - x);
                    stage.setY(event.getScreenY() - y);
                });

                stage.initStyle(StageStyle.TRANSPARENT);
                stage.setScene(scene);
                stage.show();

                logout_btn.getScene().getWindow().hide();

            } else {
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    public void close() {
        System.exit(0);
    }

    public void minimize() {
        Stage stage = (Stage) main_form.getScene().getWindow();
        stage.setIconified(true);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        availableRoomsRoomType();
        availableRoomsStatus();
        dashboardDispalyTotalIncome();
        dashboardDispalyIncomeToday();
        dashboardDispalyBookToday();
        dashboardChart();

        //  TO SHOW THE DATA ON TABLEVIEW
        availableRoomsShowData();
        // to show the data on table view for cutomer form
        customerShowData();
        customerSearch();
        showAboutUsPage();
    }

}
