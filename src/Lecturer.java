public class Lecturer {
    private String name;
    private int id;
    private int wage;
    private Title level;
    private String degreeName;
    private Department department;
    private Committee[] committees = new Committee[2];
    private int committeeCount = 0;

    public Lecturer(String name, int id, Title level, String degreeName, int wage) {
        setName(name);
        setId(id);
        setTitle(level);
        setDegreeName(degreeName);
        setWage(wage);
    }

    private boolean setName(String name) {
        this.name = name;
        return true;
    }
    public String getName() {
        return name;
    }

    private boolean setId(int id) {
        if (id > 99999999 && id < 1000000000) {
            this.id = id;
            return true;
        }
        return false;
    }
    public int getId(){
        return id;
    }

    public boolean setWage(int wage) {
        if (wage > 0) {
            this.wage = wage;
            return true;
        }
        return false;
    }
    public int getWage(){
        return wage;
    }

    public boolean setTitle(Title level) {
        this.level = level;
        return true;
    }
    public Title getTitle() {
        return level;
    }

    public boolean setDegreeName(String degreeName) {
        this.degreeName = degreeName;
        return true;
    }
    public String getDegreeName() {
        return degreeName;
    }

    public boolean setDepartment(Department department) {
        this.department = department;
        return true;
    }
    public Department getDepartment() {
        return department;
    }

    public void addCommittee(Committee committee) {
        if (committeeCount == committees.length) {
            Committee[] newCommittees = new Committee[committeeCount * 2];
            for (int i = 0; i < committeeCount; i++) {
                newCommittees[i] = committees[i];
            }
            committees = newCommittees;
        }
        committees[committeeCount] = committee;
        committeeCount++;
    }
    public Committee[] getCommittees() {
        return committees;
    }
    public int getCommitteeCount() {
        return committeeCount;
    }

    // need to add option to be removed from committe here and on Committee.java
}
