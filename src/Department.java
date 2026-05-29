public class Department {
    private String name;
    private int studentCount;
    private Lecturer[] lecturers = new Lecturer[2];
    private int lecturerCount = 0;

    public Department(String name, int studentCount) {
        setName(name);
        setStudentCount(studentCount);
    }

    private boolean setName(String name) {
        this.name = name;
        return true;
    }
    public String getName(){
        return name;
    }

    public boolean setStudentCount(int studentCount) {
        if (studentCount >= 0) {
            this.studentCount = studentCount;
            return true;
        }
        return false;
    }
    public int getStudentCount() {
        return studentCount;
    }

    public boolean addLecturer(Lecturer lecturer) {
        if (lecturer.getDepartment() != null) {
            System.out.println("Error: Lecturer already part of department.");
            return false;
        }
        if (lecturerCount == lecturers.length) {
            Lecturer[] newLecturers = new Lecturer[lecturerCount * 2];
            for (int i = 0; i < lecturerCount; i++) {
                newLecturers[i] = lecturers[i];
            }
            lecturers = newLecturers;
        }
        lecturers[lecturerCount] = lecturer;
        lecturerCount++;
        lecturer.setDepartment(this);
        return true;
    }
    public Lecturer[] getLecturers() {
        return lecturers;
    }
    public int getLecturerCount() {
        return lecturerCount;
    }

    public double getAverageWage() {
        double avg = 0;
        double sum = 0;
        if (lecturerCount == 0) {
            return avg;
        }
        for (int i = 0; i < lecturerCount; i++) {
            sum += lecturers[i].getWage();
        }
        avg = sum / lecturerCount;
        return avg;
    }
}

