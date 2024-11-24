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
    private String subjectCode;
    private String materialName;
    private String materialFile;
    private String uploadedAt;
    private Date uploaded_at;
    private String description;
    Lecturer_Profile upload_by;
    String uploaded_by;
    
    private Subjects subjectId;
    private String material_name;
    private String material_file;
        
    

    @Override
    public String toString() {
        return "Materials{" + "id=" + id + ", subjectCode=" + subjectCode + ", materialName=" + materialName + ", materialFile=" + materialFile + ", uploadedAt=" + uploadedAt + ", description=" + description + ", upload_by=" + upload_by + ", uploaded_by=" + uploaded_by + '}';
    }

    public Materials(String subjectCode, String materialName, Date uploaded_at, String description, Lecturer_Profile upload_by) {
        this.subjectCode = subjectCode;
        this.materialName = materialName;
        this.uploaded_at = uploaded_at;
        this.description = description;
        this.upload_by = upload_by;
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

    
    public Materials(int id, String subjectCode, String materialName, String materialFile, String uploadedAt, String description, String uploaded_by) {
        this.id = id;
        this.subjectCode = subjectCode;
        this.materialName = materialName;
        this.materialFile = materialFile;
        this.uploadedAt = uploadedAt;
        this.description = description;
        this.uploaded_by = uploaded_by;
    }
    
    public String getUploaded_by() {
        return uploaded_by;
    }

    public void setUploaded_by(String uploaded_by) {
        this.uploaded_by = uploaded_by;
    }
   

    public Lecturer_Profile getUpload_by() {
        return upload_by;
    }

    public void setUpload_by(Lecturer_Profile upload_by) {
        this.upload_by = upload_by;
    }

    public Materials() {
    }

    public Materials(int id, String subjectCode, String materialName, String materialFile, String uploadedAt, Date uploaded_at, String description, Lecturer_Profile upload_by, String uploaded_by, Subjects subjectId, String material_name, String material_file) {
        this.id = id;
        this.subjectCode = subjectCode;
        this.materialName = materialName;
        this.materialFile = materialFile;
        this.uploadedAt = uploadedAt;
        this.uploaded_at = uploaded_at;
        this.description = description;
        this.upload_by = upload_by;
        this.uploaded_by = uploaded_by;
        this.subjectId = subjectId;
        this.material_name = material_name;
        this.material_file = material_file;
    }
    

    public Materials(int id, String subjectCode, String materialName, String materialFile, String uploadedAt, String description, Lecturer_Profile upload_by) {
        this.id = id;
        this.subjectCode = subjectCode;
        this.materialName = materialName;
        this.materialFile = materialFile;
        this.uploadedAt = uploadedAt;
        this.description = description;
        this.upload_by = upload_by;
    }
    
    public Materials(int id, String subjectCode, String materialName, String materialFile, String uploadedAt, String description) {
        this.id = id;
        this.subjectCode = subjectCode;
        this.materialName = materialName;
        this.materialFile = materialFile;
        this.uploadedAt = uploadedAt;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public String getMaterialFile() {
        return materialFile;
    }

    public void setMaterialFile(String materialFile) {
        this.materialFile = materialFile;
    }

    public String getUploadedAt() {
        return uploadedAt;
    }

    public void setUploadedAt(String uploadedAt) {
        this.uploadedAt = uploadedAt;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
