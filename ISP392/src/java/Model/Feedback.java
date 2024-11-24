/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.sql.Date;

public class Feedback {

    private int feedbackId;
    private String rating;
    private int studentId;
    private int lecturerId;
    private int feedbackQuestionId;
    private boolean status;

    public Feedback() {
    }

    public Feedback(int feedbackId, String rating, int studentId, int lecturerId, int feedbackQuestionId, boolean status) {
        this.feedbackId = feedbackId;
        this.rating = rating;
        this.studentId = studentId;
        this.lecturerId = lecturerId;
        this.feedbackQuestionId = feedbackQuestionId;
        this.status = status;
    }

    public int getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(int feedbackId) {
        this.feedbackId = feedbackId;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getLecturerId() {
        return lecturerId;
    }

    public void setLecturerId(int lecturerId) {
        this.lecturerId = lecturerId;
    }

    public int getFeedbackQuestionId() {
        return feedbackQuestionId;
    }

    public void setFeedbackQuestionId(int feedbackQuestionId) {
        this.feedbackQuestionId = feedbackQuestionId;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

}
