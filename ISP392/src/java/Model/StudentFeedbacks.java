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
public class StudentFeedbacks {

    private int id;
    private FeedbackForms feedbackFormId;
    private int studentId;
    private int punctuality;
    private int teachingSkills;
    private int syllabusCoverage;
    private int teacherSupport;
    private int responseToQueries;
    private int teachingSatisfaction;
    private Date createdAt;
    private Date updatedAt;

    public StudentFeedbacks() {
    }

    public StudentFeedbacks(int id, FeedbackForms feedbackFormId, int studentId, int punctuality, int teachingSkills, int syllabusCoverage, int teacherSupport, int responseToQueries, int teachingSatisfaction, Date createdAt, Date updatedAt) {
        this.id = id;
        this.feedbackFormId = feedbackFormId;
        this.studentId = studentId;
        this.punctuality = punctuality;
        this.teachingSkills = teachingSkills;
        this.syllabusCoverage = syllabusCoverage;
        this.teacherSupport = teacherSupport;
        this.responseToQueries = responseToQueries;
        this.teachingSatisfaction = teachingSatisfaction;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public FeedbackForms getFeedbackFormId() {
        return feedbackFormId;
    }

    public void setFeedbackFormId(FeedbackForms feedbackFormId) {
        this.feedbackFormId = feedbackFormId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getPunctuality() {
        return punctuality;
    }

    public void setPunctuality(int punctuality) {
        this.punctuality = punctuality;
    }

    public int getTeachingSkills() {
        return teachingSkills;
    }

    public void setTeachingSkills(int teachingSkills) {
        this.teachingSkills = teachingSkills;
    }

    public int getSyllabusCoverage() {
        return syllabusCoverage;
    }

    public void setSyllabusCoverage(int syllabusCoverage) {
        this.syllabusCoverage = syllabusCoverage;
    }

    public int getTeacherSupport() {
        return teacherSupport;
    }

    public void setTeacherSupport(int teacherSupport) {
        this.teacherSupport = teacherSupport;
    }

    public int getResponseToQueries() {
        return responseToQueries;
    }

    public void setResponseToQueries(int responseToQueries) {
        this.responseToQueries = responseToQueries;
    }

    public int getTeachingSatisfaction() {
        return teachingSatisfaction;
    }

    public void setTeachingSatisfaction(int teachingSatisfaction) {
        this.teachingSatisfaction = teachingSatisfaction;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
    
    
}
