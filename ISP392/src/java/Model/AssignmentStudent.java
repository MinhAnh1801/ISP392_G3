/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author admin
 */
import java.time.LocalDateTime;

public class AssignmentStudent {

    public String id;
    public int assignmentId;
    public String studentName, submissionContent;
    public String submissionDate;
    public double grade;
    String comment;

    public AssignmentStudent() {
    }

    public AssignmentStudent(String id, int assignmentId, String studentName, String submissionContent, String comment, String submissionDate, double grade) {
        this.id = id;
        this.assignmentId = assignmentId;
        this.studentName = studentName;
        this.submissionContent = submissionContent;
        this.comment = comment;
        this.submissionDate = submissionDate;
        this.grade = grade;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getAssignmentId() {
        return assignmentId;
    }

    public void setAssignmentId(int assignmentId) {
        this.assignmentId = assignmentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getSubmissionContent() {
        return submissionContent;
    }

    public void setSubmissionContent(String submissionContent) {
        this.submissionContent = submissionContent;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getSubmissionDate() {
        return submissionDate;
    }

    public void setSubmissionDate(String submissionDate) {
        this.submissionDate = submissionDate;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

}
