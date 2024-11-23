package models;

public class SubjectDetail {

    private String subjectName;
    private String subjectDescription;
    private int credits;
    private String materialName;
    private String materialLink;
    private String classroomName;
    private String dayOfWeek;
    private String startTime;
    private String endTime;
    private String lecturerName;
    private String lecturerEmail;

    public SubjectDetail() {
    }

// Constructor
    public SubjectDetail(String subjectName, String subjectDescription, int credits, String materialName,
            String materialLink, String classroomName, String dayOfWeek, String startTime,
            String endTime, String lecturerName, String lecturerEmail) {
        this.subjectName = subjectName;
        this.subjectDescription = subjectDescription;
        this.credits = credits;
        this.materialName = materialName;
        this.materialLink = materialLink;
        this.classroomName = classroomName;
        this.dayOfWeek = dayOfWeek;
        this.startTime = startTime;
        this.endTime = endTime;
        this.lecturerName = lecturerName;
        this.lecturerEmail = lecturerEmail;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getSubjectDescription() {
        return subjectDescription;
    }

    public void setSubjectDescription(String subjectDescription) {
        this.subjectDescription = subjectDescription;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public String getMaterialLink() {
        return materialLink;
    }

    public void setMaterialLink(String materialLink) {
        this.materialLink = materialLink;
    }

    public String getClassroomName() {
        return classroomName;
    }

    public void setClassroomName(String classroomName) {
        this.classroomName = classroomName;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getLecturerName() {
        return lecturerName;
    }

    public void setLecturerName(String lecturerName) {
        this.lecturerName = lecturerName;
    }

    public String getLecturerEmail() {
        return lecturerEmail;
    }

    public void setLecturerEmail(String lecturerEmail) {
        this.lecturerEmail = lecturerEmail;
    }

}
