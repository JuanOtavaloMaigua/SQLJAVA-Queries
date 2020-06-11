public class Faculty {
    private String faculty_id;
    private String faculty_name;
    private String office;

    public Faculty(String faculty_id, String faculty_name, String office) {
        this.faculty_id = faculty_id;
        this.faculty_name = faculty_name;
        this.office = office;
    }

    public String getFaculty_id() {
        return faculty_id;
    }

    public void setFaculty_id(String faculty_id) {
        this.faculty_id = faculty_id;
    }

    public String getFaculty_name() {
        return faculty_name;
    }

    public void setFaculty_name(String faculty_name) {
        this.faculty_name = faculty_name;
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }
    
    @Override
    public String toString(){
        return getFaculty_id() + " , " + getFaculty_name() + " , " + getOffice();
    }
}
