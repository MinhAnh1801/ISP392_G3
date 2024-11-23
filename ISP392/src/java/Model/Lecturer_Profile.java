package Model;

import java.util.Date;

public class Lecturer_Profile {

    private int lecturerId;           // ID giảng viên
    private String expertise;         // Chuyên môn
    private String office;            // Văn phòng
    private String fullName;          // Tên đầy đủ
    private String email;             // Địa chỉ email
    private String phoneNumber;       // Số điện thoại
    private String department;         // Khoa/ bộ phận
    private Date joiningDate;         // Ngày gia nhập
    private String bio;               // Tiểu sử
    private String photoUrl;          // URL ảnh chân dung
    private String researchInterest;   // Lĩnh vực nghiên cứu
    private String publications;       // Công trình nghiên cứu
    private String awards;             // Giải thưởng
    private Date createdAt;           // Thời gian tạo hồ sơ
    private Date updatedAt;           // Thời gian cập nhật hồ sơ
    private int researchSkill;        // Kỹ năng nghiên cứu
    private int teachingSkill;        // Kỹ năng giảng dạy
    private int mentoringSkill;       // Kỹ năng hướng dẫn
    int major_id;
    // Constructor

    @Override
    public String toString() {
        return "Lecturer_Profile{" + "lecturerId=" + lecturerId + ", expertise=" + expertise + ", office=" + office + ", fullName=" + fullName + ", email=" + email + ", phoneNumber=" + phoneNumber + ", department=" + department + ", joiningDate=" + joiningDate + ", bio=" + bio + ", photoUrl=" + photoUrl + ", researchInterest=" + researchInterest + ", publications=" + publications + ", awards=" + awards + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", researchSkill=" + researchSkill + ", teachingSkill=" + teachingSkill + ", mentoringSkill=" + mentoringSkill + ", major_id=" + major_id + '}';
    }
    
    public Lecturer_Profile() {
    }
    public Lecturer_Profile(int lecturerId, String fullName,String email,String department,String office, int major_id) {
        this.lecturerId = lecturerId;
        this.fullName = fullName;
        this.department = department;
        this.major_id = major_id;
        this.email = email;
        this.office = office;
    }

    public Lecturer_Profile(int lecturerId, String expertise, String office, String fullName, String email, String phoneNumber, String department, Date joiningDate, String bio, String photoUrl, String researchInterest, String publications, String awards, Date createdAt, Date updatedAt, int researchSkill, int teachingSkill, int mentoringSkill) {
        this.lecturerId = lecturerId;
        this.expertise = expertise;
        this.office = office;
        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.department = department;
        this.joiningDate = joiningDate;
        this.bio = bio;
        this.photoUrl = photoUrl;
        this.researchInterest = researchInterest;
        this.publications = publications;
        this.awards = awards;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.researchSkill = researchSkill;
        this.teachingSkill = teachingSkill;
        this.mentoringSkill = mentoringSkill;
    }

    public int getMajor_id() {
        return major_id;
    }

    public void setMajor_id(int major_id) {
        this.major_id = major_id;
    }

    public int getResearchSkill() {
        return researchSkill;
    }

    public void setResearchSkill(int researchSkill) {
        this.researchSkill = researchSkill;
    }

    public int getTeachingSkill() {
        return teachingSkill;
    }

    public void setTeachingSkill(int teachingSkill) {
        this.teachingSkill = teachingSkill;
    }

    public int getMentoringSkill() {
        return mentoringSkill;
    }

    public void setMentoringSkill(int mentoringSkill) {
        this.mentoringSkill = mentoringSkill;
    }


    public int getLecturerId() {
        return lecturerId;
    }

    public void setLecturerId(int lecturerId) {
        this.lecturerId = lecturerId;
    }

    public String getExpertise() {
        return expertise;
    }

    public void setExpertise(String expertise) {
        this.expertise = expertise;
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Date getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(Date joiningDate) {
        this.joiningDate = joiningDate;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getResearchInterest() {
        return researchInterest;
    }

    public void setResearchInterest(String researchInterest) {
        this.researchInterest = researchInterest;
    }

    public String getPublications() {
        return publications;
    }

    public void setPublications(String publications) {
        this.publications = publications;
    }

    public String getAwards() {
        return awards;
    }

    public void setAwards(String awards) {
        this.awards = awards;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

}
