/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Dell
 */
public class Bed {
     private int bedId;            
    private int dormRoomId;      
    private String bedNumber;      
    private boolean isOccupied;   
    private Integer studentId;    

    public Bed() {
    }
    
    public Bed(int bedId, int dormRoomId, String bedNumber, boolean isOccupied, Integer studentId) {
        this.bedId = bedId;
        this.dormRoomId = dormRoomId;
        this.bedNumber = bedNumber;
        this.isOccupied = isOccupied;
        this.studentId = studentId;
    }

    public int getBedId() {
        return bedId;
    }

    public void setBedId(int bedId) {
        this.bedId = bedId;
    }

    public int getDormRoomId() {
        return dormRoomId;
    }

    public void setDormRoomId(int dormRoomId) {
        this.dormRoomId = dormRoomId;
    }

    public String getBedNumber() {
        return bedNumber;
    }

    public void setBedNumber(String bedNumber) {
        this.bedNumber = bedNumber;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public void setOccupied(boolean isOccupied) {
        this.isOccupied = isOccupied;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }
}
