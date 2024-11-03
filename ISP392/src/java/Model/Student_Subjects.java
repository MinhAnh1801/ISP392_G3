/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author khucx
 */
public class Student_Subjects {
    int id,student_id,subject_id,grate;
    String status,enrollment_date;

    public Student_Subjects(int id, int student_id, int subject_id, int grate, String status, String enrollment_date) {
        this.id = id;
        this.student_id = student_id;
        this.subject_id = subject_id;
        this.grate = grate;
        this.status = status;
        this.enrollment_date = enrollment_date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    public int getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(int subject_id) {
        this.subject_id = subject_id;
    }

    public int getGrate() {
        return grate;
    }

    public void setGrate(int grate) {
        this.grate = grate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEnrollment_date() {
        return enrollment_date;
    }

    public void setEnrollment_date(String enrollment_date) {
        this.enrollment_date = enrollment_date;
    }
    
}
