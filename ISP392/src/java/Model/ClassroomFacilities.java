package Model;

public class ClassroomFacilities {

    private int classroomID;
    private int numberOfTables;
    private int totalTables;
    private int numberOfChairs;
    private int totalChairs;
    private int numberOfLights;
    private int totalLights;
    private int numberOfProjectors;
    private int totalProjectors;
    private String tableCondition;
    private String chairCondition;
    private String lightCondition;
    private String projectorCondition;

    // Getters and setters for classroomID
    public int getClassroomID() {
        return classroomID;
    }

    public void setClassroomID(int classroomID) {
        this.classroomID = classroomID;
    }

    // Getters and setters for numberOfTables and totalTables
    public int getNumberOfTables() {
        return numberOfTables;
    }

    public void setNumberOfTables(int numberOfTables) {
        this.numberOfTables = numberOfTables;
    }

    public int getTotalTables() {
        return totalTables;
    }

    public void setTotalTables(int totalTables) {
        this.totalTables = totalTables;
    }

    // Getters and setters for numberOfChairs and totalChairs
    public int getNumberOfChairs() {
        return numberOfChairs;
    }

    public void setNumberOfChairs(int numberOfChairs) {
        this.numberOfChairs = numberOfChairs;
    }

    public int getTotalChairs() {
        return totalChairs;
    }

    public void setTotalChairs(int totalChairs) {
        this.totalChairs = totalChairs;
    }

    // Getters and setters for numberOfLights and totalLights
    public int getNumberOfLights() {
        return numberOfLights;
    }

    public void setNumberOfLights(int numberOfLights) {
        this.numberOfLights = numberOfLights;
    }

    public int getTotalLights() {
        return totalLights;
    }

    public void setTotalLights(int totalLights) {
        this.totalLights = totalLights;
    }

    // Getters and setters for numberOfProjectors and totalProjectors
    public int getNumberOfProjectors() {
        return numberOfProjectors;
    }

    public void setNumberOfProjectors(int numberOfProjectors) {
        this.numberOfProjectors = numberOfProjectors;
    }

    public int getTotalProjectors() {
        return totalProjectors;
    }

    public void setTotalProjectors(int totalProjectors) {
        this.totalProjectors = totalProjectors;
    }

    // Getters and setters for conditions
    public String getTableCondition() {
        return tableCondition;
    }

    public void setTableCondition(String tableCondition) {
        this.tableCondition = tableCondition;
    }

    public String getChairCondition() {
        return chairCondition;
    }

    public void setChairCondition(String chairCondition) {
        this.chairCondition = chairCondition;
    }

    public String getLightCondition() {
        return lightCondition;
    }

    public void setLightCondition(String lightCondition) {
        this.lightCondition = lightCondition;
    }

    public String getProjectorCondition() {
        return projectorCondition;
    }

    public void setProjectorCondition(String projectorCondition) {
        this.projectorCondition = projectorCondition;
    }
}
