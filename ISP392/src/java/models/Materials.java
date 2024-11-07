/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author khucx
 */
public class Materials {

    private int id;
    String  mid;
    private String subjectCode;
    private String materialName;
    private String materialFile;
    private String uploadedAt;
    private String description;

//    @Override
//    public String toString() {
//        return "Materials{" + "id=" + id + ", subjectCode=" + subjectCode + ", materialName=" + materialName + ", materialFile=" + materialFile + ", uploadedAt=" + uploadedAt + ", description=" + description + '}';
//    }

    public Materials() {
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public Materials(String mid, String subjectCode, String materialName, String materialFile, String uploadedAt, String description) {
        this.mid = mid;
        this.subjectCode = subjectCode;
        this.materialName = materialName;
        this.materialFile = materialFile;
        this.uploadedAt = uploadedAt;
        this.description = description;
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

    @Override
    public String toString() {
        return "Materials{" + "id=" + id + ", mid=" + mid + ", subjectCode=" + subjectCode + ", materialName=" + materialName + ", materialFile=" + materialFile + ", uploadedAt=" + uploadedAt + ", description=" + description + '}';
    }
}
