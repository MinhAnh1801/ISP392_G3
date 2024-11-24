/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.Date;

/**
 *
 * @author trung
 */
public class FeedbackForms {
      private int id;
    private Date startDate;
    private Date endDate;
    private Date createdAt;
    private Lecturer_Profile lecturerId;
    private Subjects1 subjectId;

    public FeedbackForms() {
    }

    public FeedbackForms(int id, Date startDate, Date endDate, Date createdAt, Lecturer_Profile lecturerId, Subjects1 subjectId) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.createdAt = createdAt;
        this.lecturerId = lecturerId;
        this.subjectId = subjectId;
    }

 
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Lecturer_Profile getLecturerId() {
        return lecturerId;
    }

    public void setLecturerId(Lecturer_Profile lecturerId) {
        this.lecturerId = lecturerId;
    }

  

    public Subjects1 getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Subjects1 subjectId) {
        this.subjectId = subjectId;
    }
    
    
}
