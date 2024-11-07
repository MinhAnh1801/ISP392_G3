/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.util.List;

/**
 *
 * @author admin
 */
public class Subjects {

    String id, code, name, credits, description, semester;
    int subjectid;
    private List<Classes> classes;

    public List<Classes> getClasses() {
        return classes;
    }

    public Subjects(String code, List<Classes> classes) {
        this.code = code;
        this.classes = classes;
    }

    public void setClasses(List<Classes> classes) {
        this.classes = classes;
    }

    public Subjects() {
    }

    public Subjects(int subjectid, String code) {
        this.subjectid = subjectid;
        this.code = code;
    }

    public int getSubjectid() {
        return subjectid;
    }

    public void setSubjectid(int subjectid) {
        this.subjectid = subjectid;
    }

    public Subjects(String id, String code, String name, String credits, String description, String semester) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.credits = credits;
        this.description = description;
        this.semester = semester;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCredits() {
        return credits;
    }

    public void setCredits(String credits) {
        this.credits = credits;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

}
