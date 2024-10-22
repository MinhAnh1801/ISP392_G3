package Model;

public class Subjects {
    private int id; 
    private String code; 
    private String name;
    private int credits; 
    private String description; 
    private int semester; 
    private int lecturerId;
    
    private Subjects conditionSubject1;
    private Subjects conditionSubject2;


    public Subjects() {
    }

    public Subjects(int id, String code, String name, int credits, String description, int semester, int lecturerId) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.credits = credits;
        this.description = description;
        this.semester = semester;
        this.lecturerId = lecturerId;
    }

    public Subjects(int id, String code, String name, int credits, String description, int semester, int lecturerId, Subjects conditionSubject1, Subjects conditionSubject2) {
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

    public Subjects getConditionSubject1() {
        return conditionSubject1;
    }

    public void setConditionSubject1(Subjects conditionSubject1) {
        this.conditionSubject1 = conditionSubject1;
    }

    public Subjects getConditionSubject2() {
        return conditionSubject2;
    }

    public void setConditionSubject2(Subjects conditionSubject2) {
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
