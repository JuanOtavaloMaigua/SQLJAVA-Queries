public class Courses {
    private String course_id;
    private String course;
    private String faculty_id;

    public Courses(String course_id, String course, String faculty_id) {
        this.course_id = course_id;
        this.course = course;
        this.faculty_id = faculty_id;
    }

    public String getCourse_id() {
        return course_id;
    }

    public void setCourse_id(String course_id) {
        this.course_id = course_id;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getFaculty_id() {
        return faculty_id;
    }

    public void setFaculty_id(String faculty_id) {
        this.faculty_id = faculty_id;
    }
    
    @Override
    public String toString(){
        return getCourse_id() + " , " + getCourse() + " , " + getFaculty_id();
    }
    
}
