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
public class Student_Profile {
    private int id;
    private User student_id;
    private Major major_id;
    private String year_of_study;
    private String full_name;
    private Date date_of_birth; 
    private String phone_number;
    private String email;
    private String gender;
    private String student_code;
    private String address;
    private String emergency_contact;
    private String photo; 
    private String national_id;
    private String class_id;
    private int enrollment_year;
    private int graduation_year;
    private double gpa;
    private boolean scholarship_status;
    private String medical_conditions;
    public Student_Profile() {
    }

    public Student_Profile(int id, Major major_id, String year_of_study) {
        this.id = id;
        this.major_id = major_id;
        this.year_of_study = year_of_study;
    }
    
    public Student_Profile(User student_id, Major major_id, String year_of_study) {
        this.student_id = student_id;
        this.major_id = major_id;
        this.year_of_study = year_of_study;
    }

    public Student_Profile(User student_id, Major major_id, String year_of_study, String full_name, Date date_of_birth, String phone_number, String email, String gender, String student_code, String address, String emergency_contact, String photo, String national_id, String class_id, int enrollment_year, int graduation_year, double gpa, boolean scholarship_status, String medical_conditions) {
        this.student_id = student_id;
        this.major_id = major_id;
        this.year_of_study = year_of_study;
        this.full_name = full_name;
        this.date_of_birth = date_of_birth;
        this.phone_number = phone_number;
        this.email = email;
        this.gender = gender;
        this.student_code = student_code;
        this.address = address;
        this.emergency_contact = emergency_contact;
        this.photo = photo;
        this.national_id = national_id;
        this.class_id = class_id;
        this.enrollment_year = enrollment_year;
        this.graduation_year = graduation_year;
        this.gpa = gpa;
        this.scholarship_status = scholarship_status;
        this.medical_conditions = medical_conditions;
    }

    @Override
    public String toString() {
        return "Student_Profile{" + "id=" + id + ", student_id=" + student_id + ", major_id=" + major_id + ", year_of_study=" + year_of_study + ", full_name=" + full_name + ", date_of_birth=" + date_of_birth + ", phone_number=" + phone_number + ", email=" + email + ", gender=" + gender + ", student_code=" + student_code + ", address=" + address + ", emergency_contact=" + emergency_contact + ", photo=" + photo + ", national_id=" + national_id + ", class_id=" + class_id + ", enrollment_year=" + enrollment_year + ", graduation_year=" + graduation_year + ", gpa=" + gpa + ", scholarship_status=" + scholarship_status + ", medical_conditions=" + medical_conditions + '}';
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public Date getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(Date date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getStudent_code() {
        return student_code;
    }

    public void setStudent_code(String student_code) {
        this.student_code = student_code;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmergency_contact() {
        return emergency_contact;
    }

    public void setEmergency_contact(String emergency_contact) {
        this.emergency_contact = emergency_contact;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getNational_id() {
        return national_id;
    }

    public void setNational_id(String national_id) {
        this.national_id = national_id;
    }

    public String getClass_id() {
        return class_id;
    }

    public void setClass_id(String class_id) {
        this.class_id = class_id;
    }

    public int getEnrollment_year() {
        return enrollment_year;
    }

    public void setEnrollment_year(int enrollment_year) {
        this.enrollment_year = enrollment_year;
    }

    public int getGraduation_year() {
        return graduation_year;
    }

    public void setGraduation_year(int graduation_year) {
        this.graduation_year = graduation_year;
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    public boolean isScholarship_status() {
        return scholarship_status;
    }

    public void setScholarship_status(boolean scholarship_status) {
        this.scholarship_status = scholarship_status;
    }

    public String getMedical_conditions() {
        return medical_conditions;
    }

    public void setMedical_conditions(String medical_conditions) {
        this.medical_conditions = medical_conditions;
    }

    

    public User getStudent_id() {
        return student_id;
    }

    public void setStudent_id(User student_id) {
        this.student_id = student_id;
    }

    public Major getMajor_id() {
        return major_id;
    }

    public void setMajor_id(Major major_id) {
        this.major_id = major_id;
    }

    public String getYear_of_study() {
        return year_of_study;
    }

    public void setYear_of_study(String year_of_study) {
        this.year_of_study = year_of_study;
    }

   
    
    
    
}