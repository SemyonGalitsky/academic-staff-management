public class Department {
    private String name;
    private int studentCount;
    private Lecturer[] lecturers = new Lecturer[2];
    private int lecturerCount = 0;

    public Department(String name, int studentCount) {
        setName(name);
        setStudentCount(studentCount);
    }

    public boolean setName(String name) {
        this.name = name;
        return true;
    }
    public String getName() { return name; }

    public boolean setStudentCount(int studentCount) {
        if (studentCount >= 0) {
            this.studentCount = studentCount;
            return true;
        }
        return false;
    }
    public int getStudentCount() { return studentCount; }

    public String addLecturer(Lecturer lecturer) {
        if (lecturer.getDepartment() != null) return "- Lecturer already part of a department.";

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
        return "SUCCESS";
    }

    public double getAverageWage() {
        if (lecturerCount == 0) return 0;

        double sum = 0;
        for (int i = 0; i < lecturerCount; i++) {
            sum += lecturers[i].getWage();
        }
        return sum / lecturerCount;
    }
}