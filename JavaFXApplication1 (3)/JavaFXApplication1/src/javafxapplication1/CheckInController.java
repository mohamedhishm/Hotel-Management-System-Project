package javafxapplication1;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import java.sql.Statement;

public class CheckInController implements Initializable {

    @FXML
    private DatePicker checkIn_date;

    @FXML
    private BorderPane checkIn_form;

    @FXML
    private DatePicker checkOut_date;

    @FXML
    private Button close;

    @FXML
    private Label customerNumber;

    @FXML
    private TextField emailAdderss;

    @FXML
    private TextField firstName;

    @FXML
    private TextField lastName;

    @FXML
    private TextField phoneNumber;

    @FXML
    private StackPane stack_form;
    @FXML
    private Label total;

    @FXML
    private Label totalDays;
    
    @FXML
    private ComboBox<?> roomType;
    @FXML
    private ComboBox<?> roomNumber;

    

//    @FXML
//    private Button availableRooms_checkInBtn;
    //DATABASE tools
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;
    private Statement statement;

    public void customerCheckIn() {
       
    String insertCustomerData = "INSERT INTO customer(customer_id,total,roomType,roomNumber,firstName,lastName,phoneNumber,email,checkIn,checkOut)"
            + " VALUES (?,?,?,?,?,?,?,?,?,?)";
    connect = database.connectDb();

    try {
        String customerNum = customerNumber.getText();
        String roomT = (String)roomType.getSelectionModel().getSelectedItem();
        String roomN = (String)roomNumber.getSelectionModel().getSelectedItem();
        String firstN = firstName.getText();
        String lastN = lastName.getText();
        String phoneNum = phoneNumber.getText();
        String email = emailAdderss.getText();
        String checkInDate = String.valueOf(checkIn_date.getValue());
        String checkOutDate = String.valueOf(checkOut_date.getValue());

        Alert alert;
        if (customerNum.isEmpty() || firstN.isEmpty() || lastN.isEmpty() || phoneNum.isEmpty()
                || email.isEmpty() || checkInDate == null || checkOutDate == null) {

            alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all blank fields");
            alert.showAndWait();
            return;
        }

        alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Message");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure?");
        Optional<ButtonType> option = alert.showAndWait();
        // String totalC = String.valueOf(totalP);
        if (option.isPresent() && option.get().equals(ButtonType.OK)) {
            prepare = connect.prepareStatement(insertCustomerData);
            prepare.setString(1, customerNum);
            prepare.setDouble(2, totalP);
            prepare.setString(3, roomT);
            prepare.setString(4, roomN);
            prepare.setString(5, firstN);
            prepare.setString(6, lastN);
            prepare.setString(7, phoneNum);
            prepare.setString(8, email);
            prepare.setString(9, checkInDate);
            prepare.setString(10, checkOutDate);
            prepare.executeUpdate();
            String date = String.valueOf(checkIn_date.getValue());

            String customerN = customerNumber.getText();
            String customerReceipt = "INSERT INTO customer_receipt (customer_num,total,date)"
                    +"VALUES(?,?,?)";
            // same room so we need to update the sql
            prepare = connect.prepareStatement(customerReceipt);
            prepare.setString(1,customerN);
            prepare.setDouble(2, totalP);
            prepare.setString(3, date);
            
            prepare.execute();
            
            String sqlEditStatus = "UPDATE room SET status ='Occupied'";
            statement = connect.createStatement();
            statement.executeUpdate(sqlEditStatus);
            
            
            alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Information Message");
            alert.setHeaderText(null);
            alert.setContentText("Customer checked in successfully!");
            alert.showAndWait();
            // after the successful insert the data of customer , the fields value will be cleared
           reset();
        }

    } catch (Exception e) {
        e.printStackTrace();
    }
}
    
public void reset(){
    firstName.setText(null);
    lastName.setText("");
    phoneNumber.setText("");
    emailAdderss.setText("");
    roomType.getSelectionModel().clearSelection();
    roomNumber.getSelectionModel().clearSelection();
    totalDays.setText("---");
    total.setText("0.0$");
    
}
    
public void totalDays() {
    Alert alert;
     if(checkOut_date.getValue().isAfter(checkIn_date.getValue())){
         getData.totalDays = checkOut_date.getValue().compareTo(checkIn_date.getValue());
         
     }
     else{
         alert = new Alert(AlertType.ERROR);
         alert.setTitle("Error message");
         alert.setHeaderText(null);
         alert.setContentText("INvalid chek-out data");
         alert.showAndWait();
         checkOut_date.setValue(null);
     }
     displayTotal();

}

    private float totalP = 0;
    public void displayTotal(){
        String totalD = String.valueOf(getData.totalDays);
        totalDays.setText(totalD);
        
        String selectItem = (String)roomNumber.getSelectionModel().getSelectedItem();
        String sql = "SELECT * FROM room WHERE roomNumber ='" + selectItem + "'";
        double priceData = 0;
        connect = database.connectDb();
        try{
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            
            if(result.next()){
                priceData = result.getDouble("price");
            }
             totalP = (float) ((priceData)*getData.totalDays);
            System.out.println("total paymetn"+totalP);
            System.out.println("priceData:"+priceData);
            total.setText("$"+String.valueOf(totalP));
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        
    }

    public void customerNumber() {
        String customerNum = "SELECT customer_id FROM customer";
        connect = database.connectDb();

        try {
            prepare = connect.prepareStatement(customerNum);
            result = prepare.executeQuery();
            while (result.next()) {
                getData.customerNum = result.getInt("customer_id");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void roomTypeList(){
        String listType = "SELECT * FROM room where status = 'Available'";
        connect = database.connectDb();
        try{
            
            ObservableList listData = FXCollections.observableArrayList();
            prepare = connect.prepareStatement(listType);
            result = prepare.executeQuery();
            while(result.next()){
                listData.add(result.getString("type"));
                
            }
            roomType.setItems(listData);
            roomNumberList();
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void roomNumberList(){
        String item = (String)roomType.getSelectionModel().getSelectedItem();
        String availableRoomNumber = "SELECT * FROM room WHERE type ='" + item + "'";
        connect = database.connectDb();
        try{
            ObservableList listData = FXCollections.observableArrayList();
            prepare = connect.prepareStatement(availableRoomNumber);
            result = prepare.executeQuery();
            while(result.next()){
                listData.add(result.getString("roomNumber"));
            }
            roomNumber.setItems(listData);
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void displayCustomerNumber() {
        customerNumber();
        customerNumber.setText(String.valueOf(getData.customerNum + 1));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        displayCustomerNumber();
        roomTypeList();
        roomNumberList();
    }

}

