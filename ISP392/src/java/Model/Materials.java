/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.Date;

/**
 *
 * @author khucx
 */
public class Materials {
    private int id; 
    private Subjects subjectId;
    private String material_name;
    private String material_file;
    private Date uploaded_at;
    private Lecturer_Profile uploaded_by;
    private String description;

    public Materials() {
    }

    public Materials(int id, Subjects subjectId, String material_name, String material_file, Date uploaded_at, Lecturer_Profile uploaded_by, String description) {
        this.id = id;
        this.subjectId = subjectId;
        this.material_name = material_name;
        this.material_file = material_file;
        this.uploaded_at = uploaded_at;
        this.uploaded_by = uploaded_by;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Subjects getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Subjects subjectId) {
        this.subjectId = subjectId;
    }

    public String getMaterial_name() {
        return material_name;
    }

    public void setMaterial_name(String material_name) {
        this.material_name = material_name;
    }

    public String getMaterial_file() {
        return material_file;
    }

    public void setMaterial_file(String material_file) {
        this.material_file = material_file;
    }

    public Date getUploaded_at() {
        return uploaded_at;
    }

    public void setUploaded_at(Date uploaded_at) {
        this.uploaded_at = uploaded_at;
    }

    public Lecturer_Profile getUploaded_by() {
        return uploaded_by;
    }

    public void setUploaded_by(Lecturer_Profile uploaded_by) {
        this.uploaded_by = uploaded_by;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Materials{" + "id=" + id + ", subjectId=" + subjectId + ", material_name=" + material_name + ", material_file=" + material_file + ", uploaded_at=" + uploaded_at + ", uploaded_by=" + uploaded_by + ", description=" + description + '}';
    }
    
    
    
}
