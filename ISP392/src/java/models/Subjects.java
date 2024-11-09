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

    String id, code, name, credits, description, semester, major_name;
    int tuition;
    int subjectid;
    int major_id;
    String tu;

    public String getTu() {
        return tu;
    }

    public void setTu(String tu) {
        this.tu = tu;
    }

    public Subjects(String id, String code, String name, String credits, String tu) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.credits = credits;
        this.tu = tu;
    }
    
    public Subjects(String id, String code, String name, String credits, String description, String semester, int tuition,int major_id) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.credits = credits;
        this.description = description;
        this.semester = semester;
        this.tuition = tuition;
        this.major_id = major_id;
    }
    public Subjects(String id, String code, String name, String credits, String description, String semester, int tuition,String major_name) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.credits = credits;
        this.description = description;
        this.semester = semester;
        this.tuition = tuition;
        this.major_name = major_name;
    }

    public Subjects(String id, String code, String name, String credits, String description, String semester, String major_name, int tuition) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.credits = credits;
        this.description = description;
        this.semester = semester;
        this.major_name = major_name;
        this.tuition = tuition;
    }

    public int getTuition() {
        return tuition;
    }

    public String getMajor_name() {
        return major_name;
    }

    public void setMajor_name(String major_name) {
        this.major_name = major_name;
    }

    public int getMajor_id() {
        return major_id;
    }

    public void setMajor_id(int major_id) {
        this.major_id = major_id;
    }

    public void setTuition(int tuition) {
        this.tuition = tuition;
    }
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

    @Override
    public String toString() {
        return "Subjects{" + "id=" + id + ", code=" + code + ", name=" + name + ", credits=" + credits + ", description=" + description + ", semester=" + semester + ", major_name=" + major_name + ", tuition=" + tuition + ", subjectid=" + subjectid + ", major_id=" + major_id + ", classes=" + classes + '}';
    }

}
