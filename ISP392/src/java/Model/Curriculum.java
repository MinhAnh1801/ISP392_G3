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
    private  Major major_id;
    private Subjects subject_id;
    private Subjects condition_subject_2;
    private Subjects condition_subject_1;

    public Curriculum() {
    }

    public Curriculum(Major major_id, Subjects subject_id, Subjects condition_subject_2, Subjects condition_subject_1) {
        this.major_id = major_id;
        this.subject_id = subject_id;
        this.condition_subject_2 = condition_subject_2;
        this.condition_subject_1 = condition_subject_1;
    }

    public Major getMajor_id() {
        return major_id;
    }

    public void setMajor_id(Major major_id) {
        this.major_id = major_id;
    }

    public Subjects getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(Subjects subject_id) {
        this.subject_id = subject_id;
    }

    public Subjects getCondition_subject_2() {
        return condition_subject_2;
    }

    public void setCondition_subject_2(Subjects condition_subject_2) {
        this.condition_subject_2 = condition_subject_2;
    }

    public Subjects getCondition_subject_1() {
        return condition_subject_1;
    }

    public void setCondition_subject_1(Subjects condition_subject_1) {
        this.condition_subject_1 = condition_subject_1;
    }

  
    @Override
    public String toString() {
        return "Curriculum{" + "major_id=" + major_id + ", subject_id=" + subject_id + ", condition_subject_2=" + condition_subject_2 + ", condition_subject_1=" + condition_subject_1 + '}';
    }
    
}
