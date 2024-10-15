package Model;

public class Subjects {
    private int id,credits;
    private String code, name, description,semester;

    @Override
    public String toString() {
        return "Subjects{" + "id=" + id + ", credits=" + credits + ", code=" + code + ", name=" + name + ", description=" + description + ", semester=" + semester + '}';
    }

    public Subjects(int id, String code) {
        this.id = id;
        this.code = code;
    }

    public Subjects(int id, int credits, String code, String name, String description, String semester) {
        this.id = id;
        this.credits = credits;
        this.code = code;
        this.name = name;
        this.description = description;
        this.semester = semester;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }
    
}
