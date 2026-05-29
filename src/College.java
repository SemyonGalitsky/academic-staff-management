public class College {
    private String name;
    private Lecturer[] lecturers = new Lecturer[2];
    private Committee[] committees = new Committee[2];
    private Department[] departments = new Department[2];
    private int lecturersCount = 0;
    private int committeesCount = 0;
    private int departmentsCount = 0;

    public College(String name) {
        setName(name);
    }

    public boolean addLecturer(String name, int id, int level, String degreeName, int wage){
        boolean isValid = true;

        if (getLecturerByName(name) != null) {
            System.out.println("Error: Lecturer already exists.");
            isValid = false;
        }

        if (id <= 99999999 || id >= 1000000000) {
            System.out.println("Error: Id out of range.");
            System.out.println("    must contain 9 digits.");
            isValid = false;
        }
        if (level > 5 || level < 0) {
            System.out.println("Error: Degree level out of range.");
            System.out.println("    must be between 1 to 4.");
            isValid = false;
        }
        if (wage < 0) {
            System.out.println("Error: Wage out of range.");
            System.out.println("    must be positive.");
            isValid = false;
        }
        if(!isValid) {
            return false;
        }

        if (lecturers.length == lecturersCount){
            Lecturer[] newLecturers = new Lecturer[lecturersCount * 2];
            for (int i = 0; i < lecturersCount; i++) {
                newLecturers[i] = lecturers[i];
            }
            lecturers = newLecturers;
        }

        Lecturer lecturer = new Lecturer(name, id, level, degreeName, wage);
        lecturers[lecturersCount] = lecturer;
        lecturersCount++;
        return true;
    }

    private Lecturer getLecturerByName(String name) {
        for (int i = 0; i < lecturersCount; i++) {
            if (lecturers[i].getName().equals(name)) {
                return lecturers[i];
            }
        }
        return null;
    }

    public double getAverageWage(int lecturersCount) {
        if (lecturersCount == 0) {
            return 0;
        }
        double wage = 0;
        for (int i = 0; i < lecturersCount; i++) {
            wage += lecturers[i].getWage();
        }
        wage = wage / lecturersCount;
        return wage;
    }

    public Lecturer[] getLecturers() {
        return lecturers;
    }

    public int getLecturersCount() {
        return lecturersCount;
    }



    public boolean addCommittee(String committeeName, String headLecturer) {
        boolean isValid = true;
        Lecturer headOfCommittee = getLecturerByName(headLecturer);

        if (getCommitteeByName(committeeName) != null) {
            System.out.println("Error: Committee already exists.");
            isValid = false;
        }
        if (headOfCommittee == null) {
            System.out.println("Error: Lecturer named " + headLecturer + " was not found.");
            isValid = false;
        } else if (headOfCommittee.getTitle() != Title.DR && headOfCommittee.getTitle() != Title.PROFESSOR) {
            System.out.println("Error: Lecturer " + headLecturer + " is not qualified to be " +
                    "head of committee.");
            System.out.println("    Requirements: " + Title.DR + " , " + Title.PROFESSOR);
            isValid = false;
        }

        if (!isValid) {
            return false;
        }

        if (committees.length == committeesCount) {
            Committee[] newCommittees = new Committee[committeesCount * 2];
            for (int i = 0; i < committeesCount; i++) {
                newCommittees[i] = committees[i];
            }
            committees = newCommittees;
        }

        Committee committee = new Committee(committeeName, headOfCommittee);
        committees[committeesCount] = committee;
        committeesCount++;
        return true;
    }

    private Committee getCommitteeByName(String name){
        for (int i = 0; i < committeesCount; i++) {
            if (committees[i].getName().equals(name)) {
                return committees[i];
            }
        }
        return null;
    }

    public Committee[] getCommittees() {
        return committees;
    }

    public int getCommitteesCount() {
        return committeesCount;
    }



    public boolean addDepartment(String departmentName, int studentCount){
        boolean isValid = true;

        if (getDepartmentByName(departmentName) != null) {
            System.out.println("Error: Department already exists.");
            isValid = false;
        }

        if (studentCount < 0) {
            System.out.println("Error: student count is out of range.");
            System.out.println("    must be positive.");
            isValid = false;
        }

        if (!isValid) {
            return false;
        }

        if (departments.length == departmentsCount) {
            Department[] newDepartments = new Department[departmentsCount * 2];
            for (int i = 0; i < departmentsCount; i++) {
                newDepartments[i] = departments[i];
            }
            departments = newDepartments;
        }

        Department department = new Department(departmentName, studentCount);
        departments[departmentsCount] = department;
        departmentsCount++;
        return true;
    }

    private Department getDepartmentByName(String name){
        for (int i = 0; i < departmentsCount; i++) {
            if (departments[i].getName().equals(name)) {
                return departments[i];
            }
        }
        return null;
    }



    public boolean assignLecturerToDepartment(String lecturerName, String departmentName) {
        boolean isValid = true;
        Lecturer lecturer = getLecturerByName(lecturerName);
        Department department = getDepartmentByName(departmentName);

        if (lecturer == null) {
            System.out.println("Error: Lecturer doesn't exists.");
            isValid = false;
        }

        if (department == null) {
            System.out.println("Error: department doesn't exists.");
            isValid = false;
        }

        if (!isValid) {
            return false;
        }
        return department.addLecturer(lecturer);

    }

    public boolean assignLecturerToCommittee(String lecturerName, String committeeName){
        boolean isValid = true;
        Lecturer lecturer = getLecturerByName(lecturerName);
        Committee committee = getCommitteeByName(committeeName);

        if (lecturer == null) {
            System.out.println("Error: Lecturer doesn't exists.");
            isValid = false;
        }

        if (committee == null) {
            System.out.println("Error: department doesn't exists.");
            isValid = false;
        }

        if (!isValid) {
            return false;
        }

        return committee.addLecturer(lecturer);

    }

    private boolean setName(String name) {
        this.name = name;
        return true;
    }
    public String getName() {
        return name;
    }
}