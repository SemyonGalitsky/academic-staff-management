public class Lecturer {
    private String name;
    private int id;
    private int wage;
    private Title degree;
    private String degreeName;
    private Department department;
    private Committee[] committees = new Committee[2];
    private int committeeCount = 0;

    public Lecturer(String name, int id, int level, String degreeName, int wage) {
        setName(name);
        setId(id);
        setTitle(level);
        setDegreeName(degreeName);
        setWage(wage);
    }

    public boolean setName(String name) {
        this.name = name;
        return true;
    }
    public String getName() { return name; }

    public boolean setId(int id) {
        if (id > 99999999 && id < 1000000000) {
            this.id = id;
            return true;
        }
        return false;
    }
    public int getId() { return id; }

    public boolean setWage(int wage) {
        if (wage >= 0) {
            this.wage = wage;
            return true;
        }
        return false;
    }
    public int getWage() { return wage; }

    public boolean setTitle(int level) {
        switch (level) {
            case 1: this.degree = Title.BACHELORS; break;
            case 2: this.degree = Title.MASTERS; break;
            case 3: this.degree = Title.DR; break;
            case 4: this.degree = Title.PROFESSOR; break;
            default: return false;
        }
        return true;
    }
    public Title getTitle() { return degree; }

    public boolean setDegreeName(String degreeName) {
        this.degreeName = degreeName;
        return true;
    }
    public String getDegreeName() { return degreeName; }

    public boolean setDepartment(Department department) {
        if (this.department == department) return false;
        this.department = department;
        return true;
    }
    public Department getDepartment() { return department; }

    public String toString() {
        String str = "Name: " + name + "\n" +
                "   Id: " + id + "\n" +
                "   Wage: " + wage + "\n" +
                "   Title: " + degree + "\n" +
                "   Degree: " + degreeName + "\n";

        if (department != null) {
            str += "    Department: " + department.getName() + "\n";
        }

        if (committeeCount > 0) {
            str += "    Committees: \n";
            for (int i = 0; i < committeeCount; i++) {
                str += "        " + committees[i].getName() + "\n";
            }
        }
        return str;
    }

    private boolean hasCommittee(Committee committee) {
        for (int i = 0; i < committeeCount; i++) {
            if (committees[i] == committee) return true;
        }
        return false;
    }

    public String addCommittee(Committee committee) {
        if (hasCommittee(committee)) return "- Lecturer already part of committee.";

        if (committeeCount == committees.length) {
            Committee[] newCommittees = new Committee[committeeCount * 2];
            for (int i = 0; i < committeeCount; i++) {
                newCommittees[i] = committees[i];
            }
            committees = newCommittees;
        }
        committees[committeeCount] = committee;
        committeeCount++;
        return "SUCCESS";
    }

    public String removeCommittee(Committee committee) {
        if (!hasCommittee(committee)) return "- Lecturer not in committee.";

        int indexToRemove = -1;
        for (int i = 0; i < committeeCount; i++) {
            if (committees[i] == committee) {
                indexToRemove = i;
                break;
            }
        }

        for (int i = indexToRemove; i < committeeCount - 1; i++) {
            committees[i] = committees[i + 1];
        }
        committees[committeeCount - 1] = null;
        committeeCount--;
        return "SUCCESS";
    }
}