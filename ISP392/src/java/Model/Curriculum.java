/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author khucx
 */
public class Curriculum {

    private Major major_id;
    private Subjects1 subject_id;
    private Subjects1 condition_subject_2;
    private Subjects1 condition_subject_1;
    private int credits;
    private int semester;

    public Curriculum() {
    }

    public Curriculum(Major major_id, Subjects1 subject_id, Subjects1 condition_subject_2, Subjects1 condition_subject_1, int credits, int semester) {
        this.major_id = major_id;
        this.subject_id = subject_id;
        this.condition_subject_2 = condition_subject_2;
        this.condition_subject_1 = condition_subject_1;
        this.credits = credits;
        this.semester = semester;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }
    
    

    public Major getMajor_id() {
        return major_id;
    }

    public void setMajor_id(Major major_id) {
        this.major_id = major_id;
    }

    public Subjects1 getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(Subjects1 subject_id) {
        this.subject_id = subject_id;
    }

    public Subjects1 getCondition_subject_2() {
        return condition_subject_2;
    }

    public void setCondition_subject_2(Subjects1 condition_subject_2) {
        this.condition_subject_2 = condition_subject_2;
    }

    public Subjects1 getCondition_subject_1() {
        return condition_subject_1;
    }

    public void setCondition_subject_1(Subjects1 condition_subject_1) {
        this.condition_subject_1 = condition_subject_1;
    }

    @Override
    public String toString() {
        return "Curriculum{" + "major_id=" + major_id + ", subject_id=" + subject_id + ", condition_subject_2=" + condition_subject_2 + ", condition_subject_1=" + condition_subject_1 + '}';
    }

}
