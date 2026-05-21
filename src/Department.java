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

    private void growLecturers() {
        Lecturer[] newArray = new Lecturer[this.lecturers.length * 2];
        for (int i = 0; i < this.lecturers.length; i++) {
            newArray[i] = this.lecturers[i];
        }
        this.lecturers = newArray;
    }

    public void addLecturer(Lecturer lecturer) {
        if (lecturerCount >= lecturers.length) {
            growLecturers();
        }
        lecturers[lecturerCount] = lecturer;
        lecturerCount++;
        lecturer.setDepartment(this);
    }
}

