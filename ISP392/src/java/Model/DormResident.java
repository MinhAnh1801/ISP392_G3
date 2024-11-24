/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.sql.Date;

public class DormResident {

    private int id;
    private int studentId;
    private int dormRoomId;
    private Date checkInDate;
    private Date checkOutDate;
    private String status;

    public DormResident() {
    }

    public DormResident(int id, int studentId, int dormRoomId, Date checkInDate, Date checkOutDate, String status) {
        this.id = id;
        this.studentId = studentId;
        this.dormRoomId = dormRoomId;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getDormRoomId() {
        return dormRoomId;
    }

    public void setDormRoomId(int dormRoomId) {
        this.dormRoomId = dormRoomId;
    }

    public Date getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(Date checkInDate) {
        this.checkInDate = checkInDate;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(Date checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
