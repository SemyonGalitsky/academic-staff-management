public class College {
    private String name;
    private Lecturer[] lecturers = new Lecturer[2];
    private Committee[] committees = new Committee[2];
    private Department[] departments = new Department[2];
    private int lecturersCount = 0;
    private int committeesCount = 0;
    private int departmentsCount = 0;

    public College(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String addLecturer(String name, int id, int level, String degreeName, int wage) {
        String errors = "";

        if (getLecturerByName(name) != null) {
            errors += "- Lecturer already exists.\n";
        }

        if (id <= 99999999 || id >= 1000000000) {
            errors += "- ID must contain exactly 9 digits.\n";
        } else {
            for (int i = 0; i < lecturersCount; i++) {
                if (id == lecturers[i].getId()) {
                    errors += "- ID already taken, must be unique.\n";
                    break;
                }
            }
        }

        if (level < 1 || level > 4) {
            errors += "- Degree level must be between 1 to 4.\n";
        }
        if (wage < 0) {
            errors += "- Wage must be positive.\n";
        }

        if (!errors.isEmpty()) {
            return errors;
        }

        if (lecturers.length == lecturersCount) {
            Lecturer[] newLecturers = new Lecturer[lecturersCount * 2];
            for (int i = 0; i < lecturersCount; i++) {
                newLecturers[i] = lecturers[i];
            }
            lecturers = newLecturers;
        }

        Lecturer lecturer = new Lecturer(name, id, level, degreeName, wage);
        lecturers[lecturersCount] = lecturer;
        lecturersCount++;
        return "SUCCESS";
    }

    private Lecturer getLecturerByName(String name) {
        for (int i = 0; i < lecturersCount; i++) {
            if (lecturers[i].getName().equals(name)) {
                return lecturers[i];
            }
        }
        return null;
    }

    public double getAverageWage() {
        if (lecturersCount == 0) return 0;
        double wage = 0;
        for (int i = 0; i < lecturersCount; i++) {
            wage += lecturers[i].getWage();
        }
        return wage / lecturersCount;
    }

    public String getAllLecturersDetails() {
        if (lecturersCount == 0) return "";
        String details = "";
        for (int i = 0; i < lecturersCount; i++) {
            details += lecturers[i].toString() + "\n";
        }
        return details;
    }

    public String addCommittee(String committeeName, String headLecturerName) {
        String errors = "";

        if (getCommitteeByName(committeeName) != null) {
            errors += "- Committee already exists.\n";
        }

        Lecturer headOfCommittee = getLecturerByName(headLecturerName);
        if (headOfCommittee == null) {
            errors += "- Lecturer named " + headLecturerName + " was not found.\n";
        } else if (headOfCommittee.getTitle() != Title.DR && headOfCommittee.getTitle() != Title.PROFESSOR) {
            errors += "- Lecturer " + headLecturerName + " is not qualified. Requirements: DR or PROFESSOR.\n";
        }

        if (!errors.isEmpty()) {
            return errors;
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
        return "SUCCESS";
    }

    private Committee getCommitteeByName(String name) {
        for (int i = 0; i < committeesCount; i++) {
            if (committees[i].getName().equals(name)) {
                return committees[i];
            }
        }
        return null;
    }

    public String getAllCommitteesDetails() {
        if (committeesCount == 0) return "";
        String details = "";
        for (int i = 0; i < committeesCount; i++) {
            details += committees[i].toString() + "\n";
        }
        return details;
    }

    public String addDepartment(String departmentName, int studentCount) {
        String errors = "";

        if (getDepartmentByName(departmentName) != null) {
            errors += "- Department already exists.\n";
        }
        if (studentCount < 0) {
            errors += "- Student count cannot be negative.\n";
        }

        if (!errors.isEmpty()) {
            return errors;
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
        return "SUCCESS";
    }

    private Department getDepartmentByName(String name) {
        for (int i = 0; i < departmentsCount; i++) {
            if (departments[i].getName().equals(name)) {
                return departments[i];
            }
        }
        return null;
    }

    public double getDepartmentAverageWage(String departmentName) {
        Department department = getDepartmentByName(departmentName);
        if (department == null) return -1;
        return department.getAverageWage();
    }

    public String assignLecturerToDepartment(String lecturerName, String departmentName) {
        String errors = "";
        Lecturer lecturer = getLecturerByName(lecturerName);
        Department department = getDepartmentByName(departmentName);

        if (lecturer == null) errors += "- Lecturer not found.\n";
        if (department == null) errors += "- Department not found.\n";

        if (!errors.isEmpty()) return errors.trim();
        return department.addLecturer(lecturer);
    }

    public String assignLecturerToCommittee(String lecturerName, String committeeName) {
        String errors = "";
        Lecturer lecturer = getLecturerByName(lecturerName);
        Committee committee = getCommitteeByName(committeeName);

        if (lecturer == null) errors += "- Lecturer not found.\n";
        if (committee == null) errors += "- Committee not found.\n";

        if (!errors.isEmpty()) return errors.trim();
        return committee.addLecturer(lecturer);
    }

    public String removeLecturerFromCommittee(String lecturerName, String committeeName) {
        String errors = "";
        Lecturer lecturer = getLecturerByName(lecturerName);
        Committee committee = getCommitteeByName(committeeName);

        if (lecturer == null) errors += "- Lecturer not found.\n";
        if (committee == null) errors += "- Committee not found.\n";

        if (!errors.isEmpty()) return errors.trim();
        return committee.removeLecturer(lecturer);
    }

    public String updateHeadOfCommittee(String lecturerName, String committeeName) {
        String errors = "";
        Committee committee = getCommitteeByName(committeeName);
        Lecturer lecturer = getLecturerByName(lecturerName);

        if (committee == null) errors += "- Committee not found.\n";
        if (lecturer == null) errors += "- Lecturer not found.\n";

        if (!errors.isEmpty()) return errors.trim();
        return committee.updateHeadOfCommittee(lecturer);
    }
}