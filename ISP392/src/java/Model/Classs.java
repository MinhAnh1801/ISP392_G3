/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author khucx
 */
public class Classs {
    private int class_id;
    private String class_name;

    public Classs() {
    }

    public Classs(int class_id, String class_name) {
        this.class_id = class_id;
        this.class_name = class_name;
    }

    public int getClass_id() {
        return class_id;
    }

    public void setClass_id(int class_id) {
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
        return "Classs{" + "class_id=" + class_id + ", class_name=" + class_name + '}';
    }
    
}
