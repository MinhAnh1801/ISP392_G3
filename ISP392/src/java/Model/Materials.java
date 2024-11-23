/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

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
    private String description;
    Lecturer_Profile upload_by;
    String uploaded_by;

    @Override
    public String toString() {
        return "Materials{" + "id=" + id + ", subjectCode=" + subjectCode + ", materialName=" + materialName + ", materialFile=" + materialFile + ", uploadedAt=" + uploadedAt + ", description=" + description + ", upload_by=" + upload_by + ", uploaded_by=" + uploaded_by + '}';
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
