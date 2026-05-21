public class Department {
    private String name;
    private int studentCount;
    private Lecturer[] lecturers;
    private int lecturerCount;

    public Department(String name, int studentCount) {
        this.name = name;
        this.studentCount = studentCount;
        this.lecturers = new Lecturer[2];
        this.lecturerCount = 0;
    }
    //getters
    public String getName(){
        return name;
    }
    public int getStudentCount(){
        return studentCount;
    }
    public Lecturer[] getLecturers() {
        return lecturers;
    }
    public int getLecturerCount(){
        return lecturerCount;
    }



}
