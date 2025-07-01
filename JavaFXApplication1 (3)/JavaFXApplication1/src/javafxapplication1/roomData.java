/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javafxapplication1;

/**
 *
 * @author pc
 */
public class roomData {
    private Integer roomNumber;
    private String roomType;
    private String status;
    private Double price;
    // we should make sure followed these data types 
    public roomData(Integer roomNumber, String roomType, String status, Double price){
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.status = status;
        this.price = price;
    }
    public Integer getRoomNumber(){
        return roomNumber;
    }
    public String getRoomType(){
        return roomType;
}
    public String getStatus(){
        return status;
    }
    public Double getPrice(){
        return price;
    }
}
        
