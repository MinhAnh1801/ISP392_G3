/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.Date;

/**
 *
 * @author khucx
 */
public class Attendance {
    private int id;
    private User studentId;
    private Subjects subject;
    private Date attendance_date;
    private String status;
    private String reason;

    private Class classId;
    private String fullName;
    
    public Attendance() {
    }

    public Attendance(int id, User studentId, Subjects subject, Date attendance_date, String status, String reason, Class classId, String fullName) {
        this.id = id;
        this.studentId = studentId;
        this.subject = subject;
        this.attendance_date = attendance_date;
        this.status = status;
        this.reason = reason;
        this.classId = classId;
        this.fullName = fullName;
    }

    public Attendance(int id, User studentId, Subjects subject, Date attendance_date, String status, String reason) {
        this.id = id;
        this.studentId = studentId;
        this.subject = subject;
        this.attendance_date = attendance_date;
        this.status = status;
        this.reason = reason;
    }

    public Attendance(int id, User studentId, Subjects subject, Date attendance_date, String status, String reason, Class classId) {
        this.id = id;
        this.studentId = studentId;
        this.subject = subject;
        this.attendance_date = attendance_date;
        this.status = status;
        this.reason = reason;
        this.classId = classId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Class getClassId() {
        return classId;
    }

    public void setClassId(Class classId) {
        this.classId = classId;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getStudentId() {
        return studentId;
    }

    public void setStudentId(User studentId) {
        this.studentId = studentId;
    }

    public Subjects getSubject() {
        return subject;
    }

    public void setSubject(Subjects subject) {
        this.subject = subject;
    }

    public Date getAttendance_date() {
        return attendance_date;
    }

    public void setAttendance_date(Date attendance_date) {
        this.attendance_date = attendance_date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
    
}
