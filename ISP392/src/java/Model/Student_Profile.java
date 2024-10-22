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
     private String ten_phu_huynh;
    private String so_dien_thoai_phu_huynh;
    private String dia_chi_phu_huynh;
    private String email_phu_huynh;
    private String nghe_nghiep_phu_huynh;
    private String noi_lam_viec_phu_huynh;
    private String so_bang_dang_ky;
    private String so_bang_dang_ky_cu;
    private String ma_thanh_vien;
    private String ngay_ghi_danh;
    private String hinh_thuc_hoc;
    private String trang_thai;
    private String hoc_ky_hien_tai;
    private String nganh_hoc;
    private String chuong_trinh_hoc;
    private String de_tai_tot_nghiep;
    private String so_du_tai_khoan;
    private String nganh_cu;
    private String quyet_dinh_chuyen_nganh;
    private String quyet_dinh_cong_nhan_sinh_vien_chinh_quy;
    private String ngay_cong_nhan_sinh_vien_chinh_quy;
    private String quyet_dinh_cong_nhan_sinh_vien_du_bi;
    private String thoi_han_hoc_tai_truong;
    private String lop_chinh;
    private String loai_tai_chinh;
    private String quyet_dinh_thoi_hoc;
    private String quyet_dinh_chuyen_co_so;
    private String quyet_dinh_ky_luat;
    private String quyet_dinh_cong_nhan_tot_nghiep;
    private String ngay_cong_nhan_tot_nghiep;
    private String trang_thai_den;
    private String chuyen_nganh;
    public Student_Profile() {
    }

    public Student_Profile(User student_id, Major major_id, String year_of_study, String full_name, Date date_of_birth, String phone_number, String email, String gender, String student_code, String address, String emergency_contact, String photo, String national_id, String class_id, int enrollment_year, int graduation_year, double gpa, boolean scholarship_status, String medical_conditions, String ten_phu_huynh, String so_dien_thoai_phu_huynh, String dia_chi_phu_huynh, String email_phu_huynh, String nghe_nghiep_phu_huynh, String noi_lam_viec_phu_huynh, String so_bang_dang_ky, String so_bang_dang_ky_cu, String ma_thanh_vien, String ngay_ghi_danh, String hinh_thuc_hoc, String trang_thai, String hoc_ky_hien_tai, String nganh_hoc, String chuong_trinh_hoc, String de_tai_tot_nghiep, String so_du_tai_khoan, String nganh_cu, String quyet_dinh_chuyen_nganh, String quyet_dinh_cong_nhan_sinh_vien_chinh_quy, String ngay_cong_nhan_sinh_vien_chinh_quy, String quyet_dinh_cong_nhan_sinh_vien_du_bi, String thoi_han_hoc_tai_truong, String lop_chinh, String loai_tai_chinh, String quyet_dinh_thoi_hoc, String quyet_dinh_chuyen_co_so, String quyet_dinh_ky_luat, String quyet_dinh_cong_nhan_tot_nghiep, String ngay_cong_nhan_tot_nghiep, String trang_thai_den, String chuyen_nganh) {
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
        this.ten_phu_huynh = ten_phu_huynh;
        this.so_dien_thoai_phu_huynh = so_dien_thoai_phu_huynh;
        this.dia_chi_phu_huynh = dia_chi_phu_huynh;
        this.email_phu_huynh = email_phu_huynh;
        this.nghe_nghiep_phu_huynh = nghe_nghiep_phu_huynh;
        this.noi_lam_viec_phu_huynh = noi_lam_viec_phu_huynh;
        this.so_bang_dang_ky = so_bang_dang_ky;
        this.so_bang_dang_ky_cu = so_bang_dang_ky_cu;
        this.ma_thanh_vien = ma_thanh_vien;
        this.ngay_ghi_danh = ngay_ghi_danh;
        this.hinh_thuc_hoc = hinh_thuc_hoc;
        this.trang_thai = trang_thai;
        this.hoc_ky_hien_tai = hoc_ky_hien_tai;
        this.nganh_hoc = nganh_hoc;
        this.chuong_trinh_hoc = chuong_trinh_hoc;
        this.de_tai_tot_nghiep = de_tai_tot_nghiep;
        this.so_du_tai_khoan = so_du_tai_khoan;
        this.nganh_cu = nganh_cu;
        this.quyet_dinh_chuyen_nganh = quyet_dinh_chuyen_nganh;
        this.quyet_dinh_cong_nhan_sinh_vien_chinh_quy = quyet_dinh_cong_nhan_sinh_vien_chinh_quy;
        this.ngay_cong_nhan_sinh_vien_chinh_quy = ngay_cong_nhan_sinh_vien_chinh_quy;
        this.quyet_dinh_cong_nhan_sinh_vien_du_bi = quyet_dinh_cong_nhan_sinh_vien_du_bi;
        this.thoi_han_hoc_tai_truong = thoi_han_hoc_tai_truong;
        this.lop_chinh = lop_chinh;
        this.loai_tai_chinh = loai_tai_chinh;
        this.quyet_dinh_thoi_hoc = quyet_dinh_thoi_hoc;
        this.quyet_dinh_chuyen_co_so = quyet_dinh_chuyen_co_so;
        this.quyet_dinh_ky_luat = quyet_dinh_ky_luat;
        this.quyet_dinh_cong_nhan_tot_nghiep = quyet_dinh_cong_nhan_tot_nghiep;
        this.ngay_cong_nhan_tot_nghiep = ngay_cong_nhan_tot_nghiep;
        this.trang_thai_den = trang_thai_den;
        this.chuyen_nganh = chuyen_nganh;
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

    public String getTen_phu_huynh() {
        return ten_phu_huynh;
    }

    public void setTen_phu_huynh(String ten_phu_huynh) {
        this.ten_phu_huynh = ten_phu_huynh;
    }

    public String getSo_dien_thoai_phu_huynh() {
        return so_dien_thoai_phu_huynh;
    }

    public void setSo_dien_thoai_phu_huynh(String so_dien_thoai_phu_huynh) {
        this.so_dien_thoai_phu_huynh = so_dien_thoai_phu_huynh;
    }

    public String getDia_chi_phu_huynh() {
        return dia_chi_phu_huynh;
    }

    public void setDia_chi_phu_huynh(String dia_chi_phu_huynh) {
        this.dia_chi_phu_huynh = dia_chi_phu_huynh;
    }

    public String getEmail_phu_huynh() {
        return email_phu_huynh;
    }

    public void setEmail_phu_huynh(String email_phu_huynh) {
        this.email_phu_huynh = email_phu_huynh;
    }

    public String getNghe_nghiep_phu_huynh() {
        return nghe_nghiep_phu_huynh;
    }

    public void setNghe_nghiep_phu_huynh(String nghe_nghiep_phu_huynh) {
        this.nghe_nghiep_phu_huynh = nghe_nghiep_phu_huynh;
    }

    public String getNoi_lam_viec_phu_huynh() {
        return noi_lam_viec_phu_huynh;
    }

    public void setNoi_lam_viec_phu_huynh(String noi_lam_viec_phu_huynh) {
        this.noi_lam_viec_phu_huynh = noi_lam_viec_phu_huynh;
    }

    public String getSo_bang_dang_ky() {
        return so_bang_dang_ky;
    }

    public void setSo_bang_dang_ky(String so_bang_dang_ky) {
        this.so_bang_dang_ky = so_bang_dang_ky;
    }

    public String getSo_bang_dang_ky_cu() {
        return so_bang_dang_ky_cu;
    }

    public void setSo_bang_dang_ky_cu(String so_bang_dang_ky_cu) {
        this.so_bang_dang_ky_cu = so_bang_dang_ky_cu;
    }

    public String getMa_thanh_vien() {
        return ma_thanh_vien;
    }

    public void setMa_thanh_vien(String ma_thanh_vien) {
        this.ma_thanh_vien = ma_thanh_vien;
    }


    public String getHinh_thuc_hoc() {
        return hinh_thuc_hoc;
    }

    public void setHinh_thuc_hoc(String hinh_thuc_hoc) {
        this.hinh_thuc_hoc = hinh_thuc_hoc;
    }

    public String getTrang_thai() {
        return trang_thai;
    }

    public void setTrang_thai(String trang_thai) {
        this.trang_thai = trang_thai;
    }

 

    public String getNganh_hoc() {
        return nganh_hoc;
    }

    public void setNganh_hoc(String nganh_hoc) {
        this.nganh_hoc = nganh_hoc;
    }

    public String getChuong_trinh_hoc() {
        return chuong_trinh_hoc;
    }

    public void setChuong_trinh_hoc(String chuong_trinh_hoc) {
        this.chuong_trinh_hoc = chuong_trinh_hoc;
    }

    public String getDe_tai_tot_nghiep() {
        return de_tai_tot_nghiep;
    }

    public void setDe_tai_tot_nghiep(String de_tai_tot_nghiep) {
        this.de_tai_tot_nghiep = de_tai_tot_nghiep;
    }

 

    public String getNganh_cu() {
        return nganh_cu;
    }

    public void setNganh_cu(String nganh_cu) {
        this.nganh_cu = nganh_cu;
    }

    public String getQuyet_dinh_chuyen_nganh() {
        return quyet_dinh_chuyen_nganh;
    }

    public void setQuyet_dinh_chuyen_nganh(String quyet_dinh_chuyen_nganh) {
        this.quyet_dinh_chuyen_nganh = quyet_dinh_chuyen_nganh;
    }

    public String getQuyet_dinh_cong_nhan_sinh_vien_chinh_quy() {
        return quyet_dinh_cong_nhan_sinh_vien_chinh_quy;
    }

    public void setQuyet_dinh_cong_nhan_sinh_vien_chinh_quy(String quyet_dinh_cong_nhan_sinh_vien_chinh_quy) {
        this.quyet_dinh_cong_nhan_sinh_vien_chinh_quy = quyet_dinh_cong_nhan_sinh_vien_chinh_quy;
    }

 
    public String getQuyet_dinh_cong_nhan_sinh_vien_du_bi() {
        return quyet_dinh_cong_nhan_sinh_vien_du_bi;
    }

    public void setQuyet_dinh_cong_nhan_sinh_vien_du_bi(String quyet_dinh_cong_nhan_sinh_vien_du_bi) {
        this.quyet_dinh_cong_nhan_sinh_vien_du_bi = quyet_dinh_cong_nhan_sinh_vien_du_bi;
    }



    public String getLop_chinh() {
        return lop_chinh;
    }

    public void setLop_chinh(String lop_chinh) {
        this.lop_chinh = lop_chinh;
    }

    public String getLoai_tai_chinh() {
        return loai_tai_chinh;
    }

    public void setLoai_tai_chinh(String loai_tai_chinh) {
        this.loai_tai_chinh = loai_tai_chinh;
    }

    public String getQuyet_dinh_thoi_hoc() {
        return quyet_dinh_thoi_hoc;
    }

    public void setQuyet_dinh_thoi_hoc(String quyet_dinh_thoi_hoc) {
        this.quyet_dinh_thoi_hoc = quyet_dinh_thoi_hoc;
    }

    public String getQuyet_dinh_chuyen_co_so() {
        return quyet_dinh_chuyen_co_so;
    }

    public void setQuyet_dinh_chuyen_co_so(String quyet_dinh_chuyen_co_so) {
        this.quyet_dinh_chuyen_co_so = quyet_dinh_chuyen_co_so;
    }

    public String getQuyet_dinh_ky_luat() {
        return quyet_dinh_ky_luat;
    }

    public void setQuyet_dinh_ky_luat(String quyet_dinh_ky_luat) {
        this.quyet_dinh_ky_luat = quyet_dinh_ky_luat;
    }

    public String getQuyet_dinh_cong_nhan_tot_nghiep() {
        return quyet_dinh_cong_nhan_tot_nghiep;
    }

    public void setQuyet_dinh_cong_nhan_tot_nghiep(String quyet_dinh_cong_nhan_tot_nghiep) {
        this.quyet_dinh_cong_nhan_tot_nghiep = quyet_dinh_cong_nhan_tot_nghiep;
    }

    public String getNgay_ghi_danh() {
        return ngay_ghi_danh;
    }

    public void setNgay_ghi_danh(String ngay_ghi_danh) {
        this.ngay_ghi_danh = ngay_ghi_danh;
    }

    public String getHoc_ky_hien_tai() {
        return hoc_ky_hien_tai;
    }

    public void setHoc_ky_hien_tai(String hoc_ky_hien_tai) {
        this.hoc_ky_hien_tai = hoc_ky_hien_tai;
    }

    public String getSo_du_tai_khoan() {
        return so_du_tai_khoan;
    }

    public void setSo_du_tai_khoan(String so_du_tai_khoan) {
        this.so_du_tai_khoan = so_du_tai_khoan;
    }

    public String getNgay_cong_nhan_sinh_vien_chinh_quy() {
        return ngay_cong_nhan_sinh_vien_chinh_quy;
    }

    public void setNgay_cong_nhan_sinh_vien_chinh_quy(String ngay_cong_nhan_sinh_vien_chinh_quy) {
        this.ngay_cong_nhan_sinh_vien_chinh_quy = ngay_cong_nhan_sinh_vien_chinh_quy;
    }

    public String getThoi_han_hoc_tai_truong() {
        return thoi_han_hoc_tai_truong;
    }

    public void setThoi_han_hoc_tai_truong(String thoi_han_hoc_tai_truong) {
        this.thoi_han_hoc_tai_truong = thoi_han_hoc_tai_truong;
    }

    public String getNgay_cong_nhan_tot_nghiep() {
        return ngay_cong_nhan_tot_nghiep;
    }

    public void setNgay_cong_nhan_tot_nghiep(String ngay_cong_nhan_tot_nghiep) {
        this.ngay_cong_nhan_tot_nghiep = ngay_cong_nhan_tot_nghiep;
    }

    

    public String getTrang_thai_den() {
        return trang_thai_den;
    }

    public void setTrang_thai_den(String trang_thai_den) {
        this.trang_thai_den = trang_thai_den;
    }

    public String getChuyen_nganh() {
        return chuyen_nganh;
    }

    public void setChuyen_nganh(String chuyen_nganh) {
        this.chuyen_nganh = chuyen_nganh;
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

    @Override
    public String toString() {
        return "Student_Profile{" + "student_id=" + student_id + ", major_id=" + major_id + ", year_of_study=" + year_of_study + ", full_name=" + full_name + ", date_of_birth=" + date_of_birth + ", phone_number=" + phone_number + ", email=" + email + ", gender=" + gender + ", student_code=" + student_code + ", address=" + address + ", emergency_contact=" + emergency_contact + ", photo=" + photo + ", national_id=" + national_id + ", class_id=" + class_id + ", enrollment_year=" + enrollment_year + ", graduation_year=" + graduation_year + ", gpa=" + gpa + ", scholarship_status=" + scholarship_status + ", medical_conditions=" + medical_conditions + ", ten_phu_huynh=" + ten_phu_huynh + ", so_dien_thoai_phu_huynh=" + so_dien_thoai_phu_huynh + ", dia_chi_phu_huynh=" + dia_chi_phu_huynh + ", email_phu_huynh=" + email_phu_huynh + ", nghe_nghiep_phu_huynh=" + nghe_nghiep_phu_huynh + ", noi_lam_viec_phu_huynh=" + noi_lam_viec_phu_huynh + ", so_bang_dang_ky=" + so_bang_dang_ky + ", so_bang_dang_ky_cu=" + so_bang_dang_ky_cu + ", ma_thanh_vien=" + ma_thanh_vien + ", ngay_ghi_danh=" + ngay_ghi_danh + ", hinh_thuc_hoc=" + hinh_thuc_hoc + ", trang_thai=" + trang_thai + ", hoc_ky_hien_tai=" + hoc_ky_hien_tai + ", nganh_hoc=" + nganh_hoc + ", chuong_trinh_hoc=" + chuong_trinh_hoc + ", de_tai_tot_nghiep=" + de_tai_tot_nghiep + ", so_du_tai_khoan=" + so_du_tai_khoan + ", nganh_cu=" + nganh_cu + ", quyet_dinh_chuyen_nganh=" + quyet_dinh_chuyen_nganh + ", quyet_dinh_cong_nhan_sinh_vien_chinh_quy=" + quyet_dinh_cong_nhan_sinh_vien_chinh_quy + ", ngay_cong_nhan_sinh_vien_chinh_quy=" + ngay_cong_nhan_sinh_vien_chinh_quy + ", quyet_dinh_cong_nhan_sinh_vien_du_bi=" + quyet_dinh_cong_nhan_sinh_vien_du_bi + ", thoi_han_hoc_tai_truong=" + thoi_han_hoc_tai_truong + ", lop_chinh=" + lop_chinh + ", loai_tai_chinh=" + loai_tai_chinh + ", quyet_dinh_thoi_hoc=" + quyet_dinh_thoi_hoc + ", quyet_dinh_chuyen_co_so=" + quyet_dinh_chuyen_co_so + ", quyet_dinh_ky_luat=" + quyet_dinh_ky_luat + ", quyet_dinh_cong_nhan_tot_nghiep=" + quyet_dinh_cong_nhan_tot_nghiep + ", ngay_cong_nhan_tot_nghiep=" + ngay_cong_nhan_tot_nghiep + ", trang_thai_den=" + trang_thai_den + ", chuyen_nganh=" + chuyen_nganh + '}';
    }

   
    
    
    
}
