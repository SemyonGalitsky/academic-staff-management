public class Department {
    private String name;
    private int studentCount;
    private Lecturer[] lecturers = new Lecturer[2];
    private int lecturerCount = 0;

    public Department(String name, int studentCount) throws ManagementException {
        setName(name);
        setStudentCount(studentCount);
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public void setStudentCount(int studentCount) throws ManagementException {
        if (studentCount >= 0) {
            this.studentCount = studentCount;
        } else {
            throw new ManagementException("Student count cannot be negative.");
        }
    }
    public int getStudentCount() { return studentCount; }

    public void addLecturer(Lecturer lecturer) throws ManagementException {
        if (lecturer.getDepartment() != null) {
            throw new ManagementException("- Lecturer already part of a department.");
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
    }

    public double getAverageWage() {
        if (lecturerCount == 0) return 0;

        double sum = 0;
        for (int i = 0; i < lecturerCount; i++) {
            sum += lecturers[i].getWage();
        }
        return sum / lecturerCount;
    }


    public boolean equals(Object obj) {
        // TODO: לבדוק בעזרת instanceof אם obj הוא מסוג Department
        // TODO: לבצע המרה (Casting) ל-Department ולהשוות בין שמות המחלקות (this.name.equals(((Department)obj).name))
        return false;
    }

    public String toString() {
        // TODO: להחזיר מחרוזת שמייצגת את המחלקה (למשל: שם המחלקה, כמות סטודנטים)
        // TODO: (אופציונלי) אפשר גם לרוץ בלולאה ולשרשר את שמות המרצים שבמערך lecturers
        return "";
    }
}