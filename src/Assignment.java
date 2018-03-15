

import java.time.LocalDateTime;

public class Assignment {

    private LocalDateTime date;
    private Course course;
    private Category category;
    private Priority priorityLevel;

    public Assignment() {
        date = LocalDateTime.now();
        course = Course.DATA_STRUCTURES;
        category = Category.HOMEWORK;
        priorityLevel = Priority.HIGH_PRIORITY;
    }

    public Assignment(LocalDateTime date, Course course, Category category, Priority priorityLevel) {
        this.date = date;
        this.course = course;
        this.category = category;
        this.priorityLevel = priorityLevel;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Priority getPriorityLevel() {
        return priorityLevel;
    }

    public void setPriorityLevel(Priority priorityLevel) {
        this.priorityLevel = priorityLevel;
    }

    @Override
    public String toString() {
        return "\nAssignment Details\n" +
                "date: " + date +
                "\ncourse: " + course +
                "\ncategory: " + category +
                "\npriorityLevel: " + priorityLevel;
    }

    @Override
    public boolean equals(Object obj) {
        Assignment assignment = (Assignment) obj;
        if (this.date.equals(assignment.date) &&
                this.course.equals(assignment.course) &&
                this.category.equals(assignment.category) &&
                this.priorityLevel.equals(assignment.priorityLevel)){
            return true;
        }
        else {
            return false;
        }
    }

    public String compareTo(Assignment assignment){
        if (this.date.isBefore(assignment.date)){
            return "BEFORE";
        }
        else if (this.date.isAfter(assignment.date)){
            return "AFTER";
        }
        else {
            return "EQUALS";
        }
    }


}
