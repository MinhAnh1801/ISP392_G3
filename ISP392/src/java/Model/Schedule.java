/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author khucx
 */
public class Schedule {
    int id;
    String class_name,classroom_name,subject_code,day_of_week, start_time, end_time;

    @Override
    public String toString() {
        return "Schedule{" + "id=" + id + ", class_name=" + class_name + ", classroom_name=" + classroom_name + ", subject_code=" + subject_code + ", day_of_week=" + day_of_week + ", start_time=" + start_time + ", end_time=" + end_time + '}';
    }

    public Schedule(int id, String class_name, String classroom_name, String subject_code, String day_of_week, String start_time, String end_time) {
        this.id = id;
        this.class_name = class_name;
        this.classroom_name = classroom_name;
        this.subject_code = subject_code;
        this.day_of_week = day_of_week;
        this.start_time = start_time;
        this.end_time = end_time;
    }

    public String getClass_name() {
        return class_name;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }

    public String getClassroom_name() {
        return classroom_name;
    }

    public void setClassroom_name(String classroom_name) {
        this.classroom_name = classroom_name;
    }

    public String getSubject_code() {
        return subject_code;
    }

    public void setSubject_code(String subject_code) {
        this.subject_code = subject_code;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
    
}
