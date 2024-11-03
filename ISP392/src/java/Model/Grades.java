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
public class Grades {
    private int  id;
    private Student_Profile studentId;
    private Subjects subjectId;
    private double grade; 
    private Date date;
    private String comment;
    
    // thêm 
    private typeGrade type;

    public Grades(int id, Student_Profile studentId, Subjects subjectId, double grade, Date date, String comment, typeGrade type) {
        this.id = id;
        this.studentId = studentId;
        this.subjectId = subjectId;
        this.grade = grade;
        this.date = date;
        this.comment = comment;
        this.type = type;
    }

    public typeGrade getType() {
        return type;
    }

    public void setType(typeGrade type) {
        this.type = type;
    }
    

    public Grades() {
    }

    public Grades(int id, Student_Profile studentId, Subjects subjectId, double grade, Date date, String comment) {
        this.id = id;
        this.studentId = studentId;
        this.subjectId = subjectId;
        this.grade = grade;
        this.date = date;
        this.comment = comment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Student_Profile getStudentId() {
        return studentId;
    }

    public void setStudentId(Student_Profile studentId) {
        this.studentId = studentId;
    }

    public Subjects getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Subjects subjectId) {
        this.subjectId = subjectId;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Grades{" + "id=" + id + ", studentId=" + studentId + ", subjectId=" + subjectId + ", grade=" + grade + ", date=" + date + ", comment=" + comment + '}';
    }
    
}
