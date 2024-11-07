/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author admin
 */
public class Classes {

    String class_id, class_name;
    int capacity;
    public Classes() {
    }

    public Classes(String class_id, String class_name,int capacity) {
        this.class_id = class_id;
        this.class_name = class_name;
        this.capacity = capacity;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getClass_id() {
        return class_id;
    }

    public void setClass_id(String class_id) {
        this.class_id = class_id;
    }

    public String getClass_name() {
        return class_name;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }

    @Override
    public String toString() {
        return "Classes{" + "class_id=" + class_id + ", class_name=" + class_name + ", capacity=" + capacity + '}';
    }

}
