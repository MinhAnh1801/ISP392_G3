/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author admin
 */
public class GradeReport {
    private int id;
    private double grade;
    private String uploadDate;
    private String comments;
    private int type;
    private int percentId;
    String typeName;
    float percent;
    String subject_name;

    public GradeReport() {
    }

    public GradeReport(int id, double grade, String uploadDate, String comments, int type, int percentId, String typeName, float percent, String subject_name) {
        this.id = id;
        this.grade = grade;
        this.uploadDate = uploadDate;
        this.comments = comments;
        this.type = type;
        this.percentId = percentId;
        this.typeName = typeName;
        this.percent = percent;
        this.subject_name = subject_name;
    }

    public String getSubject_name() {
        return subject_name;
    }

    public void setSubject_name(String subject_name) {
        this.subject_name = subject_name;
    }

    public GradeReport(int id, double grade, String uploadDate, String comments, int type, int percentId, String typeName, float percent) {
        this.id = id;
        this.grade = grade;
        this.uploadDate = uploadDate;
        this.comments = comments;
        this.type = type;
        this.percentId = percentId;
        this.typeName = typeName;
        this.percent = percent;
    }
    
    
    public GradeReport(int id, double grade, String uploadDate, String comments, int type, int percentId) {
        this.id = id;
        
        this.grade = grade;
        this.uploadDate = uploadDate;
        this.comments = comments;
        this.type = type;
        this.percentId = percentId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public float getPercent() {
        return percent;
    }

    public void setPercent(float percent) {
        this.percent = percent;
    }

    
    
    // Getters và setters
    public int getId() { 
        return id; 
    }
    public void setId(int id) { 
        this.id = id; 
    }
    public double getGrade() { 
        return grade; 
    }
    public void setGrade(double grade) { this.grade = grade; }
    public String getUploadDate() { return uploadDate; }
    public void setUploadDate(String uploadDate) { this.uploadDate = uploadDate; }
    public String getComments() { return comments; }
    public void setComments(String comments) { this.comments = comments; }
    public int getType() { return type; }
    public void setType(int type) { this.type = type; }
    public int getPercentId() { return percentId; }
    public void setPercentId(int percentId) { this.percentId = percentId; }

    @Override
    public String toString() {
        return "GradeReport{" + "id=" + id + ", grade=" + grade + ", uploadDate=" + uploadDate + ", comments=" + comments + ", type=" + type + ", percentId=" + percentId + '}';
    }
    
    
}
