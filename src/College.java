public class College {
    private String name;
    private Lecturer[] lecturers;
    private Committee[] committees;
    private Department[] departments;
    private int lecturersCount;
    private int committeesCount;
    private int departmentsCount;

    public College(String name){
        this.name = name;
        this.lecturers = new Lecturer[2];
        this.committees = new Committee[2];
        this.departments = new Department[2];
        lecturersCount = 0;
        committeesCount = 0;
        departmentsCount = 0;
    }

    public boolean addLecturer(Lecturer lecturer){
        if (this.lecturersCount == this.lecturers.length){
            Lecturer[] newLecturers = new Lecturer[lecturersCount * 2];
            for (int i = 0; i < lecturersCount; i++) {
                newLecturers[i] = lecturers[i];
            }
            lecturers = newLecturers;
        }
        lecturers[lecturersCount] = lecturer;
        lecturersCount++;
        return true;
    }

    public boolean isLecturerExists (String name){
        for (int i = 0; i < lecturersCount; i++) {
            if (lecturers[i].getName().equals(name)){
                return true;
            }
        }
        return false;
    }
    private Lecturer getLecturerByName(String name) {
        for (int i = 0; i < lecturersCount; i++) {
            if (lecturers[i].getName().equals(name)) {
                return lecturers[i];
            }
        }
        return null;
    }

    public boolean addCommittee(String committeeName, String chairName) {
        Lecturer chair = getLecturerByName(chairName);

        // check is committee exists and head lecturer is in college
        if (!isCommitteeExists(committeeName) && chair != null) {

            // check if not already in another committee as head
            for (int i = 0; i < committeesCount; i++) {
                if (committees[i].getHeadOfCommittee() == chair) {
                    return false;
                }
            }

            // title check for being the head
            if (chair.getTitle() != Title.DR && chair.getTitle() != Title.PROF) {
                return false;
            }

            // in case its full
            if (this.committeesCount == this.committees.length) {
                Committee[] newCommittees = new Committee[committeesCount * 2];
                for (int i = 0; i < committeesCount; i++) {
                    newCommittees[i] = committees[i];
                }
                committees = newCommittees;
            }


            Committee committee = new Committee(committeeName, chair);
            committees[committeesCount] = committee;
            committeesCount++;

            return true;
        }

        return false;
    }

    public boolean isCommitteeExists (String name){
        for (int i = 0; i < committeesCount; i++) {
            if (committees[i].getName().equals(name)){
                return true;
            }
        }
        return false;
    }










    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Lecturer[] getLecturers() {
        return lecturers;
    }

    public void setLecturers(Lecturer[] lecturers) {
        this.lecturers = lecturers;
    }


    public int getCommitteesCount() {
        return committeesCount;
    }

    public void setCommittees(Committee[] committees) {
        this.committees = committees;
    }
}

