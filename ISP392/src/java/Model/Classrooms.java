/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author khucx
 */


public class Classrooms {

    int ID,capacity;
    String name,location;

    @Override
    public String toString() {
        return "Classrooms{" + "id=" + ID + ", capacity=" + capacity + ", name=" + name + ", location=" + location + '}';
    }

    public Classrooms() {
    }
    
    public Classrooms(int ID, int capacity, String name, String location) {
        this.ID = ID;
        this.capacity = capacity;
        this.name = name;
        this.location = location;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

}
