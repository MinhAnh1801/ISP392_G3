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
    String day_of_week,start_time,end_time,subject_code,subjectName,classroomName,lecturer_name,class_name,lecturer_email;
    float attendance_percentage;
    int class_id;
    public Timetable() {
    }

    public String getLecturer_email() {
        return lecturer_email;
    }

    public void setLecturer_email(String lecturer_email) {
        this.lecturer_email = lecturer_email;
    }

    public Timetable(int id, int student_id, int subject_id, int classroom_id, String day_of_week, String start_time, String end_time, String subject_code, String subjectName, String classroomName, String lecturer_name, String class_name, String lecturer_email, float attendance_percentage, int class_id) {
        this.id = id;
        this.student_id = student_id;
        this.subject_id = subject_id;
        this.classroom_id = classroom_id;
        this.day_of_week = day_of_week;
        this.start_time = start_time;
        this.end_time = end_time;
        this.subject_code = subject_code;
        this.subjectName = subjectName;
        this.classroomName = classroomName;
        this.lecturer_name = lecturer_name;
        this.class_name = class_name;
        this.lecturer_email = lecturer_email;
        this.attendance_percentage = attendance_percentage;
        this.class_id = class_id;
    }
    
    public Timetable(int id, int student_id, int subject_id, int classroom_id, String day_of_week, String start_time, String end_time, String subject_code, String subjectName, String classroomName, String lecturer_name, String class_name, float attendance_percentage, int class_id) {
        this.id = id;
        this.student_id = student_id;
        this.subject_id = subject_id;
        this.classroom_id = classroom_id;
        this.day_of_week = day_of_week;
        this.start_time = start_time;
        this.end_time = end_time;
        this.subject_code = subject_code;
        this.subjectName = subjectName;
        this.classroomName = classroomName;
        this.lecturer_name = lecturer_name;
        this.class_name = class_name;
        this.attendance_percentage = attendance_percentage;
        this.class_id = class_id;
    }

    public String getSubject_code() {
        return subject_code;
    }

    public void setSubject_code(String subject_code) {
        this.subject_code = subject_code;
    }

    public String getClass_name() {
        return class_name;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }

    public Timetable(int id, String subjectName, String classroomName, String lecturer_name, String class_name) {
        this.id = id;
        this.subjectName = subjectName;
        this.classroomName = classroomName;
        this.lecturer_name = lecturer_name;
        this.class_name = class_name;
    }

    public Timetable(int id, int student_id, int subject_id, int classroom_id, String day_of_week, String start_time, String end_time, String subjectName, String classroomName, String lecturer_name, float attendance_percentage, int class_id) {
        this.id = id;
        this.student_id = student_id;
        this.subject_id = subject_id;
        this.classroom_id = classroom_id;
        this.day_of_week = day_of_week;
        this.start_time = start_time;
        this.end_time = end_time;
        this.subjectName = subjectName;
        this.classroomName = classroomName;
        this.lecturer_name = lecturer_name;
        this.attendance_percentage = attendance_percentage;
        this.class_id = class_id;
    }

    public String getLecturer_name() {
        return lecturer_name;
    }

    public void setLecturer_name(String lecturer_name) {
        this.lecturer_name = lecturer_name;
    }

    public Timetable(int id, int student_id, int subject_id, int classroom_id, String day_of_week, String start_time, String end_time, String subjectName, String classroomName, float attendance_percentage, int class_id) {
        this.id = id;
        this.student_id = student_id;
        this.subject_id = subject_id;
        this.classroom_id = classroom_id;
        this.day_of_week = day_of_week;
        this.start_time = start_time;
        this.end_time = end_time;
        this.subjectName = subjectName;
        this.classroomName = classroomName;
        this.attendance_percentage = attendance_percentage;
        this.class_id = class_id;
    }

    public int getClass_id() {
        return class_id;
    }

    public void setClass_id(int class_id) {
        this.class_id = class_id;
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
        return "Timetable{" + "id=" + id + ", student_id=" + student_id + ", subject_id=" + subject_id + ", classroom_id=" + classroom_id + ", day_of_week=" + day_of_week + ", start_time=" + start_time + ", end_time=" + end_time + ", subjectName=" + subjectName + ", classroomName=" + classroomName + ", lecturer_name=" + lecturer_name + ", attendance_percentage=" + attendance_percentage + ", class_id=" + class_id + '}';
    }

    
}
