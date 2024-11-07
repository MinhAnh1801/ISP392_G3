/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author khucx
 */
public class Timetable {
    int id,student_id,subject_id,classroom_id;
    String day_of_week,start_time,end_time,subjectName,classroomName;
    float attendance_percentage;

    public Timetable() {
    }

    public Timetable(int id, int student_id, int subject_id, int classroom_id, String day_of_week, String start_time, String end_time, float attendance_percentage) {
        this.id = id;
        this.student_id = student_id;
        this.subject_id = subject_id;
        this.classroom_id = classroom_id;
        this.day_of_week = day_of_week;
        this.start_time = start_time;
        this.end_time = end_time;
        this.attendance_percentage = attendance_percentage;
    }

    public Timetable(int id, int student_id, String day_of_week, String start_time, String end_time, String subjectName, String classroomName, float attendance_percentage) {
        this.id = id;
        this.student_id = student_id;
        this.day_of_week = day_of_week;
        this.start_time = start_time;
        this.end_time = end_time;
        this.subjectName = subjectName;
        this.classroomName = classroomName;
        this.attendance_percentage = attendance_percentage;
    }
    
    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getClassroomName() {
        return classroomName;
    }

    public void setClassroomName(String classroomName) {
        this.classroomName = classroomName;
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

    public int getClassroom_id() {
        return classroom_id;
    }

    public void setClassroom_id(int classroom_id) {
        this.classroom_id = classroom_id;
    }

    public String getDay_of_week() {
        return day_of_week;
    }

    public void setDay_of_week(String day_of_week) {
        this.day_of_week = day_of_week;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public float getAttendance_percentage() {
        return attendance_percentage;
    }

    public void setAttendance_percentage(float attendance_percentage) {
        this.attendance_percentage = attendance_percentage;
    }

    @Override
    public String toString() {
        return "Timetable{" + "id=" + id + ", student_id=" + student_id + ", subject_id=" + subject_id + ", classroom_id=" + classroom_id + ", day_of_week=" + day_of_week + ", start_time=" + start_time + ", end_time=" + end_time + ", subjectName=" + subjectName + ", classroomName=" + classroomName + ", attendance_percentage=" + attendance_percentage + '}';
    }
}
