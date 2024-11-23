/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author admin
 */
public class Grade {

    String id, student_id,subject_id ,grade, upload_date, comments;

    public Grade() {
    }

    public Grade(String id, String student_id,String subject_id ,String grade, String upload_date, String comments) {
        this.id = id;
        this.student_id = student_id;        
        this.subject_id = subject_id;        
        this.grade = grade;
        this.upload_date = upload_date;
        this.comments = comments;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public String getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(String subject_id) {
        this.subject_id = subject_id;
    }
     
    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getUpload_date() {
        return upload_date;
    }

    public void setUpload_date(String upload_date) {
        this.upload_date = upload_date;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

}
