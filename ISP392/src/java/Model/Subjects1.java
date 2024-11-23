package Model;

public class Subjects1 {
    private int id; 
    private String code; 
    private String name;
    private int credits; 
    private String description; 
    private int semester; 
    private int lecturerId;
    
    private Subjects1 conditionSubject1;
    private Subjects1 conditionSubject2;

    public Subjects1(int id, String code, String name, String description) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.description = description;
        
    }

    @Override
    public String toString() {
        return "Subjects1{" + "id=" + id + ", code=" + code + ", name=" + name + ", credits=" + credits + ", description=" + description + ", semester=" + semester + ", lecturerId=" + lecturerId + ", conditionSubject1=" + conditionSubject1 + ", conditionSubject2=" + conditionSubject2 + '}';
    }

   


    public Subjects1() {
    }

    public Subjects1(int id, String code, String name, int credits, String description, int semester, int lecturerId) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.credits = credits;
        this.description = description;
        this.semester = semester;
        this.lecturerId = lecturerId;
    }

    public Subjects1(int id, String code, String name, int credits, String description, int semester, int lecturerId, Subjects1 conditionSubject1, Subjects1 conditionSubject2) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.credits = credits;
        this.description = description;
        this.semester = semester;
        this.lecturerId = lecturerId;
        this.conditionSubject1 = conditionSubject1;
        this.conditionSubject2 = conditionSubject2;
    }

    public Subjects1 getConditionSubject1() {
        return conditionSubject1;
    }

    public void setConditionSubject1(Subjects1 conditionSubject1) {
        this.conditionSubject1 = conditionSubject1;
    }

    public Subjects1 getConditionSubject2() {
        return conditionSubject2;
    }

    public void setConditionSubject2(Subjects1 conditionSubject2) {
        this.conditionSubject2 = conditionSubject2;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public int getLecturerId() {
        return lecturerId;
    }

    public void setLecturerId(int lecturerId) {
        this.lecturerId = lecturerId;
    }

   
}
