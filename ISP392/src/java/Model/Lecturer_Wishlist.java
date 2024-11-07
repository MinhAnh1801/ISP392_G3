/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author khucx
 */
public class Lecturer_Wishlist {
    int id,lecturer_id;
    String day_of_week,start_time,end_time;

    @Override
    public String toString() {
        return "Lecturer_Wishlist{" + "id=" + id + ", lecturer_id=" + lecturer_id + ", day_of_week=" + day_of_week + ", start_time=" + start_time + ", end_time=" + end_time + '}';
    }

    public Lecturer_Wishlist(int id, int lecturer_id, String day_of_week, String start_time, String end_time) {
        this.id = id;
        this.lecturer_id = lecturer_id;
        this.day_of_week = day_of_week;
        this.start_time = start_time;
        this.end_time = end_time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLecturer_id() {
        return lecturer_id;
    }

    public void setLecturer_id(int lecturer_id) {
        this.lecturer_id = lecturer_id;
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
