package Model;

import lombok.Builder;

@Builder

public class Subjects {

    private int id;
    private String code;
    private String name;
    private int credits;
    private String description;
    private int semester;

    // Constructor
    public Subjects() {
    }

    public Subjects(int id, String code) {
        this.id = id;
        this.code = code;
    }

    public Subjects(int id, String code, String name, int credits, String description, int semester) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.credits = credits;
        this.description = description;
        this.semester = semester;
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

}
