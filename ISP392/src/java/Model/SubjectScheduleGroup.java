package Model;

import java.util.List;

public class SubjectScheduleGroup {
    private String subjectCode;
    private String subjectName;
    private List<Schedule> schedules;

    public SubjectScheduleGroup(String subjectCode, List<Schedule> schedules) {
        this.subjectCode = subjectCode;
        this.subjectName = subjectName;
        this.schedules = schedules;
    }

    // Getters and setters
    public String getSubjectCode() { return subjectCode; }
    public String getSubjectName() { return subjectName; }
    public List<Schedule> getSchedules() { return schedules; }
}
